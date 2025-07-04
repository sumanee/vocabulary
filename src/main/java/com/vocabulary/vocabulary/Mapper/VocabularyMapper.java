package com.vocabulary.vocabulary.Mapper;

import com.vocabulary.vocabulary.Entity.VocabularyEntry;
import com.vocabulary.vocabulary.dto.ResponseVocabularyDTO;
import org.springframework.stereotype.Component;

@Component
public class VocabularyMapper {

    public static ResponseVocabularyDTO toDTO(VocabularyEntry vocab, String categoryName) {
        return new ResponseVocabularyDTO(
                vocab.getId(),
                vocab.getName(),
                vocab.getMeaning(),
                categoryName,
                vocab.isPin(),
                vocab.isEnabled());
    }
}
