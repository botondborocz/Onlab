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
public class CreateLaLiga {
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Transactional
    public void initLaLiga(){
        createTeams(championshipRepository.save(Championship.builder().name("La Liga").build()));
    }
    @Transactional
    public void createTeams(Championship championship){
        Team team1 = teamRepository.save(Team.builder().name("FC Barcelona").build());
        Team team2 = teamRepository.save(Team.builder().name("Real Madrid").build());
        Team team3 = teamRepository.save(Team.builder().name("Atletico Madrid").build());
        Team team4 = teamRepository.save(Team.builder().name("Girona").build());
        Team team5 = teamRepository.save(Team.builder().name("Valencia").build());

        createFCBarcelona(team1);
        championship.addTeam(team1);
        createRealMadrid(team2);
        championship.addTeam(team2);
        createAtleticoMadrid(team3);
        championship.addTeam(team3);
        createGirona(team4);
        championship.addTeam(team4);
        createAthleticBilbao(team5);
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
    public void createFCBarcelona(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Robert").lastName("Lewandowski").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Joao").lastName("Felix").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Ferran").lastName("Torres").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Lamine").lastName("Yamal").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Fermin").lastName("Lopez").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createAtleticoMadrid(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Jan").lastName("Oblak").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Angel").lastName("Correa").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Marcos").lastName("Llorente").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Memphis").lastName("Depay").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Antoine").lastName("Griezmann").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createGirona(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Daley").lastName("Blind").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Aleix").lastName("Garcia").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Ivan").lastName("Martin").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Viktor").lastName("Tsygankov").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Artem").lastName("Dovbyk").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createAthleticBilbao(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Unai").lastName("Simon").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Ander").lastName("Herrera").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Iker").lastName("Muniain").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Inaki").lastName("Williams").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Nico").lastName("Williams").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }

    @Transactional
    public void createRealMadrid(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Jude").lastName("Bellingham").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Luka").lastName("Modric").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Toni").lastName("Kroos").team(team).build());
        Player player4 = playerRepository.save(Player.builder().firstName("Federico").lastName("Valverde").team(team).build());
        Player player5 = playerRepository.save(Player.builder().firstName("Brahim").lastName("Diaz").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
    }
}
