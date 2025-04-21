package com.portdombo.backend.infrastructure.api.controller;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.api.dto.CreateTechnologyRequest;
import com.portdombo.backend.infrastructure.api.dto.Response;
import com.portdombo.backend.infrastructure.mapper.TechnologyMapper;
import com.portdombo.backend.usecase.technology.ICreateTechnology;
import com.portdombo.backend.usecase.technology.IReadAllTechnologies;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/technologies")
@Tag(name = "Technology", description = "Technology")
public class TechnologyController {
    private final ICreateTechnology createTechnology;
    private final IReadAllTechnologies readAllTechnologies;
    private final IReadTechnologyByCode readTechnologyByCode;
    private final TechnologyMapper mapper;

    @PostMapping
    @Operation(summary = "Create Technology")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<Response> create(@Valid @RequestBody CreateTechnologyRequest request) {
        Technology technology = mapper.map(request, Technology.class);
        createTechnology.create(technology);
        Response response = Response.builder().statusCode(HttpStatus.CREATED.value()).data("CREATED").build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Read all Technology")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<Response> readAll() {
        List<Technology> technologies = readAllTechnologies.readAll();
        Response response = Response.builder().statusCode(HttpStatus.OK.value()).data(technologies).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    @Operation(summary = "Read Technology by code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<Response> readByCode(@PathVariable("code") Long code) {
        Technology result = readTechnologyByCode.read(code);
        Response response = Response.builder().statusCode(HttpStatus.OK.value()).data(result).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
