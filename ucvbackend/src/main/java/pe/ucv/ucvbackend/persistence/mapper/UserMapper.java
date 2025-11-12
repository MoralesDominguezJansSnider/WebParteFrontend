package pe.ucv.ucvbackend.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.ucv.ucvbackend.domain.User;
import pe.ucv.ucvbackend.persistence.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "firstname", target = "firstName"),
            @Mapping(source = "lastname", target = "lastName"),
            @Mapping(source = "nickname", target = "username"),
            @Mapping(source = "role", target = "userRole"),
            @Mapping(source = "cargo", target = "position"),
            @Mapping(source = "active", target = "isActive"),
    })
    User toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(User user);
}