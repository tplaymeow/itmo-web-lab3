package com.tplaymeow.itmoweblab3.DB;

import com.tplaymeow.itmoweblab3.Models.Result;

import java.util.List;

public interface ResultsRepository {
    void add(Result result);
    List<Result> findAll();
}
