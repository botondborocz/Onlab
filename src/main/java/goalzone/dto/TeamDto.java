package goalzone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import goalzone.model.Championship;
import goalzone.model.Game;
import goalzone.model.Player;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDto {
    private int id;
    private String teamName;
    @JsonIgnore
    private List<Championship> championships;
    @JsonIgnore
    private List<Player> players;
    @JsonIgnore
    private List<Game> games;
}
