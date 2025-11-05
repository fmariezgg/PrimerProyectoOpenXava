package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity // Marca la clase como entidad JPA
@Getter @Setter
@View(name="Simple", // Esta vista solo se usará cuando se especifique "Simple"
        members="numero, nombre" // Muestra únicamente numero y nombre en la misma línea
)
public class Cliente {

    @Id // La propiedad numero es la clave
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Hidden
    private int numero;

    @Column(length = 50) // Longitud para la columna nombre
    @Required // Validación de OpenXava: campo obligatorio
    private String nombre;

    @Embedded // Así para referenciar a una clase incrustable
    Direccion direccion; // Una referencia Java convencional

}
