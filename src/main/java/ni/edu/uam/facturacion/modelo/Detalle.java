package ni.edu.uam.facturacion.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DETALLES")
@Getter @Setter
public class Detalle {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String oid;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Producto producto;

    private int cantidad;

    @Money
    private BigDecimal precio;

    @Transient
    public BigDecimal getSubtotal() {
        if (precio == null) return BigDecimal.ZERO;
        return precio.multiply(new BigDecimal(cantidad));
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTURA_OID")
    private Factura factura;
}