package com.portdombo.backend.adapters.usesaceImpl;

import com.portdombo.backend.usecase.technology.IExistsTechnologyByName;

public class ExistsTechnologyByName implements IExistsTechnologyByName {
    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
