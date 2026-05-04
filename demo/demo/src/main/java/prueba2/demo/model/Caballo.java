package prueba2.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "caballos")

public class Caballo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

    @NotBlank ( message = "El nombre es obligatorio y debe tener como maximo 50 caracteres")
    @Column (nullable = false, length = 50)
    private String nombre;

    @NotBlank( message = "Este campo es obligatorio")
    @NotNull (message = "No puede ser nulo")
    @Column (nullable = false)
    private Integer edad;

    



}
