package pe.ucv.ucvbackend.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.ucv.ucvbackend.domain.Incident;
import pe.ucv.ucvbackend.persistence.entity.Incidencia;

@Mapper(componentModel = "spring")
public interface IncidentMapper {

    @Mapping(source = "id", target = "incidentId")
    @Mapping(source = "fechaIncidencia", target = "incidentDate")
    @Mapping(source = "nivelPrioridad", target = "priorityLevel")
    @Mapping(source = "fechaRegistro", target = "registeredDate")
    @Mapping(source = "usuarioRegistro", target = "registeredUser")
    @Mapping(source = "categoriaId", target = "categoryId")
    @Mapping(source = "departamentoId", target = "departmentId")
    @Mapping(source = "usuarioId", target = "userId")
    Incident toIncident(Incidencia incidencia);

    @Mapping(source = "incidentId", target = "id")
    @Mapping(source = "incidentDate", target = "fechaIncidencia")
    @Mapping(source = "priorityLevel", target = "nivelPrioridad")
    @Mapping(source = "registeredDate", target = "fechaRegistro")
    @Mapping(source = "registeredUser", target = "usuarioRegistro")
    @Mapping(source = "categoryId", target = "categoriaId")
    @Mapping(source = "departmentId", target = "departamentoId")
    @Mapping(source = "userId", target = "usuarioId")
    Incidencia toIncidencia(Incident incident);
}