package com.ezpark.web_service.iam.domain.services;

import com.ezpark.web_service.iam.domain.model.aggregates.User;
import com.ezpark.web_service.iam.domain.model.queries.GetAllUsersQuery;
import com.ezpark.web_service.iam.domain.model.queries.GetUserByIdQuery;
import com.ezpark.web_service.iam.domain.model.queries.GetUserByEmailQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
  List<User> handle(GetAllUsersQuery query);
  Optional<User> handle(GetUserByIdQuery query);
  Optional<User> handle(GetUserByEmailQuery query);
}
