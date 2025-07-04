package com.vocabulary.vocabulary.Repository;

import com.vocabulary.vocabulary.Entry.MasterPartOfSpeechEntry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository

public interface MasterPartOfSpeechRepository extends MongoRepository<MasterPartOfSpeechEntry, ObjectId> {
    List<MasterPartOfSpeechEntry> findByEnabledTrue();

    long countByName(String name); // case-sensitive

    List<MasterPartOfSpeechEntry> findAllByOrderByUpdateAtDesc();
}
