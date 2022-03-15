package com.example.demo2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@ToString
public class FigureMoveDto implements Serializable {
    private String source;
    private String destination;
    private FigureType type;
}
