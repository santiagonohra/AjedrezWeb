package com.example.demo2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MoveValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoveValidator.class);

    public boolean isMoveValid(FigureMove figureMove) {

        if(figureMove.getType().equals(FigureType.BISHOP)){
            LOGGER.info("source {}",figureMove.getSource().toString());
            LOGGER.info("destination {}",figureMove.getDestination().toString());
            int sourceX = figureMove.getSource().getX();
            int sourceY = figureMove.getSource().getY();
            int destinationX = figureMove.getDestination().getX();
            int destinationY = figureMove.getDestination().getY();
            if (Math.abs(sourceX-destinationX) == Math.abs(sourceY - destinationY)) {
                return true;
            }
        }
        if(figureMove.getType().equals(FigureType.PAWN)){
            LOGGER.info("source {}",figureMove.getSource().toString());
            LOGGER.info("destination {}",figureMove.getDestination().toString());
            int sourceX = figureMove.getSource().getX();
            int sourceY = figureMove.getSource().getY();
            int destinationX = figureMove.getDestination().getX();
            int destinationY = figureMove.getDestination().getY();
            if ((Math.abs(sourceY-destinationY) == 2) && (Math.abs(sourceX-destinationX) == 0) && (sourceY == 1 || sourceY == 6) ) {
                return true;
            }
        }



        return false;
    }
}
