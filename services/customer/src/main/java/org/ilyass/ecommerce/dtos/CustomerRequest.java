package org.ilyass.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @NotNull
    String id;
    @NotNull(message = "Customer firstname is required")
    private String firstName;
    @NotNull(message = "Customer lastname is required")
    private String lastName;
    @NotNull(message = "Customer email is required")
    @Email(message = "invalid format of email")
    private String email;

    private String phone;
    private String address;
}
