package com.ezpark.web_service.core.entities.unit.tests;

import com.ezpark.web_service.iam.domain.model.aggregates.User;
import com.ezpark.web_service.iam.domain.model.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UserTest {
    private Role role;

    @BeforeEach
    void setUp() {
        role = mock(Role.class);
    }

    @Test
    void testConstructorWithFields() {
        User user = new User("test@mail.com", "Password123", List.of(role));
        assertNotNull(user);
        assertEquals("test@mail.com", user.getEmail());
        assertEquals("Password123", user.getPassword());
        assertEquals(1, user.getRoles().size());
    }

    @Test
    void testGetEmail() {
        User user = new User("test@mail.com", "Password123", List.of(role));
        assertEquals("test@mail.com", user.getEmail());
    }

    @Test
    void testSetPassword() {
        User user = new User("test@mail.com", "Password123", List.of(role));
        user.setPassword("NewPassword456");
        assertEquals("NewPassword456", user.getPassword());
    }

    @Test
    void testAddRole() {
        User user = new User("test@mail.com", "Password123", List.of());
        user.addRole(role);
        assertEquals(1, user.getRoles().size());
    }
}