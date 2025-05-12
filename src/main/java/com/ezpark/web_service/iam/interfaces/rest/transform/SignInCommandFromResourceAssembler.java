package com.ezpark.web_service.iam.interfaces.rest.transform;

import com.ezpark.web_service.iam.domain.model.commands.SignInCommand;
import com.ezpark.web_service.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
