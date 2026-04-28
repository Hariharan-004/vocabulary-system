package com.vocabulary.vocabulary_system;

public class VocabularyResponse {

    private String word;
    private String message;

    public VocabularyResponse(String word,String message)
    {
        this.word=word;
        this.message=message;
    }

    public String getWord()
    {
        return word;
    }
    public String getMessage()
    {
        return message;
    }
}
