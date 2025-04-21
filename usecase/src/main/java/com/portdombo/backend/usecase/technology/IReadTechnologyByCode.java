package com.portdombo.backend.usecase.technology;

import com.portdombo.backend.domain.entity.Technology;

import java.util.List;
import java.util.Optional;

public interface IReadTechnologyByCode {
    Technology read(Long code);
}
