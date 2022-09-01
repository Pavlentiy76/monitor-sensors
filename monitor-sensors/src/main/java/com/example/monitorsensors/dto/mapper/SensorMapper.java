package com.example.monitorsensors.dto.mapper;

import com.example.monitorsensors.dto.SensorDto;
import com.example.monitorsensors.entity.Sensor;

public interface SensorMapper {

    Sensor toEntity(SensorDto sensorDto);

    SensorDto toDto(Sensor sensor);

    Sensor updateFromDto(Sensor sensor, SensorDto sensorDto);
}
