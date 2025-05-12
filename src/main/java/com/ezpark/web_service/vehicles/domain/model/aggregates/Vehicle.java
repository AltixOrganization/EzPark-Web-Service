package com.ezpark.web_service.vehicles.domain.model.aggregates;


import com.ezpark.web_service.vehicles.domain.model.commands.CreateVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.UpdateVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.valueobjects.ProfileId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "vehicles")

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String model;
    private String brand;

    @Embedded
    private ProfileId profileId;

    public Vehicle(String licensePlate, String model, String brand, Long profileId) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.profileId = new ProfileId(profileId);
    }


    public Vehicle(CreateVehicleCommand command) {
        this.licensePlate = command.licensePlate();
        this.model = command.model();
        this.brand = command.brand();
        this.profileId = new ProfileId(command.profileId());
    }

    public Vehicle updatedVehicle(UpdateVehicleCommand command) {
        this.licensePlate = command.licensePlate();
        this.model = command.model();
        this.brand = command.brand();
        return this;
    }
}