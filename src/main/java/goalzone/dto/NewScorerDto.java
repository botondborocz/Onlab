package goalzone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewScorerDto {
    private int gameId;
    private int homeScorerId;
    private int awayScorerId;
    private int homeScore;
    private int awayScore;
}
