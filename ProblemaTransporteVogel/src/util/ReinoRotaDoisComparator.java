package util;

import java.util.Comparator;
import model.Reino;

/**
 *
 * @author Gabriel
 */
public class ReinoRotaDoisComparator implements Comparator<Reino> {

    @Override
    public int compare(Reino obj1, Reino obj2) {
        if (obj1.getRotaFabricaDois() < obj2.getRotaFabricaDois()) {
            return -1;
        } else if (obj1.getRotaFabricaDois() > obj2.getRotaFabricaDois()) {
            return 1;
        }
        return 0;
    }
}
