package com.example.monitorsensors.db.repository;

import com.example.monitorsensors.entity.Sensor;

import java.util.List;

public interface SensorRepository {

    void save(Sensor sensor);

    Sensor getById(Long id);

    void deleteSensor(Sensor sensor);

    Sensor updateSensor(Long id, Sensor sensor);

    List<Sensor> getAllSensors();

    List<Sensor> searchByPartOfTitleOrModel(String partOfNameOrModel);
}
