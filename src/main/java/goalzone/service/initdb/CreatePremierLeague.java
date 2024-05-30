package goalzone.service.initdb;

import goalzone.model.*;
import goalzone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CreatePremierLeague {
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void initPremierLeauge(){
        createTeams(championshipRepository.save(Championship.builder().name("Premier League").build()));
    }
    @Transactional
    public void createTeams(Championship championship){
        Team team1 = teamRepository.save(Team.builder().name("Manchester City").build());
        Team team2 = teamRepository.save(Team.builder().name("Manchester United").build());
        Team team3 = teamRepository.save(Team.builder().name("Arsenal").build());
        Team team4 = teamRepository.save(Team.builder().name("Liverpool").build());
        Team team5 = teamRepository.save(Team.builder().name("Chelsea").build());

        createManchesterCity(team1);
        championship.addTeam(team1);
        createManchesterUnited(team2);
        championship.addTeam(team2);
        createArsenal(team3);
        championship.addTeam(team3);
        createLiverpool(team4);
        championship.addTeam(team4);
        createChelsea(team5);
        championship.addTeam(team5);

        Game game1 = createGame(team1, team2, championship, LocalDate.now());
        Game game2 = createGame(team3, team2, championship, LocalDate.now().minusDays(1));
        Game game3 = createGame(team4, team1, championship, LocalDate.now().minusDays(2));
        Game game4 = createGame(team5, team1, championship, LocalDate.now().minusDays(3));
        Game game5 = createGame(team4, team2, championship, LocalDate.now().plusDays(1));
        Game game6 = createGame(team4, team3, championship, LocalDate.now().plusDays(2));
        Game game7 = createGame(team2, team5, championship, LocalDate.now().plusDays(3));
        championship.addGame(game1);
        championship.addGame(game2);
        championship.addGame(game3);
        championship.addGame(game4);
        championship.addGame(game5);
        championship.addGame(game6);
        championship.addGame(game7);
        game4.setHomeScore(0); game4.setAwayScore(2); game4.setAwayScorers(Arrays.asList(team1.getPlayers().get(0), team1.getPlayers().get(1)));
        game3.setHomeScore(2); game3.setAwayScore(0); game3.setHomeScorers(Arrays.asList(team4.getPlayers().get(0), team4.getPlayers().get(1)));
        game2.setHomeScore(1); game2.setAwayScore(1); game2.setHomeScorers(Arrays.asList(team3.getPlayers().get(3))); game2.setAwayScorers(Arrays.asList(team2.getPlayers().get(1)));

    }
    @Transactional
    public Game createGame(Team homeTeam, Team awayTeam, Championship championship, LocalDate date) {
        return gameRepository.save(Game.builder().teams(Arrays.asList(homeTeam, awayTeam))
                .homeTeamName(homeTeam.getName()).awayTeamName(awayTeam.getName())
                .homeTeamId(homeTeam.getId()).awayTeamId(awayTeam.getId())
                .championship(championship)
                .champId(championship.getId()).date(date).build());
    }
    @Transactional
    public void createManchesterCity(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Erling").lastName("Haaland").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Kevin").lastName("de Bruyne").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Julian").lastName("Alvarez").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Phil").lastName("Foden").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Bernardo").lastName("Silva").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createManchesterUnited(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Bruno").lastName("Fernandes").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Harry").lastName("Maguire").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Marcus").lastName("Rashford").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Raphael").lastName("Varane").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Christian").lastName("Eriksen").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createArsenal(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("William").lastName("Saliba").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Kai").lastName("Havertz").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Martin").lastName("Odegaard").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Declan").lastName("Rice").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Gabriel").lastName("Jesus").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createLiverpool(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Virgil").lastName("van Dijk").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Dominik").lastName("Szoboszlai").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Alexis").lastName("Mac Allister").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Cody").lastName("Gakpo").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Mohamed").lastName("Salah").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createChelsea(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Cole").lastName("Palmer").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Christopher").lastName("Nkunku").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Raheem").lastName("Sterling").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Thiago").lastName("Silva").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Reece").lastName("James").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }
}
