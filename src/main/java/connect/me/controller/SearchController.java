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
        if (o instanceof SearchController) {
            SearchController e = (SearchController)o;
            return e.toString().hashCode() == toString().hashCode();
        }
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
        List<Estado> suc = new LinkedList<Estado>();
        for(int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                ComponentModel[][] table = this.deepCopy();
                ComponentModel componentModel = table[row][col];
                if (componentModel.getType() != Constant.ComponentTypes.none && componentModel.getType() != Constant.ComponentTypes.blocked) {
                    // Rotate
                    if (componentModel.getType() == Constant.ComponentTypes.rotate) {
                        componentModel.rotateRight();
                        operation = "Component: " + componentModel.getType() + " | Position: x" + row + " y" + col + " | Pins: pl" + componentModel.getLeftPins() + " pt" + componentModel.getTopPins() + " pr" + componentModel.getRightPins() + " pb" + componentModel.getBottomPins() + " | Operation: Roll";
                        suc.add(new SearchController(table, operation));
                    } else if(componentModel.getType() == Constant.ComponentTypes.move) {
                        //Move down
                        int nextRow;
                        int backRow;
                        if (row == 3) {
                            nextRow = 0;
                        } else {
                            nextRow = row + 1;
                        }

                        if (row == 0) {
                            backRow = 3;
                        } else {
                            backRow = row - 1;
                        }
                        table[row][col] = new ComponentModel();
                        table[nextRow][col] = componentModel;
                        operation = "Component: " + componentModel.getType() + " | Position: x" + nextRow + " y" + col + " | Operation: Move";
                        suc.add(new SearchController(table, operation));
                        table[nextRow][col] = new ComponentModel();
                        table[backRow][col] = componentModel;

                        //Move Right
                        int nextCol;
                        int backCol;
                        if (col == 3) {
                            nextCol = 0;
                        } else {
                            nextCol = col + 1;
                        }

                        if (col == 0) {
                            backCol = 3;
                        } else {
                            backCol = col - 1;
                        }

                        table[row][col] = new ComponentModel();
                        table[row][nextCol] = componentModel;
                        operation = "Component: " + componentModel.getType() + " | Position: x" + row + " y" + nextCol + " | Operation: Move";
                        suc.add(new SearchController(table, operation));
                        table[row][nextCol] = new ComponentModel();
                        table[row][backCol] = componentModel;
                    }
                }
            }
        }
        return suc;
    }

    private ComponentModel transformInNoneComponent() {
        return new ComponentModel();
    }

    private ComponentModel[][] deepCopy() {
        ComponentModel[][] clone = new ComponentModel[4][4];
        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                clone[row][col] = this.componentModels[row][col];
            }
        }
        return clone;
    }
}
