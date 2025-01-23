package api.codesoft.com.comparativefibra.controller;

import api.codesoft.com.comparativefibra.model.Oferta;
import api.codesoft.com.comparativefibra.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "https://tufibrarapida.es",
        "https://tufibrarapida.com",
        "http://localhost:4321"
})
@RequestMapping("/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    // Endpoint para insertar una oferta
    @PostMapping("/insertar")
    public Oferta insertarOferta(@RequestBody Oferta oferta) {
        return ofertaService.insertarOferta(oferta);
    }

    // Endpoint para insertar una lista de ofertas
    @PostMapping("/inserts")
    public List<Oferta> insertarOfertas(@RequestBody List<Oferta> ofertas) {
        return ofertaService.insertarOfertas(ofertas);  // Llamamos al servicio para insertar todas las ofertas
    }


    // Endpoint para filtrar ofertas por código postal
    @GetMapping("/filtro/{codigoPostal}")
    public List<Oferta> obtenerOfertasPorCodigoPostal(@PathVariable String codigoPostal) {
        return ofertaService.obtenerOfertasPorCodigoPostal(codigoPostal);  // Llamamos al servicio para obtener las ofertas filtradas
    }

    // Endpoint para obtener las tres ofertas más baratas
    @GetMapping("/ofertasMasBaratas")
    public List<Oferta> obtenerOfertasMasBaratas() {
        return ofertaService.obtenerOfertasMasBaratas();
    }

}

