package model;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany
    private List<Player> players;

    @OneToMany
    private List<Game> games;
}
