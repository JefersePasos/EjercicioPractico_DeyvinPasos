package ejerciciopractico_deyvin.repository;

import ejerciciopractico_deyvin.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}