package ir.javapro.springsecurityjavapro.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private boolean expired;
    private boolean revoked;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationAt;
    @ManyToOne
    private User user;


}
