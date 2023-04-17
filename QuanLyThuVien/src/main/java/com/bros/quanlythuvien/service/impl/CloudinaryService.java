package com.bros.quanlythuvien.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        // create Cloudinary object with API credentials
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dsxybm23c",
                "api_key", "523944772643779",
                "api_secret", "iTHQSb3BJDBo5rqxQK4FKsxHVa4"));
    }

    public String upload(File selectedFile) {
        try {
            // upload file to Cloudinary
            Map<String, Object> uploadResult = cloudinary.uploader().upload(selectedFile, ObjectUtils.emptyMap());
            String cloudinaryUrl = (String) uploadResult.get("secure_url");
            return cloudinaryUrl;
        } catch (IOException e) {
            System.err.println("Error uploading file to Cloudinary");
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(String imageUrl) {
        try {
            // extract public ID from image URL
            String publicId = extractPublicId(imageUrl);
            // delete image from Cloudinary
            Map<String, Object> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return deleteResult.get("result").equals("ok");
        } catch (Exception e) {
            System.err.println("Error deleting file from Cloudinary");
            e.printStackTrace();
            return false;
        }
    }

    private String extractPublicId(String imageUrl) {
        // extract public ID from image URL
        int startIndex = imageUrl.lastIndexOf("/") + 1;
        int endIndex = imageUrl.lastIndexOf(".");
        return imageUrl.substring(startIndex, endIndex);
    }
}
