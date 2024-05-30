package goalzone.controller;

import goalzone.dto.AverageUserDto;
import goalzone.dto.GameDto;
import goalzone.dto.TeamDto;
import goalzone.mapping.AverageUserMapper;
import goalzone.mapping.GameMapper;
import goalzone.mapping.TeamMapper;
import goalzone.model.AverageUser;
import goalzone.model.Game;
import goalzone.model.Team;
import goalzone.repository.AverageUserRepository;
import goalzone.repository.GameRepository;
import goalzone.repository.TeamRepository;
import goalzone.service.AverageUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AverageUserController {

    private final AverageUserRepository averageUserRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    private final AverageUserService averageUserService;

    private final AverageUserMapper averageUserMapper;
    private final GameMapper gameMapper;
    private final TeamMapper teamMapper;

    @GetMapping("/personaldata/{username}")
    public AverageUserDto getUser(@PathVariable("username") String username){
        return averageUserMapper.averageUserToDto(averageUserRepository.findByUsername(username));
    }

    @PostMapping("/personaldata/{username}")
    public void modifyPersonalData(@RequestBody AverageUserDto averageUserDto, @PathVariable String username) {
        averageUserDto.setBirthDate(averageUserDto.getBirthDate().plusDays(1));
        try {
            AverageUser userForCheck = averageUserRepository.findByUsername(averageUserDto.getUsername());
            if (userForCheck != null && !userForCheck.getUsername().equals(username))
                throw new RuntimeException("Username already taken");
            averageUserService.modifyPersonalData(averageUserMapper.dtoToAverageUser(averageUserDto), username);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("changehomefavorite/{username}")
    public void changeHomeFavorite(@RequestBody GameDto gameDto, @PathVariable("username") String username) {
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        Team team = teamRepository.findByName(gameDto.getHomeTeamName());
        if (averageUser.getFavorites().contains(team)) {
            averageUser.removeFavorite(team);
        }
        else {
            averageUser.addFavorite(team);
        }
        averageUserService.setFavorites(averageUser);
    }

    @PostMapping("changeawayfavorite/{username}")
    public void changeAwayFavorite(@RequestBody GameDto gameDto, @PathVariable String username) {
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        Team team = teamRepository.findById(gameDto.getAwayTeamId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (averageUser.getFavorites().contains(team)) {
            averageUser.removeFavorite(team);
        }
        else {
            averageUser.addFavorite(team);
        }
        averageUserService.setFavorites(averageUser);
    }

    @GetMapping("favoriteteams/{username}")
    public List<TeamDto> favoriteTeams(@PathVariable String username) {
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return teamMapper.teamsToDtos(averageUser.getFavorites());
    }

    @GetMapping("favoritegamesbefore")
    public List<GameDto> getGamesOfFavoriteTeamsPast(@RequestParam("teamId") int teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> games = new ArrayList<>();
        for (Game game : team.getGames()) {
            if (game.getDate().isBefore(LocalDate.now())) {
                games.add(gameMapper.gameToDto(game));
            }
        }
        return games;
    }

    @GetMapping("favoritegamesafter")
    public List<GameDto> getGamesOfFavoriteTeamsFuture(@RequestParam("teamId") int teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> games = new ArrayList<>();
        for (Game game : team.getGames()) {
            if (game.getDate().isAfter(LocalDate.now()) || game.getDate().equals(LocalDate.now())) {
                games.add(gameMapper.gameToDto(game));
            }
        }
        return games;
    }
}
