package com.portdombo.backend.usecase.technology;

import com.portdombo.backend.domain.entity.Technology;

public interface IReadTechnologyByCode {
    Technology read(Long code);
}
