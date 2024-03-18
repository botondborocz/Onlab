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

    //private Map<>;

    public void addTeam(Team team) {
        if (teams == null) {
            teams = new ArrayList<Team>();
        }
        teams.add(team);
    }

    public void addGame(Game game) {
        if (games == null) {
            games = new ArrayList<Game>();
        }
        games.add(game);
    }
}
