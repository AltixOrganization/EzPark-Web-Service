package com.ezpark.web_service.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String email, String username, List<String> roles) {
}
