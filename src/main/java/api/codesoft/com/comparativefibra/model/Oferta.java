package api.codesoft.com.comparativefibra.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oferta")
public class Oferta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "proveedor")
    @JsonProperty("proveedor")
    private String proveedor;

    @Column(name = "nombre")
    @JsonProperty("nombre")
    private String nombre;

    @Column(name = "velocidad")
    @JsonProperty("velocidad")
    private String velocidad;

    @Column(name = "precio")
    @JsonProperty("precio")
    private String precio;

    @Column(name = "permanencia")
    @JsonProperty("permanencia")
    private String permanencia;

    @Column(name = "descuento")
    @JsonProperty("descuento")
    private String descuento;

    @Column(name = "cuota_mensual")
    @JsonProperty("cuota_mensual")
    private String cuotaMensual;

    @Column(name = "imagen_proveedor")
    @JsonProperty("imagen_proveedor")
    private String imagenProveedor;

    @Column(name = "codigo_postal")
    @JsonProperty("codigo_postal")
    private String codigoPostal;

    public String getPrecio() {
        return precio;
    }

}
