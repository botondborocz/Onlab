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
public class Player {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int goals;
    private int assists;
    @ManyToOne
    private Team team;
    @ManyToMany
    private List<Game> homeGames;
    @ManyToMany
    private List<Game> awayGames;
}
