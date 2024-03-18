package goalzone.controller;

import goalzone.dto.ChampionshipDto;
import goalzone.dto.GameDto;
import goalzone.dto.TeamDto;
import goalzone.mapping.AverageUserMapper;
import goalzone.mapping.ChampionshipMapper;
import goalzone.mapping.GameMapper;
import goalzone.mapping.TeamMapper;
import goalzone.model.Championship;
import goalzone.repository.AverageUserRepository;
import goalzone.repository.ChampionshipRepository;
import goalzone.repository.GameRepository;
import goalzone.repository.TeamRepository;
import goalzone.service.AverageUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/general")
@RequiredArgsConstructor
public class GeneralController {
    private final ChampionshipRepository championshipRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    private final ChampionshipMapper championshipMapper;
    private final GameMapper gameMapper;
    private final TeamMapper teamMapper;

    @GetMapping("/games")
    public List<GameDto> getGamesFromChampionship(@RequestParam("championshipId") int championshipId) {
        Championship championship = championshipRepository.findById(championshipId).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        System.out.println(gameMapper.gamesToDtos(championship.getGames()));
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

}
