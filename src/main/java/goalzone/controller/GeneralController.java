package goalzone.controller;

import goalzone.dto.ChampionshipDto;
import goalzone.dto.GameDto;
import goalzone.dto.PlayerDto;
import goalzone.dto.TeamDto;
import goalzone.mapping.ChampionshipMapper;
import goalzone.mapping.GameMapper;
import goalzone.mapping.PlayerMapper;
import goalzone.mapping.TeamMapper;
import goalzone.model.AverageUser;
import goalzone.model.Championship;
import goalzone.model.Game;
import goalzone.model.Team;
import goalzone.repository.AverageUserRepository;
import goalzone.repository.ChampionshipRepository;
import goalzone.repository.GameRepository;
import goalzone.repository.TeamRepository;
import goalzone.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/general")
@RequiredArgsConstructor
public class GeneralController {
    private final ChampionshipRepository championshipRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final AverageUserRepository averageUserRepository;

    private final GameService gameService;

    private final ChampionshipMapper championshipMapper;
    private final GameMapper gameMapper;
    private final TeamMapper teamMapper;
    private final PlayerMapper playerMapper;

    @GetMapping("/games")
    public List<GameDto> getGamesFromChampionship(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return gameMapper.gamesToDtos(championship.getGames());
    }

    @GetMapping("/allgames")
    public List<GameDto> getGames() {
        return gameMapper.gamesToDtos(gameRepository.findAll());
    }

    @GetMapping("/allchampionships")
    public List<ChampionshipDto> getChampionships() {
        return championshipMapper.championshipsToDtos(championshipRepository.findAll());
    }
    @GetMapping("/allteams")
    public List<TeamDto> getTeams() {
        return teamMapper.teamsToDtos(teamRepository.findAll());
    }

    @GetMapping("/teams")
    public List<TeamDto> getTeamsFromChampionship(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return teamMapper.teamsToDtos(championship.getTeams());
    }

    @GetMapping("/game")
    public List<GameDto> getSpecificGame(@RequestParam("gameId") int gameId) {
        List<GameDto> result = new ArrayList<>();
        result.add(gameMapper.gameToDto(gameRepository.findById(gameId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND))));
        return result;
    }

    @GetMapping("/game/homescorers")
    public List<PlayerDto> getHomeScorers(@RequestParam("gameId") int gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return playerMapper.playersToDtos(game.getHomeScorers());
    }

    @GetMapping("/game/awayscorers")
    public List<PlayerDto> getAwayScorers(@RequestParam("gameId") int gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return playerMapper.playersToDtos(game.getAwayScorers());
    }

    @GetMapping("/players")
    public List<PlayerDto> getPlayersFromTeam(@RequestParam("teamName") String teamName) {
        Team team = teamRepository.findByName(teamName);
        return playerMapper.playersToDtos(team.getPlayers());
    }

    @GetMapping("gamestoday/{username}")
    public List<GameDto> getGamesToday(@RequestParam("championshipId") int championshipId, @PathVariable("username") String username) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return gameService.gamesToday(championship, averageUser);
    }

    @GetMapping("gamesyesterday/{username}")
    public List<GameDto> getGamesYesterday(@RequestParam("championshipId") int championshipId, @PathVariable("username") String username) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return gameService.gamesYesterday(championship, averageUser);
    }

    @GetMapping("games2daysago/{username}")
    public List<GameDto> getGames2DaysAgo(@RequestParam("championshipId") int championshipId, @PathVariable("username") String username) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return gameService.games2DaysAgo(championship, averageUser);
    }

    @GetMapping("games3daysago/{username}")
    public List<GameDto> getGames3DaysAgo(@RequestParam("championshipId") int championshipId, @PathVariable("username") String username) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return gameService.games3DaysAgo(championship, averageUser);
    }

    @GetMapping("gamestomorrow/{username}")
    public List<GameDto> getGamesTomorrow(@RequestParam("championshipId") int championshipId, @PathVariable("username") String username) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return gameService.gamesTomorrow(championship, averageUser);
    }

    @GetMapping("games2dayslater/{username}")
    public List<GameDto> getGames2DaysLater(@RequestParam("championshipId") int championshipId, @PathVariable("username") String username) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return gameService.games2DaysLater(championship, averageUser);
    }

    @GetMapping("games3dayslater/{username}")
    public List<GameDto> getGames3DaysLater(@RequestParam("championshipId") int championshipId, @PathVariable("username") String username) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        return gameService.games3DaysLater(championship, averageUser);
    }
}
