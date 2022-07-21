package connect.me.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class TableModel {
    @JsonProperty
    private ComponentModel[][] rows;

    public TableModel() {
        super();
    }
}
