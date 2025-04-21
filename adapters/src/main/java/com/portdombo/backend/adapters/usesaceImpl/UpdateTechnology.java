package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.IUpdateTechnologyGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import com.portdombo.backend.usecase.technology.IUpdateTechnology;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTechnology implements IUpdateTechnology {
    private final IReadTechnologyByCode readTechnologyByCode;
    private final IUpdateTechnologyGateway gateway;

    @Override
    public void update(Technology technology) {
        Technology savedTechnology = readTechnologyByCode.read(technology.getCode());
    }
}
