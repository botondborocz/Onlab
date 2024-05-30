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
public class CreateLigue1 {
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void initLigue1(){
        createTeams(championshipRepository.save(Championship.builder().name("Ligue 1").build()));
    }
    @Transactional
    public void createTeams(Championship championship){
        Team team1 = teamRepository.save(Team.builder().name("PSG").build());
        Team team2 = teamRepository.save(Team.builder().name("Monaco").build());
        Team team3 = teamRepository.save(Team.builder().name("Brest").build());
        Team team4 = teamRepository.save(Team.builder().name("Lille").build());
        Team team5 = teamRepository.save(Team.builder().name("Nice").build());

        createPSG(team1);
        championship.addTeam(team1);
        createMonaco(team2);
        championship.addTeam(team2);
        createBrest(team3);
        championship.addTeam(team3);
        createLille(team4);
        championship.addTeam(team4);
        createNice(team5);
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
    public void createPSG(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Fabian").lastName("Ruiz").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Carlos").lastName("Soler").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Marco").lastName("Asensio").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Ousmane").lastName("Dembele").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Kylian").lastName("Mbappe").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createMonaco(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Edan").lastName("Diop").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Youssouf").lastName("Fofana").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Mohamed").lastName("Camara").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Lucas").lastName("Michal").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Wissam").lastName("Ben Yedder").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createBrest(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Jonas").lastName("Martin").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Hugo").lastName("Magnetti").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Steve").lastName("Mounie").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Martin").lastName("Satriano").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Adrien").lastName("Lebeau").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createLille(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Benjamin").lastName("Andre").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Lilian").lastName("Baret").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Nabil").lastName("Bentaleb").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Jonathan").lastName("David").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Andrej").lastName("Ilic").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createNice(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Tom").lastName("Louchet").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Sofiane").lastName("Diop").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Daouda").lastName("Traore").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Aliou").lastName("Balde").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Jeremie").lastName("Boga").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }
}
