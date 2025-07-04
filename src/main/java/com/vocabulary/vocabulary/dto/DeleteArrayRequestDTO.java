package com.vocabulary.vocabulary.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteArrayRequestDTO {
    private List<String> arrayId;
}
