package com.tplaymeow.itmoweblab3;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.tplaymeow.itmoweblab3.Managers.SaveManager;
import com.tplaymeow.itmoweblab3.Models.Coordinates;
import com.tplaymeow.itmoweblab3.Models.Result;
import com.tplaymeow.itmoweblab3.Services.Checker;
import lombok.Getter;
import lombok.extern.java.Log;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

@Log
public class ResultsBean {
    @Getter
    private final List<Result> list = Collections.synchronizedList(new ArrayList<>());
    @Getter
    private Coordinates currentCoordinates = new Coordinates();
    @Getter
    private Result last = null;

    private final Checker checker = new Checker();

//    private final SaveManager saveManager = new SaveManager();

    {
//        List<Result> results = this.saveManager.getAll();
//        this.list.addAll(results);
    }

    public void processFromCanvas() {
        try {
            log.log(Level.INFO, "From canvas");

            log.log(Level.INFO, FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().keySet().toString());

            String xString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
            String yString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");

            log.log(Level.INFO, xString);
            log.log(Level.INFO, yString);

            int x = Math.toIntExact(Math.round(Double.parseDouble(xString)));
            double y = Double.parseDouble(yString);

            log.log(Level.INFO, Integer.toString(x));
            log.log(Level.INFO, Double.toString(y));

            if (!(x >= -3 && x <= 5 && y >= -5 && y <= 5)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Validation Error",
                                "Wrong parameters values"
                        )
                );
                return;
            }

            log.log(Level.INFO, "Success validation");

            this.currentCoordinates.setX(x);
            this.currentCoordinates.setY(y);

            process();
        } catch (Exception ignored) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "OMG Error",
                            "Something going wrong"
                    )
            );
        }
    }

    public void process() {
        Coordinates coordinates = this.currentCoordinates;
        boolean success = this.checker.check(coordinates);

        log.log(Level.INFO, Integer.toString(coordinates.getX()));
        log.log(Level.INFO, Double.toString(coordinates.getY()));
        log.log(Level.INFO, Double.toString(coordinates.getR()));
        log.log(Level.INFO, Boolean.toString(success));

        Result newResult = new Result(coordinates, success);

        this.last = newResult;
        this.list.add(newResult);
        this.currentCoordinates = new Coordinates();
//        this.saveManager.save(newResult);
    }

    public String getJsonList() {
        Gson serializer = new GsonBuilder().create();
        return serializer.toJson(list.toArray());
    }
}
