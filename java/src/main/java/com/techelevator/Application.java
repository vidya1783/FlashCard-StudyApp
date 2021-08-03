package com.techelevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

/**
 * TODO
 * Add authentication to all methods
 * Add Retrieve flashcard by flashcardId
 * Retrieve all cards by a given deck Id
 * Add tag to card
 * Retrieve tag list
 * -- later --
 * Search by tag
 * put update for flashcard
 * put update for deck
  */
