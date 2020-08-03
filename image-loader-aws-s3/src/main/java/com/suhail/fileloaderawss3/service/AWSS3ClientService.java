package com.suhail.fileloaderawss3.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface AWSS3ClientService {

    void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess);

    void deleteFileFromS3Bucket(String fileName);

    Set<String> listAllFiles();

    Object downloadFile(String fileName);
}
