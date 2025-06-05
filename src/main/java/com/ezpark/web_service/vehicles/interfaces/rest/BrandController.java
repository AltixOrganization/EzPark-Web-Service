package com.ezpark.web_service.vehicles.interfaces.rest;

import com.ezpark.web_service.vehicles.domain.model.commands.DeleteBrandCommand;
import com.ezpark.web_service.vehicles.domain.model.queries.GetAllBrandsQuery;
import com.ezpark.web_service.vehicles.domain.services.BrandCommandService;
import com.ezpark.web_service.vehicles.domain.services.BrandQueryService;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.BrandWithModelListResource;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.CreateBrandResource;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.UpdateBrandResource;
import com.ezpark.web_service.vehicles.interfaces.rest.transform.BrandResourceFromEntityAssembler;
import com.ezpark.web_service.vehicles.interfaces.rest.transform.CreateBrandCommandFromResourceAssembler;
import com.ezpark.web_service.vehicles.interfaces.rest.transform.UpdateBrandCommandFromResourceAssembler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/brands")
public class BrandController {
    private BrandCommandService brandCommandService;
    private BrandQueryService brandQueryService;

    @GetMapping
    public ResponseEntity<List<BrandWithModelListResource>> getAllBrands() {
        var getAllBrandsQuery = new GetAllBrandsQuery();
        List<BrandWithModelListResource> brands = brandQueryService.handle(getAllBrandsQuery).stream().map(BrandResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(brands);
    }

    @PostMapping
    public ResponseEntity<BrandWithModelListResource> createBrand(@Valid @RequestBody CreateBrandResource resource) {
        return brandCommandService.handle(CreateBrandCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(BrandResourceFromEntityAssembler::toResourceFromEntity)
                .map(brand -> ResponseEntity.status(HttpStatus.CREATED).body(brand))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandWithModelListResource> updateBrand(@PathVariable Long id, @Valid @RequestBody UpdateBrandResource resource) {
        return brandCommandService.handle(UpdateBrandCommandFromResourceAssembler.toCommandFromResource(id, resource))
                .map(BrandResourceFromEntityAssembler::toResourceFromEntity)
                .map(brand -> ResponseEntity.status(HttpStatus.OK).body(brand))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        var deleteBrandCommand = new  DeleteBrandCommand(id);
        brandCommandService.handle(deleteBrandCommand);
        return ResponseEntity.noContent().build();
    }
}
