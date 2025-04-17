package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.gateway.ICreateTechnologyGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.domain.exceptions.ConflictException;
import com.portdombo.backend.usecase.technology.ICreateTechnology;
import com.portdombo.backend.usecase.technology.IExistsTechnologyByName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTechnology implements ICreateTechnology {
    private final IExistsTechnologyByName existsTechnologyByName;
    private final ICreateTechnologyGateway ICreateTechnologyGateway;

    @Override
    public void create(Technology entity) {
        boolean exists = existsTechnologyByName.existsByName(entity.getName());
        if (exists) throw new ConflictException("Technology already exists");
        ICreateTechnologyGateway.create(entity);
    }
}
