package pe.ucv.ucvbackend.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.ucv.ucvbackend.domain.Category;
import pe.ucv.ucvbackend.persistence.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "id", target = "categoryId"),
            @Mapping(source = "category", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "priorityLevel", target = "priorityLevel"),
            @Mapping(source = "registeredDate", target = "registeredDate"),
            @Mapping(source = "type", target = "type")
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    Categoria toCategoria(Category category);
}