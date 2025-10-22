package ejerciciopractico_deyvin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCategoria;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;

    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    @Column(nullable = false)
    private boolean activo;
}
