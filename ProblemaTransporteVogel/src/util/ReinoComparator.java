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
public class ReinoComparator implements Comparator<Reino> {

    @Override
    public int compare(Reino obj1, Reino obj2) {
        int somaObj1 = obj1.getRotaFabricaDois() + obj1.getRotaFabricaUm();
        int somaObj2 = obj2.getRotaFabricaDois() + obj2.getRotaFabricaUm();
        return somaObj1 - somaObj2;
    }

}
