package com.vocabulary.vocabulary.Service.Impl;

import com.vocabulary.vocabulary.Entity.CategoryEntry;
import com.vocabulary.vocabulary.Entry.MasterPartOfSpeechEntry;
import com.vocabulary.vocabulary.Repository.MasterPartOfSpeechRepository;
import com.vocabulary.vocabulary.Service.MasterPartOfSpeechService;
import com.vocabulary.vocabulary.Util.DefaultValidatorUtil;
import com.vocabulary.vocabulary.exception.ResourceErrorException;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MasterPartOfSpeechImpl implements MasterPartOfSpeechService {

    private final MasterPartOfSpeechRepository masterPartOfSpeechRepository;

    // ใช้ในหลายเมทอด เช่น update, delete, getById
    private MasterPartOfSpeechEntry getMasterPartOfSpeechByIdOrThrow(String id) {
        MasterPartOfSpeechEntry queryMasterPartOfSpeechEntry = masterPartOfSpeechRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new ResourceErrorException("Data Not Found"));
        return queryMasterPartOfSpeechEntry;
    }

    @Override
    public void deletePartOfSpeechByIds(List<String> ids) {
        List<ObjectId> objectIds = ids.stream().map(ObjectId::new).toList();
        masterPartOfSpeechRepository.deleteAllById(objectIds);
    }

    @Override
    public void deletePartOfSpeech(String id) {
        MasterPartOfSpeechEntry queryMasterPartOfSpeechEntry = getMasterPartOfSpeechByIdOrThrow(id);
        masterPartOfSpeechRepository.deleteById(new ObjectId(queryMasterPartOfSpeechEntry.getId()));
    }

    @Override
    public MasterPartOfSpeechEntry createPartOfSpeech(MasterPartOfSpeechEntry dataMasterPartOfSpeechEntry) {
        long countName = masterPartOfSpeechRepository.countByName(dataMasterPartOfSpeechEntry.getName());
        DefaultValidatorUtil.checkNameDuplicate(countName);

        MasterPartOfSpeechEntry masterPartOfSpeechEntry = masterPartOfSpeechRepository.save(dataMasterPartOfSpeechEntry);
        return masterPartOfSpeechEntry;
    }

    @Override
    public MasterPartOfSpeechEntry updatePartOfSpeech(String id, MasterPartOfSpeechEntry dataMasterPartOfSpeechEntry) {
        long countName = masterPartOfSpeechRepository.countByName(dataMasterPartOfSpeechEntry.getName());
        DefaultValidatorUtil.checkNameDuplicate(countName);

        MasterPartOfSpeechEntry queryMasterPartOfSpeechEntry = getMasterPartOfSpeechByIdOrThrow(id);

        queryMasterPartOfSpeechEntry.setName(dataMasterPartOfSpeechEntry.getName());
        queryMasterPartOfSpeechEntry.setDescription(dataMasterPartOfSpeechEntry.getDescription());

        MasterPartOfSpeechEntry masterPartOfSpeechEntry = masterPartOfSpeechRepository.save(queryMasterPartOfSpeechEntry);
        return masterPartOfSpeechEntry;
    }

    @Override
    public List<MasterPartOfSpeechEntry> getListAllPartOfSpeech() {
        List<MasterPartOfSpeechEntry> listMasterPartOfSpeechEntry = masterPartOfSpeechRepository.findAll();
        return listMasterPartOfSpeechEntry;
    }

    @Override
    public List<MasterPartOfSpeechEntry> getListActivePartOfSpeech() {
        List<MasterPartOfSpeechEntry> listMasterPartOfSpeechEntry = masterPartOfSpeechRepository.findByEnabledTrue();
        return listMasterPartOfSpeechEntry;
    }

    @Override
    public MasterPartOfSpeechEntry getByIdPartOfSpeech(String id) {
        MasterPartOfSpeechEntry queryMasterPartOfSpeechEntry = getMasterPartOfSpeechByIdOrThrow(id);
        return queryMasterPartOfSpeechEntry;
    }

    @Override
    public MasterPartOfSpeechEntry enabledPartOfSpeech(String id) {
        MasterPartOfSpeechEntry queryMasterPartOfSpeechEntry = getMasterPartOfSpeechByIdOrThrow(id);

        queryMasterPartOfSpeechEntry.setEnabled(true);
        MasterPartOfSpeechEntry saveMasterPartOfSpeechEntry = masterPartOfSpeechRepository.save(queryMasterPartOfSpeechEntry);
        return saveMasterPartOfSpeechEntry;
    }

    @Override
    public MasterPartOfSpeechEntry disabledPartOfSpeech(String id) {
        MasterPartOfSpeechEntry queryMasterPartOfSpeechEntry = getMasterPartOfSpeechByIdOrThrow(id);
        queryMasterPartOfSpeechEntry.setEnabled(false);
        MasterPartOfSpeechEntry saveMasterPartOfSpeechEntry = masterPartOfSpeechRepository.save(queryMasterPartOfSpeechEntry);
        return saveMasterPartOfSpeechEntry;

    }
}
