package goalzone.controller;

import goalzone.dto.*;
import goalzone.mapping.AdminUserMapper;
import goalzone.mapping.ChampionshipMapper;
import goalzone.mapping.GameMapper;
import goalzone.mapping.TeamMapper;
import goalzone.model.*;
import goalzone.repository.*;
import goalzone.service.AdminUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserRepository adminUserRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final ChampionshipRepository championshipRepository;
    private final PlayerRepository playerRepository;

    private final AdminUserService adminUserService;

    private final AdminUserMapper adminUserMapper;
    private final GameMapper gameMapper;
    private final TeamMapper teamMapper;
    private final ChampionshipMapper championshipMapper;

    @GetMapping("/personaldata/{username}")
    public AdminUserDto getUser(@PathVariable("username") String username){
        return adminUserMapper.adminUserToDto(adminUserRepository.findByUsername(username));
    }

    @PostMapping("/personaldata/{username}")
    public void modifyPersonalData(@RequestBody AdminUserDto adminUserDto, @PathVariable("username") String username){
        adminUserDto.setBirthDate(adminUserDto.getBirthDate().plusDays(1));
        try{
            AdminUser userForCheck = adminUserRepository.findByUsername(adminUserDto.getUsername());
            if (userForCheck != null && !userForCheck.getUsername().equals(username))
                throw new RuntimeException("Username already taken");
            adminUserService.modifyPersonalData(adminUserMapper.dtoToAdminUser(adminUserDto), username);
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("newscore")
    public void addNewScore(@RequestBody GameDto gameDto) {
        adminUserService.addNewScore(gameMapper.dtoToGame(gameDto));
    }

    @PostMapping("/newgame")
    public void createNewGame(@RequestBody GameDto gameDto) {
        gameDto.setDate(gameDto.getDate().plusDays(1));
        Game game = gameMapper.dtoToGame(gameDto);
        Team team1 = teamRepository.findByName(game.getHomeTeamName());
        Team team2 = teamRepository.findByName(game.getAwayTeamName());
        Championship championship = championshipRepository.findById(game.getChampId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        game.setTeams(Arrays.asList(team1, team2));
        game.setChampionship(championship);
        try {
            if (!championship.getTeams().contains(team1) || !championship.getTeams().contains(team2))
                throw new RuntimeException("Teams are not in that championship");
            else {
                gameRepository.save(game);
                championship.addGame(game);
                championshipRepository.save(championship);
            }
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addscorer")
    public void addNewScorer(@RequestBody NewScorerDto newScorerDto) {
        Game game = gameRepository.findById(newScorerDto.getGameId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Player homeScorer;
        Player awayScorer;
        if (newScorerDto.getHomeScorerId() != 0) {
            homeScorer = playerRepository.findById(newScorerDto.getHomeScorerId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            game.setHomeScore(newScorerDto.getHomeScore());
            game.addHomeScorer(homeScorer);
        }
        if (newScorerDto.getAwayScorerId() != 0) {
            awayScorer = playerRepository.findById(newScorerDto.getAwayScorerId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            game.setAwayScore(newScorerDto.getAwayScore());
            game.addAwayScorer(awayScorer);
        }
        gameRepository.save(game);
    }
}
