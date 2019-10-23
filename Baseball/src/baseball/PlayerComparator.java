/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

import java.util.Comparator;

/**
 *
 * @author garretttolbert
 */
public class PlayerComparator implements Comparator<Player> {
    
     /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     * @throws NullPointerException if an argument is null and this
     *         comparator does not permit null arguments
     * @throws ClassCastException if the argument's types prevent them from
     *         being compared by this comparator.
     */
    @Override
    public int compare(Player o1, Player o2) {
         return o1.getName().compareTo(o2.getName());
    }
    
}

