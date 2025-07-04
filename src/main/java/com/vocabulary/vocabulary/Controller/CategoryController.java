package com.vocabulary.vocabulary.Controller;


import com.vocabulary.vocabulary.Entity.CategoryEntry;
import com.vocabulary.vocabulary.Service.CategoryService;
import com.vocabulary.vocabulary.dto.DeleteArrayRequestDTO;
import com.vocabulary.vocabulary.dto.DeleteResponseDTO;
import com.vocabulary.vocabulary.exception.ResourceErrorException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/category")
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/test")
    public void testException() {
        throw new ResourceErrorException("Test error message");
    }

    @PostMapping
    public ResponseEntity<?> createCategoryEntry(@RequestBody CategoryEntry dataCategory) {
        CategoryEntry categoryEntry = categoryService.createCategory(dataCategory);
        return new ResponseEntity<>(categoryEntry, HttpStatus.CREATED);
        
    }

    @PostMapping("duplicate/{id}")
    public ResponseEntity<CategoryEntry> duplicateCategoryEntry(@PathVariable String id) {
        CategoryEntry categoryEntry = categoryService.duplicateCategory(id);
        return new ResponseEntity<>(categoryEntry, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCategoryEntry(@PathVariable String id, @RequestBody CategoryEntry dataCategoryEntry) {
        CategoryEntry CategoryEntry = categoryService.updateCategory(id, dataCategoryEntry);
        return new ResponseEntity<>(CategoryEntry, HttpStatus.OK);
    }

    @PutMapping("enabled/{id}")
    public ResponseEntity<?> enabledPartOfSpeech(@PathVariable String id) {
        CategoryEntry partOfSpeech = categoryService.enabledCategory(id);
        return new ResponseEntity<>(partOfSpeech, HttpStatus.OK);
    }

    @PutMapping("disabled/{id}")
    public ResponseEntity<?> disabledPartOfSpeech(@PathVariable String id) {
        CategoryEntry partOfSpeech = categoryService.disabledCategory(id);
        return new ResponseEntity<>(partOfSpeech, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryEntry>> getListAllMasterPartOfSpeech() {
        List<CategoryEntry> listAllPartOfSpeech = categoryService.getListAllCategory();
        return new ResponseEntity<>(listAllPartOfSpeech, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryEntry>> getListActiveMasterPartOfSpeech() {
        List<CategoryEntry> listAllPartOfSpeech = categoryService.getListActiveCategory();
        return new ResponseEntity<>(listAllPartOfSpeech, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryEntry> getListActiveCategory(@PathVariable String id) {
        CategoryEntry partOfSpeech = categoryService.getByIdCategory(id);
        return new ResponseEntity<>(partOfSpeech, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {

        categoryService.deleteCategory(id);

        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("isDeleted", true);
        mapResponse.put("message", "Delete completed");

        DeleteResponseDTO response = new DeleteResponseDTO(true, "Delete completed");
        DeleteResponseDTO responseHelperMethod = DeleteResponseDTO.fromMap(mapResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategoryByIds(@RequestBody DeleteArrayRequestDTO deleteArrayRequestDto) {

        categoryService.deleteCategoryByIds(deleteArrayRequestDto.getArrayId());

        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("isDeleted", true);
        mapResponse.put("message", "Delete completed");

        DeleteResponseDTO response = new DeleteResponseDTO(true, "Delete completed");
        DeleteResponseDTO responseHelperMethod = DeleteResponseDTO.fromMap(mapResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<Page<CategoryEntry>> search(@RequestParam String name,
                                                      @RequestParam String status,
                                                      @RequestParam(defaultValue = "0") int page,         // หน้าเริ่มที่ 0
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "name") String sortBy,
                                                      @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        int zeroBasedPage = (page > 0) ? page - 1 : 0;
        Pageable pageable = PageRequest.of(zeroBasedPage, size, sort);
        Page<CategoryEntry> pageCategoryEntry = categoryService.searchCategory(name, Boolean.parseBoolean(status), pageable);
        System.out.println("pageCategoryEntry" + pageCategoryEntry);
        return new ResponseEntity<>(pageCategoryEntry, HttpStatus.OK);


    }
}
