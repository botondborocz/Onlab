package goalzone.mapping;

import goalzone.dto.GameDto;
import goalzone.dto.PlayerDto;
import goalzone.model.Game;
import goalzone.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDto playerToDto(Player player);
    Player dtoToPlayer(PlayerDto playerDto);
    List<PlayerDto> playersToDtos (List<Player> players);
}
