package goalzone.dto;

import goalzone.model.Team;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AverageUserDto {
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    @ManyToMany
    private List<Team> favourites;
}
