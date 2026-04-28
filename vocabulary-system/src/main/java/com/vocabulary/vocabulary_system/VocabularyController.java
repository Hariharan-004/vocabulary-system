package com.vocabulary.vocabulary_system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vocabulary")
public class VocabularyController {

    @GetMapping("/hello")
    public String hello()
    {
        return "Vocabulary System is running!";
    }

    @GetMapping("/search/{word}")
    public VocabularyResponse searchWord(@PathVariable String word)
    {
        return new VocabularyResponse(word,"Searching for:"+word);
    }
}
