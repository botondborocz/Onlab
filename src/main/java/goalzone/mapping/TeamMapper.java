package goalzone.mapping;

import goalzone.dto.TeamDto;
import goalzone.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(target = "teamName", source = "name")
    TeamDto teamToDto(Team team);
    Team dtoToTeam(TeamDto teamDto);
    @Mapping(target = "teamName", source = "name")
    List<TeamDto> teamsToDtos(List<Team> teams);
}
