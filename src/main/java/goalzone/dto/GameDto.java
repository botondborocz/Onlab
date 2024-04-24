package goalzone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import goalzone.model.Championship;
import goalzone.model.Team;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GameDto {
    private int id;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamId;
    private int awayTeamId;
    @JsonIgnore
    private List<Team> teams;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private int homeScore;
    private int awayScore;
    @JsonIgnore
    private ChampionshipDto championship;
    private String championshipName;
    private boolean homeFavorite;
    private boolean awayFavorite;
}
