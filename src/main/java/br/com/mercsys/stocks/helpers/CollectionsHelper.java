package br.com.mercsys.stocks.helpers;

import java.util.List;

import static java.util.Objects.nonNull;

public class CollectionsHelper {

    private CollectionsHelper() {}

    public static boolean isValid(List<?> list) {
        return nonNull(list) && !list.isEmpty();
    }

    public static boolean isValid(Object[] array) {
        return nonNull(array) && array.length > 0;
    }
}
