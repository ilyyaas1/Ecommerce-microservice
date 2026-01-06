package org.ilyass.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Customer {
    private String id;

    @NotNull(message = "firstname is required")
    private String firstName;
    @NotNull(message = "lastname is required")
    private String lastName;
    @NotNull(message = "email is required")
    @Email
    private String email;


}
