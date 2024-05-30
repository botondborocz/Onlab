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
public class Team {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private List<Championship> championships;

    @OneToMany (mappedBy = "team")
    private List<Player> players;

    @ManyToMany (mappedBy = "teams")
    private List<Game> games;

    @ManyToMany (mappedBy = "favorites")
    private List<AverageUser> favoriteForUsers;

    public void addPlayer(Player player) {
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
    }
}
