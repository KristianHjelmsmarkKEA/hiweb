package dk.kea.demo.repositories;

import dk.kea.demo.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
