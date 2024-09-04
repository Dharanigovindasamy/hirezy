package com.ideas2it.hirezy.dto;

import com.ideas2it.hirezy.model.enums.FeedbackType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackDto {
    private Long id;

    private FeedbackType feedBackType;

    private Long userId;

    private String content;

    private String adminReply;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


}
