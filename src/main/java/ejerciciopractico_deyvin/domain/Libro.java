package ejerciciopractico_deyvin.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "libro")
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío.")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres.")
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío.")
    @Size(max = 200, message = "El nombre del autor no puede tener más de 200 caracteres.")
    private String autor;

    @Size(max = 20, message = "El ISBN no puede tener más de 20 caracteres.")
    private String isbn;

    @Lob
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    private Boolean disponible = true;

    @DecimalMin(value = "0.00", inclusive = true, message = "El precio no puede ser negativo.")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener hasta 2 decimales.")
    private BigDecimal precio;
}