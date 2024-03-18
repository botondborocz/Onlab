package goalzone.mapping;

import goalzone.dto.GameDto;
import goalzone.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDto gameToDto(Game game);
    @Mapping(target = "championshipName", source = "championshipName")
    Game dtoToGame(GameDto gameDto);
    List<GameDto> gamesToDtos(List<Game> games);

}
