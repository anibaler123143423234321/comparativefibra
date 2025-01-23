package api.codesoft.com.comparativefibra.repository;

import api.codesoft.com.comparativefibra.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    List<Oferta> findByCodigoPostal(String codigoPostal);
    List<Oferta> findByProveedor(String proveedor); // Nuevo método

}

