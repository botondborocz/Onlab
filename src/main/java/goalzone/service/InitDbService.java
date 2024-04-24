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
    private final PlayerRepository playerRepository;

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

        Game game1 = createGame(team1, team2, championship1, LocalDate.now());
        Game game2 = createGame(team2, team1, championship1, LocalDate.now().plusDays(2));
        game1.setHomeScore(4);
        game1.setAwayScore(1);
        Game game3 = createGame(team3, team4, championship2, LocalDate.now().minusDays(1));
        Game game4 = createGame(team5, team6, championship3, LocalDate.now().minusDays(2));

        championship1.addGame(game1);
        championship1.addGame(game2);
        championship2.addGame(game3);
        championship3.addGame(game4);

        Player player1 = createPlayer("Robert", "Lewandowski", team1);
        Player player2 = createPlayer("Joao", "Felix", team1);
        Player player3 = createPlayer("Jude", "Bellingham", team2);

        team1.addPlayer(player1);
        team1.addPlayer(player2);
        team2.addPlayer(player3);

        game1.setHomeScorers(Arrays.asList(player1, player1, player1, player2));
        game1.setAwayScorers(Arrays.asList(player3));
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

    private Game createGame(Team homeTeam, Team awayTeam, Championship championship, LocalDate date) {
        return gameRepository.save(Game.builder().teams(Arrays.asList(homeTeam, awayTeam))
                .homeTeamName(homeTeam.getName()).awayTeamName(awayTeam.getName())
                .homeTeamId(homeTeam.getId()).awayTeamId(awayTeam.getId())
                .championship(championship)
                .championshipName(championship.getName()).date(date).build());
    }

    private Championship createChampionship(String name) {
        return championshipRepository.save(Championship.builder().name(name).build());
    }

    private Player createPlayer(String firstName, String lastName, Team team) {
        return playerRepository.save(Player.builder().firstName(firstName).lastName(lastName).team(team).build());
    }

    public void clearDb() {
        adminUserRepository.deleteAllInBatch();
        averageUserRepository.deleteAllInBatch();
        gameRepository.deleteAllInBatch();
        championshipRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }
}
