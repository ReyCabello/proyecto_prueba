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
@Table(name = "Entrenador")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false, name = "años_experiencia")
    private Integer anos_exp;


    private String comentario;

    @OneToMany(mappedBy = "entrenador")
    private List<Caballo> caballos;

}
