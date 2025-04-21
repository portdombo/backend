package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.gateway.IReadTechnologyByCodeGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.domain.exceptions.NotFoundException;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadTechnologyByCode implements IReadTechnologyByCode {
    private final IReadTechnologyByCodeGateway gateway;

    @Override
    public Technology read(Long code) {
        return gateway.read(code)
                .orElseThrow(() -> new NotFoundException("Technology not found"));
    }
}
