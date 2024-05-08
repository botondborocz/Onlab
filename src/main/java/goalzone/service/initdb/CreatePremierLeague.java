package goalzone.service.initdb;

import goalzone.model.*;
import goalzone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePremierLeague {
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

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

        championship.addTeam(team3);

        championship.addTeam(team4);

        championship.addTeam(team5);
    }

    @Transactional
    public void createManchesterCity(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Erling").lastName("Haaland").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Kevin").lastName("de Bruyne").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Julian").lastName("Alvarez").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
    }

    @Transactional
    public void createManchesterUnited(Team team) {
        Player player1 = playerRepository.save(Player.builder().firstName("Bruno").lastName("Fernandes").team(team).build());
        Player player2 = playerRepository.save(Player.builder().firstName("Harry").lastName("Maguire").team(team).build());
        Player player3 = playerRepository.save(Player.builder().firstName("Marcus").lastName("Rashford").team(team).build());
        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
    }
}
