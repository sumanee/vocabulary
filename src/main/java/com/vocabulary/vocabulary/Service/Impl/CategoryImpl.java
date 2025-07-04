package com.vocabulary.vocabulary.Service.Impl;

import com.vocabulary.vocabulary.Entity.CategoryEntry;
import com.vocabulary.vocabulary.Repository.CategoryRepository;
import com.vocabulary.vocabulary.Service.CategoryService;
import com.vocabulary.vocabulary.Util.DefaultValidatorUtil;
import com.vocabulary.vocabulary.exception.ResourceErrorException;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CategoryImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // ใช้ในหลายเมทอด เช่น update, delete, getById
    private CategoryEntry getCategoryByIdOrThrow(String id, String status, boolean checkDefault) {
        CategoryEntry categoryEntry = categoryRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new ResourceErrorException("Data Not Found"));
        if (checkDefault) {
            DefaultValidatorUtil.checkNotDefault(categoryEntry, status);
        }

        return categoryEntry;
    }

    @Override
    public Page<CategoryEntry> searchCategory(String name, boolean status, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            Page<CategoryEntry> pageCategoryEntry = categoryRepository.findByEnabled(status, pageable);
            return pageCategoryEntry;

        }
        return categoryRepository.findByNameContainingIgnoreCaseAndEnabled(name, status, pageable);
    }

    @Override
    public void deleteCategoryByIds(List<String> ids) {
        List<ObjectId> objectIds = ids.stream().map(ObjectId::new).toList();
        List<CategoryEntry> listCategoryEntry = categoryRepository.findAllById(objectIds);
        DefaultValidatorUtil.validateNotDefault(listCategoryEntry);
        categoryRepository.deleteAllById(objectIds);
    }

    @Override
    public CategoryEntry duplicateCategory(String id) {
        CategoryEntry categoryEntry = getCategoryByIdOrThrow(id, "edit", false);
        long countName = categoryRepository.countByName(categoryEntry.getName());
        countName = countName + 1;
        CategoryEntry newCategoryEntry = new CategoryEntry();
        newCategoryEntry.setName(categoryEntry.getName() + "(" + countName + ")");
        newCategoryEntry.setDescription(categoryEntry.getDescription());
        newCategoryEntry.setDefaultValue(false);
        CategoryEntry returnCategoryEntry = categoryRepository.save(newCategoryEntry);
        return returnCategoryEntry;
    }

    @Override
    public void deleteCategory(String id) {
        CategoryEntry categoryEntry = getCategoryByIdOrThrow(id, "delete", true);
        categoryRepository.deleteById(new ObjectId(categoryEntry.getId()));

    }

    @Override
    public CategoryEntry disabledCategory(String id) {
        CategoryEntry categoryEntry = getCategoryByIdOrThrow(id, "edit", true);
        categoryEntry.setEnabled(false);
        CategoryEntry returnCategoryEntry = categoryRepository.save(categoryEntry);
        return returnCategoryEntry;
    }

    @Override
    public CategoryEntry enabledCategory(String id) {
        CategoryEntry categoryEntry = getCategoryByIdOrThrow(id, "edit", true);
        categoryEntry.setEnabled(true);
        CategoryEntry returnCategoryEntry = categoryRepository.save(categoryEntry);
        return returnCategoryEntry;
    }

    @Override
    public CategoryEntry getByIdCategory(String id) {
        CategoryEntry categoryEntry = getCategoryByIdOrThrow(id, "edit", false);
        return categoryEntry;
    }

    @Override
    public List<CategoryEntry> getListActiveCategory() {
        List<CategoryEntry> listCategory = categoryRepository.findByEnabledTrue();
        return listCategory;
    }

    @Override
    public List<CategoryEntry> getListAllCategory() {
        List<CategoryEntry> listCategory = categoryRepository.findAll();
        return listCategory;
    }

    @Override
    public CategoryEntry updateCategory(String id, CategoryEntry dataCategoryEntry) {
        long countName = categoryRepository.countByName(dataCategoryEntry.getName());
        DefaultValidatorUtil.checkNameDuplicate(countName);

        CategoryEntry categoryEntry = getCategoryByIdOrThrow(id, "edit", true);

        categoryEntry.setName(dataCategoryEntry.getName());
        categoryEntry.setDescription(dataCategoryEntry.getDescription());
        CategoryEntry returnCategoryEntry = categoryRepository.save(categoryEntry);
        return returnCategoryEntry;
    }

    @Override
    public CategoryEntry createCategory(CategoryEntry dataCategoryEntry) {
        long countName = categoryRepository.countByName(dataCategoryEntry.getName());
        DefaultValidatorUtil.checkNameDuplicate(countName);

        CategoryEntry categoryEntry = categoryRepository.save(dataCategoryEntry);
        return categoryEntry;
    }
}
