package ir.javapro.springsecurityjavapro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserSaveRequest(
        @NotEmpty @Size(min = 5) String username, @NotEmpty @Size(min = 8) String password) {

}
