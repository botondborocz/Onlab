package goalzone.service;

import goalzone.dto.GameDto;
import goalzone.mapping.GameMapper;
import goalzone.model.AverageUser;
import goalzone.model.Championship;
import goalzone.model.Game;
import goalzone.model.Team;
import goalzone.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {
    private final TeamRepository teamRepository;

    private final GameMapper gameMapper;

    public List<GameDto> gamesToday(Championship championship, AverageUser averageUser) {
        List<GameDto> games = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now())) {
                games.add(gameMapper.gameToDto(setGameFavorite(averageUser, game)));
            }
        }
        return games;
    }

    public List<GameDto> gamesYesterday(Championship championship, AverageUser averageUser) {
        List<GameDto> games = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().minusDays(1))) {
                games.add(gameMapper.gameToDto(setGameFavorite(averageUser, game)));
            }
        }
        return games;
    }

    public List<GameDto> games2DaysAgo(Championship championship, AverageUser averageUser) {
        List<GameDto> games = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().minusDays(2))) {
                games.add(gameMapper.gameToDto(setGameFavorite(averageUser, game)));
            }
        }
        return games;
    }

    public List<GameDto> games3DaysAgo(Championship championship, AverageUser averageUser) {
        List<GameDto> games = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().minusDays(3))) {
                games.add(gameMapper.gameToDto(setGameFavorite(averageUser, game)));
            }
        }
        return games;
    }

    public List<GameDto> gamesTomorrow(Championship championship, AverageUser averageUser) {
        List<GameDto> games = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().plusDays(1))) {
                games.add(gameMapper.gameToDto(setGameFavorite(averageUser, game)));
            }
        }
        return games;
    }

    public List<GameDto> games2DaysLater(Championship championship, AverageUser averageUser) {
        List<GameDto> games = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().plusDays(2))) {
                games.add(gameMapper.gameToDto(setGameFavorite(averageUser, game)));
            }
        }
        return games;
    }

    public List<GameDto> games3DaysLater(Championship championship, AverageUser averageUser) {
        List<GameDto> games = new ArrayList<>();
        List<Game> allgames = championship.getGames();
        for (Game game: allgames) {
            if (game.getDate().equals(LocalDate.now().plusDays(3))) {
                games.add(gameMapper.gameToDto(setGameFavorite(averageUser, game)));
            }
        }
        return games;
    }

    public Game setGameFavorite(AverageUser averageUser, Game game) {
        Team homeTeam = teamRepository.findByName(game.getHomeTeamName());
        Team awayTeam = teamRepository.findByName(game.getAwayTeamName());
        if (game.isHomeFavorite() && !averageUser.getFavorites().contains(homeTeam)) {
            game.setHomeFavorite(false);
        }
        if (!game.isHomeFavorite() && averageUser.getFavorites().contains(homeTeam)) {
            game.setHomeFavorite(true);
        }
        if (game.isAwayFavorite() && !averageUser.getFavorites().contains(awayTeam)) {
            game.setAwayFavorite(false);
        }
        if (!game.isAwayFavorite() && averageUser.getFavorites().contains(awayTeam)) {
            game.setAwayFavorite(true);
        }
        return game;
    }
}
