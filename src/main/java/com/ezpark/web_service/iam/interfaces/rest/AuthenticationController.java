package com.ezpark.web_service.iam.interfaces.rest;

import com.ezpark.web_service.iam.domain.services.UserCommandService;
import com.ezpark.web_service.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.ezpark.web_service.iam.interfaces.rest.resources.SignInResource;
import com.ezpark.web_service.iam.interfaces.rest.resources.SignUpResource;
import com.ezpark.web_service.iam.interfaces.rest.resources.UserResource;
import com.ezpark.web_service.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.ezpark.web_service.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.ezpark.web_service.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.ezpark.web_service.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {

  private final UserCommandService userCommandService;

  public AuthenticationController(UserCommandService userCommandService) {
    this.userCommandService = userCommandService;
  }

  /**
   * Handles the sign-in request.
   * @param signInResource the sign-in request body.
   * @return the authenticated user resource.
   */
  @PostMapping("/sign-in")
  public ResponseEntity<AuthenticatedUserResource> signIn(
          @Valid @RequestBody SignInResource signInResource) {

    var signInCommand = SignInCommandFromResourceAssembler
            .toCommandFromResource(signInResource);
    var authenticatedUser = userCommandService.handle(signInCommand);
    if (authenticatedUser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler
            .toResourceFromEntity(
                    authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
    return ResponseEntity.ok(authenticatedUserResource);
  }

  /**
   * Handles the sign-up request.
   * @param signUpResource the sign-up request body.
   * @return the created user resource.
   */
  @PostMapping("/sign-up")
  public ResponseEntity<UserResource> signUp(@Valid @RequestBody SignUpResource signUpResource) {
    var signUpCommand = SignUpCommandFromResourceAssembler
            .toCommandFromResource(signUpResource);
    System.out.println(signUpCommand.roles());
    var user = userCommandService.handle(signUpCommand);
    if (user.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return new ResponseEntity<>(userResource, HttpStatus.CREATED);
  }
}
