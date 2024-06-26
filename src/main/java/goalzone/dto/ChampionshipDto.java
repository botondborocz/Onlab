package goalzone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import goalzone.model.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChampionshipDto {
    private int id;

    private String name;

    @JsonIgnore
    private List<Team> teams;
}
