package com.ezpark.web_service.vehicles.domain.services;


import com.ezpark.web_service.vehicles.domain.model.commands.CreateModelCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.DeleteModelCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.UpdateModelCommand;
import com.ezpark.web_service.vehicles.domain.model.entities.Model;

import java.util.Optional;

public interface ModelCommandService {
    Optional<Model> handle(CreateModelCommand command);
    Optional<Model> handle(UpdateModelCommand command);
    void handle(DeleteModelCommand command);
}
