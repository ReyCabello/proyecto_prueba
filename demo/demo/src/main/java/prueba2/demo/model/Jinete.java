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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "jinetes")

public class Jinete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "este campo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private Integer edad;

    @NotNull(message = "este campo es obligatorio")
    @Column(nullable = false)
    private Integer anosExp;

    
    private String titulos;

    @Column(length = 300)
    private String comentario;

    @OneToMany(mappedBy = "jinete")
    private List<Carrera> carreras;

}
