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
public class ReinoPenalidadeComparator implements Comparator<Reino> {

    @Override
    public int compare(Reino obj1, Reino obj2) {
        if (obj1.getPenalidade() < obj2.getPenalidade()) {
            return -1;
        } else if (obj1.getPenalidade() > obj2.getPenalidade()) {
            return 1;
        }
        return 0;
    }

}
