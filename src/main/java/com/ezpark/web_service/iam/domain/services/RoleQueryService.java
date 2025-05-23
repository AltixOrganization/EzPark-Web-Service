package com.ezpark.web_service.iam.domain.services;

import com.ezpark.web_service.iam.domain.model.entities.Role;
import com.ezpark.web_service.iam.domain.model.queries.GetAllRolesQuery;
import com.ezpark.web_service.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
  List<Role> handle(GetAllRolesQuery query);
  Optional<Role> handle(GetRoleByNameQuery query);
}
