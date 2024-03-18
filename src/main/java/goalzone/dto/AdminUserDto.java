package goalzone.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdminUserDto {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
