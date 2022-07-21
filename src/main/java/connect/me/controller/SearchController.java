package connect.me.controller;

import connect.me.model.ComponentModel;
import connect.me.model.TableModel;

import java.util.Objects;

public class SearchController {

    public static boolean checkCompleted(ComponentModel[][] componentModels) {
        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                if(col != 0) {
                    //verificar esquerda
                    if (componentModels[row][col].getLeftPins() != componentModels[row][col-1].getRightPins()) {
                        return false;
                    }
                }
                if(col != 3) {
                    //verificar direita
                    if (componentModels[row][col].getRightPins() != componentModels[row][col+1].getLeftPins()) {
                        return false;
                    }
                }
                if(row != 0) {
                    //verificar cima
                    if (componentModels[row][col].getTopPins() != componentModels[row-1][col].getBottomPins()) {
                        return false;
                    }
                }

                if(row != 3) {
                    //verificar baixo
                    if (componentModels[row][col].getBottomPins() != componentModels[row+1][col].getTopPins()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void deepFind(TableModel tableModel){
//        Nodo nodo = new BuscaProfundidade<SearchController>().busca(this);
    }
}
