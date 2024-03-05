package goalzone.service;

import goalzone.model.Game;
import goalzone.model.Team;
import goalzone.repository.AverageUserRepository;
import lombok.RequiredArgsConstructor;
import goalzone.model.AverageUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import goalzone.repository.GameRepository;
import goalzone.repository.TeamRepository;

import java.time.LocalDate;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class InitDbService {
    private final AverageUserRepository averageUserRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void initDb() {
        AverageUser averageUser1 = createAverageUser("viccelek", "viccelek", "Elek", "Vicc", LocalDate.of(2000, 01, 01));
        AverageUser averageUser2 = createAverageUser("viccelek2", "viccelek2", "Elek", "Vicc", LocalDate.of(2001, 02, 02));
        AverageUser averageUser3 = createAverageUser("viccelek3", "viccelek3", "Elek", "Vicc", LocalDate.of(2002, 03, 03));
        AverageUser averageUser4 = createAverageUser("viccelek4", "viccelek4", "Elek", "Vicc", LocalDate.of(2003, 04, 04));
        Team team1 = createTeam("FC Barcelona");
        Team team2 = createTeam("Real Madrid");
        Game game1 = createGame(team1, team2);
    }

    private AverageUser createAverageUser(String username, String password, String firstName, String lastName, LocalDate birthDate) {
        return averageUserRepository.save(AverageUser.builder().username(username).password(password).firstName(firstName).lastName(lastName).birthDate(birthDate).build());
    }

    private Team createTeam(String name) {
        return teamRepository.save(Team.builder().name(name).build());
    }

    private Game createGame(Team homeTeam, Team awayTeam) {
        return gameRepository.save(Game.builder().teams(Arrays.asList(homeTeam, awayTeam)).build());
    }

    public void clearDb() {
        averageUserRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
        gameRepository.deleteAllInBatch();
    }
}
