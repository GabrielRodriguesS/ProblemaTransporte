/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;
import model.Reino;

/**
 *
 * @author Gabriel
 */
public class ReinoComparatorDummy implements Comparator<Reino> {

    @Override
    public int compare(Reino obj1, Reino obj2) {
        int somaObj1 = this.getPenalidade(obj1);
        int somaObj2 = this.getPenalidade(obj2);
        if (somaObj1 < somaObj2) {
            return -1;
        } else if (somaObj1 > somaObj2) {
            return 1;
        }
        return 0;
    }

    private int getPenalidade(Reino obj) {
    if (obj.getRotaFabricaUm() > obj.getRotaFabricaDois()) {
            return obj.getRotaFabricaDois();
        } else {
            return obj.getRotaFabricaUm();
        }
    }

}
