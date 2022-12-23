package com.tplaymeow.itmoweblab3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.primefaces.PrimeFaces;
import com.tplaymeow.itmoweblab3.Checker.CoordinatesChecker;
import com.tplaymeow.itmoweblab3.Checker.CoordinatesCheckerImpl;
import com.tplaymeow.itmoweblab3.DB.ResultsRepository;
import com.tplaymeow.itmoweblab3.DB.ResultsRepositoryImpl;
import com.tplaymeow.itmoweblab3.Models.Coordinates;
import com.tplaymeow.itmoweblab3.Models.Result;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

@Log
public class ResultsBean {
    private ResultsRepository repository = null;
    private final CoordinatesChecker checker = new CoordinatesCheckerImpl();

    @Getter
    @Setter
    private Coordinates coordinates = new Coordinates(0.0, 0.0, 1);

    @Resource(name="jdbc/jdbc-res")
    public void setDatabase(DataSource dataSource) {
        if (Objects.nonNull(dataSource))
            repository = new ResultsRepositoryImpl(dataSource);
    }

    public void calculate() {
        if (Objects.isNull(repository))
            return;

        Result result = new Result(
                coordinates.getX(),
                coordinates.getY(),
                coordinates.getR(),
                checker.check(coordinates),
                LocalDateTime.now());

        repository.add(result);

        updateChart();

//        PrimeFaces.current().executeScript("createDot('" + result.getX() +"', '" + result.getY() +"', '" + result.getSuccess() + "')");
    }

    public void calculateClick() {
        if (Objects.isNull(repository))
            return;

        String param1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String param2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");

        Double x = Double.valueOf(param1);
        Double y = Double.valueOf(param2);

        Coordinates coordinates = new Coordinates(x, y, this.coordinates.getR());
        Result result = new Result(
                coordinates.getX(),
                coordinates.getY(),
                coordinates.getR(),
                checker.check(coordinates),
                LocalDateTime.now());

        repository.add(result);

        updateChart();
    }

    public void updateRadius(Integer newRadius) {
        coordinates.setR(newRadius);
        updateChart();
    }

    public List<Result> getResults() {
        if (Objects.isNull(repository))
            return new ArrayList<>();

        return repository.findAll();
    }

    public String displayWelcomePage() {
        return "success";
    }

    public void updateChart() {
        Integer radius = coordinates.getR();
        Object[] itemsJSON = getResults().stream().map(result -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("x", result.getX());
            resultMap.put("y", result.getY());
            resultMap.put("color", result.getSuccess() ? "green" : "red");
            return resultMap;
        }).toArray();

        Map<String, Object> json = new HashMap<>();
        json.put("radius", radius);
        json.put("items", itemsJSON);

        String jsonString = new GsonBuilder().create().toJson(json);

        String statement = String.format("chart.render(%s)", jsonString);
        PrimeFaces.current().executeScript(statement);
        log.info(statement);
    }
}
