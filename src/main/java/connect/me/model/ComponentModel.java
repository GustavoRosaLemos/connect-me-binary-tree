package connect.me.model;

import connect.me.constant.Constant;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ComponentModel {
    private int leftPins;
    private int rightPins;
    private int topPins;
    private int bottomPins;
    private Constant.ComponentTypes type;
}
