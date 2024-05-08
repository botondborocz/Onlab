package goalzone.controller;

import goalzone.dto.ChampionshipDto;
import goalzone.dto.GameDto;
import goalzone.dto.PlayerDto;
import goalzone.dto.TeamDto;
import goalzone.mapping.*;
import goalzone.model.Championship;
import goalzone.model.Game;
import goalzone.model.Player;
import goalzone.model.Team;
import goalzone.repository.*;
import goalzone.service.AverageUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/general")
@RequiredArgsConstructor
public class GeneralController {
    private final ChampionshipRepository championshipRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

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

    @GetMapping("gamestoday")
    public List<GameDto> getGamesToday(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> todayGames = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now())) {
                todayGames.add(gameMapper.gameToDto(game));
            }
        }
        return todayGames;
    }

    @GetMapping("gamesyesterday")
    public List<GameDto> getGamesYesterday(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> todayGames = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().minusDays(1))) {
                todayGames.add(gameMapper.gameToDto(game));
                System.out.println("Yesterday: " + game.isHomeFavorite() + " " + game.isAwayFavorite());
            }
        }
        return todayGames;
    }

    @GetMapping("games2daysago")
    public List<GameDto> getGames2DaysAgo(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> todayGames = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().minusDays(2))) {
                todayGames.add(gameMapper.gameToDto(game));
                System.out.println("2 days ago: " + game.isHomeFavorite() + " " + game.isAwayFavorite());
            }
        }
        return todayGames;
    }

    @GetMapping("games3daysago")
    public List<GameDto> getGames3DaysAgo(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> todayGames = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().minusDays(3))) {
                todayGames.add(gameMapper.gameToDto(game));
                System.out.println("3 days ago: " + game.isHomeFavorite() + " " + game.isAwayFavorite());
            }
        }
        return todayGames;
    }

    @GetMapping("gamestomorrow")
    public List<GameDto> getGamesTomorrow(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> todayGames = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().plusDays(1))) {
                todayGames.add(gameMapper.gameToDto(game));
                System.out.println("Tomorrow: " + game.isHomeFavorite() + " " + game.isAwayFavorite());
            }
        }
        return todayGames;
    }

    @GetMapping("games2dayslater")
    public List<GameDto> getGames2DaysLater(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> todayGames = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().plusDays(2))) {
                todayGames.add(gameMapper.gameToDto(game));
                System.out.println("2 days later: " + game.isHomeFavorite() + " " + game.isAwayFavorite());
            }
        }
        return todayGames;
    }

    @GetMapping("games3dayslater")
    public List<GameDto> getGames3DaysLater(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<GameDto> todayGames = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().plusDays(3))) {
                todayGames.add(gameMapper.gameToDto(game));
                System.out.println("3 days later: " + game.isHomeFavorite() + " " + game.isAwayFavorite());
            }
        }
        return todayGames;
    }
}
