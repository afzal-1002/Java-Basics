package com.collections.iterable_collection_map;

import java.util.*;

public class MapInterface {

    enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public void mapFunctions() {

        /*
         * ============================================================
         * 1. HASHMAP
         * ============================================================
         *
         * Stores KEY -> VALUE pairs
         * Keys are unique
         * Values can be duplicated
         * No guaranteed order
         *
         * put(), get(), remove(), containsKey() -> O(1) average
         */

        Map<Integer, String> map = new HashMap<>();

        // put()
        map.put(1, "Java");
        map.put(4, "Python");
        map.put(7, "C++");
        map.put(1, "C"); // Same key -> replaces "Java"
        map.put(3, "JavaScript");
        map.put(15, "Go");

        System.out.println("========== HASHMAP ==========");
        System.out.println("HashMap: " + map);

        // get()
        System.out.println("Get key 4: " + map.get(4));

        // getOrDefault()
        System.out.println("Get key 100 or default: " + map.getOrDefault(100, "Not Found"));

        // containsKey()
        System.out.println("Contains key 15: " + map.containsKey(15));

        // containsValue()
        System.out.println("Contains value Python: " + map.containsValue("Python"));

        // size()
        System.out.println("Size: " + map.size());

        // isEmpty()
        System.out.println("Is Empty: " + map.isEmpty());

        // putIfAbsent()
        map.putIfAbsent(4, "Ruby"); // Does nothing because key 4 already exists
        map.putIfAbsent(20, "Rust");
        System.out.println("After putIfAbsent: " + map);

        // replace()
        map.replace(7, "C++17");
        System.out.println("After replace: " + map);

        // replace(key, oldValue, newValue)
        System.out.println("Replace key 3 conditionally: " + map.replace(3, "JavaScript", "TypeScript"));
        System.out.println("After conditional replace: " + map);

        // remove(key)
        System.out.println("Remove key 1: " + map.remove(1));

        // remove(key, value)
        System.out.println("Remove key 4 with value Python: " + map.remove(4, "Python"));

        // putAll()
        Map<Integer, String> extraMap = new HashMap<>();
        extraMap.put(30, "Kotlin");
        extraMap.put(40, "Scala");
        map.putAll(extraMap);
        System.out.println("After putAll: " + map);

        // keySet()
        System.out.println("Keys: " + map.keySet());

        // values()
        System.out.println("Values: " + map.values());

        // entrySet()
        System.out.println("Entries: " + map.entrySet());

        // forEach()
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        // compute()
        map.compute(3, (key, value) -> value == null ? "New Value" : value + " Updated");
        System.out.println("After compute: " + map);

        // computeIfAbsent()
        map.computeIfAbsent(50, key -> "Spring");
        System.out.println("After computeIfAbsent: " + map);

        // computeIfPresent()
        map.computeIfPresent(50, (key, value) -> value + " Boot");
        System.out.println("After computeIfPresent: " + map);

        // merge()
        map.merge(50, "Java", (oldValue, newValue) -> oldValue + " + " + newValue);
        System.out.println("After merge: " + map);

        // replaceAll()
        map.replaceAll((key, value) -> value.toUpperCase());
        System.out.println("After replaceAll: " + map);

        // clear()
        // map.clear();


        /*
         * ============================================================
         * 2. LINKEDHASHMAP
         * ============================================================
         *
         * Stores KEY -> VALUE pairs
         * Keys are unique
         * Maintains insertion order
         *
         * put(), get(), remove(), containsKey() -> O(1) average
         */

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(4, "Python");
        linkedHashMap.put(7, "C++");
        linkedHashMap.put(1, "Java");
        linkedHashMap.put(3, "JavaScript");
        linkedHashMap.put(15, "Go");
        linkedHashMap.put(7, "C++17"); // Same key -> replaces old value

        System.out.println("\n========== LINKEDHASHMAP ==========");
        System.out.println("LinkedHashMap: " + linkedHashMap);

        // get()
        System.out.println("Get key 7: " + linkedHashMap.get(7));

        // containsKey()
        System.out.println("Contains key 5: " + linkedHashMap.containsKey(5));

        // containsValue()
        System.out.println("Contains value Java: " + linkedHashMap.containsValue("Java"));

        // size()
        System.out.println("Size: " + linkedHashMap.size());

        // isEmpty()
        System.out.println("Is Empty: " + linkedHashMap.isEmpty());

        // remove()
        System.out.println("Remove key 4: " + linkedHashMap.remove(4));

        // putIfAbsent()
        linkedHashMap.putIfAbsent(20, "Rust");
        System.out.println("After putIfAbsent: " + linkedHashMap);

        // putAll()
        Map<Integer, String> linkedExtra = new LinkedHashMap<>();
        linkedExtra.put(30, "Kotlin");
        linkedExtra.put(40, "Scala");
        linkedHashMap.putAll(linkedExtra);
        System.out.println("After putAll: " + linkedHashMap);

        // keySet()
        System.out.println("Keys: " + linkedHashMap.keySet());

        // values()
        System.out.println("Values: " + linkedHashMap.values());

        // entrySet()
        System.out.println("Entries: " + linkedHashMap.entrySet());

        // forEach()
        linkedHashMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));


        /*
         * ============================================================
         * 3. TREEMAP
         * ============================================================
         *
         * Stores KEY -> VALUE pairs
         * Keys are unique
         * Automatically sorts by KEY
         * Uses Red-Black Tree
         *
         * put(), get(), remove(), containsKey() -> O(log N)
         */

        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(4, "Python");
        treeMap.put(7, "C++");
        treeMap.put(1, "Java");
        treeMap.put(3, "JavaScript");
        treeMap.put(15, "Go");
        treeMap.put(10, "Spring");
        treeMap.put(20, "Docker");
        treeMap.put(30, "Kubernetes");
        treeMap.put(7, "C++17"); // Same key -> replaces value

        System.out.println("\n========== TREEMAP ==========");
        System.out.println("TreeMap: " + treeMap);

        /*
         * Common Map methods
         */

        // put()
        System.out.println("Put key 25: " + treeMap.put(25, "Kafka"));

        // get()
        System.out.println("Get key 10: " + treeMap.get(10));

        // remove()
        System.out.println("Remove key 4: " + treeMap.remove(4));

        // containsKey()
        System.out.println("Contains key 15: " + treeMap.containsKey(15));

        // containsValue()
        System.out.println("Contains value Java: " + treeMap.containsValue("Java"));

        // size()
        System.out.println("Size: " + treeMap.size());

        // isEmpty()
        System.out.println("Is Empty: " + treeMap.isEmpty());

        /*
         * TreeMap special methods
         */

        // firstKey() -> smallest key
        System.out.println("First Key: " + treeMap.firstKey());

        // lastKey() -> largest key
        System.out.println("Last Key: " + treeMap.lastKey());

        // firstEntry() -> smallest key-value entry
        System.out.println("First Entry: " + treeMap.firstEntry());

        // lastEntry() -> largest key-value entry
        System.out.println("Last Entry: " + treeMap.lastEntry());

        // lowerKey(x) -> <
        System.out.println("Lower Key than 10: " + treeMap.lowerKey(10));

        // floorKey(x) -> <=
        System.out.println("Floor Key of 10: " + treeMap.floorKey(10));

        // ceilingKey(x) -> >=
        System.out.println("Ceiling Key of 5: " + treeMap.ceilingKey(5));

        // higherKey(x) -> >
        System.out.println("Higher Key than 10: " + treeMap.higherKey(10));

        /*
         * Memory:
         *
         * lowerKey   <
         * floorKey   <=
         * ceilingKey >=
         * higherKey  >
         */

        // lowerEntry()
        System.out.println("Lower Entry than 10: " + treeMap.lowerEntry(10));

        // floorEntry()
        System.out.println("Floor Entry of 10: " + treeMap.floorEntry(10));

        // ceilingEntry()
        System.out.println("Ceiling Entry of 5: " + treeMap.ceilingEntry(5));

        // higherEntry()
        System.out.println("Higher Entry than 10: " + treeMap.higherEntry(10));

        // headMap(x) -> keys < x
        System.out.println("headMap(15): " + treeMap.headMap(15));

        // headMap(x, true) -> keys <= x
        System.out.println("headMap(15, true): " + treeMap.headMap(15, true));

        // tailMap(x) -> keys >= x
        System.out.println("tailMap(10): " + treeMap.tailMap(10));

        // tailMap(x, false) -> keys > x
        System.out.println("tailMap(10, false): " + treeMap.tailMap(10, false));

        // subMap(from, to) -> from <= key < to
        System.out.println("subMap(7, 20): " + treeMap.subMap(7, 20));

        // subMap(from, fromInclusive, to, toInclusive)
        System.out.println("subMap(7, true, 20, true): " + treeMap.subMap(7, true, 20, true));

        // descendingMap()
        System.out.println("Descending Map: " + treeMap.descendingMap());

        // navigableKeySet()
        System.out.println("Navigable Key Set: " + treeMap.navigableKeySet());

        // descendingKeySet()
        System.out.println("Descending Key Set: " + treeMap.descendingKeySet());

        // comparator()
        // Returns null when natural ordering is used
        System.out.println("Comparator: " + treeMap.comparator());

        // pollFirstEntry() -> return + remove smallest entry
        System.out.println("pollFirstEntry(): " + treeMap.pollFirstEntry());
        System.out.println("After pollFirstEntry(): " + treeMap);

        // pollLastEntry() -> return + remove largest entry
        System.out.println("pollLastEntry(): " + treeMap.pollLastEntry());
        System.out.println("After pollLastEntry(): " + treeMap);


        /*
         * ============================================================
         * 4. ENUMMAP
         * ============================================================
         *
         * Keys must be enum values
         * Keys are unique
         * Values can be duplicated
         * Keeps enum declaration order
         * Null keys are NOT allowed
         * Null values ARE allowed
         * Very efficient
         */

        System.out.println("\n========== ENUMMAP ==========");

        EnumMap<Day, String> enumMap = new EnumMap<>(Day.class);

        // put()
        enumMap.put(Day.MONDAY, "Java");
        enumMap.put(Day.TUESDAY, "Spring Boot");
        enumMap.put(Day.WEDNESDAY, "Docker");
        enumMap.put(Day.THURSDAY, "Kubernetes");
        enumMap.put(Day.FRIDAY, "Kafka");
        enumMap.put(Day.SATURDAY, "Football");
        enumMap.put(Day.SUNDAY, "Rest");

        System.out.println("EnumMap: " + enumMap);

        // get()
        System.out.println("Get Monday: " + enumMap.get(Day.MONDAY));

        // getOrDefault()
        System.out.println("Get Sunday or default: " + enumMap.getOrDefault(Day.SUNDAY, "No Plan"));

        // containsKey()
        System.out.println("Contains Monday key: " + enumMap.containsKey(Day.MONDAY));

        // containsValue()
        System.out.println("Contains Java value: " + enumMap.containsValue("Java"));

        // size()
        System.out.println("Size: " + enumMap.size());

        // isEmpty()
        System.out.println("Is Empty: " + enumMap.isEmpty());

        // putIfAbsent()
        enumMap.putIfAbsent(Day.MONDAY, "Python"); // Does nothing
        System.out.println("After putIfAbsent: " + enumMap);

        // replace()
        enumMap.replace(Day.MONDAY, "Java 21");
        System.out.println("After replace Monday: " + enumMap);

        // replace(key, oldValue, newValue)
        System.out.println("Conditional replace Tuesday: " + enumMap.replace(Day.TUESDAY, "Spring Boot", "Spring"));
        System.out.println("After conditional replace: " + enumMap);

        // remove()
        System.out.println("Remove Wednesday: " + enumMap.remove(Day.WEDNESDAY));

        // putAll()
        EnumMap<Day, String> extraEnumMap = new EnumMap<>(Day.class);
        extraEnumMap.put(Day.WEDNESDAY, "PostgreSQL");
        extraEnumMap.put(Day.SUNDAY, "Algorithms");
        enumMap.putAll(extraEnumMap);
        System.out.println("After putAll: " + enumMap);

        // keySet()
        System.out.println("Keys: " + enumMap.keySet());

        // values()
        System.out.println("Values: " + enumMap.values());

        // entrySet()
        System.out.println("Entries: " + enumMap.entrySet());

        // forEach()
        enumMap.forEach((day, activity) -> System.out.println(day + " -> " + activity));

        // compute()
        enumMap.compute(Day.MONDAY, (day, value) -> value == null ? "Java" : value + " Practice");
        System.out.println("After compute Monday: " + enumMap);

        // computeIfAbsent()
        enumMap.computeIfAbsent(Day.WEDNESDAY, day -> "SQL");
        System.out.println("After computeIfAbsent Wednesday: " + enumMap);

        // computeIfPresent()
        enumMap.computeIfPresent(Day.FRIDAY, (day, value) -> value + " Practice");
        System.out.println("After computeIfPresent Friday: " + enumMap);

        // merge()
        enumMap.merge(Day.SATURDAY, " + Gym", (oldValue, newValue) -> oldValue + newValue);
        System.out.println("After merge Saturday: " + enumMap);

        // replaceAll()
        enumMap.replaceAll((day, value) -> value.toUpperCase());
        System.out.println("After replaceAll: " + enumMap);

        // clone()
        EnumMap<Day, String> clonedEnumMap = enumMap.clone();
        System.out.println("Cloned EnumMap: " + clonedEnumMap);

        // clear()
        clonedEnumMap.clear();
        System.out.println("After clear cloned EnumMap: " + clonedEnumMap);
    }

    public static void main(String[] args) {

        MapInterface mapInterface = new MapInterface();

        mapInterface.mapFunctions();
    }
}
