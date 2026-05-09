package com.vocabulary.vocabulary_system;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vocabulary")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    public VocabularyController(VocabularyService vocabularyService)
    {
        this.vocabularyService=vocabularyService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Vocabulary System is running!";
    }

    @GetMapping("/search/{word}")
    public VocabularyResponse searchWord(@PathVariable String word,
                                         @RequestParam(required = false, defaultValue = "general") String field,
                                         @RequestParam(required = false, defaultValue = "beginner") String level) {

        return vocabularyService.search(word, field, level);
    }

    @PostMapping("/search")
    public VocabularyResponse searchWordPost(@RequestBody VocabularyRequest request)
    {
        return vocabularyService.search(
                request.getWord(),
                request.getField(),
                request.getLevel()
        );
    }


}
