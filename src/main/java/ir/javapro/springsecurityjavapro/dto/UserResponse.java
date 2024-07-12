package ir.javapro.springsecurityjavapro.dto;

import ir.javapro.springsecurityjavapro.model.Role;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;


@Builder
public record UserResponse(int id, String username, List<Role> role) implements Serializable {

}
