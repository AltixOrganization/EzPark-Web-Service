package com.ezpark.web_service.iam.domain.services;

import com.ezpark.web_service.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
