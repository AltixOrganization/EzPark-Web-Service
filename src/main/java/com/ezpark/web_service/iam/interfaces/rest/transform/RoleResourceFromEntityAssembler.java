package com.ezpark.web_service.iam.interfaces.rest.transform;

import com.ezpark.web_service.iam.domain.model.entities.Role;
import com.ezpark.web_service.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
