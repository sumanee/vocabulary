package com.vocabulary.vocabulary.Repository;

import com.vocabulary.vocabulary.Entity.VocabularyEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.vocabulary.vocabulary.dto.ResponseVocabularyDTO;

import java.util.List;

public interface VocabularyRepository extends MongoRepository<VocabularyEntry, ObjectId> {
    List<VocabularyEntry> findByEnabledTrue();

    @Aggregation(pipeline = {
            "{ $match: { enabled: true } }",
            "{ $lookup: { from: 'category', localField: 'categoryId', foreignField: '_id', as: 'category' } }",
            "{ $unwind: '$category' }",
            "{ $project: { " +
                    "id: { $toString: '$_id' }, " +       // แปลง ObjectId เป็น String
                    "name: 1, " +
                    "meaning: 1, " +
                    "description: 1, " +
                    "enabled: 1, " +
                    "pin: 1, " +
                    "categoryName: '$category.name' " +
                    "} }"
    })
    List<ResponseVocabularyDTO> findVocabWithCategory();


    @Aggregation(pipeline = {
            "{ $lookup: { from: 'category', localField: 'categoryId', foreignField: '_id', as: 'category' } }",
            "{ $unwind: '$category' }",
            "{ $project: { " +
                    "id: { $toString: '$_id' }, " +       // แปลง ObjectId เป็น String
                    "name: 1, " +
                    "meaning: 1, " +
                    "description: 1, " +
                    "enabled: 1, " +
                    "pin: 1, " +
                    "categoryName: '$category.name' " +
                    "} }"
    })
    List<ResponseVocabularyDTO> findVocabAllWithCategory();


}
