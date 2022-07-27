package connect.me.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StopWatch;

@Getter
@AllArgsConstructor
public class LevelReponseModel {
    private ComponentModel[][] table;
    private StopWatch watch;
}
