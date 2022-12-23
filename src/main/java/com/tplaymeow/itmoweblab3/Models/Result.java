package com.tplaymeow.itmoweblab3.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Result {
    private Double x;
    private Double y;
    private Integer r;
    private Boolean success;
    private LocalDateTime timestamp;
}
