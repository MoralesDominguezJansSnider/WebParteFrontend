package pe.ucv.ucvbackend.persistence.repository;

import pe.ucv.ucvbackend.persistence.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidenciaJpaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByAreaContainingIgnoreCase(String area);
    List<Incidencia> findByNivelPrioridad(String nivelPrioridad);
    List<Incidencia> findByUsuarioId(Long usuarioId);
    List<Incidencia> findByCategoriaId(Long categoriaId);
    List<Incidencia> findByDepartamentoId(Long departamentoId);
}
