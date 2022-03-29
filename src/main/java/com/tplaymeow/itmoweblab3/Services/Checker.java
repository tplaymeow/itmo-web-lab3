package com.tplaymeow.itmoweblab3.Services;

import com.tplaymeow.itmoweblab3.Models.Coordinates;

public class Checker {
    public boolean check(Coordinates coordinates) {
        int absX = Math.abs(coordinates.getX());
        double absY = Math.abs(coordinates.getY());
        double absR = coordinates.getR();

        if (coordinates.getX() >= 0 && coordinates.getY() >= 0)
            return (absX <= absR / 2.0) && (absY <= absR);
        if (coordinates.getX() <= 0 && coordinates.getY() >= 0)
            return (absX / 2.0 + absY <= absR / 2.0);
        if (coordinates.getX() >= 0 && coordinates.getY() <= 0)
            return (absX * absX + absY * absY < Math.pow((absR / 2.0), 2));

        return false;
    }
}