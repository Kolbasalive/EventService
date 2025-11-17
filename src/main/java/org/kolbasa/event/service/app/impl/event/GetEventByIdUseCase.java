package org.kolbasa.event.service.app.impl.event;

import lombok.RequiredArgsConstructor;
import org.kolbasa.event.service.app.api.event.GetEventByIdInbound;
import org.kolbasa.event.service.app.api.event.MinioService;
import org.kolbasa.event.service.app.api.event.dto.ResponseEventDto;
import org.kolbasa.event.service.app.api.event.mapper.EventMapper;
import org.kolbasa.event.service.app.api.repository.EventRepository;
import org.kolbasa.event.service.app.impl.exception.EventNotFoundException;
import org.kolbasa.event.service.app.impl.exception.FileUrlGenerationException;
import org.kolbasa.event.service.domain.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEventByIdUseCase implements GetEventByIdInbound {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final MinioService minioService;

    @Override
    public ResponseEventDto execute(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        ResponseEventDto eventDto = eventMapper.eventToResponseEventDto(event);
        String mainPhotoUrl = safeGetUrl(event.getMainPhotoUrl());
        eventDto.setMainPhotoUrl(mainPhotoUrl);
        List<String> photoUrls = event.getPhotoUrls()
                .stream()
                .map(url -> safeGetUrl(url.getUrl()))
                .toList();
        eventDto.setPhotoUrls(photoUrls);

        return eventDto;
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String safeGetUrl(String objectName) {
        try {
            return minioService.getFileUrl(objectName);
        } catch (Exception e) {
            throw new FileUrlGenerationException(e);
        }
    }
}