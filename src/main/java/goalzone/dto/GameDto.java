package goalzone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import goalzone.model.Championship;
import goalzone.model.Team;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameDto {
    private int id;
    private String homeTeamName;
    private String awayTeamName;
    @JsonIgnore
    private List<Team> teams;
    private int homeScore;
    private int awayScore;
    @JsonIgnore
    private ChampionshipDto championship;
    private String championshipName;
}
