package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.Feedback;
import com.ideas2it.hirezy.model.enums.FeedbackType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByIdAndUserIdAndFeedBackType(Long feedbackId, Long userId, FeedbackType feedbackType);
}
