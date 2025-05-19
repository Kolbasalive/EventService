package org.kolbasa.event.service.app.api.review.mapper;

import org.kolbasa.event.service.app.api.review.dto.ResponseReviewDto;
import org.kolbasa.event.service.app.api.review.dto.ReviewDto;
import org.kolbasa.event.service.domain.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
//    @Mapping(source = "employeeId", target = "employee")
//    @Mapping(source = "eventId", target = "event")
    Review reviewDtoToReview(ReviewDto reviewDto);
    ResponseReviewDto reviewToResponseReviewDto(Review review);
}
