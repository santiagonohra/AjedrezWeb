package com.example.demo2.model;

import java.util.HashMap;

public class MoveConverter {

    private final HashMap<String,Integer> letterToNumber = new HashMap(){{put("a",1);put("b",2);put("c",3);put("d",4);put("e",5);put("f",6);put("g",7);put("h",8);}};

    public FigureMove toFigureMove(FigureMoveDto figureMoveDto) {
        int y = letterToNumber.get(Character.toString(figureMoveDto.getSource().charAt(0)));
        Point source = new Point(Integer.parseInt(Character.toString(figureMoveDto.getSource().charAt(2)))
                ,y);
        y=letterToNumber.get(Character.toString(figureMoveDto.getDestination().charAt(0)));
        Point destination= new Point(Integer.parseInt(Character.toString(figureMoveDto.getDestination().charAt(2)))
                ,y);
        return new FigureMove(source,destination,figureMoveDto.getType());
    }
}
