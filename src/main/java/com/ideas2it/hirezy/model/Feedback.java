package com.ideas2it.hirezy.model;

import com.ideas2it.hirezy.model.enums.FeedbackType;
import com.ideas2it.hirezy.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@Table(name = "feedback")
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FeedbackType feedBackType;

    private Long userId;

    private String content;

    private String adminReply;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
