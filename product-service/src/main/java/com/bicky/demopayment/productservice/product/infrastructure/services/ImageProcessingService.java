package com.bicky.demopayment.productservice.product.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class ImageProcessingService {

    public byte[] convertToWebP(BufferedImage image) throws IOException {
        ByteArrayOutputStream webpOutputStream = new ByteArrayOutputStream();
        ImageWriter writer = null;
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("webp");

        if (writers.hasNext()) {
            writer = writers.next();
        } else {
            throw new IOException("No writer found for WebP format.");
        }
        boolean written = ImageIO.write(image, "webp", webpOutputStream);
        if (!written) {
            throw new IOException("Failed to write image in WebP format.");
        }
        return webpOutputStream.toByteArray();
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight);
    }

    public boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    public void validateFileSize(MultipartFile file) throws IOException {
        long maxSize = 5 * 1024 * 1024; // 5 MB
        if (file.getSize() > maxSize) {
            throw new IOException("File size exceeds the maximum limit of 5 MB.");
        }
    }
}
