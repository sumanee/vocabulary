package com.vocabulary.vocabulary.Service.Impl;

import com.vocabulary.vocabulary.Entity.CategoryEntry;
import com.vocabulary.vocabulary.Entity.VocabularyEntry;
import com.vocabulary.vocabulary.Mapper.VocabularyMapper;
import com.vocabulary.vocabulary.Repository.CategoryRepository;
import com.vocabulary.vocabulary.Repository.VocabularyRepository;
import com.vocabulary.vocabulary.Service.VocabularyService;
import com.vocabulary.vocabulary.Util.DefaultValidatorUtil;
import com.vocabulary.vocabulary.dto.ResponseVocabularyDTO;
import com.vocabulary.vocabulary.exception.ResourceErrorException;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VocabularyImpl implements VocabularyService {

    private final VocabularyRepository vocabularyRepository;
    private final CategoryRepository categoryRepository;
    private final VocabularyMapper vocabularyMapper;

    private CategoryEntry getCategoryByIdOrThrow(String id) {
        CategoryEntry categoryEntry = categoryRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new ResourceErrorException("Data Not Found"));

        return categoryEntry;
    }


    private VocabularyEntry getVocabularyByIdOrThrow(String id) {
        VocabularyEntry vocabularyEntry = vocabularyRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new ResourceErrorException("Data Not Found"));

        return vocabularyEntry;
    }

    @Override
    public VocabularyEntry createVocabulary(VocabularyEntry dataVocabulary) {
        CategoryEntry categoryEntry = getCategoryByIdOrThrow(dataVocabulary.getCategoryId().toHexString());
        VocabularyEntry vocabularyEntry = vocabularyRepository.save(dataVocabulary);
        return vocabularyEntry;
    }

    @Override
    public VocabularyEntry getVocabularyById(String id) {
        VocabularyEntry vocabularyEntry = getVocabularyByIdOrThrow(id);


        return vocabularyEntry;
    }

    @Override
    public VocabularyEntry updateVocabulary(String id, VocabularyEntry dataVocabulary) {
        VocabularyEntry saveVocabularyEntry = getVocabularyByIdOrThrow(id);

        saveVocabularyEntry.setName(dataVocabulary.getName());
        saveVocabularyEntry.setMeaning(dataVocabulary.getMeaning());
        saveVocabularyEntry.setCategoryId(dataVocabulary.getCategoryId());
        saveVocabularyEntry.setPronunciation(dataVocabulary.getPronunciation());
        saveVocabularyEntry.setDescription(dataVocabulary.getDescription());

        VocabularyEntry returnVocabularyEntry = vocabularyRepository.save(saveVocabularyEntry);
        return returnVocabularyEntry;
    }

    @Override
    public VocabularyEntry enableVocabulary(String id) {
        VocabularyEntry saveVocabularyEntry = getVocabularyByIdOrThrow(id);
        saveVocabularyEntry.setEnabled(true);
        VocabularyEntry returnVocabularyEntry = vocabularyRepository.save(saveVocabularyEntry);
        return returnVocabularyEntry;
    }

    @Override
    public VocabularyEntry disabledVocabulary(String id) {
        VocabularyEntry saveVocabularyEntry = getVocabularyByIdOrThrow(id);
        saveVocabularyEntry.setEnabled(false);
        VocabularyEntry returnVocabularyEntry = vocabularyRepository.save(saveVocabularyEntry);
        return returnVocabularyEntry;
    }

    @Override
    public void deleteVocabularyById(String id) {
        VocabularyEntry saveVocabularyEntry = getVocabularyByIdOrThrow(id);
        vocabularyRepository.deleteById(new ObjectId(saveVocabularyEntry.getId()));
    }

    @Override
    public void deleteVocabularyByIds(List<String> ids) {
        List<ObjectId> objectIds = ids.stream().map(ObjectId::new).toList();
        vocabularyRepository.deleteAllById(objectIds);
    }

    @Override
    public VocabularyEntry pinVocabulary(String id) {
        VocabularyEntry queryVocabularyEntry = getVocabularyByIdOrThrow(id);
        if (queryVocabularyEntry.isPin()) {
            queryVocabularyEntry.setPin(false);
        } else {
            queryVocabularyEntry.setPin(true);
        }

        VocabularyEntry returnVocabularyEntry = vocabularyRepository.save(queryVocabularyEntry);
        return returnVocabularyEntry;
    }

    @Override
    public List<ResponseVocabularyDTO> getListEnableTrue() {

        List<VocabularyEntry> vocabList = vocabularyRepository.findByEnabledTrue();

        Set<ObjectId> categoryIds = vocabList.stream()
                .map(VocabularyEntry::getCategoryId)
                .collect(Collectors.toSet());

        List<CategoryEntry> categories = categoryRepository.findAllById(categoryIds);

        Map<String, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(
                        CategoryEntry::getId,   // คืน String เช่น "60a1b2c3d4..."
                        CategoryEntry::getName,
                        (a, b) -> a
                ));

        return vocabList.stream()
                .map(vocab -> VocabularyMapper.toDTO(
                        vocab,
                        categoryMap.get(vocab.getCategoryId() != null ? vocab.getCategoryId().toHexString() : null)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseVocabularyDTO> findVocabWithCategory() {

        List<ResponseVocabularyDTO> vocabularyDTOS = vocabularyRepository.findVocabWithCategory();
        return vocabularyDTOS;
    }

    @Override
    public List<ResponseVocabularyDTO> findVocabAllWithCategory() {
        List<ResponseVocabularyDTO> vocabularyDTOS = vocabularyRepository.findVocabAllWithCategory();
        return vocabularyDTOS;

    }
}
