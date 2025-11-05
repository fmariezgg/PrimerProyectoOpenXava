package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Getter @Setter
@View(members =
        "numero; descripcion, categoria; precio; autor; fotos; observaciones"
)
public class Producto {
    @Id
    private int numero;

    @Column(length=50) @Required
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Categoria categoria;

    @Money
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Autor autor;

    @Files
    @Column(length=32)
    private String fotos;

    @TextArea
    private String observaciones;
}