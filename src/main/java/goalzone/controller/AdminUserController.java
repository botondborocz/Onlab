package goalzone.controller;

import goalzone.dto.*;
import goalzone.mapping.AdminUserMapper;
import goalzone.mapping.ChampionshipMapper;
import goalzone.mapping.GameMapper;
import goalzone.mapping.TeamMapper;
import goalzone.model.*;
import goalzone.repository.AdminUserRepository;
import goalzone.repository.ChampionshipRepository;
import goalzone.repository.GameRepository;
import goalzone.repository.TeamRepository;
import goalzone.service.AdminUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserRepository adminUserRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final ChampionshipRepository championshipRepository;

    private final AdminUserService adminUserService;

    private final AdminUserMapper adminUserMapper;
    private final GameMapper gameMapper;
    private final TeamMapper teamMapper;
    private final ChampionshipMapper championshipMapper;

    @GetMapping("/personaldata/{username}")
    public AdminUserDto getUser(@PathVariable("username") String username){
        System.out.println("itt vagyok");
        System.out.println(adminUserRepository.findByUsername(username).getPassword());
        System.out.println(adminUserRepository.findByUsername(username).getFirstName());
        return adminUserMapper.adminUserToDto(adminUserRepository.findByUsername(username));
    }

    @PostMapping("/personaldata/{username}")
    public void modifyPersonalData(@RequestBody AdminUserDto adminUserDto, @PathVariable("username") String username){
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
        System.out.println(gameDto.getId());
        adminUserService.addNewScore(gameMapper.dtoToGame(gameDto));
    }

    @PostMapping("/newgame")
    public void createNewGame(@RequestBody GameDto gameDto) {
        Game game = gameMapper.dtoToGame(gameDto);
        Championship championship = championshipRepository.findByName(game.getChampionshipName());
        Team team1 = teamRepository.findByName(game.getHomeTeamName());
        Team team2 = teamRepository.findByName(game.getAwayTeamName());
        try {
            if (!championship.getTeams().contains(team1) || !championship.getTeams().contains(team2))
                throw new RuntimeException("Teams are not in that championship");
            else
                gameRepository.save(game);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }
}
