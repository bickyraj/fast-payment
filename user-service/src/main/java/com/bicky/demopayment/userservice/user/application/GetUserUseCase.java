package com.bicky.demopayment.userservice.user.application;

import com.bicky.demopayment.userservice.user.domain.entity.UserEntity;
import com.bicky.demopayment.userservice.user.infrastructure.services.UserService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetUserUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final UserEntity user;
    }

    private final UserService userService;

    public Response execute() {
        return Response.of(userService.getCurrentUser());
    }
}
