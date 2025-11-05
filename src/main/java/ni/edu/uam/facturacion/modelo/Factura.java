package ni.edu.uam.facturacion.modelo;
import java.time.*;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import ni.edu.uam.facturacion.calculadores.CalculadorSiguienteNumeroParaAnyo;
import lombok.*;

@Entity @Getter @Setter
@View(members= // Esta vista no tiene nombre, por tanto será la vista usada por defecto
        "anyo, numero, fecha;" + // Separados por coma significa en la misma línea
                "cliente;" + // Punto y coma significa nueva línea
                "detalles;" +
                "observaciones"
)

public class Factura {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    private String oid;

    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Año actual
    private int anyo;

    @Column(length=6)
    @DefaultValueCalculator(value=CalculadorSiguienteNumeroParaAnyo.class,
            properties=@PropertyValue(name="anyo")
    )
    private int numero;

    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    private LocalDate fecha;

    @TextArea
    private String observaciones;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @ReferenceView("Simple") // La vista llamada 'Simple' se usará para visualizar esta referencia
    Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ListProperties("producto.numero, producto.descripcion, cantidad")
    private List<Detalle> detalles;
}