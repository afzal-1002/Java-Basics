package com.collections.iterable_collection_set;

import java.util.*;

public class SetInterface {

    enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public void setFunctions() {

        /*
         * ============================================================
         * 1. HASHSET
         * ============================================================
         *
         * Unique elements
         * No guaranteed order
         * add(), remove(), contains() -> O(1) average
         */

        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.add(4);
        set.add(7);
        set.add(1); // Duplicate - ignored
        set.add(3);
        set.add(15);

        System.out.println("========== HASHSET ==========");
        System.out.println("HashSet: " + set);

        // remove()
        System.out.println("Remove 1: " + set.remove(1));

        // contains()
        System.out.println("Contains 15: " + set.contains(15));

        // containsAll()
        System.out.println("Contains 4 and 5: " + set.containsAll(List.of(4, 5)));

        // size()
        System.out.println("Size: " + set.size());

        // isEmpty()
        System.out.println("Is Empty: " + set.isEmpty());

        // addAll()
        set.addAll(List.of(20, 30, 40));
        System.out.println("After addAll: " + set);

        // removeAll()
        set.removeAll(List.of(20, 30));
        System.out.println("After removeAll: " + set);

        // retainAll()
        set.retainAll(List.of(3, 4, 7, 100));
        System.out.println("After retainAll: " + set);

        // removeIf()
        set.removeIf(number -> number < 5);
        System.out.println("After removeIf(number < 5): " + set);

        // forEach()
        set.forEach(number -> System.out.println("HashSet value: " + number));

        // clear()
        // set.clear();


        /*
         * ============================================================
         * 2. LINKEDHASHSET
         * ============================================================
         *
         * Unique elements
         * Maintains insertion order
         * add(), remove(), contains() -> O(1) average
         */

        Set<Integer> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add(4);
        linkedHashSet.add(7);
        linkedHashSet.add(1);
        linkedHashSet.add(3);
        linkedHashSet.add(15);
        linkedHashSet.add(7); // Duplicate - ignored

        System.out.println("\n========== LINKEDHASHSET ==========");
        System.out.println("LinkedHashSet: " + linkedHashSet);

        // remove()
        System.out.println("Remove 4: " + linkedHashSet.remove(4));

        // size()
        System.out.println("Size: " + linkedHashSet.size());

        // contains()
        System.out.println("Contains 5: " + linkedHashSet.contains(5));

        // isEmpty()
        System.out.println("Is Empty: " + linkedHashSet.isEmpty());

        // addAll()
        linkedHashSet.addAll(List.of(20, 30, 40));
        System.out.println("After addAll: " + linkedHashSet);


        /*
         * ============================================================
         * 3. TREESET
         * ============================================================
         *
         * Unique elements
         * Automatically sorted
         * Uses Red-Black Tree
         *
         * add(), remove(), contains() -> O(log N)
         */

        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(4);
        treeSet.add(7);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(15);
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(30);
        treeSet.add(7); // Duplicate - ignored

        System.out.println("\n========== TREESET ==========");
        System.out.println("TreeSet: " + treeSet);

        /*
         * Common Set methods
         */

        // add()
        System.out.println("Add 25: " + treeSet.add(25));

        // remove()
        System.out.println("Remove 4: " + treeSet.remove(4));

        // contains()
        System.out.println("Contains 15: " + treeSet.contains(15));

        // size()
        System.out.println("Size: " + treeSet.size());

        // isEmpty()
        System.out.println("Is Empty: " + treeSet.isEmpty());

        // containsAll()
        System.out.println("Contains 7 and 15: " + treeSet.containsAll(List.of(7, 15)));

        /*
         * TreeSet special methods
         */

        // first() -> smallest element
        System.out.println("First: " + treeSet.first());

        // last() -> largest element
        System.out.println("Last: " + treeSet.last());

        // lower(x) -> <
        System.out.println("Lower than 10: " + treeSet.lower(10));

        // floor(x) -> <=
        System.out.println("Floor of 10: " + treeSet.floor(10));

        // ceiling(x) -> >=
        System.out.println("Ceiling of 5: " + treeSet.ceiling(5));

        // higher(x) -> >
        System.out.println("Higher than 10: " + treeSet.higher(10));

        /*
         * Memory:
         *
         * lower   <
         * floor   <=
         * ceiling >=
         * higher  >
         */

        // headSet(x) -> elements < x
        System.out.println("headSet(15): " + treeSet.headSet(15));

        // headSet(x, true) -> elements <= x
        System.out.println("headSet(15, true): " + treeSet.headSet(15, true));

        // tailSet(x) -> elements >= x
        System.out.println("tailSet(10): " + treeSet.tailSet(10));

        // tailSet(x, false) -> elements > x
        System.out.println("tailSet(10, false): " + treeSet.tailSet(10, false));

        // subSet(from, to) -> from <= x < to
        System.out.println("subSet(7, 20): " + treeSet.subSet(7, 20));

        // subSet(from, fromInclusive, to, toInclusive)
        System.out.println("subSet(7, true, 20, true): " + treeSet.subSet(7, true, 20, true));

        // descendingSet()
        System.out.println("Descending Set: " + treeSet.descendingSet());

        // descendingIterator()
        Iterator<Integer> descendingIterator = treeSet.descendingIterator();

        System.out.print("Descending Iterator: ");

        while (descendingIterator.hasNext()) {
            System.out.print(descendingIterator.next() + " ");
        }

        System.out.println();

        // comparator()
        // Returns null if natural ordering is being used
        System.out.println("Comparator: " + treeSet.comparator());

        // pollFirst() -> return + remove smallest element
        System.out.println("pollFirst(): " + treeSet.pollFirst());
        System.out.println("After pollFirst(): " + treeSet);

        // pollLast() -> return + remove largest element
        System.out.println("pollLast(): " + treeSet.pollLast());
        System.out.println("After pollLast(): " + treeSet);


        /*
         * ============================================================
         * 4. ENUMSET
         * ============================================================
         *
         * Only works with enum values
         * No duplicates
         * Does not allow null
         * Very efficient
         * Keeps enum declaration order
         */

        System.out.println("\n========== ENUMSET ==========");

        /*
         * EnumSet.of()
         * Create EnumSet using selected enum values
         */

        EnumSet<Day> selectedDays = EnumSet.of(Day.MONDAY, Day.WEDNESDAY, Day.FRIDAY);

        System.out.println("Selected Days: " + selectedDays);

        // add()
        selectedDays.add(Day.SATURDAY);
        System.out.println("After adding Saturday: " + selectedDays);

        // remove()
        selectedDays.remove(Day.WEDNESDAY);
        System.out.println("After removing Wednesday: " + selectedDays);

        // contains()
        System.out.println("Contains Monday: " + selectedDays.contains(Day.MONDAY));

        // size()
        System.out.println("Size: " + selectedDays.size());

        // isEmpty()
        System.out.println("Is Empty: " + selectedDays.isEmpty());


        /*
         * EnumSet.noneOf()
         * Create an empty EnumSet
         */

        EnumSet<Day> emptyDays = EnumSet.noneOf(Day.class);

        System.out.println("noneOf(): " + emptyDays);


        /*
         * EnumSet.allOf()
         * Contains every enum constant
         */

        EnumSet<Day> allDays = EnumSet.allOf(Day.class);

        System.out.println("allOf(): " + allDays);


        /*
         * EnumSet.range()
         * Includes both starting and ending enum values
         */

        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);

