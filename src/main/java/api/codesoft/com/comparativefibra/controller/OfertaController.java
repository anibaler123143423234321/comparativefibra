package api.codesoft.com.comparativefibra.controller;

import api.codesoft.com.comparativefibra.model.Oferta;
import api.codesoft.com.comparativefibra.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    // Endpoint para insertar una oferta
    @PostMapping("/insertar")
    public Oferta insertarOferta(@RequestBody Oferta oferta) {
        System.out.println("API: /ofertas/insertar - Insertar oferta: " + oferta);
        Oferta result = ofertaService.insertarOferta(oferta);
        System.out.println("Respuesta: " + result);
        return result;
    }

    // Endpoint para insertar una lista de ofertas
    @PostMapping("/inserts")
    public List<Oferta> insertarOfertas(@RequestBody List<Oferta> ofertas) {
        System.out.println("API: /ofertas/inserts - Insertar lista de ofertas: " + ofertas);
        List<Oferta> result = ofertaService.insertarOfertas(ofertas);
        System.out.println("Respuesta: " + result);
        return result;
    }

    // Endpoint para filtrar ofertas por código postal
    @GetMapping("/filtro/{codigoPostal}")
    public List<Oferta> obtenerOfertasPorCodigoPostal(@PathVariable String codigoPostal) {
        System.out.println("API: /ofertas/filtro/" + codigoPostal + " - Filtrar ofertas por código postal");
        List<Oferta> result = ofertaService.obtenerOfertasPorCodigoPostal(codigoPostal);
        System.out.println("Respuesta: " + result);
        return result;
    }

    // Endpoint para obtener las tres ofertas más baratas
    @GetMapping("/ofertasMasBaratas")
    public List<Oferta> obtenerOfertasMasBaratas() {
        System.out.println("API: /ofertas/ofertasMasBaratas - Obtener las tres ofertas más baratas");
        List<Oferta> result = ofertaService.obtenerOfertasMasBaratas();
        System.out.println("Respuesta: " + result);
        return result;
    }

    // Endpoint para filtrar ofertas por proveedor
    @GetMapping("/proveedor/{proveedor}")
    public List<Oferta> obtenerOfertasPorProveedor(@PathVariable String proveedor) {
        System.out.println("API: /ofertas/proveedor/" + proveedor + " - Filtrar ofertas por proveedor");
        List<Oferta> result = ofertaService.obtenerOfertasPorProveedor(proveedor);
        System.out.println("Respuesta: " + result);
        return result;
    }

    // Endpoint para obtener ofertas paginadas
    @GetMapping("/todas")
    public Page<Oferta> obtenerOfertasPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        System.out.println("API: /ofertas/todas - Obtener ofertas paginadas: page=" + page + ", size=" + size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Oferta> result = ofertaService.obtenerOfertasPaginadas(pageable);
        System.out.println("Respuesta: " + result.getContent());
        return result;
    }
}
