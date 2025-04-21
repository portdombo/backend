package com.portdombo.backend.usecase.technology;

import com.portdombo.backend.domain.entity.Technology;

import java.util.List;

public interface IReadAllTechnologies {
    public List<Technology> readAll();
}
