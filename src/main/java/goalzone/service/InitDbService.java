package goalzone.service;

import goalzone.model.*;
import goalzone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class InitDbService {
    private final AverageUserRepository averageUserRepository;
    private final AdminUserRepository adminUserRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final ChampionshipRepository championshipRepository;

    @Transactional
    public void initDb() {
        AdminUser adminUser1 = createAdminUser("admin", "admin", "Admin", "Admin", LocalDate.of(1999, 12, 12));
        AverageUser averageUser1 = createAverageUser("viccelek", "viccelek", "Elek", "Vicc", LocalDate.of(2000, 01, 01));
        AverageUser averageUser2 = createAverageUser("viccelek2", "viccelek2", "Elek", "Vicc", LocalDate.of(2001, 02, 02));
        AverageUser averageUser3 = createAverageUser("viccelek3", "viccelek3", "Elek", "Vicc", LocalDate.of(2002, 03, 03));
        AverageUser averageUser4 = createAverageUser("viccelek4", "viccelek4", "Elek", "Vicc", LocalDate.of(2003, 04, 04));

        Team team1 = createTeam("FC Barcelona");
        Team team2 = createTeam("Real Madrid");
        Team team3 = createTeam("Manchester City");
        Team team4 = createTeam("Liverpool");
        Team team5 = createTeam("AC Milan");
        Team team6 = createTeam("Juventus");

        Championship championship1 = createChampionship("La Liga");
        championship1.addTeam(team1);
        championship1.addTeam(team2);
        Championship championship2 = createChampionship("Premier League");
        championship2.addTeam(team3);
        championship2.addTeam(team4);
        Championship championship3 = createChampionship("Serie A");
        championship3.addTeam(team5);
        championship3.addTeam(team6);

        Game game1 = createGame(team1, team2, championship1);
        game1.setHomeScore(4);
        game1.setAwayScore(1);
        Game game2 = createGame(team3, team4, championship2);
        Game game3 = createGame(team5, team6, championship3);

        championship1.addGame(game1);
        championship2.addGame(game2);
        championship3.addGame(game3);
    }

    private AverageUser createAverageUser(String username, String password, String firstName, String lastName, LocalDate birthDate) {
        return averageUserRepository.save(AverageUser.builder().username(username).password(password).firstName(firstName).lastName(lastName).birthDate(birthDate).build());
    }

    private AdminUser createAdminUser(String username, String password, String firstName, String lastName, LocalDate birthDate) {
        return adminUserRepository.save(AdminUser.builder().username(username).password(password).firstName(firstName).lastName(lastName).birthDate(birthDate).build());
    }

    private Team createTeam(String name) {
        return teamRepository.save(Team.builder().name(name).build());
    }

    private Game createGame(Team homeTeam, Team awayTeam, Championship championship) {
        return gameRepository.save(Game.builder().teams(Arrays.asList(homeTeam, awayTeam))
                .homeTeamName(homeTeam.getName()).awayTeamName(awayTeam.getName()).championship(championship)
                .championshipName(championship.getName()).build());
    }

    private Championship createChampionship(String name) {
        return championshipRepository.save(Championship.builder().name(name).build());
    }

    public void clearDb() {
        adminUserRepository.deleteAllInBatch();
        averageUserRepository.deleteAllInBatch();
        gameRepository.deleteAllInBatch();
        championshipRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }
}
