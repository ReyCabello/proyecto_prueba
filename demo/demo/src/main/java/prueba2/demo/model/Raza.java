package prueba2.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "razas")

public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank( message = "El nombre es obligatorio")
    @Column( nullable = false, length = 50 )
    private String nombre;

    @NotBlank( message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String origen;

    @Column(length = 300)
    private String descripcion;

    @OneToMany(mappedBy = "raza")
    private List<Caballo> caballo;
}