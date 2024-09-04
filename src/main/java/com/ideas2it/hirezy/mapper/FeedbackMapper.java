package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.dto.JobCategoryDto;
import com.ideas2it.hirezy.model.Feedback;

/**
 * Mapper for converting between feedback entity and feedbackDTO.
 * @author kishore
 */
public class FeedbackMapper {

    /**
     * <p>
     *     This method used for conversion of feedback to feedbackDto
     * </p>
     * @param feedback - feedback details from the user
     * @return FeedbackDto -{@link FeedbackDto} given to the user
     */
    public static FeedbackDto mapToFeedbackDto(Feedback feedback) {
        return FeedbackDto.builder()
                .id(feedback.getId())
                .userId(feedback.getUserId())
                .feedBackType(feedback.getFeedBackType())
                .content(feedback.getContent())
                .createdDate(feedback.getCreatedDate())
                .updatedDate(feedback.getUpdatedDate())
                .adminReply(feedback.getAdminReply())
                .build();
    }

    /**
     * <p>
     *     Conversion of FeedbackDto to Feedback
     * </p>
     * @param feedbackDto - {@link FeedbackDto}from user
     * @return feedback - Feedback details to the user
     */
    public static Feedback mapToFeedback(FeedbackDto feedbackDto) {
        return Feedback.builder()
                .id((feedbackDto.getId()))
                .userId(feedbackDto.getUserId())
                .feedBackType(feedbackDto.getFeedBackType())
                .content(feedbackDto.getContent())
                .createdDate(feedbackDto.getCreatedDate())
                .updatedDate(feedbackDto.getUpdatedDate())
                .adminReply(feedbackDto.getAdminReply())
                .build();
    }
}
