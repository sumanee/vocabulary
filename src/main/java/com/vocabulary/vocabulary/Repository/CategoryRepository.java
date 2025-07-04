package com.vocabulary.vocabulary.Repository;

import com.vocabulary.vocabulary.Entity.CategoryEntry;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<CategoryEntry, ObjectId> {

    List<CategoryEntry> findByEnabledTrue();

    long countByName(String name); // case-sensitive

    //findByNameTypeFoodContainingIgnoreCase
    Page<CategoryEntry> findByEnabled(boolean enabled, Pageable pageable);

    Page<CategoryEntry> findByNameContainingIgnoreCaseAndEnabled(String name, boolean enabled, Pageable pageable);

}
