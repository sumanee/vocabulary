package com.vocabulary.vocabulary.Service;

import com.vocabulary.vocabulary.Entry.MasterPartOfSpeechEntry;

import java.util.List;

public interface MasterPartOfSpeechService {

    MasterPartOfSpeechEntry createPartOfSpeech(MasterPartOfSpeechEntry dataMasterPartOfSpeechEntry);

    MasterPartOfSpeechEntry updatePartOfSpeech(String id, MasterPartOfSpeechEntry dataMasterPartOfSpeechEntry);

    List<MasterPartOfSpeechEntry> getListAllPartOfSpeech();

    List<MasterPartOfSpeechEntry> getListActivePartOfSpeech();

    MasterPartOfSpeechEntry getByIdPartOfSpeech(String id);

    MasterPartOfSpeechEntry enabledPartOfSpeech(String id);

    MasterPartOfSpeechEntry disabledPartOfSpeech(String id);

    void deletePartOfSpeech(String id);

    void deletePartOfSpeechByIds(List<String> ids);


}
