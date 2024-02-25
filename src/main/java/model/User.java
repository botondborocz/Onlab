package model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue
    private int id;

    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    @ManyToMany
    private List<Team> favourites; // TODO kell Fav class???

}
