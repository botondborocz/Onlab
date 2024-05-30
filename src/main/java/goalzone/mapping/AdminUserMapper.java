package goalzone.mapping;

import goalzone.dto.AdminUserDto;
import goalzone.model.AdminUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminUserMapper {
    AdminUserDto adminUserToDto(AdminUser adminUser);

    AdminUser dtoToAdminUser(AdminUserDto adminUserDto);
}
