package ejerciciopractico_deyvin.repository;

import ejerciciopractico_deyvin.domain.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    List<Libro> findByDisponibleTrue();

}