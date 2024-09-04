package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.mapper.FeedbackMapper;
import com.ideas2it.hirezy.model.Feedback;
import com.ideas2it.hirezy.model.enums.FeedbackType;
import com.ideas2it.hirezy.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.ideas2it.hirezy.mapper.FeedbackMapper.mapToFeedback;
import static com.ideas2it.hirezy.mapper.FeedbackMapper.mapToFeedbackDto;

/**
 *
 * This class used for feedback object details that can add, retrieve, update and delete performs
 *
 * @author kishore
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public FeedbackDto getFeedbackByIdUserIdAndType(Long feedbackId, Long userId, FeedbackType feedbackType) {
        Feedback feedback = feedbackRepository.findByIdAndUserIdAndFeedBackType(feedbackId, userId, feedbackType)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
        return mapToFeedbackDto(feedback);
    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = mapToFeedback(feedbackDto);
        feedback.setCreatedDate(LocalDateTime.now());
        feedback = feedbackRepository.save(feedback);
        return mapToFeedbackDto(feedback);
    }

    @Override
    public FeedbackDto replyToFeedback(Long feedbackId, String replyContent) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
        feedback.setAdminReply(replyContent);
        feedback.setUpdatedDate(LocalDateTime.now());
        feedback = feedbackRepository.save(feedback);
        return mapToFeedbackDto(feedback);
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream()
                .map(FeedbackMapper::mapToFeedbackDto)
                .collect(Collectors.toList());
    }
}
