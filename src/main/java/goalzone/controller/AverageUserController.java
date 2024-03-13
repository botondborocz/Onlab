package goalzone.controller;

import goalzone.dto.AverageUserDto;
import goalzone.mapping.AverageUserMapper;
import goalzone.repository.AverageUserRepository;
import goalzone.service.AverageUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AverageUserController {

    private final AverageUserRepository averageUserRepository;

    private final AverageUserService averageUserService;

    private final AverageUserMapper averageUserMapper;

    @GetMapping("/personaldata/{username}")
    public AverageUserDto getUser(@PathVariable("username") String username){
        System.out.println("nasfdjokfsf");
        System.out.println(averageUserMapper.averageUserToDto(averageUserRepository.findByUsername(username)).getUsername());
        System.out.println(averageUserMapper.averageUserToDto(averageUserRepository.findByUsername(username)).getPassword());
        System.out.println(averageUserMapper.averageUserToDto(averageUserRepository.findByUsername(username)).getLastName());
        return averageUserMapper.averageUserToDto(averageUserRepository.findByUsername(username));
    }

    @PostMapping("/personaldata")
    public void modifyPersonalData(@RequestBody AverageUserDto averageUserDto){
        System.out.println("itt vagyok");
        try{
            averageUserService.modifyPersonalData(averageUserMapper.dtoToAverageUser(averageUserDto));
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
