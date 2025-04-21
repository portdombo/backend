package com.portdombo.backend.adapters.gateway;

import com.portdombo.backend.domain.entity.Technology;

import java.util.Optional;

public interface IReadTechnologyByCodeGateway {
    Optional<Technology> read(Long code);
}
