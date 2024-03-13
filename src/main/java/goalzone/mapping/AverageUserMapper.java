package goalzone.mapping;

import goalzone.dto.AverageUserDto;
import goalzone.model.AverageUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AverageUserMapper {
    AverageUserDto averageUserToDto(AverageUser averageUser);

    AverageUser dtoToAverageUser(AverageUserDto averageUserDto);

}
