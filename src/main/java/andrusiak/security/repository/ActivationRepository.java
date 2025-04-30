package andrusiak.security.repository;

import andrusiak.security.domain.model.Activation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationRepository extends JpaRepository<Activation, Long> {
    Optional<Activation> findByKey(String key);

}
