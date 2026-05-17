package com.vocabulary.vocabulary_system;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="words")
public class Word {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String word;
    private String field;
    private String level;
    @Column(columnDefinition = "TEXT")
    private String definition;

    private LocalDateTime createdAt;

    public Word() {}

    public Word(String word, String field, String level, String definition) {
        this.word = word;
        this.field = field;
        this.level = level;
        this.definition = definition;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getWord() { return word; }
    public String getField() { return field; }
    public String getLevel() { return level; }
    public String getDefinition() { return definition; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
