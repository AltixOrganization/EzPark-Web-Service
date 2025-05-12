package com.ezpark.web_service.parkings.domain.model.aggregates;

import com.ezpark.web_service.parkings.domain.model.commands.CreateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.entities.Location;
import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import com.ezpark.web_service.parkings.domain.model.valueobjects.ProfileId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor@Data

@Entity
@Table(name = "parkings")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ProfileId profileId;

    private Double width;
    private Double length;
    private Double height;
    private Double price;
    private String phone;
    private Integer space;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parking", orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        schedule.setParking(this);
        this.schedules.add(schedule);
    }

    public void updateLocation(Location location) {
        this.location = location;
        this.location.setParking(this);
    }

    public Parking(CreateParkingCommand command) {
        this.profileId = new ProfileId(command.profileId());
        this.width = command.width();
        this.length = command.length();
        this.height = command.height();
        this.price = command.price();
        this.phone = command.phone();
        this.space = command.space();
        this.description = command.description();

        this.location = new Location();
        this.location.setAddress(command.address());
        this.location.setNumDirection(command.numDirection());
        this.location.setStreet(command.street());
        this.location.setDistrict(command.district());
        this.location.setCity(command.city());
        this.location.setLatitude(command.latitude());
        this.location.setLongitude(command.longitude());
        this.location.setParking(this);
    }

    public void updateParking(UpdateParkingCommand command) {
        this.width = command.width();
        this.length = command.length();
        this.height = command.height();
        this.price = command.price();
        this.phone = command.phone();
        this.space = command.space();
        this.description = command.description();

        this.location.setAddress(command.address());
        this.location.setNumDirection(command.numDirection());
        this.location.setStreet(command.street());
        this.location.setDistrict(command.district());
        this.location.setCity(command.city());
        this.location.setLatitude(command.latitude());
        this.location.setLongitude(command.longitude());
        this.location.setParking(this);
    }
}