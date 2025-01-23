package api.codesoft.com.comparativefibra.service;

import api.codesoft.com.comparativefibra.model.Oferta;
import api.codesoft.com.comparativefibra.repository.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    // Método para insertar una única oferta
    public Oferta insertarOferta(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    // Método para insertar una lista de ofertas (múltiples ofertas)
    public List<Oferta> insertarOfertas(List<Oferta> ofertas) {
        return ofertaRepository.saveAll(ofertas);
    }

    // Método para obtener ofertas por código postal
    public List<Oferta> obtenerOfertasPorCodigoPostal(String codigoPostal) {
        return ofertaRepository.findByCodigoPostal(codigoPostal);
    }

    // Método para obtener las 3 ofertas más baratas
    public List<Oferta> obtenerOfertasMasBaratas() {
        // Obtener todas las ofertas
        List<Oferta> todasOfertas = ofertaRepository.findAll();

        // Ordenar las ofertas por precio (convertido a Double) y seleccionar las 3 más baratas
        List<Oferta> tresMasBaratas = todasOfertas.stream()
                .sorted((o1, o2) -> {
                    // Limpiar y convertir el precio a Double para comparar
                    Double precio1 = limpiarYConvertirPrecio(o1.getPrecio());  // Usamos el getter para acceder a 'precio'
                    Double precio2 = limpiarYConvertirPrecio(o2.getPrecio());  // Usamos el getter para acceder a 'precio'
                    return precio1.compareTo(precio2);  // Comparamos los precios
                })
                .limit(3)  // Limitamos a las tres más baratas
                .collect(Collectors.toList());

        return tresMasBaratas;
    }

    // Método para limpiar y convertir el precio
    private Double limpiarYConvertirPrecio(String precio) {
        if (precio != null) {
            // Eliminar el símbolo de euro (€) y convertir la coma (,) en punto (.)
            precio = precio.replace("€", "").replace(",", ".");
            try {
                return Double.parseDouble(precio);  // Intentar convertir a Double
            } catch (NumberFormatException e) {
                // Si no se puede convertir, retornar un valor por defecto
                return Double.MAX_VALUE;  // Asegura que este valor no se considere al ordenar
            }
        }
        return Double.MAX_VALUE;  // Si el precio es nulo, también lo excluimos de la comparación
    }

    // Nuevo método para obtener ofertas por proveedor
    public List<Oferta> obtenerOfertasPorProveedor(String proveedor) {
        return ofertaRepository.findByProveedor(proveedor);
    }

    // Método para obtener ofertas paginadas
    public Page<Oferta> obtenerOfertasPaginadas(Pageable pageable) {
        return ofertaRepository.findAll(pageable);
    }
}
