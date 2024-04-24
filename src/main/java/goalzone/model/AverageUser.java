package goalzone.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AverageUser extends Person {
    @Id
    @GeneratedValue
    private int id;
    @ManyToMany
    private List<Team> favorites;

    public void addFavorite(Team team) {
        favorites.add(team);
    }

    public void removeFavorite(Team team) {
        favorites.remove(team);
    }
}
