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
public class Championship {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private List<Team> teams;

    //private Map<>;
}
