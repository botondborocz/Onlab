package goalzone.service;

import lombok.RequiredArgsConstructor;
import goalzone.model.AverageUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import goalzone.repository.AverageUserRepository;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AverageUserService {
    private final AverageUserRepository averageUserRepository;
    @Transactional
    public AverageUser signIn(String username, String password){
        AverageUser averageUser = averageUserRepository.findByUsername(username);
        if(averageUser == null) throw new RuntimeException("Nincs ilyen felhasználó.");
        if(!averageUser.getPassword().equals(password)) throw new RuntimeException("Hibás jelszó.");
        else if(!averageUser.getUsername().equals(username)) throw new RuntimeException("Hibás felhasználónév.");
        return averageUser;
    }

    @Transactional
    public AverageUser signUp(String username, String password) {
        AverageUser userInDb = averageUserRepository.findByUsername(username);
        if(userInDb != null) {
            throw new RuntimeException("A felhasználónév már foglalt.");
        }
        AverageUser newUser = AverageUser.builder().username(username).password(password).build();
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
            System.out.println(averageUser.getBirthDate());
            averageUserInDb.setPassword(averageUser.getPassword());
            averageUserInDb.setFirstName(averageUser.getFirstName());
            averageUserInDb.setLastName(averageUser.getLastName());
            averageUserInDb.setBirthDate(averageUser.getBirthDate());
            averageUserInDb.setFavourites(averageUser.getFavourites());
            averageUserRepository.save(averageUserInDb);
        }
    }
}
