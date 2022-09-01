package com.example.monitorsensors.dto;

import com.example.monitorsensors.db.enums.Type;
import com.example.monitorsensors.db.enums.Unit;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDto {

    private Long id;
    @NotBlank(message = "Title must not be blank")
    @Size(max = 30, message = "Title size must not exceed 30 characters")
    private String title;
    @NotBlank(message = "Model must not be blank")
    @Size(max = 15, message = "Model size must not exceed 15 characters")
    private String model;
    @NotNull
    private Type type;
    @Valid
    private RangeDto range;
    private Unit unit;
    @Size(max = 40, message = "Location size must not exceed 40 characters")
    private String location;
    @Size(max = 200, message = "Description size must not exceed 200 characters")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public RangeDto getRange() {
        return range;
    }

    public void setRange(RangeDto range) {
        this.range = range;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
