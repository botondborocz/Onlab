package goalzone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import goalzone.model.Game;
import goalzone.model.Team;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PlayerDto {
    private int id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int goals;
    private int assists;
    @JsonIgnore
    private Team team;
    @JsonIgnore
    private List<Game> homeGames;
    @JsonIgnore
    private List<Game> awayGames;
}
