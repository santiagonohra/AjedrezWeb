package com.example.demo2.model;

import lombok.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
class FigureMove {
    private Point source;
    private Point destination;
    private FigureType type;
}
