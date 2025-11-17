package org.kolbasa.event.service.app.api.event;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    String uploadFile(MultipartFile file, String eventId) throws Exception;
    String getFileUrl(String objectName) throws Exception;
}