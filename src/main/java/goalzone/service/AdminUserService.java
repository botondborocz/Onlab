package goalzone.service;

import goalzone.model.AverageUser;
import lombok.RequiredArgsConstructor;
import goalzone.model.AdminUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import goalzone.repository.AdminUserRepository;

@RequiredArgsConstructor
@Service
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
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
}
