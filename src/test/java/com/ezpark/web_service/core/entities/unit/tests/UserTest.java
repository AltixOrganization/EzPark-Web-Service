// File: src/test/java/com/ezpark/web_service/core/entities/unit/tests/UserServiceTest.java
package com.ezpark.web_service.core.entities.unit.tests;

import com.ezpark.web_service.iam.domain.model.aggregates.User;
import com.ezpark.web_service.iam.domain.model.commands.SignUpCommand;
import com.ezpark.web_service.iam.domain.model.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * This class represents the unit tests for the User aggregate.
 */
public class UserTest {
    private Role role;
    private SignUpCommand command;

    @BeforeEach
    void setUp() {
        role = mock(Role.class);
        command = new SignUpCommand("test@mail.com", "testuser", "Password123", List.of(role));
    }

    @Test
    void testConstructorWithCommand() {
        // Act
        User user = new User(command.email(), command.username(), command.password(), command.roles());
        // Assert
        assertNotNull(user);
        assertEquals("test@mail.com", user.getEmail());
        assertEquals("testuser", user.getUsername());
        assertEquals("Password123", user.getPassword());
        assertEquals(1, user.getRoles().size());
    }

    @Test
    void testConstructorWithFields() {
        // Act
        User user = new User("test@mail.com", "testuser", "Password123", List.of(role));
        // Assert
        assertNotNull(user);
        assertEquals("test@mail.com", user.getEmail());
        assertEquals("testuser", user.getUsername());
        assertEquals("Password123", user.getPassword());
        assertEquals(1, user.getRoles().size());
    }

    @Test
    void testGetEmail() {
        User user = new User(command.email(), command.username(), command.password(), command.roles());
        assertEquals("test@mail.com", user.getEmail());
    }

    @Test
    void testSetUsername() {
        User user = new User(command.email(), command.username(), command.password(), command.roles());
        user.setUsername("newuser");
        assertEquals("newuser", user.getUsername());
    }

    @Test
    void testSetPassword() {
        User user = new User(command.email(), command.username(), command.password(), command.roles());
        user.setPassword("NewPassword456");
        assertEquals("NewPassword456", user.getPassword());
    }
}