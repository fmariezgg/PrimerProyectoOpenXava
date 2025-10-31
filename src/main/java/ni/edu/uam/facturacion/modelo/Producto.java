package ni.edu.uam.facturacion.modelo;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Getter @Setter
public class Producto {
    @Id
    private int numero;

    @Column(length=50) @Required
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY) // La referencia se almacena como una relación en la base de datos
    @DescriptionsList // Así la referencia se visualiza usando un combo
    private Categoria categoria; // Una referencia Java convencional

    @Money // La propiedad precio se usa para almacenar dinero
    private BigDecimal precio; // BigDecimal se suele usar para dinero

    @Files // Una galería de fotos completa está disponible
    @Column(length=32) // La cadena de 32 de longitud es para almacenar la clave de la galería
    private String fotos;

    @TextArea // Esto es para un texto grande, se usará un área de texto o equivalente
    private String observaciones;
}
