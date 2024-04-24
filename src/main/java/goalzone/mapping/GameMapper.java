package goalzone.mapping;

import goalzone.dto.GameDto;
import goalzone.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "date", source = "date")
    GameDto gameToDto(Game game);
    @Mapping(target = "championshipName", source = "championshipName")
    @Mapping(target = "homeFavorite", source = "homeFavorite")
    @Mapping(target = "awayFavorite", source = "awayFavorite")
    @Mapping(target = "homeTeamId", source = "homeTeamId")
    @Mapping(target = "awayTeamId", source = "awayTeamId")
    Game dtoToGame(GameDto gameDto);
    List<GameDto> gamesToDtos(List<Game> games);

}
