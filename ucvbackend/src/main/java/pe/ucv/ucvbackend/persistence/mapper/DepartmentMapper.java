package pe.ucv.ucvbackend.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.ucv.ucvbackend.domain.Department;
import pe.ucv.ucvbackend.persistence.entity.Departamento;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mappings({
            @Mapping(source = "id", target = "departmentId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "classroom", target = "classroom"),
            @Mapping(source = "floor", target = "floor"),
            @Mapping(source = "tower", target = "tower"),
            @Mapping(source = "registeredDate", target = "registeredDate"),
            @Mapping(source = "registeredUser", target = "registeredUser")
    })
    Department toDepartment(Departamento departamento);

    @InheritInverseConfiguration
    Departamento toDepartamento(Department department);
}