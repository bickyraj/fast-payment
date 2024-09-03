package com.bicky.demopayment.userservice.user.application;

import com.bicky.demopayment.userservice.user.entrypoint.rest.RegisterUserRequestBody;
import com.bicky.demopayment.userservice.user.infrastructure.services.UserService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterUserUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final RegisterUserRequestBody requestBody;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Boolean success;
    }

    private final UserService userService;

    public Response execute(Request request) {
        userService.saveUser(RegisterUserRequestBody.toEntity(request.requestBody));
        return Response.of(true);
    }
}
