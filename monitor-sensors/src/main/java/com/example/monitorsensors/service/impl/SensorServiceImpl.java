package com.example.monitorsensors.service.impl;

import com.example.monitorsensors.db.repository.SensorRepository;
import com.example.monitorsensors.dto.SensorDto;
import com.example.monitorsensors.dto.mapper.SensorMapper;
import com.example.monitorsensors.entity.Range;
import com.example.monitorsensors.entity.Sensor;
import com.example.monitorsensors.exception.SensorNotFoundException;
import com.example.monitorsensors.service.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorMapper sensorMapper;
    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorMapper sensorMapper, SensorRepository sensorRepository) {
        this.sensorMapper = sensorMapper;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public void save(SensorDto sensorDto) {
        Sensor sensor = sensorMapper.toEntity(sensorDto);
        sensorRepository.save(sensor);
    }

    @Override
    public SensorDto getById(Long id) {
        return Optional.ofNullable(
                sensorRepository.getById(id))
                .map(sensorMapper::toDto)
                .orElseThrow(() -> new SensorNotFoundException("Sensor not found"));
    }

    @Override
    public List<SensorDto> getAllSensors() {
        return sensorRepository.getAllSensors().stream()
                .map(sensorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSensorById(Long id) {
        Sensor sensor = Optional.ofNullable(sensorRepository.getById(id))
                .orElseThrow(() -> new SensorNotFoundException("Sensor not found"));
        sensorRepository.deleteSensor(sensor);
    }

    @Override
    public SensorDto updateSensor(Long id, SensorDto sensorDto) {
        Sensor sensor = Optional.ofNullable(sensorRepository.getById(id))
                .orElseThrow(() -> new SensorNotFoundException("Sensor not found"));
        sensorMapper.updateFromDto(sensor, sensorDto);
        Range range = sensor.getRange();
        range.setFrom(sensorDto.getRange().getFrom());
        range.setTo(sensorDto.getRange().getTo());
        sensor.setRange(range);
        Sensor updSensor = sensorRepository.updateSensor(id, sensor);
        return sensorMapper.toDto(updSensor);
    }

    @Override
    public List<SensorDto> searchByPartOfTitleOrModel(String partOfTitleOrModel){
        return sensorRepository.searchByPartOfTitleOrModel(partOfTitleOrModel).stream()
                .map(sensorMapper::toDto)
                .collect(Collectors.toList());
    }
}
