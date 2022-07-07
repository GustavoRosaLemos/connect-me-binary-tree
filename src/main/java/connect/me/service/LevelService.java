package connect.me.service;

import connect.me.constant.Constant;
import connect.me.model.ComponentModel;

import java.util.ArrayList;

public class LevelService {
    public ArrayList<ComponentModel> loadRow(String level, int column, boolean completed) {
        ArrayList<ComponentModel> componentModels = new ArrayList<>();
        switch (column) {
            case 1: {
                if (completed) {
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.blocked));
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.move));
                } else {
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.blocked));
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.move));
                    componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                }
                break;
            }
            case 2: {
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                break;
            }
            case 3: {
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.move));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                break;
            }
            case 4: {
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
                componentModels.add(new ComponentModel(0, 0, 0, 0, Constant.ComponentTypes.none));
            }
        }
        return componentModels;
    }
}
