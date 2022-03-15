package com.example.demo2.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChessConfiguration {

    @Bean
    ChessFacade chessFacade() {
        MoveValidator moveValidator = new MoveValidator();
        MoveConverter moveConverter = new MoveConverter();
        return new ChessFacade(moveValidator, moveConverter);
    }
}
