package com.suhail.fileloaderawss3.controller;

import com.suhail.fileloaderawss3.service.AWSS3ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file/")
public class FileLoaderController {

    @Autowired
    private AWSS3ClientService awss3ClientService;

    @PostMapping
    @ApiOperation(
            value = "Upload file to S3 bucket"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "File uploaded",
                    response = String.class
            ),
            @ApiResponse(
                    code = 500,
                    message = "Unsuccessful Operation"
            )
    })
    public ResponseEntity uploadFile(@RequestPart(value = "file") MultipartFile file) {
        this.awss3ClientService.uploadFileToS3Bucket(file, true);
        return ResponseEntity.ok()
                .body("File \"" + file.getOriginalFilename() + "\"" + "uploading request submitted");
    }

    @DeleteMapping
    @ApiOperation(
            value = "Delete image"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 204,
                    message = "Successful Operation"
            )
    })
    public ResponseEntity deleteFile(@RequestParam("file_name") String fileName) {
        this.awss3ClientService.deleteFileFromS3Bucket(fileName);
        return ResponseEntity.ok().body("file \" + fileName + \" removing request submitted successfully.");
    }

    @GetMapping("list")
    @ApiOperation(
            value = "List files in the folder"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Successful Operation"
            )
    })
    public ResponseEntity ListFiles() {
        return ResponseEntity.ok().body(this.awss3ClientService.listAllFiles());
    }

    @GetMapping
    @ApiOperation(
            value = "Download File"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Successful Operation"
            )
    })
    public ResponseEntity downloadFile(@RequestParam("file_name") String fileName) {
        return ResponseEntity.ok().body(this.awss3ClientService.downloadFile(fileName));
    }

}
