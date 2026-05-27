package com.vocabulary.vocabulary_system;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word,Long> {

    Optional<Word> findByWordAndFieldAndLevel(String word, String field,String level);

}
