package com.vocabulary.vocabulary.Service;

import com.vocabulary.vocabulary.Entity.VocabularyEntry;
import com.vocabulary.vocabulary.dto.ResponseVocabularyDTO;

import java.util.List;

public interface VocabularyService {
    VocabularyEntry createVocabulary(VocabularyEntry dataVocabulary);

    VocabularyEntry getVocabularyById(String id);

    VocabularyEntry updateVocabulary(String id, VocabularyEntry dataVocabulary);

    VocabularyEntry enableVocabulary(String id);

    VocabularyEntry disabledVocabulary(String id);

    void deleteVocabularyById(String id);

    void deleteVocabularyByIds(List<String> ids);

    VocabularyEntry pinVocabulary(String id);


    List<ResponseVocabularyDTO> getListEnableTrue();

    List<ResponseVocabularyDTO> findVocabWithCategory();

    List<ResponseVocabularyDTO> findVocabAllWithCategory();

}