        System.out.println("Weekdays: " + weekdays);


        /*
         * EnumSet.complementOf()
         * Returns everything not contained in the provided EnumSet
         */

        EnumSet<Day> weekend = EnumSet.complementOf(weekdays);

        System.out.println("Weekend: " + weekend);


        /*
         * EnumSet.copyOf()
         */

        EnumSet<Day> copiedDays = EnumSet.copyOf(weekdays);

        System.out.println("Copied weekdays: " + copiedDays);


        // addAll()
        selectedDays.addAll(EnumSet.of(Day.TUESDAY, Day.THURSDAY));
        System.out.println("After addAll: " + selectedDays);

        // containsAll()
        System.out.println("Contains Monday and Tuesday: " + selectedDays.containsAll(EnumSet.of(Day.MONDAY, Day.TUESDAY)));

        // retainAll()
        EnumSet<Day> retainDays = EnumSet.copyOf(allDays);

        retainDays.retainAll(weekdays);

        System.out.println("After retainAll(weekdays): " + retainDays);


        // removeAll()
        EnumSet<Day> removeExample = EnumSet.copyOf(allDays);

        removeExample.removeAll(weekend);

        System.out.println("After removeAll(weekend): " + removeExample);


        // removeIf()
        EnumSet<Day> removeIfExample = EnumSet.allOf(Day.class);

        removeIfExample.removeIf(day -> day == Day.SATURDAY || day == Day.SUNDAY);

        System.out.println("After removeIf(weekend): " + removeIfExample);


        // forEach()
        weekdays.forEach(day -> System.out.println("Weekday: " + day));


        // clone()
        EnumSet<Day> clonedDays = weekdays.clone();

        System.out.println("Cloned EnumSet: " + clonedDays);


        // clear()
        emptyDays.clear();

        System.out.println("After clear(): " + emptyDays);
    }


    public static void main(String[] args) {

        SetInterface setInterface = new SetInterface();

        setInterface.setFunctions();
    }
}