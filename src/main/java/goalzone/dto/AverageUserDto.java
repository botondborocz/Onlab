package goalzone.dto;

import goalzone.model.Team;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AverageUserDto {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @ManyToMany
    private List<Team> favourites;
}
