package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.FeedbackDto;
import com.ideas2it.hirezy.model.enums.FeedbackType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 * Interface for FeedbackService  to handle  Feedback-related operation.
 *</p>
 * @author  Kishore
 *
 */
@Service
public interface FeedbackService {

    /**
     * Retrieves a feedback by its ID, user ID, and feedback type.
     *
     * @param feedbackId   The unique identifier of the feedback.
     * @param userId       The unique identifier of the user (either employee or employer).
     * @param feedbackType The type of feedback (e.g., EMPLOYEE_FEEDBACK, EMPLOYER_FEEDBACK).
     * @return A FeedbackDto representing the requested feedback.
     */
    FeedbackDto getFeedbackByIdUserIdAndType(Long feedbackId, Long userId, FeedbackType feedbackType);

    /**
     * Creates a new feedback and stores it in the repository.
     *
     * @param feedbackDto The feedback data transfer object containing the details of the feedback.
     * @return The created FeedbackDto with its generated ID.
     */
    FeedbackDto createFeedback(FeedbackDto feedbackDto);

    /**
     * Adds a reply to an existing feedback and updates the feedback in the repository.
     *
     * @param feedbackId   The unique identifier of the feedback to reply to.
     * @param replyContent The content of the reply.
     * @return The updated FeedbackDto containing the reply.
     */
    FeedbackDto replyToFeedback(Long feedbackId, String replyContent);

    /**
     * Retrieves all feedbacks from the repository.
     *
     * @return A list of FeedbackDto representing all feedbacks.
     */
    List<FeedbackDto> getAllFeedbacks();
}
