package com.portdombo.backend.adapters.gateway;

import com.portdombo.backend.domain.entity.Technology;

import java.util.List;

public interface IReadAllTechnologiesGateway {
    public List<Technology> readAll();
}