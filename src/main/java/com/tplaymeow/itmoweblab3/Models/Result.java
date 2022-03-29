package com.tplaymeow.itmoweblab3.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Getter
    @Setter
    @OneToOne
    private Coordinates coordinates;
    @Getter
    @Setter
    private boolean success;

    @Id
    @GeneratedValue
    private Long id;

    public Result(Coordinates coordinates, boolean success) {
        this.coordinates = coordinates;
        this.success = success;
    }
}
