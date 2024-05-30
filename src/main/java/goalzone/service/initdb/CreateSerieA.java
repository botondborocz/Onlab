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
public class CreateSerieA {
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void initSerieA(){
        createTeams(championshipRepository.save(Championship.builder().name("Serie A").build()));
    }
    @Transactional
    public void createTeams(Championship championship){
        Team team1 = teamRepository.save(Team.builder().name("Inter").build());
        Team team2 = teamRepository.save(Team.builder().name("AC Milan").build());
        Team team3 = teamRepository.save(Team.builder().name("Juventus").build());
        Team team4 = teamRepository.save(Team.builder().name("Atalanta").build());
        Team team5 = teamRepository.save(Team.builder().name("Bologna").build());

        createInter(team1);
        championship.addTeam(team1);
        createACMilan(team2);
        championship.addTeam(team2);
        createJuventus(team3);
        championship.addTeam(team3);
        createAtalanta(team4);
        championship.addTeam(team4);
        createBologna(team5);
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
    public void createInter(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Nicolo").lastName("Barella").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Hakan").lastName("Calhanoglu").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Henrikh").lastName("Mkhitaryan").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Alexis").lastName("Sanchez").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Lautaro").lastName("Martinez").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createACMilan(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Fikayo").lastName("Tomori").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Pierre").lastName("Kalulu").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Olivier").lastName("Giroud").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Rafael").lastName("Leao").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Noah").lastName("Okafor").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createJuventus(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Alex").lastName("Sandro").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Filip").lastName("Kostic").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Adrien").lastName("Rabiot").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Federico").lastName("Chiesa").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Dusan").lastName("Vlahovic").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createAtalanta(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Emil").lastName("Holm").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Michel").lastName("Adopo").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Pietro").lastName("Comi").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Ademola").lastName("Lookman").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Mario").lastName("Pasalic").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createBologna(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Nikola").lastName("Moro").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Remo").lastName("Freuler").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Dan").lastName("Ndoye").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Jens").lastName("Odgaard").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Jesper").lastName("Karlsson").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }
}
