package goalzone.service;

import goalzone.model.AdminUser;
import goalzone.model.Game;
import goalzone.repository.AdminUserRepository;
import goalzone.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final GameRepository gameRepository;
    @Transactional
    public AdminUser signIn(String username, String password){
        AdminUser adminUser = adminUserRepository.findByUsername(username);
        if(adminUser == null) throw new RuntimeException("User not found.");
        if(!adminUser.getPassword().equals(password)) throw new RuntimeException("Incorrect password.");
        else if(!adminUser.getUsername().equals(username)) throw new RuntimeException("Incorrect username.");
        return adminUser;
    }

    @Transactional
    public AdminUser signUp(String username, String password, String firstName, String lastName) {
        AdminUser userInDb = adminUserRepository.findByUsername(username);
        if(userInDb != null) {
            throw new RuntimeException("Username already taken.");
        }
        AdminUser newUser = AdminUser.builder().username(username).password(password).firstName(firstName).lastName(lastName).build();
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
