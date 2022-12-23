package com.tplaymeow.itmoweblab3.DB;

import com.tplaymeow.itmoweblab3.Models.Result;
import lombok.extern.java.Log;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Log
public class ResultsRepositoryImpl implements ResultsRepository {
    private final DataSource dataSource;

    public ResultsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Result result) {
        String query = String.format(
                "INSERT INTO %s(%s,%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?, ?)",
                Constants.TABLE_NAME,
                Constants.X_COLUMN,
                Constants.Y_COLUMN,
                Constants.R_COLUMN,
                Constants.SUCCESS_COLUMN,
                Constants.TIMESTAMP_DATE_COLUMN,
                Constants.TIMESTAMP_TIME_COLUMN);

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, result.getX());
            statement.setDouble(2, result.getY());
            statement.setInt(3, result.getR());
            statement.setBoolean(4, result.getSuccess());
            statement.setDate(5, Date.valueOf(result.getTimestamp().toLocalDate()));
            statement.setTime(6, Time.valueOf(result.getTimestamp().toLocalTime()));

            statement.execute();

            log.info("Result successfully added");
        } catch (SQLException e) {
            String message = String.format("Failed to add result. State: %s. Code: %s", e.getSQLState(), e.getErrorCode());
            log.log(Level.WARNING, message);
        }
    }

    @Override
    public List<Result> findAll() {
        String query = String.format("SELECT * FROM %s", Constants.TABLE_NAME);

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery()) {

            List<Result> items = new ArrayList<>();

            while (result.next()) {
                Double x = result.getDouble(Constants.X_COLUMN);
                Double y = result.getDouble(Constants.Y_COLUMN);
                Integer r = result.getInt(Constants.R_COLUMN);
                Boolean success = result.getBoolean(Constants.SUCCESS_COLUMN);

                LocalDate date = result.getDate(Constants.TIMESTAMP_DATE_COLUMN).toLocalDate();
                LocalTime time = result.getTime(Constants.TIMESTAMP_TIME_COLUMN).toLocalTime();
                LocalDateTime timestamp = LocalDateTime.of(date, time);

                Result item = new Result(x, y, r, success, timestamp);

                items.add(item);
            }

            log.info("Results successfully fetched");

            return items;
        } catch (SQLException e) {
            String message = String.format("Failed to get results. State: %s. Code: %s", e.getSQLState(), e.getErrorCode());
            log.log(Level.WARNING, message);

            return new ArrayList<>();
        }
    }

    private static final class Constants {
        private final static String TABLE_NAME = "Results";
        private final static String X_COLUMN = "x_coord";
        private final static String Y_COLUMN = "y_coord";
        private final static String R_COLUMN = "r_coord";
        private final static String SUCCESS_COLUMN = "success";
        private final static String TIMESTAMP_DATE_COLUMN = "timestamp_date";
        private final static String TIMESTAMP_TIME_COLUMN = "timestamp_time";
    }
}