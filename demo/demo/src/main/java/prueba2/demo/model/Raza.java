package prueba2.demo.model;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

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


}
