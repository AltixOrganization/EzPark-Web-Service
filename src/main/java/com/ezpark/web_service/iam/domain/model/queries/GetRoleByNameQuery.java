package com.ezpark.web_service.iam.domain.model.queries;

import com.ezpark.web_service.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
