/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Comparator;

/**
 *
 * @author watso
 */
public class Sorting {

    int sortField;

    public Sorting(int sortField) {
        this.sortField = sortField;
    }

    private Comparator compareWord = new Comparator<FootballPlayer>() {

        @Override
        public int compare(FootballPlayer t, FootballPlayer t1) {

            return (t.getAttribute(sortField).compareTo(t1.getAttribute(sortField)));

        }
    };

    private Comparator compareNumber = new Comparator<FootballPlayer>() {
        @Override
        public int compare(FootballPlayer t, FootballPlayer t1) {

            if (Integer.parseInt(t.getAttribute(sortField)) < Integer.parseInt(t1.getAttribute(sortField))) {
                return -1;
            }
            if (Integer.parseInt(t.getAttribute(sortField)) == Integer.parseInt(t1.getAttribute(sortField))) {
                return 0;
            }
            return 1;
        }
    };

    private Comparator compareHeights = new Comparator<FootballPlayer>() {
        @Override
        public int compare(FootballPlayer t, FootballPlayer t1) {
            if (t.getHeight().getFeet() < t1.getHeight().getFeet()) {
                return -1;
            }
            if (t.getHeight().getFeet() > t1.getHeight().getFeet()) {
                return 1;
            }
            if (t.getHeight().getFeet() == t1.getHeight().getFeet()) {

                if (t.getHeight().getInches() < t1.getHeight().getInches()) {
                    return -1;
                }
                if (t.getHeight().getInches() > t1.getHeight().getInches()) {
                    return 1;
                }
            }
            return 0;
        }
    };

    public Comparator getCompareHeight() {
        return compareHeights;
    }

    public Comparator getCompareWord() {
        return compareWord;
    }

    public Comparator getCompareNumber() {
        return compareNumber;
    }

}


/*public ArrayList<TableMember> quickSort(ArrayList<TableMember> arr, int low, int high, int field) {
        
        if (low < high) {
            /* pi is partitioning index, arr[pi] is now
           at right place 
            int pi = partition(arr, low, high, field);

            quickSort(arr, low, pi - 1,field);
            quickSort(arr, pi + 1, high,field);
            
        }
        return arr;
    }

    public int partition(ArrayList<TableMember> arr, int low, int high, int field) {
        // pivot (Element to be placed at right position)
        int pivot = Integer.parseInt(arr.get(high).getAttribute(field));
        System.out.println(pivot);
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            
            if (compareNumber.compare(Integer.parseInt(arr.get(j).getAttribute(field)), pivot) < 0) {
        
                i++;

                TableMember item1 = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, item1);

            }
        }
        TableMember item2 = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, item2);
        return (1 + 1);
    }
 */
