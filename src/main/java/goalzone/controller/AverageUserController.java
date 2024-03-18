package goalzone.controller;

import goalzone.dto.AverageUserDto;
import goalzone.dto.ChampionshipDto;
import goalzone.dto.GameDto;
import goalzone.mapping.AverageUserMapper;
import goalzone.mapping.ChampionshipMapper;
import goalzone.mapping.GameMapper;
import goalzone.model.AverageUser;
import goalzone.model.Championship;
import goalzone.repository.AverageUserRepository;
import goalzone.repository.ChampionshipRepository;
import goalzone.service.AverageUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AverageUserController {

    private final AverageUserRepository averageUserRepository;
    private final ChampionshipRepository championshipRepository;

    private final AverageUserService averageUserService;

    private final AverageUserMapper averageUserMapper;
    private final ChampionshipMapper championshipMapper;
    private final GameMapper gameMapper;

    @GetMapping("/personaldata/{username}")
    public AverageUserDto getUser(@PathVariable("username") String username){
        return averageUserMapper.averageUserToDto(averageUserRepository.findByUsername(username));
    }

    @PostMapping("/personaldata/{username}")
    public void modifyPersonalData(@RequestBody AverageUserDto averageUserDto, @PathVariable String username) {
        System.out.println(averageUserDto.getBirthDate());
        try {
            AverageUser userForCheck = averageUserRepository.findByUsername(averageUserDto.getUsername());
            if (userForCheck != null && !userForCheck.getUsername().equals(username))
                throw new RuntimeException("Username already taken");
            averageUserService.modifyPersonalData(averageUserMapper.dtoToAverageUser(averageUserDto), username);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
