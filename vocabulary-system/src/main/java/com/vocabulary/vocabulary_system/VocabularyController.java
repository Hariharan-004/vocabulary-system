package com.vocabulary.vocabulary_system;

import org.springframework.web.bind.annotation.GetMapping;
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
}
