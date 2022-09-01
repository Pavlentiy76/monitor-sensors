package com.example.monitorsensors.service;

import com.example.monitorsensors.dto.SensorDto;

import java.util.List;

public interface SensorService {

    void save(SensorDto sensorDto);

    SensorDto getById(Long id);

    void deleteSensorById(Long id);

    SensorDto updateSensor(Long id, SensorDto sensorDto);

    List<SensorDto> getAllSensors();

    List<SensorDto> searchByPartOfTitleOrModel(String partOfTitleOrModel);

}
