package com.bicky.demopayment.productservice.product.infrastructure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MinIOService {
    private final S3Client s3Client;

    public boolean bucketExists(String bucketName) {
        try {
            HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            s3Client.headBucket(headBucketRequest);
            return true;
        } catch (NoSuchBucketException e) {
            return false;
        } catch (S3Exception e) {
            throw e;
        }
    }

    // Create a bucket if it doesn't exist
    public void createBucket(String bucketName) {
        try {
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            s3Client.createBucket(createBucketRequest);
            System.out.println("Bucket created: " + bucketName);
        } catch (BucketAlreadyExistsException | BucketAlreadyOwnedByYouException e) {
            System.out.println("Bucket already exists: " + bucketName);
        } catch (S3Exception e) {
            e.printStackTrace();
        }
    }

    // Upload a file to a bucket
    public void uploadFile(String bucketName, String objectKey, byte[] data, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(data));
        System.out.println("File uploaded to bucket: " + bucketName);
    }

    // List all buckets
    public List<Bucket> listBuckets() {
        return s3Client.listBuckets().buckets();
    }

    // Download a file from a bucket
    public void downloadFile(String bucketName, String objectKey, String downloadPath) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        s3Client.getObject(getObjectRequest, Paths.get(downloadPath));
        System.out.println("File downloaded to: " + downloadPath);
    }

    // Delete a file from a bucket
    public void deleteFile(String bucketName, String objectKey) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
        System.out.println("File deleted from bucket: " + bucketName);
    }

    public InputStream getObject(String bucketName, String objectKey) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        return s3Client.getObject(getObjectRequest);
    }
}
