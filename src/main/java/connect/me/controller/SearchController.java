package connect.me.controller;

import connect.me.constant.Constant;
import connect.me.model.ComponentModel;
import connect.me.model.TableModel;
import connect.me.service.BuscaProfundidade;
import connect.me.service.Estado;
import connect.me.service.Nodo;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class SearchController implements Estado {

    private final ComponentModel[][] componentModels;
    private static ComponentModel[][] tableSoluction;
    private final String operation;

    public boolean checkCompleted() {
//        tableSoluction = componentModels;
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
        tableSoluction = componentModels;
        return true;
    }

    public ComponentModel[][] deepFind() throws Exception {
        Nodo nodo = new BuscaProfundidade<SearchController>().busca(this);
        if (nodo == null) {
            System.out.println("Nao existe solucao");
        } else {
            System.out.println("Solucao encontrada");
            return tableSoluction;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
//        if (o instanceof SearchController) {
//            SearchController e = (SearchController)o;
//            return e.toString().hashCode() == toString().hashCode();
//        }
        return false;
    }

    public String toString() {
        return "\n" + " - " + operation;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String getDescricao() {
        return null;
    }

    @Override
    public boolean ehMeta() {
        return checkCompleted();
    }

    @Override
    public int custo() {
        return 1;
    }

    @Override
    public List<Estado> sucessores() {
        String operation;
//        operation = "Component: " + componentModel.getType() + " | Position: x" + row + " y" + col + " | Pins: pl" + componentModel.getLeftPins() + " pt" + componentModel.getTopPins() + " pr" + componentModel.getRightPins() + " pb" + componentModel.getBottomPins() + " | Operation: Roll";
//        operation = "Component: " + componentModel.getType() + " | Position: x" + row + " y" + nextCol + " | Operation: Move";
        List<Estado> suc = new LinkedList<Estado>();
        for(int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                ComponentModel[][] table = this.deepCopy(this.componentModels);
                ComponentModel componentModel = table[row][col];
                if (componentModel.getType() != Constant.ComponentTypes.none && componentModel.getType() != Constant.ComponentTypes.blocked) {
                    // Rotate
                    if (componentModel.getType() == Constant.ComponentTypes.rotate) {
                            ComponentModel[][] tempTable = this.deepCopy(table);
                            ComponentModel copyComponent = tempTable[row][col];
                        for (int r = 0; r < 4; r++) {
                            Integer leftPins = copyComponent.getLeftPins();
                            Integer topPins = copyComponent.getTopPins();
                            Integer rightPins = copyComponent.getRightPins();
                            Integer bottomPins = copyComponent.getBottomPins();

                            ComponentModel rotateComponent = new ComponentModel(topPins, bottomPins, rightPins, leftPins, Constant.ComponentTypes.rotate);
                            tempTable[row][col] = rotateComponent;
                            operation = "Component: " + rotateComponent.getType() + " | Position: x" + row + " y" + col + " | Pins: pl" + leftPins + " pt" + topPins + " pr" + rightPins + " pb" + leftPins + " | Operation: Roll";
                            suc.add(new SearchController(tempTable, operation));
                        }
                    } else if(componentModel.getType() == Constant.ComponentTypes.move) {
                        for (int x = 0; x < 4; x++) {
                            for (int y = 0; y < 4; y++) {
                                ComponentModel[][] tempTable = this.deepCopy(table);
                                if (tempTable[x][y].getType() == Constant.ComponentTypes.none) {
                                    tempTable[row][col] = new ComponentModel();
                                    tempTable[x][y] = componentModel;
                                    if (isComponentConnected(tempTable, x, y)) {
                                        operation = "Component: " + componentModel.getId() + " | Position: x" + x + " y" + y + " | Operation: Move";
                                        suc.add(new SearchController(tempTable, operation));
                                    };
                                }
                            }
                        }
                    }
                }
            }
        }
        return suc;
    }

    private boolean isComponentConnected(ComponentModel[][] table, int row, int col) {
        ComponentModel componentModel = table[row][col];
        if(col != 0) {
            //verificar esquerda
            if (componentModels[row][col].getLeftPins() == componentModels[row][col-1].getRightPins()) {
                return true;
            }
        }
        if(col != 3) {
            //verificar direita
            if (componentModels[row][col].getRightPins() == componentModels[row][col+1].getLeftPins()) {
                return true;
            }
        }
        if(row != 0) {
            //verificar cima
            if (componentModels[row][col].getTopPins() == componentModels[row-1][col].getBottomPins()) {
                return true;
            }
        }

        if(row != 3) {
            //verificar baixo
            if (componentModels[row][col].getBottomPins() == componentModels[row+1][col].getTopPins()) {
                return true;
            }
        }
        return false;
    }

    private ComponentModel[][] deepCopy(ComponentModel[][] table) {
        ComponentModel[][] clone = new ComponentModel[4][4];
        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                clone[row][col] = table[row][col];
            }
        }
        return clone;
    }
}
