package com.vocabulary.vocabulary_system;

public class VocabularyRequest {

    private String word;
    private String field;
    private String level;

    public String getWord() { return word; }
    public String getField() { return field; }
    public String getLevel() { return level; }

    public void setWord(String word) { this.word = word; }
    public void setField(String field) { this.field = field; }
    public void setLevel(String level) { this.level = level; }
}
