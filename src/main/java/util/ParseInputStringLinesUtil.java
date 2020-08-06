package util;

import enumvalue.LineSymbol;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParseInputStringLinesUtil {
    private ParseInputStringLinesUtil() {
    }

    public static List<String> getStringQueryList(String inputString) {
        if (inputString.isEmpty() || inputString.length() == 0) {
            throw new RuntimeException("Empty input line !");
        }
        List<Integer> idxMarkList = getIdxMark(inputString);
        List<String> stringQueryList = new LinkedList<>();
        for (int i = 0; i < idxMarkList.size(); i++) {
            stringQueryList.add(inputString.substring(idxMarkList.get(i),
                    i == idxMarkList.size() - 1
                            ? inputString.length()
                            : idxMarkList.get(i + 1)).trim());
        }
        return stringQueryList;
    }

    private static List<Integer> getIdxMark(String inputString) {
        List<Integer> outputArray = new LinkedList<>();
        for (int i = 0; i < LineSymbol.values().length; i++) {
            int idxPosition = 0;
            String queryMarkName = LineSymbol.values()[i].name();
            while ((idxPosition = inputString.indexOf(queryMarkName, idxPosition)) != -1) {
                outputArray.add(idxPosition);
                idxPosition = ++idxPosition;
            }
        }
        Collections.sort(outputArray);
        return outputArray;
    }
}
