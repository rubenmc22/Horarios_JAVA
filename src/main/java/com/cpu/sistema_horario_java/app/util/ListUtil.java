package com.cpu.sistema_horario_java.app.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Collection of methods to ease the work with lists.
 * 
 * @author Luis A. Bastidas M.
 * @since 13-03-2020
 * @updated 18-03-2020
 * @version 0.1.2
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
     * Return a random Object of type <T> from provided list but ignoring any
     * element equals to another provided list of same type. If provided list to
     * ignore is equal to the list used to pick an element the method returns null
     * 
     * @param <T>
     * @param list
     * @param ignoreThis
     * @return
     */
    public static <T> Object getRandomElement(List<T> list, List<T> ignoreThis) {

        if (list.equals(ignoreThis)) {
            return null;
        }

        Object element = getRandomElement(list);

        while (ignoreThis.contains(element)) {
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
     * Returns true or false based on if provided element is the last of the
     * provided list of the same type
     * 
     * @param <T>
     * @param list
     * @param element
     * @return
     */
    public static <T> boolean isLast(List<T> list, Object element) {
        return list.get(list.size() - 1).equals(element);
    }

    /**
     * Returns true or false based on if provided element is the first of the
     * provided list of the same type
     * 
     * @param <T>
     * @param list
     * @param element
     * @return
     */
    public static <T> boolean isFirst(List<T> list, Object element) {
        return list.get(0).equals(element);
    }

    /**
     * Returns the element just before of provided one in a provided list of same
     * type. List is expected to be ordered but not enforced.
     * 
     * @param <T>
     * @param list
     * @param element
     * @return
     */
    public static <T> Object getPreviousElement(List<T> list, Object element) {
        return (T) list.get(list.indexOf(element) - 1);

    }

    /**
     * Returns the element just after of provided one in a provided list of same
     * type. List is expected to be ordered but not enforced.
     * 
     * @param <T>
     * @param list
     * @param element
     * @return
     */
    public static <T> Object getNextElement(List<T> list, Object element) {
        return (T) list.get(list.indexOf(element) + 1);

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