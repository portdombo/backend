package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.gateway.IReadTechnologyByCodeGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.domain.exceptions.NotFoundException;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ReadTechnologyByCode implements IReadTechnologyByCode {
    private final IReadTechnologyByCodeGateway gateway;

    @Override
    public Technology read(Long code) {
        Optional<Technology> technology = gateway.read(code);
        if (technology.isEmpty()) throw new NotFoundException("Technology not found");
        return null;
    }
}
