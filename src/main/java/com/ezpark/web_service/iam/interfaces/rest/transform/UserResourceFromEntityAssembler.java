package com.ezpark.web_service.iam.interfaces.rest.transform;

import com.ezpark.web_service.iam.domain.model.aggregates.User;
import com.ezpark.web_service.iam.domain.model.entities.Role;
import com.ezpark.web_service.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
            .map(Role::getStringName)
            .toList();
    return new UserResource(user.getId(), user.getEmail(), roles);
  }
}
