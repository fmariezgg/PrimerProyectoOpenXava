package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity // Marca la clase como entidad JPA
@Getter @Setter
public class Cliente {

    @Id // La propiedad numero es la clave
    private int numero;

    @Column(length = 50) // Longitud para la columna nombre
    @Required // Validación de OpenXava: campo obligatorio
    private String nombre;

    @Embedded // Así para referenciar a una clase incrustable
    Direccion direccion; // Una referencia Java convencional

}
