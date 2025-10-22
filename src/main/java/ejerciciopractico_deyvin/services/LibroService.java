package ejerciciopractico_deyvin.services;

import ejerciciopractico_deyvin.domain.Libro;
import ejerciciopractico_deyvin.repository.LibroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Transactional(readOnly = true)
    public List<Libro> getLibros(boolean disponible) {
        if (disponible) {
            return libroRepository.findByDisponibleTrue();
        }
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Libro> getLibro(Long idLibro) {
        return libroRepository.findById(idLibro);
    }

    @Transactional
    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    @Transactional
    public void delete(Long idLibro) {
        if (!libroRepository.existsById(idLibro)) {
            throw new IllegalArgumentException("El libro con ID " + idLibro + " no existe.");
        }
        try {
            libroRepository.deleteById(idLibro);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el libro. Tiene datos asociados.", e);
        }
    }
}