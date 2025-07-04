package com.vocabulary.vocabulary.Service;

import com.vocabulary.vocabulary.Entity.CategoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryEntry createCategory(CategoryEntry dataCategoryEntry);

    CategoryEntry updateCategory(String id, CategoryEntry dataMasterCategoryEntry);

    List<CategoryEntry> getListAllCategory();

    List<CategoryEntry> getListActiveCategory();

    CategoryEntry getByIdCategory(String id);

    CategoryEntry enabledCategory(String id);

    CategoryEntry disabledCategory(String id);

    void deleteCategory(String id);

    CategoryEntry duplicateCategory(String id);

    void deleteCategoryByIds(List<String> ids);

    Page<CategoryEntry> searchCategory(String nameCate, boolean status, Pageable pageable);
}
