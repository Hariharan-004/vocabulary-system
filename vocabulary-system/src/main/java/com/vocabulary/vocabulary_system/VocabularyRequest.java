package com.vocabulary.vocabulary_system;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class VocabularyRequest {


    @NotBlank(message="Word cannot be empty")
    @Pattern(regexp="[a-zA-Z]+",message="Only letters allowed,no numbers or special characters")
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
