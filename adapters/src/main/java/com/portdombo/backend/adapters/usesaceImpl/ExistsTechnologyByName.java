package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.adapters.gateway.IExistsTechnologyByNameGateway;
import com.portdombo.backend.usecase.technology.IExistsTechnologyByName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistsTechnologyByName implements IExistsTechnologyByName {
    private final IExistsTechnologyByNameGateway gateway;

    @Override
    public boolean existsByName(String name) {
        return gateway.existsByName(name);
    }
}
