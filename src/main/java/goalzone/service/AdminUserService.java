package goalzone.service;

import goalzone.dto.GameDto;
import goalzone.model.*;
import goalzone.repository.ChampionshipRepository;
import goalzone.repository.GameRepository;
import goalzone.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import goalzone.repository.AdminUserRepository;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final GameRepository gameRepository;
    private final ChampionshipRepository championshipRepository;
    private final TeamRepository teamRepository;
    @Transactional
    public AdminUser signIn(String username, String password){
        AdminUser adminUser = adminUserRepository.findByUsername(username);
        if(adminUser == null) throw new RuntimeException("Nincs ilyen felhasználó.");
        if(!adminUser.getPassword().equals(password)) throw new RuntimeException("Hibás jelszó.");
        else if(!adminUser.getUsername().equals(username)) throw new RuntimeException("Hibás felhasználónév.");
        return adminUser;
    }

    @Transactional
    public AdminUser signUp(String username, String password) {
        AdminUser userInDb = adminUserRepository.findByUsername(username);
        if(userInDb != null) {
            throw new RuntimeException("A felhasználónév már foglalt.");
        }
        AdminUser newUser = AdminUser.builder().username(username).password(password).build();
        adminUserRepository.save(newUser);
        return newUser;
    }

    @Transactional
    public void modifyPersonalData(AdminUser adminUser, String oldUsername){
        AdminUser adminUserInDb = adminUserRepository.findByUsername(adminUser.getUsername());
        if(adminUserInDb == null) {
            adminUserRepository.save(adminUser);
            adminUserRepository.delete(adminUserRepository.findByUsername(oldUsername));
        }
        else {
            adminUserInDb.setPassword(adminUser.getPassword());
            adminUserInDb.setFirstName(adminUser.getFirstName());
            adminUserInDb.setLastName(adminUser.getLastName());
            adminUserInDb.setBirthDate(adminUser.getBirthDate());
            adminUserInDb.setFavourites(adminUser.getFavourites());
            adminUserRepository.save(adminUserInDb);
        }
    }

    @Transactional
    public void addNewScore(Game game) {
        Game gameInDb = gameRepository.findById(game.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        gameInDb.setHomeScore(game.getHomeScore());
        gameInDb.setAwayScore(game.getAwayScore());
        gameRepository.save(gameInDb);
    }
}
