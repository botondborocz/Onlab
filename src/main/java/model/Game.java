package model;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
}

