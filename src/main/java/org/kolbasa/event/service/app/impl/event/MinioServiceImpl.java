package org.kolbasa.event.service.app.impl.event;

import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.MinioService;
import org.kolbasa.event.service.app.api.repository.EventPhotoRepository;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.app.impl.exception.EventNotFoundException;
import org.kolbasa.event.service.domain.Event;
import org.kolbasa.event.service.domain.EventPhoto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    private static final String BUCKET_NAME = "event-photos";
    private static final String PATH_TO_PHOTOS = "event/%s/photos/";
    private static final String PATH_TO_MAIN_PHOTO = "event/%s/mainPhoto/";

    private final MinioClient minioClient;
    private final EventPhotoRepository eventPhotoRepository;
    private final EventRepository eventRepository;

    public String uploadFile(MultipartFile file, String eventId) throws Exception {
        String fullPathMainPhoto = getFullPathMainPhoto(eventId, file);
        savePathEventPhotos(fullPathMainPhoto, eventId);

        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(fullPathMainPhoto)
                            .stream(is, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        }

        return fullPathMainPhoto;
    }

    public String getFileUrl(String objectName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(objectName)
                        .method(Method.GET)
                        .expiry(1, TimeUnit.HOURS)
                        .build()
        );
    }

    private void initBucket() {
        try {
            boolean found = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(BUCKET_NAME).build()
            );
            if (!found) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(BUCKET_NAME).build()
                );
                System.out.println("Bucket создан: " + BUCKET_NAME);
            } else {
                System.out.println("Bucket уже существует: " + BUCKET_NAME);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации bucket: " + e.getMessage(), e);
        }
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void savePathEventPhotos(String fullPathMainPhoto, String eventId) {
        Event event = eventRepository.findById(Long.valueOf(eventId))
                .orElseThrow(() -> new EventNotFoundException(Long.valueOf(eventId)));

        EventPhoto eventPhoto = new EventPhoto();
        eventPhoto.setEvent(event)
                .setUrl(fullPathMainPhoto);

        eventPhotoRepository.save(eventPhoto);
    }

    private String getFullPathMainPhoto(String eventId, MultipartFile file) {
        return BUCKET_NAME +
                "/" +
                format(PATH_TO_MAIN_PHOTO, eventId) +
                file.getOriginalFilename();
    }
}