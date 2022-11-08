package com.example.monitorsensors.entity;

import com.example.monitorsensors.db.enums.Type;
import com.example.monitorsensors.db.enums.Unit;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sensors")
public class Sensor extends RepresentationModel<Sensor> implements Serializable {

    private static final long serialVersionUID = 445862574L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String model;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sensor_type")
    private Type type;
    @Enumerated(value = EnumType.STRING)
    private Unit unit;
    private String location;
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "range_id", referencedColumnName = "id")
    private Range range;

    public Sensor() {
    }

    public Long getId() {
        return id;
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

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(title, sensor.title) &&
                Objects.equals(model, sensor.model) &&
                type == sensor.type &&
                unit == sensor.unit &&
                Objects.equals(location, sensor.location) &&
                Objects.equals(description, sensor.description) &&
                Objects.equals(range, sensor.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, model, type, unit, location, description, range);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", model='" + model + '\'' +
                ", type=" + type +
                ", unit=" + unit +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", range=" + range +
                '}';
    }
}
