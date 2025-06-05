package com.ezpark.web_service.vehicles.interfaces.rest;

import com.ezpark.web_service.vehicles.domain.model.commands.DeleteModelCommand;
import com.ezpark.web_service.vehicles.domain.services.ModelCommandService;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.CreateModelResource;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.ModelResource;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.UpdateModelResource;
import com.ezpark.web_service.vehicles.interfaces.rest.transform.CreateModelCommandFromResourceAssembler;
import com.ezpark.web_service.vehicles.interfaces.rest.transform.ModelResourceFromEntityAssembler;
import com.ezpark.web_service.vehicles.interfaces.rest.transform.UpdateModelCommandFromResourceAssembler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/models")
public class ModelController {
    private ModelCommandService modelCommandService;

    @PostMapping
    public ResponseEntity<ModelResource> createModel(@Valid CreateModelResource resource) {
        return modelCommandService.handle(CreateModelCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(ModelResourceFromEntityAssembler::toResourceFromEntity)
                .map(model -> ResponseEntity.status(HttpStatus.CREATED).body(model))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelResource> updateModel(@PathVariable Long id, @Valid @RequestBody UpdateModelResource resource) {
        return modelCommandService.handle(UpdateModelCommandFromResourceAssembler.toCommandFromResource(id, resource))
                .map(ModelResourceFromEntityAssembler::toResourceFromEntity)
                .map(model -> ResponseEntity.status(HttpStatus.OK).body(model))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        var deleteModelCommand = new DeleteModelCommand(id);
        modelCommandService.handle(deleteModelCommand);
        return ResponseEntity.noContent().build();
    }
}
