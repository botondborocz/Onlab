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
public class CreateBundesliga {
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void initBundesliga(){
        createTeams(championshipRepository.save(Championship.builder().name("Bundesliga").build()));
    }
    @Transactional
    public void createTeams(Championship championship){
        Team team1 = teamRepository.save(Team.builder().name("Leverkusen").build());
        Team team2 = teamRepository.save(Team.builder().name("VfB Stuttgart").build());
        Team team3 = teamRepository.save(Team.builder().name("Bayern München").build());
        Team team4 = teamRepository.save(Team.builder().name("RB Leipzig").build());
        Team team5 = teamRepository.save(Team.builder().name("Borussia Dortmund").build());

        createLeverkusen(team1);
        championship.addTeam(team1);
        createVfBStuttgart(team2);
        championship.addTeam(team2);
        createBayernMünchen(team3);
        championship.addTeam(team3);
        createRBLeipzig(team4);
        championship.addTeam(team4);
        createBorussiaDortmund(team5);
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
    public void createLeverkusen(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Nathan").lastName("Tella").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Florian").lastName("Wirtz").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Granit").lastName("Xhaka").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Victor").lastName("Boniface").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Borja").lastName("Iglesias").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createVfBStuttgart(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Enzo").lastName("Millot").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Raul").lastName("Paula").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Luca").lastName("Raimund").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Deniz").lastName("Undav").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Chris").lastName("Fuhrich").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createBayernMünchen(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Leon").lastName("Goretzka").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Joshua").lastName("Kimmich").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Jamal").lastName("Musiala").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Harry").lastName("Kane").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Leroy").lastName("Sane").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createRBLeipzig(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Lois").lastName("Openda").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Xavi").lastName("Simons").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Dani").lastName("Olmo").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Willi").lastName("Orban").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Péter").lastName("Gulácsi").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createBorussiaDortmund(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Niklas").lastName("Süle").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Marco").lastName("Reus").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Karim").lastName("Adeyemi").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Samuel").lastName("Bamba").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Cole").lastName("Campbell").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }
}
