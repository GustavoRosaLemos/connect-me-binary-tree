package connect.me.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import connect.me.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.bcel.Const;

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

    public boolean rotate() {
        if (this.type != Constant.ComponentTypes.rotate && this.type != Constant.ComponentTypes.move_and_rotate) {
            return false;
        }

        Integer leftPins = this.leftPins;
        Integer topPins = this.topPins;
        Integer rightPins = this.rightPins;
        Integer bottomPins = this.bottomPins;

        this.leftPins = bottomPins;
        this.topPins = leftPins;
        this.rightPins = topPins;
        this.bottomPins = rightPins;

        return true;
    }
}
