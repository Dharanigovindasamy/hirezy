package com.ideas2it.hirezy.model;

import com.ideas2it.hirezy.model.enums.FeedBackType;
import com.ideas2it.hirezy.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FeedBackType feedBackType;

    private Long userId;

    private String content;

    private String adminReply;

    private Status status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
