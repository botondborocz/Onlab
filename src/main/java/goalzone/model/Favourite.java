package goalzone.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favourite {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne // ???
    private Team team;
}
