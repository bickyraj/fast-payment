package com.bicky.demopayment.userservice.user.entrypoint.rest.response;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
public class GetUserResponseBody {
    private Long id;
    private String email;
}
