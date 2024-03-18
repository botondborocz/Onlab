package goalzone.mapping;

import goalzone.dto.AdminUserDto;
import goalzone.dto.AverageUserDto;
import goalzone.model.AdminUser;
import goalzone.model.AverageUser;
import org.mapstruct.Mapper;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Mapper(componentModel = "spring")
public interface AdminUserMapper {
    AdminUserDto adminUserToDto(AdminUser adminUser);

    AdminUser dtoToAdminUser(AdminUserDto adminUserDto);
}
