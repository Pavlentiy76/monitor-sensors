package com.example.monitorsensors.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/login")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class LoginController {

    @PostMapping
    @Operation(summary = "Login")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Link> login() {
        return ResponseEntity.status(200)
                .body(linkTo(methodOn(SensorController.class).getAllSensors()).withSelfRel());
    }
}
