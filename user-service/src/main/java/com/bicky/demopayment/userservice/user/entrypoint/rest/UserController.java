package com.bicky.demopayment.userservice.user.entrypoint.rest;

import com.bicky.demopayment.userservice.user.application.GetUserUseCase;
import com.bicky.demopayment.userservice.user.application.RegisterUserUseCase;
import com.bicky.demopayment.userservice.user.entrypoint.rest.response.GetUserResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserUseCase getUserUseCase;

    @GetMapping("/me")
    @PreAuthorize("permitAll()")
    public ResponseEntity<GetUserResponseBody> me() {
        GetUserUseCase.Response response = getUserUseCase.execute();
        return new ResponseEntity<>(
                GetUserResponseBody.of(response.getUser().getId(), response.getUser().getEmail()),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public Boolean register(@RequestBody RegisterUserRequestBody requestBody) {
        return registerUserUseCase.execute(RegisterUserUseCase.Request.of(requestBody))
                .getSuccess();
    }
}
