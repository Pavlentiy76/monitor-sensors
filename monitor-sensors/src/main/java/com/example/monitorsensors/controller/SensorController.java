package com.example.monitorsensors.controller;

import com.example.monitorsensors.dto.SensorDto;
import com.example.monitorsensors.service.SensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Sensors")
@RestController
@RequestMapping(value = "/sensors")
public class SensorController {

    SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Get sensor by id")
    public ResponseEntity<SensorDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sensorService.getById(id));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiOperation(value = "Get all sensors")
    public ResponseEntity<List<SensorDto>> getAllSensors() {
        return ResponseEntity.ok().body(sensorService.getAllSensors());
    }

    @GetMapping("/search/{partOfTitleOrModel}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiOperation(value = "Search sensors")
    public ResponseEntity<List<SensorDto>> getAllSensors(@PathVariable("partOfTitleOrModel") String partOfTitleOrModel) {
        return ResponseEntity.ok().body(sensorService.searchByPartOfTitleOrModel(partOfTitleOrModel));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Create sensor")
    public ResponseEntity<Void> save(@Valid @RequestBody SensorDto sensorDto) {
        sensorService.save(sensorDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Delete sensor")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        sensorService.deleteSensorById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Update sensor")
    public ResponseEntity<SensorDto> updateById(@PathVariable("id") Long id,
                                                @Valid @RequestBody SensorDto sensorDto) {
        return ResponseEntity.status(200).body(sensorService.updateSensor(id, sensorDto));
    }

}
