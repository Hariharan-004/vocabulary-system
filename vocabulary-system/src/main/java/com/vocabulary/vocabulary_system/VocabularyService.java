package com.vocabulary.vocabulary_system;

import org.springframework.stereotype.Service;

@Service
public class VocabularyService {

    public VocabularyResponse search(String word,
                                     String field,
                                     String level)
    {
        String message="Searching for "+word+
                "in field: "+field+
                "at level: "+level;
        return new VocabularyResponse(word,message);
    }
}
