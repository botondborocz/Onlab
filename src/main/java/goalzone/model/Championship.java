package goalzone.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Championship {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private List<Team> teams;

    @OneToMany
    private List<Game> games;

    public void addTeam(Team team) {
        if (teams == null) {
            teams = new ArrayList<>();
        }
        teams.add(team);
    }

    public void addGame(Game game) {
        if (games == null) {
            games = new ArrayList<>();
        }
        games.add(game);
    }

    public void deleteGame(Game game) {
        games.remove(game);
    }
}
