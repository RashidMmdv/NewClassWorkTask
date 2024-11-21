package az.writhline.product.mapper;


import az.writhline.product.Dto.UserDto;
import az.writhline.product.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper {

    User dtoToUser(UserDto dto);
}
