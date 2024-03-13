package goalzone.mapping;

import goalzone.dto.AverageUserDto;
import goalzone.model.AverageUser;
import goalzone.model.Team;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T11:15:40+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class AverageUserMapperImpl implements AverageUserMapper {

    @Override
    public AverageUserDto averageUserToDto(AverageUser averageUser) {
        if ( averageUser == null ) {
            return null;
        }

        AverageUserDto averageUserDto = new AverageUserDto();

        averageUserDto.setId( averageUser.getId() );
        averageUserDto.setUsername( averageUser.getUsername() );
        averageUserDto.setPassword( averageUser.getPassword() );
        averageUserDto.setFirstName( averageUser.getFirstName() );
        averageUserDto.setLastName( averageUser.getLastName() );
        averageUserDto.setBirthDate( averageUser.getBirthDate() );
        List<Team> list = averageUser.getFavourites();
        if ( list != null ) {
            averageUserDto.setFavourites( new ArrayList<Team>( list ) );
        }

        return averageUserDto;
    }

    @Override
    public AverageUser dtoToAverageUser(AverageUserDto averageUserDto) {
        if ( averageUserDto == null ) {
            return null;
        }

        AverageUser.AverageUserBuilder<?, ?> averageUser = AverageUser.builder();

        averageUser.username( averageUserDto.getUsername() );
        averageUser.password( averageUserDto.getPassword() );
        averageUser.firstName( averageUserDto.getFirstName() );
        averageUser.lastName( averageUserDto.getLastName() );
        averageUser.birthDate( averageUserDto.getBirthDate() );
        List<Team> list = averageUserDto.getFavourites();
        if ( list != null ) {
            averageUser.favourites( new ArrayList<Team>( list ) );
        }
        averageUser.id( averageUserDto.getId() );

        return averageUser.build();
    }
}
