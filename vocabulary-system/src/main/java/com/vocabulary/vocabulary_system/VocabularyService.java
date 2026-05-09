package com.vocabulary.vocabulary_system;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VocabularyService {

    private final WordRepository wordRepository;

    public VocabularyService(WordRepository wordRepository)
    {
        this.wordRepository=wordRepository;
    }


    public VocabularyResponse search(String word,
                                     String field,
                                     String level) {

        if (word == null || word.trim().isEmpty()) {
            throw new InvalidWordException("Word cannot be empty");
        }

        if (!word.matches("[a-zA-Z]+")) {
            throw new InvalidWordException("Invalid word: " + word + ". Only letters allowed");

        }
        Optional<Word> existing = wordRepository.findByWordAndField(word, field);

        if (existing.isPresent())
        {
            Word found=existing.get();
            return new VocabularyResponse(
                    found.getWord(),
                    "Found in DB: " + found.getDefinition()
            );
        }
        String definition = "Definition of " + word +
                " in " + field + " context";

        Word newWord = new Word(word, field, level, definition);
        wordRepository.save(newWord);

        return new VocabularyResponse(word, definition);
    }
}
