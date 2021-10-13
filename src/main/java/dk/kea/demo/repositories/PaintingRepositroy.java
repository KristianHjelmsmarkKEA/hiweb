package dk.kea.demo.repositories;

import dk.kea.demo.models.Painting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepositroy extends JpaRepository<Painting, Long> {
}
