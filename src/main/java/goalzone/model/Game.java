package goalzone.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue
    private int id;
    @ManyToMany
    private List<Team> teams;
    private LocalDate date;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamId;
    private int awayTeamId;
    private int homeScore;
    private int awayScore;
    @ManyToOne
    private Championship championship;
    private int champId;
    @ManyToMany
    private List<Player> homeScorers;
    @ManyToMany
    private List<Player> awayScorers;
    private boolean homeFavorite;
    private boolean awayFavorite;
    public void addHomeScorer(Player player) {
        homeScorers.add(player);
    }

    public void addAwayScorer(Player player) {
        awayScorers.add(player);
    }
}

