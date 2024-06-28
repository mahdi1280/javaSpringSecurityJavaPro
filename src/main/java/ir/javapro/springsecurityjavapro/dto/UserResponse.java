package ir.javapro.springsecurityjavapro.dto;

import lombok.Builder;

import java.io.Serializable;


@Builder
public record UserResponse(int id, String username, String role) implements Serializable {

}
