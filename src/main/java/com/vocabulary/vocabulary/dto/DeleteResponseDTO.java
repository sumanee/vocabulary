package com.vocabulary.vocabulary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeleteResponseDTO {
    private boolean isDeleted;
    private String message;


    public static DeleteResponseDTO fromMap(Map<String, Object> map) {
        DeleteResponseDTO dto = new DeleteResponseDTO();
        dto.setDeleted((Boolean) map.get("isDeleted"));
        dto.setMessage((String) map.get("message"));
        return dto;
    }
}

