package com.ezpark.web_service.profiles.domain.model.aggregates;

import com.ezpark.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.ezpark.web_service.profiles.domain.model.commands.UpdateProfileCommand;
import com.ezpark.web_service.profiles.domain.model.valueobject.UserId;
import com.ezpark.web_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "profiles")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    private String name;
    private String lastName;
    private String address;

    @Embedded
    private UserId userId;

    public Profile(String lastName, String name) {
        this.lastName = lastName;
        this.name = name;
    }

    public Profile(CreateProfileCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.address = command.address();
        this.userId = new UserId(command.userId());
    }
    public Profile updatedProfile(UpdateProfileCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.address = command.address();
        return this;
    }
}
