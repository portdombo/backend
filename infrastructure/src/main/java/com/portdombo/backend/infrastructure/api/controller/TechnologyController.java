package com.portdombo.backend.infrastructure.api.controller;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.api.dto.CreateTechnologyRequest;
import com.portdombo.backend.infrastructure.api.dto.Response;
import com.portdombo.backend.usecase.technology.ICreateTechnology;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("v1/technologies")
@Tag(name = "Technology", description = "Technology")
public class TechnologyController {
    private final ICreateTechnology createTechnology;
    private final ModelMapper mapper;

    @PostMapping
    @Operation(summary = "Create Technology")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    public ResponseEntity<Response> create(@RequestBody CreateTechnologyRequest request) {
        Technology technology = mapper.map(request, Technology.class);
        createTechnology.create(technology);
        Response response = Response.builder().statusCode(HttpStatus.CREATED.value()).data("CREATED").build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
