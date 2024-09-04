package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.Feedback;
import com.ideas2it.hirezy.model.enums.FeedbackType;
import com.ideas2it.hirezy.repository.FeedbackRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    private Feedback feedback;
    private FeedbackDto feedbackDto;

    @BeforeEach
    public void setUp() {
        feedback = new Feedback();
        feedback.setId(1L);
        feedback.setUserId(23L);
        feedback.setFeedBackType(FeedbackType.EMPLOYEE);
        feedback.setContent("feedback");

        feedbackDto = new FeedbackDto();
        feedbackDto.setId(1L);
        feedbackDto.setUserId(23L);
        feedbackDto.setFeedBackType(FeedbackType.EMPLOYEE);
        feedbackDto.setContent("feedback");
    }

    @Test
    public void testGetFeedbackByIdUserIdAndType_Success() {
        when(feedbackRepository.findByIdAndUserIdAndFeedBackType(1L, 23L, FeedbackType.EMPLOYEE))
                .thenReturn(Optional.of(feedback));
        FeedbackDto result = feedbackService.getFeedbackByIdUserIdAndType(1L, 23L, FeedbackType.EMPLOYEE);
        assertEquals(feedbackDto.getId(), result.getId());
    }

    @Test
    public void testGetFeedbackByIdUserIdAndType_NotFound() {
        when(feedbackRepository.findByIdAndUserIdAndFeedBackType(1L, 23L, FeedbackType.EMPLOYEE))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->
                feedbackService.getFeedbackByIdUserIdAndType(1L, 23L, FeedbackType.EMPLOYEE));
    }

    @Test
    public void testCreateFeedback_Success() {
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);
        FeedbackDto result = feedbackService.createFeedback(feedbackDto);
        assertEquals(feedbackDto.getId(), result.getId());
    }

    @Test
    public void testReplyToFeedback_Success() {
        String replyContent = "reply";
        feedback.setAdminReply(replyContent);
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.of(feedback));
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);
        FeedbackDto result = feedbackService.replyToFeedback(anyLong(), replyContent);
        assertEquals(feedbackDto.getId(), result.getId());
    }

    @Test
    public void testReplyToFeedback_NotFound() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->
                feedbackService.replyToFeedback(1L, "This is a reply"));
    }

    @Test
    public void testGetAllFeedbacks_Success() {
        List<Feedback> feedbackList = List.of(feedback);
        List<FeedbackDto> feedbackDtoList = List.of(feedbackDto);
        when(feedbackRepository.findAll()).thenReturn(feedbackList);
        List<FeedbackDto> result = feedbackService.getAllFeedbacks();
        assertEquals(feedbackDtoList.size(), result.size());
    }
}