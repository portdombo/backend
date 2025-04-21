package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.gateway.IReadAllTechnologiesGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.usecase.technology.IReadAllTechnologies;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ReadAllTechnologies implements IReadAllTechnologies {
    private final IReadAllTechnologiesGateway gateway;

    @Override
    public List<Technology> readAll() {
        return gateway.readAll();
    }
}
