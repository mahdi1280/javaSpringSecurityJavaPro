package ir.javapro.springsecurityjavapro.repository;

import ir.javapro.springsecurityjavapro.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
