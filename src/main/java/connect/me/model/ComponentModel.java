package connect.me.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import connect.me.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ComponentModel {
    @JsonProperty
    private Integer leftPins;
    @JsonProperty
    private Integer rightPins;
    @JsonProperty
    private Integer topPins;
    @JsonProperty
    private Integer bottomPins;
    @JsonProperty
    private Constant.ComponentTypes type;

    public ComponentModel() {
        super();
    }
}
