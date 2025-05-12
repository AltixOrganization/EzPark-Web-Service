package com.ezpark.web_service.reservations.domain.model.aggregates;

import com.ezpark.web_service.reservations.domain.model.commands.CreateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import com.ezpark.web_service.reservations.domain.model.valueobject.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Data
@Entity
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer hoursRegistered;
    private Double totalFare;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private String paymentReceiptUrl;
    private String paymentReceiptDeleteUrl;



    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private GuestId guestId;

    @Embedded
    private HostId hostId;

    @Embedded
    private ParkingId parkingId;

    @Embedded
    private VehicleId vehicleId;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Reservation(CreateReservationCommand command) {
        this.hoursRegistered = command.hoursRegistered();
        this.totalFare = command.totalFare();
        this.reservationDate = command.reservationDate();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.hostId = new HostId(command.hostId());
        this.guestId = new GuestId(command.guestId());
        this.parkingId = new ParkingId(command.parkingId());
        this.vehicleId = new VehicleId(command.vehicleId());
        this.status = Status.Pending;
    }
    public Reservation updatedReservation(UpdateReservationCommand command){
        this.hoursRegistered = command.hoursRegistered();
        this.totalFare = command.totalFare();
        this.reservationDate = command.reservationDate();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        return this;
    }
    public Reservation updatedStatus(UpdateStatusCommand command){
        this.status = command.status();
        return this;
    }

}
