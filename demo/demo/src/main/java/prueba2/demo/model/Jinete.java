package prueba2.demo.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "este campo es obligatorio")
    @Column(nullable = false)
    private Integer años_experiencia;

    @Column(nullable = true, length = 300)
    private String comentario;

}
