package ir.javapro.springsecurityjavapro.config;

import ir.javapro.springsecurityjavapro.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
