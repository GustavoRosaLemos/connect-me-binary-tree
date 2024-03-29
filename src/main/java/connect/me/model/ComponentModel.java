package connect.me.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import connect.me.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter @Setter
public class ComponentModel {
    private UUID id = UUID.randomUUID();
    @JsonProperty
    private Integer leftPins = 0;
    @JsonProperty
    private Integer rightPins = 0;
    @JsonProperty
    private Integer topPins = 0;
    @JsonProperty
    private Integer bottomPins = 0;
    @JsonProperty
    private Constant.ComponentTypes type = Constant.ComponentTypes.none;
    private boolean rotated = false;

    public ComponentModel() {
        super();
    }

    public ComponentModel(Integer leftPins, Integer rightPins, Integer topPins, Integer bottomPins, Constant.ComponentTypes type) {
        this.leftPins = leftPins;
        this.rightPins = rightPins;
        this.topPins = topPins;
        this.bottomPins = bottomPins;
        this.type = type;
    }

    public boolean rotateRight() {
        if (this.type != Constant.ComponentTypes.rotate && this.type != Constant.ComponentTypes.move_and_rotate) {
            return false;
        }

        Integer leftPins = this.leftPins;
        Integer topPins = this.topPins;
        Integer rightPins = this.rightPins;
        Integer bottomPins = this.bottomPins;

        this.leftPins = bottomPins;
        this.bottomPins = rightPins;
        this.rightPins = topPins;
        this.topPins = leftPins;

        return true;
    }

    public boolean rotateLeft() {
        if (this.type != Constant.ComponentTypes.rotate && this.type != Constant.ComponentTypes.move_and_rotate) {
            return false;
        }

        Integer leftPins = this.leftPins;
        Integer topPins = this.topPins;
        Integer rightPins = this.rightPins;
        Integer bottomPins = this.bottomPins;

        this.leftPins = topPins;
        this.topPins = rightPins;
        this.rightPins = bottomPins;
        this.bottomPins = leftPins;

        return true;
    }
}
