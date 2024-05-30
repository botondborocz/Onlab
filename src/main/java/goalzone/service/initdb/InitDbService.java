package goalzone.service.initdb;

import goalzone.model.*;
import goalzone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InitDbService {
    private final CreateLaLiga createLaLiga;
    private final CreatePremierLeague createPremierLeague;
    private final CreateBundesliga createBundesliga;
    private final CreateSerieA createSerieA;
    private final CreateLigue1 createLigue1;

    private final AverageUserRepository averageUserRepository;
    private final AdminUserRepository adminUserRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final ChampionshipRepository championshipRepository;
    private final PlayerRepository playerRepository;

    @Transactional
    public void initDb() {
        createLaLiga.initLaLiga();
        createPremierLeague.initPremierLeauge();
        createBundesliga.initBundesliga();
        createSerieA.initSerieA();
        createLigue1.initLigue1();
        AdminUser adminUser1 = createAdminUser("admin", "admin", "Admin", "Admin", LocalDate.of(1999, 12, 12));
        AverageUser averageUser1 = createAverageUser("asd", "asd", "Elek", "Vicc", LocalDate.of(2000, 01, 01));
    }

    private AverageUser createAverageUser(String username, String password, String firstName, String lastName, LocalDate birthDate) {
        return averageUserRepository.save(AverageUser.builder().username(username).password(password).firstName(firstName).lastName(lastName).birthDate(birthDate).build());
    }

    private AdminUser createAdminUser(String username, String password, String firstName, String lastName, LocalDate birthDate) {
        return adminUserRepository.save(AdminUser.builder().username(username).password(password).firstName(firstName).lastName(lastName).birthDate(birthDate).build());
    }
}
