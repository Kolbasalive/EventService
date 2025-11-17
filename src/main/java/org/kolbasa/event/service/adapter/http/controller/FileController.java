package org.kolbasa.event.service.adapter.http.controller;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.MinioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final MinioService minioService;

    @PostMapping("/{eventId}/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @PathVariable String eventId) {
        try {
            String objectName = minioService.uploadFile(file, eventId);
            String url = minioService.getFileUrl(objectName);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<String> getFile(@RequestParam String name) {
        try {
            String url = minioService.getFileUrl(name);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка: " + e.getMessage());
        }
    }
}

