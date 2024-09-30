package com.bicky.demopayment.productservice.product.infrastructure.services;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.bicky.demopayment.productservice.utils.FileUtils.sanitizeFileName;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final String PRODUCT_BUCKET_IMAGE = "product-images";
    private final ProductRepository productRepository;
    private final MinIOService minIOService;
    private final ImageProcessingService imageProcessingService;

    public boolean saveProduct(String name, double price, String description, MultipartFile imageFile) {
        if (!minIOService.bucketExists(PRODUCT_BUCKET_IMAGE)) {
            minIOService.createBucket(PRODUCT_BUCKET_IMAGE);
        }
        String objectKey = System.currentTimeMillis() + "_" + sanitizeFileName(imageFile.getOriginalFilename());
        try {
            Map<String, String> imageUrls = uploadImage(imageFile, objectKey);
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setOriginalImageUrl(imageUrls.get("original"));
            product.setThumbImageUrl(imageUrls.get("thumbnail"));
            product.setMediumImageUrl(imageUrls.get("medium"));
            product.setLargeImageUrl(imageUrls.get("large"));
            productRepository.save(product);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Map<String, String> uploadImage(MultipartFile imageFile, String objectKey) throws IOException {
        if (!imageProcessingService.isImageFile(imageFile)) {
            throw new IOException("Uploaded file is not a valid image.");
        }

        imageProcessingService.validateFileSize(imageFile);

        // Read the original image
        BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());
        if (originalImage == null) {
            throw new IOException("Invalid image file.");
        }

        // Convert to WebP format
        byte[] jpegData = imageProcessingService.convertToJpeg(originalImage);

        // Generate different sizes
        BufferedImage thumbnail = imageProcessingService.resizeImage(originalImage, 150, 150);
        BufferedImage medium = imageProcessingService.resizeImage(originalImage, 500, 500);
        BufferedImage large = imageProcessingService.resizeImage(originalImage, 1000, 1000);

        // Convert resized images to WebP
        byte[] thumbData = imageProcessingService.convertToJpeg(thumbnail);
        byte[] mediumData = imageProcessingService.convertToJpeg(medium);
        byte[] largeData = imageProcessingService.convertToJpeg(large);

        // Upload to MinIO
        minIOService.uploadFile(PRODUCT_BUCKET_IMAGE, objectKey + "_thumbnail.jpeg", thumbData, "image/jpeg");
        minIOService.uploadFile(PRODUCT_BUCKET_IMAGE, objectKey + "_medium.jpeg", mediumData, "image/jpeg");
        minIOService.uploadFile(PRODUCT_BUCKET_IMAGE, objectKey + "_large.jpeg", largeData, "image/jpeg");
        minIOService.uploadFile(PRODUCT_BUCKET_IMAGE, objectKey + ".jpeg", jpegData, "image/jpeg");

        // Construct image URLs (adjust based on your MinIO access configuration)
        Map<String, String> imageUrls = new HashMap<>();
        String baseUrl = "http://localhost:8080/api/products/images/" + PRODUCT_BUCKET_IMAGE + "/"; // Update host and port as needed
        imageUrls.put("thumbnail", baseUrl + objectKey + "_thumbnail.jpeg");
        imageUrls.put("medium", baseUrl + objectKey + "_medium.jpeg");
        imageUrls.put("large", baseUrl + objectKey + "_large.jpeg");
        imageUrls.put("original", baseUrl + objectKey + ".jpeg");
        return imageUrls;
    }
}
