package andrusiak.security.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "activation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "key")
    private String key;
    @Column(name = "user_id")
    private Long userId;

    public Activation(String key, Long userId) {
        this.key = key;
        this.userId = userId;
    }
}
