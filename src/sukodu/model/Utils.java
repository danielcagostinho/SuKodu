package sukodu.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static Integer[] randSeqGen() {
        Integer[] newArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> intList = Arrays.asList(newArr);
        Collections.shuffle(intList);
        intList.toArray(newArr);
        return newArr;
    }

    public static int randNumGen(int N) {
        return (int) (Math.random()*((N+1)));
    }

}
