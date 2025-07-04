package com.vocabulary.vocabulary.Controller;


import com.vocabulary.vocabulary.Entry.MasterPartOfSpeechEntry;
import com.vocabulary.vocabulary.Service.MasterPartOfSpeechService;

import com.vocabulary.vocabulary.dto.DeleteArrayRequestDTO;
import com.vocabulary.vocabulary.dto.DeleteResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/masterPartOfSpeech")
@CrossOrigin("*")
public class MasterPartOfSpeechController {
    private final MasterPartOfSpeechService masterPartOfSpeechService;

    @PostMapping
    public ResponseEntity<MasterPartOfSpeechEntry> createPartOfSpeech(@RequestBody MasterPartOfSpeechEntry dataMasterPartOfSpeechEntry) {
        MasterPartOfSpeechEntry masterPartOfSpeechEntry = masterPartOfSpeechService.createPartOfSpeech(dataMasterPartOfSpeechEntry);
        return new ResponseEntity<>(masterPartOfSpeechEntry, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<MasterPartOfSpeechEntry> updateMasterPartOfSpeech(@PathVariable String id, @RequestBody MasterPartOfSpeechEntry dataMasterPartOfSpeechEntry) {
        MasterPartOfSpeechEntry masterPartOfSpeechEntry = masterPartOfSpeechService.updatePartOfSpeech(id, dataMasterPartOfSpeechEntry);
        return new ResponseEntity<>(masterPartOfSpeechEntry, HttpStatus.OK);
    }

    @PutMapping("enabled/{id}")
    public ResponseEntity<MasterPartOfSpeechEntry> enabledPartOfSpeech(@PathVariable String id) {
        MasterPartOfSpeechEntry partOfSpeech = masterPartOfSpeechService.enabledPartOfSpeech(id);
        return new ResponseEntity<>(partOfSpeech, HttpStatus.OK);
    }

    @PutMapping("disabled/{id}")
    public ResponseEntity<MasterPartOfSpeechEntry> disabledPartOfSpeech(@PathVariable String id) {
        MasterPartOfSpeechEntry partOfSpeech = masterPartOfSpeechService.disabledPartOfSpeech(id);
        return new ResponseEntity<>(partOfSpeech, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MasterPartOfSpeechEntry>> getListAllMasterPartOfSpeech() {
        List<MasterPartOfSpeechEntry> listAllPartOfSpeech = masterPartOfSpeechService.getListAllPartOfSpeech();
        return new ResponseEntity<>(listAllPartOfSpeech, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MasterPartOfSpeechEntry>> getListActiveMasterPartOfSpeech() {
        List<MasterPartOfSpeechEntry> listAllPartOfSpeech = masterPartOfSpeechService.getListActivePartOfSpeech();
        return new ResponseEntity<>(listAllPartOfSpeech, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MasterPartOfSpeechEntry> getListActiveMasterPartOfSpeech(@PathVariable String id) {
        MasterPartOfSpeechEntry partOfSpeech = masterPartOfSpeechService.getByIdPartOfSpeech(id);
        return new ResponseEntity<>(partOfSpeech, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDTO> deletePartOfSpeech(@PathVariable String id) {
        masterPartOfSpeechService.deletePartOfSpeech(id);

        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("isDeleted", true);
        mapResponse.put("message", "Delete completed");

        DeleteResponseDTO response = new DeleteResponseDTO(true, "Delete completed");
        DeleteResponseDTO responseHelperMethod = DeleteResponseDTO.fromMap(mapResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<DeleteResponseDTO> deletePartOfSpeechByIds(@RequestBody DeleteArrayRequestDTO deleteArrayRequestDTO) {
        masterPartOfSpeechService.deletePartOfSpeechByIds(deleteArrayRequestDTO.getArrayId());

        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("isDeleted", true);
        mapResponse.put("message", "Delete completed");

        DeleteResponseDTO response = new DeleteResponseDTO(true, "Delete completed");
        DeleteResponseDTO responseHelperMethod = DeleteResponseDTO.fromMap(mapResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
