package com.ezpark.web_service.iam.application.acl;

import com.ezpark.web_service.iam.domain.model.commands.SignUpCommand;
import com.ezpark.web_service.iam.domain.model.entities.Role;
import com.ezpark.web_service.iam.domain.model.queries.GetUserByIdQuery;
import com.ezpark.web_service.iam.domain.model.queries.GetUserByUsernameQuery;
import com.ezpark.web_service.iam.domain.services.UserCommandService;
import com.ezpark.web_service.iam.domain.services.UserQueryService;
import com.ezpark.web_service.iam.interfaces.acl.IamContextFacade;
import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * IamContextFacadeImpl
 * <p>
 *     This class provides the implementation of the IamContextFacade interface.
 *     This class is used by the ACL module to interact with the IAM module.
 * </p>
 */
@Service
public class IamContextFacadeImpl implements IamContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    /**
     * Constructor
     * @param userCommandService the {@link UserCommandService} user command service
     * @param userQueryService the {@link UserQueryService} user query service
     */
    public IamContextFacadeImpl(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    // inherited javadoc
    @Override
    public Long createUser(String email, String username, String password) {
        var signUpCommand = new SignUpCommand(email, username, password, List.of(Role.getDefaultRole()));
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    // inherited javadoc
    @Override
    public Long createUser(String email, String username, String password, List<String> roleNames) {
        var roles = roleNames == null ? new ArrayList<Role>() : roleNames.stream().map(Role::toRoleFromName).toList();
        var signUpCommand = new SignUpCommand(email, username, password, roles);
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    // inherited javadoc
    @Override
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    @Override
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

    @Override
    public Boolean checkProfileExistsByUserId(Long userId) {
        var getUserByIdQuery = new  GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        return result.isPresent();
    }
}
