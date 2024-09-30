package com.bicky.demopayment.productservice.product.infrastructure.services;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageProcessingService {

    public byte[] convertToJpeg(BufferedImage image) throws IOException {
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        // Set up ImageOutputStream
        ImageOutputStream ios = ImageIO.createImageOutputStream(jpegOutputStream);

        // Get the ImageWriter for JPEG
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
        writer.setOutput(ios);

        // Write the image
        try {
            // Optionally set compression quality
            javax.imageio.plugins.jpeg.JPEGImageWriteParam jpegParams =
                    (javax.imageio.plugins.jpeg.JPEGImageWriteParam) writer.getDefaultWriteParam();
            jpegParams.setCompressionMode(javax.imageio.ImageWriteParam.MODE_EXPLICIT);
            jpegParams.setCompressionQuality(0.85f); // Adjust quality as needed (0.0f - 1.0f)

            writer.write(null, new javax.imageio.IIOImage(image, null, (IIOMetadata) null), jpegParams);
        } finally {
            writer.dispose();
            ios.close();
        }

        return jpegOutputStream.toByteArray();
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
