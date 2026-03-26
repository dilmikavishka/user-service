package lk.ijse.userservice.mapper;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(UserDTO dto, @MappingTarget User user);
}