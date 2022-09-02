package com.example.monitorsensors.controller;

import com.example.monitorsensors.dto.SensorDto;
import com.example.monitorsensors.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping(value = "/sensors")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400",
                description = "Bad Request",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "401",
                description = "Unauthorized",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "403",
                description = "Forbidden",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404",
                description = "Not Found",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500",
                description = "Internal Server Error",
                content = @Content(mediaType = "application/json"))
})
public class SensorController {

    SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get sensor by id")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<SensorDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sensorService.getById(id));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get all sensors")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<SensorDto>> getAllSensors() {
        return ResponseEntity.ok().body(sensorService.getAllSensors());
    }

    @GetMapping("/search/{partOfTitleOrModel}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Search sensors")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<SensorDto>> searchSensors(@PathVariable("partOfTitleOrModel") String partOfTitleOrModel) {
        return ResponseEntity.ok().body(sensorService.searchByPartOfTitleOrModel(partOfTitleOrModel));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Create sensor")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Void> save(@Valid @RequestBody SensorDto sensorDto) {
        sensorService.save(sensorDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete sensor")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        sensorService.deleteSensorById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update sensor")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<SensorDto> updateById(@PathVariable("id") Long id,
                                                @Valid @RequestBody SensorDto sensorDto) {
        return ResponseEntity.status(200).body(sensorService.updateSensor(id, sensorDto));
    }

}
