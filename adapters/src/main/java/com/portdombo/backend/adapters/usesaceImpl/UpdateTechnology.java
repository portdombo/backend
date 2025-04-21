package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.IUpdateTechnologyGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.domain.exceptions.ConflictException;
import com.portdombo.backend.usecase.technology.IExistsTechnologyByName;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import com.portdombo.backend.usecase.technology.IUpdateTechnology;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTechnology implements IUpdateTechnology {
    private final IReadTechnologyByCode readTechnologyByCode;
    private final IExistsTechnologyByName existsTechnologyByName;
    private final IUpdateTechnologyGateway gateway;

    @Override
    public void update(Technology technology) {
        Technology savedTechnology = readTechnologyByCode.read(technology.getCode());
        if (!savedTechnology.getName().equals(technology.getName())) {
            boolean exists = existsTechnologyByName.existsByName(technology.getName());
            if (exists) throw new ConflictException("You already have a technology with this name! " +
                    "Please change the name before update.");
        }
        gateway.update(technology);
    }
}
