package com.ideas2it.hirezy.dto;

import com.ideas2it.hirezy.model.enums.FeedbackType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackDto {
    private long id;

    private FeedbackType feedBackType;

    @NotNull
    private long userId;

    @NotBlank
    private String content;

    private String adminReply;

    @NotNull
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


}
