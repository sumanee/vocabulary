package com.vocabulary.vocabulary.Controller;

import com.vocabulary.vocabulary.Entity.VocabularyEntry;
import com.vocabulary.vocabulary.Service.VocabularyService;
import com.vocabulary.vocabulary.dto.DeleteArrayRequestDTO;
import com.vocabulary.vocabulary.dto.DeleteResponseDTO;
import com.vocabulary.vocabulary.dto.ResponseVocabularyDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/vocabulary")
@CrossOrigin("*")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @PostMapping()
    public ResponseEntity<VocabularyEntry> createVocabulary(@RequestBody VocabularyEntry dataVocabularyEntry) {
        VocabularyEntry vocabularyEntry = vocabularyService.createVocabulary(dataVocabularyEntry);

        return new ResponseEntity<>(vocabularyEntry, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<VocabularyEntry> updateVocabulary(@PathVariable String id, @RequestBody VocabularyEntry dataVocabularyEntry) {
        VocabularyEntry vocabularyEntry = vocabularyService.updateVocabulary(id, dataVocabularyEntry);
        return new ResponseEntity<>(vocabularyEntry, HttpStatus.OK);
    }

    @PutMapping("enabled/{id}")
    public ResponseEntity<VocabularyEntry> enableVocabulary(@PathVariable String id) {
        VocabularyEntry vocabularyEntry = vocabularyService.enableVocabulary(id);
        return new ResponseEntity<>(vocabularyEntry, HttpStatus.OK);
    }

    @PutMapping("disabled/{id}")
    public ResponseEntity<VocabularyEntry> disabledVocabulary(@PathVariable String id) {
        VocabularyEntry vocabularyEntry = vocabularyService.disabledVocabulary(id);
        return new ResponseEntity<>(vocabularyEntry, HttpStatus.OK);
    }

    @PutMapping("pin/{id}")
    public ResponseEntity<VocabularyEntry> pinVocabulary(@PathVariable String id) {
        VocabularyEntry vocabularyEntry = vocabularyService.pinVocabulary(id);
        return new ResponseEntity<>(vocabularyEntry, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<VocabularyEntry> getVocabularyById(@PathVariable String id) {
        VocabularyEntry vocabularyEntry = vocabularyService.getVocabularyById(id);
        return new ResponseEntity<>(vocabularyEntry, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDTO> deleteVocabulary(@PathVariable String id) {
        vocabularyService.deleteVocabularyById(id);
        DeleteResponseDTO response = new DeleteResponseDTO(true, "Delete completed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteResponseDTO> deleteVocabularyByIds(@RequestBody DeleteArrayRequestDTO deleteArrayRequestDTO) {
        vocabularyService.deleteVocabularyByIds(deleteArrayRequestDTO.getArrayId());

        DeleteResponseDTO response = new DeleteResponseDTO(true, "Delete completed");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<ResponseVocabularyDTO>> getList() {
        List<ResponseVocabularyDTO> responseVocabularyDTOS = vocabularyService.getListEnableTrue();
        return new ResponseEntity<>(responseVocabularyDTOS, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<List<ResponseVocabularyDTO>> findVocabWithCategory() {
        List<ResponseVocabularyDTO> responseVocabularyDTOS = vocabularyService.findVocabWithCategory();

        return new ResponseEntity<>(responseVocabularyDTOS, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<ResponseVocabularyDTO>> findVocabAllWithCategory() {
        List<ResponseVocabularyDTO> responseVocabularyDTOS = vocabularyService.findVocabAllWithCategory();

        return new ResponseEntity<>(responseVocabularyDTOS, HttpStatus.OK);
    }

}
