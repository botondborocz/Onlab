package goalzone.model;

import jakarta.persistence.*;
import lombok.*;

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
    private String homeTeamName;
    private String awayTeamName;
    private int homeScore;
    private int awayScore;
    @ManyToOne
    private Championship championship;
    private String championshipName;
}

