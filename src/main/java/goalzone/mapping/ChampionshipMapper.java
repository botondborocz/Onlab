package goalzone.mapping;

import goalzone.dto.ChampionshipDto;
import goalzone.dto.GameDto;
import goalzone.model.Championship;
import goalzone.model.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChampionshipMapper {
    ChampionshipDto championshipToDto(Championship championship);
    Championship dtoToChampionship(ChampionshipDto championshipDto);
    List<ChampionshipDto> championshipsToDtos(List<Championship> championships);
}
