package goalzone.service.initdb;

import goalzone.model.*;
import goalzone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateLaLiga {
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Transactional
    public void initLaLiga(){
        createTeams(championshipRepository.save(Championship.builder().name("La Liga").build()));
    }
    @Transactional
    public void createTeams(Championship championship){
        Team team1 = teamRepository.save(Team.builder().name("FC Barcelona").build());
        Team team2 = teamRepository.save(Team.builder().name("Real Madrid").build());
        Team team3 = teamRepository.save(Team.builder().name("Atletico Madrid").build());
        Team team4 = teamRepository.save(Team.builder().name("Sevilla").build());
        Team team5 = teamRepository.save(Team.builder().name("Valencia CF").build());

        createFCBarcelona(team1);
        championship.addTeam(team1);
        createRealMadrid(team2);
        championship.addTeam(team2);

        championship.addTeam(team3);

        championship.addTeam(team4);

        championship.addTeam(team5);
    }

    @Transactional
    public void createFCBarcelona(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Robert").lastName("Lewandowski").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Joao").lastName("Felix").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Ferran").lastName("Torres").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
    }

    @Transactional
    public void createRealMadrid(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Jude").lastName("Bellingham").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Luka").lastName("Modric").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Toni").lastName("Kroos").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
    }
}
