package com.tplaymeow.itmoweblab3.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Coordinates {
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private double r;

    @Id
    @GeneratedValue
    private Long id;

    public Coordinates(int x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Coordinates() {
        this.x = 0;
        this.y = 0;
        this.r = 2.5;
    }
}
