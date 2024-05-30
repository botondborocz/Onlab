package goalzone.service;

import goalzone.model.AverageUser;
import goalzone.model.Game;
import goalzone.model.Team;
import goalzone.repository.AverageUserRepository;
import goalzone.repository.GameRepository;
import goalzone.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AverageUserService {
    private final AverageUserRepository averageUserRepository;
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    @Transactional
    public AverageUser signIn(String username, String password){
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        if(averageUser == null) throw new RuntimeException("User not found.");
        if(!averageUser.getPassword().equals(password)) throw new RuntimeException("Incorrect password.");
        else if(!averageUser.getUsername().equals(username)) throw new RuntimeException("Incorrect username.");
        return averageUser;
    }

    @Transactional
    public AverageUser signUp(String username, String password, String firstName, String lastName) {
        AverageUser userInDb = averageUserRepository.findByUsername(username);
        if(userInDb != null) {
            throw new RuntimeException("Username already taken.");
        }
        AverageUser newUser = AverageUser.builder().username(username).password(password).firstName(firstName).lastName(lastName).build();
        averageUserRepository.save(newUser);
        return newUser;
    }

    @Transactional
    public void modifyPersonalData(AverageUser averageUser, String oldUsername){
        AverageUser averageUserInDb = averageUserRepository.findByUsername(averageUser.getUsername());
        if(averageUserInDb == null) {
            averageUserRepository.save(averageUser);
            averageUserRepository.delete(averageUserRepository.findByUsername(oldUsername));
        }
        else {
            averageUserInDb.setPassword(averageUser.getPassword());
            averageUserInDb.setFirstName(averageUser.getFirstName());
            averageUserInDb.setLastName(averageUser.getLastName());
            averageUserInDb.setBirthDate(averageUser.getBirthDate());
            averageUserInDb.setFavorites(averageUser.getFavorites());
            averageUserRepository.save(averageUserInDb);
        }
    }

    @Transactional
    public void setFavorites(AverageUser averageUser) {
        List<Game> allgames = gameRepository.findAll();
        for (Game game : allgames) {
            Team homeTeam = teamRepository.findById(game.getHomeTeamId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Team awayTeam = teamRepository.findById(game.getAwayTeamId()).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            game.setHomeFavorite(averageUser.getFavorites().contains(homeTeam));
            game.setAwayFavorite(averageUser.getFavorites().contains(awayTeam));
        }
    }
}
