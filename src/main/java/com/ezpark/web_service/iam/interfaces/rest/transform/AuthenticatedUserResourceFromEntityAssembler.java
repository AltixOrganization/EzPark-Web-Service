package com.ezpark.web_service.iam.interfaces.rest.transform;

import com.ezpark.web_service.iam.domain.model.aggregates.User;
import com.ezpark.web_service.iam.domain.model.entities.Role;
import com.ezpark.web_service.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    var roles = user.getRoles().stream()
            .map(Role::getStringName)
            .toList();
    return new AuthenticatedUserResource(user.getId(), user.getEmail(), token, roles);
  }
}