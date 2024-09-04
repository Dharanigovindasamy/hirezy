package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.model.Feedback;

public class FeedbackMapper {
    public static FeedbackDto mapToFeedbackDto(Feedback feedback) {
        return FeedbackDto.builder()
                .userId(feedback.getUserId())
                .feedBackType(feedback.getFeedBackType())
                .content(feedback.getContent())
                .createdDate(feedback.getCreatedDate())
                .updatedDate(feedback.getUpdatedDate())
                .adminReply(feedback.getAdminReply())
                .build();
    }

    public static Feedback mapToFeedback(FeedbackDto feedbackDto) {
        return Feedback.builder()
                .userId(feedbackDto.getUserId())
                .feedBackType(feedbackDto.getFeedBackType())
                .content(feedbackDto.getContent())
                .createdDate(feedbackDto.getCreatedDate())
                .updatedDate(feedbackDto.getUpdatedDate())
                .adminReply(feedbackDto.getAdminReply())
                .build();
    }
}
