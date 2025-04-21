package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.gateway.IDeleteTechnologyGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.usecase.technology.IDeleteTechnology;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTechnology implements IDeleteTechnology {
    private final IReadTechnologyByCode readTechnologyByCode;
    private final IDeleteTechnologyGateway gateway;

    @Override
    public void delete(Long code) {
        Technology technology = readTechnologyByCode.read(code);
    }
}
