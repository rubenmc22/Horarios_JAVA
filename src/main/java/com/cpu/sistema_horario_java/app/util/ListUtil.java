package com.cpu.sistema_horario_java.app.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by:  Luis A. Bastidas M.
 * Date:        13-03-2020
 * version:     0.1
 */
@SuppressWarnings("unchecked")
public class ListUtil {

    /**
     * Return a random Object of type <T> from provided list of same type.
     * 
     * @param <T>
     * @param list
     * @param ignoreThis
     * @return
     */
    public static <T> Object getRandomElement(List<T> list) {
        int randomIndex = new Random().nextInt(list.size());
        Object element = (T) list.get(randomIndex);
        return (T) element;

    }

    /**
     * Return a random Object of type <T> from provided list but ignoring any
     * element equals to another provided object of same type.
     * 
     * @param <T>
     * @param list
     * @param ignoreThis
     * @return
     */
    public static <T> Object getRandomElement(List<T> list, Object ignoreThis) {
        Object element = getRandomElement(list);

        while (element.equals(ignoreThis)) {
            element = getRandomElement(list);
        }
        return (T) element;
    }

    /**
     * Return a random Object of type <T> from provided list of same type. If
     * removeFromList is set to true this element will be removed from list before
     * return.
     * 
     * @param <T>
     * @param list
     * @param removeFromList
     * @return
     */
    public static <T> Object getRandomElement(List<T> list, Boolean removeFromList) {
        Object element = getRandomElement(list);

        if (removeFromList) {
            list.remove(element);
        }
        return (T) element;
    }

    /**
     * Return a random Object of type <T> from provided list but ignoring any
     * element equals to another provided object of same type. If removeFromList is
     * set to true this element will be removed from list before return.
     * 
     * @param <T>
     * @param list
     * @param ignoreThis
     * @param removeFromList
     * @return
     */
    public static <T> Object getRandomElement(List<T> list, Object ignoreThis, Boolean removeFromList) {
        Object element = getRandomElement(list, ignoreThis);

        if (removeFromList) {
            list.remove(element);
        }
        return (T) element;
    }

    /**
     * Turns a HashSet of type <T> to a List of same type.
     * 
     * @param <T>
     * @param set
     * @return
     */
    public static <T> List<T> toList(Set<T> set) {
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

}