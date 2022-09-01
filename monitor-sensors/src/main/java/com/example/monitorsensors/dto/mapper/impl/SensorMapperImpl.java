package com.example.monitorsensors.dto.mapper.impl;

import com.example.monitorsensors.dto.SensorDto;
import com.example.monitorsensors.dto.mapper.RangeMapper;
import com.example.monitorsensors.dto.mapper.SensorMapper;
import com.example.monitorsensors.entity.Sensor;
import org.springframework.stereotype.Service;

@Service
public class SensorMapperImpl implements SensorMapper {

    private final RangeMapper rangeMapper;

    public SensorMapperImpl(RangeMapper rangeMapper) {
        this.rangeMapper = rangeMapper;
    }

    @Override
    public Sensor toEntity(SensorDto sensorDto) {
        Sensor sensor = new Sensor();
        return updateFromDto(sensor, sensorDto);
    }

    @Override
    public SensorDto toDto(Sensor sensor) {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setId(sensor.getId());
        sensorDto.setTitle(sensor.getTitle());
        sensorDto.setDescription(sensor.getDescription());
        sensorDto.setModel(sensor.getModel());
        sensorDto.setRange(rangeMapper.toDto(sensor.getRange()));
        sensorDto.setUnit(sensor.getUnit());
        sensorDto.setLocation(sensor.getLocation());
        sensorDto.setType(sensor.getType());
        return sensorDto;
    }

    @Override
    public Sensor updateFromDto(Sensor sensor, SensorDto sensorDto) {
        sensor.setTitle(sensorDto.getTitle());
        sensor.setDescription(sensorDto.getDescription());
        sensor.setModel(sensorDto.getModel());
        sensor.setRange(rangeMapper.updateFromDto(sensor.getRange(), sensorDto.getRange()));
        sensor.setUnit(sensorDto.getUnit());
        sensor.setLocation(sensorDto.getLocation());
        sensor.setType(sensorDto.getType());
        return sensor;
    }
}
