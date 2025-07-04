package com.vocabulary.vocabulary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private String message;
    private int status;
}
