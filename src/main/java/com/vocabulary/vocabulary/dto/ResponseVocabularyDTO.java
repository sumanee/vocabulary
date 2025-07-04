package com.vocabulary.vocabulary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class ResponseVocabularyDTO {
    private String id;
    private String name;
    private String meaning;
    private String categoryName;
    private boolean pin;
    private boolean enabled;


}
