# Java Set and Map Hierarchy — Features, Methods, and Big-O Time Complexity

## 1. Set and Map Have Similar Naming Families

Java has very similar names on the `Set` side and the `Map` side.

| Set Side | Map Side | Type |
|---|---|---|
| `Set` | `Map` | Interface |
| `HashSet` | `HashMap` | Class |
| `LinkedHashSet` | `LinkedHashMap` | Class |
| `SortedSet` | `SortedMap` | Interface |
| `NavigableSet` | `NavigableMap` | Interface |
| `TreeSet` | `TreeMap` | Class |
| `EnumSet` | `EnumMap` | Class |
| `ConcurrentSkipListSet` | `ConcurrentSkipListMap` | Class |

### Short Memory Trick

```text
Hash        -> HashSet / HashMap
LinkedHash  -> LinkedHashSet / LinkedHashMap
Sorted      -> SortedSet / SortedMap
Navigable   -> NavigableSet / NavigableMap
Tree        -> TreeSet / TreeMap
Enum        -> EnumSet / EnumMap
ConcurrentSkipList -> Set / Map versions
```

---

# 2. Main Difference Between Set and Map

## Set

A `Set` stores unique elements.

```java
Set<String> set = new HashSet<>();

set.add("Java");
set.add("Python");
set.add("Java");
```

The second `"Java"` is ignored.

```text
Set -> unique elements
```

---

## Map

A `Map` stores key-value pairs.

```java
Map<String, Integer> map = new HashMap<>();

map.put("Java", 10);
map.put("Python", 20);
```

Conceptually:

```text
Java   -> 10
Python -> 20
```

Important:

```text
Map -> unique keys
Map -> values may be duplicated
```

Example:

```java
map.put("Java", 10);
map.put("Python", 10);
```

This is valid because the keys are different.

---

# 3. Simplified Hierarchy

```text
SET SIDE                            MAP SIDE

Set                                 Map
 |                                   |
 +-- HashSet                         +-- HashMap
 |     |                             |     |
 |     +-- LinkedHashSet             |     +-- LinkedHashMap
 |
 +-- SortedSet                       +-- SortedMap
       |                                   |
       +-- NavigableSet                    +-- NavigableMap
              |                                   |
              +-- TreeSet                         +-- TreeMap
```

Additional specialized implementations:

```text
EnumSet                  <-> EnumMap
ConcurrentSkipListSet    <-> ConcurrentSkipListMap
```

---

# 4. Interface vs Class

These are interfaces:

```text
Set
SortedSet
NavigableSet

Map
SortedMap
NavigableMap
```

These are classes:

```text
HashSet
LinkedHashSet
TreeSet
EnumSet

HashMap
LinkedHashMap
TreeMap
EnumMap
```

Example:

```java
Set<Integer> set = new HashSet<>();
Map<String, Integer> map = new HashMap<>();
```

You cannot do:

```java
Map<String, Integer> map = new Map<>(); // WRONG
```

because `Map` is an interface.

---

# 5. HashSet and HashMap

## HashSet

```java
Set<Integer> set = new HashSet<>();
```

Features:

```text
Unique elements       YES
Insertion order       NO guarantee
Sorted                NO
Fast lookup           YES
null                   One null allowed
Thread-safe           NO
```

Typical average complexity:

```text
add()       -> O(1)
remove()    -> O(1)
contains()  -> O(1)
```

---

## HashMap

```java
Map<String, Integer> map = new HashMap<>();
```

Features:

```text
Unique keys            YES
Duplicate values       YES
Insertion order        NO guarantee
Sorted                 NO
Fast lookup            YES
One null key           YES
Multiple null values   YES
Thread-safe            NO
```

### Memory Trick

```text
HashSet -> unique elements
HashMap -> unique keys + values
```

---

# 6. HashSet Internally Uses HashMap

A `HashSet` is internally backed by a `HashMap`.

Conceptually:

```java
HashSet<String> set = new HashSet<>();
set.add("Java");
```

works approximately like:

```text
HashMap:

"Java" -> PRESENT
```

So:

```text
HashSet element
      ↓
HashMap key
```

This is why `HashSet` and `HashMap` have very similar hashing behavior.

---

# 7. Common Map Methods

Create a map:

```java
Map<String, Integer> map = new HashMap<>();
```

## `put(K key, V value)`

```java
map.put("Java", 10);
```

Adds a key-value pair.

If the key already exists:

```java
map.put("Java", 100);
```

the old value is replaced.

Average for `HashMap`:

```text
O(1)
```

---

## `get(key)`

```java
map.get("Java");
```

Average for `HashMap`:

```text
O(1)
```

---

## `getOrDefault(key, defaultValue)`

```java
map.getOrDefault("C++", 0);
```

Returns the stored value if the key exists, otherwise the default.

Average:

```text
O(1)
```

---

## `containsKey(key)`

```java
map.containsKey("Java");
```

Average:

```text
O(1)
```

---

## `containsValue(value)`

```java
map.containsValue(100);
```

Java generally has to inspect values.

```text
O(N)
```

### Important Interview Difference

```text
containsKey()   -> O(1) average
containsValue() -> O(N)
```

Why?

```text
Hashing is based on the KEY.
```

---

## `remove(key)`

```java
map.remove("Java");
```

Average:

```text
O(1)
```

---

## `remove(key, value)`

```java
map.remove("Java", 100);
```

Removes the mapping only if the key maps to that value.

Average:

```text
O(1)
```

---

## `replace(key, value)`

```java
map.replace("Java", 500);
```

Average:

```text
O(1)
```

---

## `replace(key, oldValue, newValue)`

```java
map.replace("Java", 100, 500);
```

Average:

```text
O(1)
```

---

## `putIfAbsent(key, value)`

```java
map.putIfAbsent("Java", 100);
```

Adds only if the key is absent.

Average:

```text
O(1)
```

---

## `size()`

```java
map.size();
```

```text
O(1)
```

---

## `isEmpty()`

```java
map.isEmpty();
```

```text
O(1)
```

---

## `clear()`

```java
map.clear();
```

```text
O(N)
```

---

# 8. Map Views

Suppose:

```java
Map<String, Integer> map = new HashMap<>();

map.put("Java", 10);
map.put("Python", 20);
map.put("C++", 30);
```

## `keySet()`

```java
Set<String> keys = map.keySet();
```

Creates a view of keys.

```text
Creating view -> O(1)
Iterating     -> O(N)
```

---

## `values()`

```java
Collection<Integer> values = map.values();
```

Creates a values view.

```text
Creating view -> O(1)
Iterating     -> O(N)
```

---

## `entrySet()`

```java
Set<Map.Entry<String, Integer>> entries = map.entrySet();
```

Use this when you need both key and value.

```java
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

Iteration:

```text
O(N)
```

---

# 9. `forEach()`

```java
map.forEach((key, value) -> System.out.println(key + " -> " + value));
```

Visits every entry.

```text
O(N)
```

---

# 10. `putAll()`

```java
map1.putAll(map2);
```

If `map2` has `K` entries:

```text
O(K)
```

average for `HashMap`.

---

# 11. `compute()`

```java
map.compute("Java", (key, value) -> value == null ? 1 : value + 1);
```

Average map lookup:

```text
O(1)
```

plus the cost of the function.

---

# 12. `computeIfAbsent()`

```java
map.computeIfAbsent("Java", key -> 100);
```

Only computes/inserts if key is missing.

Average:

```text
O(1)
```

plus function cost.

Very common pattern:

```java
Map<String, List<Integer>> map = new HashMap<>();

map.computeIfAbsent("Java", key -> new ArrayList<>()).add(10);
```

---

# 13. `computeIfPresent()`

```java
map.computeIfPresent("Java", (key, value) -> value + 10);
```

Runs only if key already exists.

Average:

```text
O(1)
```

plus function cost.

---

# 14. `merge()`

Useful for counters.

```java
Map<String, Integer> count = new HashMap<>();

count.merge("Java", 1, Integer::sum);
count.merge("Java", 1, Integer::sum);
```

Result:

```text
Java -> 2
```

Average:

```text
O(1)
```

plus merge-function cost.

---

# 15. `replaceAll()`

```java
map.replaceAll((key, value) -> value * 2);
```

Touches every entry.

```text
O(N)
```

---

# 16. HashMap Time Complexity Summary

| Method | Average Complexity | Reason |
|---|---:|---|
| `put()` | `O(1)` | Hash lookup |
| `get()` | `O(1)` | Hash lookup |
| `getOrDefault()` | `O(1)` | Hash lookup |
| `containsKey()` | `O(1)` | Hash lookup |
| `containsValue()` | `O(N)` | Scan values |
| `remove(key)` | `O(1)` | Hash lookup |
| `remove(key,value)` | `O(1)` | Hash lookup |
| `putIfAbsent()` | `O(1)` | Hash lookup |
| `replace()` | `O(1)` | Hash lookup |
| `compute()` | `O(1)` + function | Hash lookup |
| `computeIfAbsent()` | `O(1)` + function | Hash lookup |
| `computeIfPresent()` | `O(1)` + function | Hash lookup |
| `merge()` | `O(1)` + function | Hash lookup |
| `size()` | `O(1)` | Stored size |
| `isEmpty()` | `O(1)` | Stored size |
| `keySet()` | `O(1)` view creation | Iteration `O(N)` |
| `values()` | `O(1)` view creation | Iteration `O(N)` |
| `entrySet()` | `O(1)` view creation | Iteration `O(N)` |
| `forEach()` | `O(N)` | Visits all entries |
| `putAll(K)` | `O(K)` average | Inserts K entries |
| `replaceAll()` | `O(N)` | Visits every entry |
| `clear()` | `O(N)` | Clears references |

### Memory Trick

```text
HashMap KEY operation -> O(1) average
Search by VALUE       -> O(N)
Visit all entries     -> O(N)
```

---

# 17. LinkedHashMap

```java
Map<String, Integer> map = new LinkedHashMap<>();
```

It combines:

```text
HashMap + linked ordering
```

Features:

```text
Unique keys            YES
Duplicate values       YES
Insertion order        YES by default
Fast key lookup        YES
Sorted                 NO
One null key           YES
Multiple null values   YES
Thread-safe            NO
```

Example:

```java
Map<String, Integer> map = new LinkedHashMap<>();

map.put("C", 3);
map.put("A", 1);
map.put("B", 2);

System.out.println(map);
```

Output:

```text
{C=3, A=1, B=2}
```

---

# 18. LinkedHashMap Time Complexity

| Method | Average Complexity |
|---|---:|
| `put()` | `O(1)` |
| `get()` | `O(1)` |
| `getOrDefault()` | `O(1)` |
| `containsKey()` | `O(1)` |
| `containsValue()` | `O(N)` |
| `remove()` | `O(1)` |
| `putIfAbsent()` | `O(1)` |
| `replace()` | `O(1)` |
| `compute()` | `O(1)` + function |
| `computeIfAbsent()` | `O(1)` + function |
| `computeIfPresent()` | `O(1)` + function |
| `merge()` | `O(1)` + function |
| `size()` | `O(1)` |
| `isEmpty()` | `O(1)` |
| iteration | `O(N)` |
| `forEach()` | `O(N)` |
| `clear()` | `O(N)` |

### Memory

```text
LinkedHashMap = HashMap + ORDER
```

---

# 19. LinkedHashMap Access Order

`LinkedHashMap` can also maintain access order.

```java
Map<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
```

The third argument:

```text
true
```

means accessed entries move according to access order.

This is useful when implementing:

```text
LRU cache
```

---

# 20. SortedMap

`SortedMap` is an interface.

```java
SortedMap<Integer, String> map = new TreeMap<>();
```

Its main rule:

```text
Keys are sorted.
```

Important:

```text
SortedMap itself does not have one fixed complexity.
The implementation decides the complexity.
```

The most common implementation is:

```text
TreeMap
```

which usually gives:

```text
O(log N)
```

for key-based operations.

---

# 21. SortedMap Important Methods

Suppose:

```java
SortedMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
```

## `firstKey()`

```java
map.firstKey();
```

Returns the smallest key.

---

## `lastKey()`

```java
map.lastKey();
```

Returns the largest key.

---

## `headMap(toKey)`

```java
map.headMap(30);
```

Returns entries with keys:

```text
< 30
```

---

## `tailMap(fromKey)`

```java
map.tailMap(20);
```

Returns keys:

```text
>= 20
```

---

## `subMap(fromKey, toKey)`

```java
map.subMap(20, 40);
```

Returns keys:

```text
20 <= key < 40
```

---

## `comparator()`

```java
map.comparator();
```

Returns the comparator.

If natural ordering is used:

```text
null
```

---

# 22. NavigableMap

`NavigableMap` extends `SortedMap`.

```text
Map
 ↓
SortedMap
 ↓
NavigableMap
 ↓
TreeMap
```

It adds navigation methods.

---

# 23. NavigableMap Navigation Methods

Suppose:

```java
NavigableMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
map.put(50, "E");
```

## `lowerKey(key)`

```java
map.lowerKey(30);
```

Returns:

```text
20
```

Meaning:

```text
<
```

---

## `floorKey(key)`

```java
map.floorKey(30);
```

Returns:

```text
30
```

Meaning:

```text
<=
```

---

## `ceilingKey(key)`

```java
map.ceilingKey(30);
```

Returns:

```text
30
```

Meaning:

```text
>=
```

---

## `higherKey(key)`

```java
map.higherKey(30);
```

Returns:

```text
40
```

Meaning:

```text
>
```

### Memory Trick

```text
lowerKey   <
floorKey   <=

ceilingKey >=
higherKey  >
```

---

# 24. NavigableMap Entry Methods

These return the whole `Map.Entry`.

```java
map.lowerEntry(30);
map.floorEntry(30);
map.ceilingEntry(30);
map.higherEntry(30);
```

For a `TreeMap`:

```text
O(log N)
```

---

# 25. `firstEntry()` and `lastEntry()`

```java
map.firstEntry();
map.lastEntry();
```

Return the first and last key-value entries.

---

# 26. `pollFirstEntry()`

```java
map.pollFirstEntry();
```

Returns and removes the smallest-key entry.

For `TreeMap`:

```text
O(log N)
```

---

# 27. `pollLastEntry()`

```java
map.pollLastEntry();
```

Returns and removes the largest-key entry.

For `TreeMap`:

```text
O(log N)
```

---

# 28. `descendingMap()`

```java
map.descendingMap();
```

Returns a reverse-order view.

---

# 29. `navigableKeySet()`

```java
map.navigableKeySet();
```

Returns a `NavigableSet` view of keys.

---

# 30. `descendingKeySet()`

```java
map.descendingKeySet();
```

Returns keys in reverse order.

---

# 31. TreeMap

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

Features:

```text
Unique keys          YES
Duplicate values     YES
Keys sorted          YES
Insertion order      NO
Thread-safe          NO
```

TreeMap uses a:

```text
Red-Black Tree
```

So common key operations are:

```text
put()         -> O(log N)
get()         -> O(log N)
remove()      -> O(log N)
containsKey() -> O(log N)
```

---

# 32. TreeMap Example

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(50, "E");
map.put(10, "A");
map.put(30, "C");
map.put(20, "B");
map.put(40, "D");

System.out.println(map);
```

Output:

```text
{10=A, 20=B, 30=C, 40=D, 50=E}
```

Keys are sorted automatically.

---

# 33. TreeMap Time Complexity

| Method | Complexity |
|---|---:|
| `put()` | `O(log N)` |
| `get()` | `O(log N)` |
| `getOrDefault()` | `O(log N)` |
| `containsKey()` | `O(log N)` |
| `containsValue()` | `O(N)` |
| `remove()` | `O(log N)` |
| `putIfAbsent()` | `O(log N)` |
| `replace()` | `O(log N)` |
| `compute()` | `O(log N)` + function |
| `computeIfAbsent()` | `O(log N)` + function |
| `computeIfPresent()` | `O(log N)` + function |
| `merge()` | `O(log N)` + function |
| `firstKey()` | `O(log N)` conceptually |
| `lastKey()` | `O(log N)` conceptually |
| `lowerKey()` | `O(log N)` |
| `floorKey()` | `O(log N)` |
| `ceilingKey()` | `O(log N)` |
| `higherKey()` | `O(log N)` |
| `lowerEntry()` | `O(log N)` |
| `floorEntry()` | `O(log N)` |
| `ceilingEntry()` | `O(log N)` |
| `higherEntry()` | `O(log N)` |
| `pollFirstEntry()` | `O(log N)` |
| `pollLastEntry()` | `O(log N)` |
| `size()` | `O(1)` |
| `isEmpty()` | `O(1)` |
| iteration | `O(N)` |
| `forEach()` | `O(N)` |
| `replaceAll()` | `O(N)` |
| `clear()` | `O(N)` |

### Memory

```text
TreeMap = SORTED KEYS + O(log N)
```

---

# 34. TreeSet vs TreeMap Matching Methods

| TreeSet | TreeMap |
|---|---|
| `first()` | `firstKey()` / `firstEntry()` |
| `last()` | `lastKey()` / `lastEntry()` |
| `lower(x)` | `lowerKey(x)` / `lowerEntry(x)` |
| `floor(x)` | `floorKey(x)` / `floorEntry(x)` |
| `ceiling(x)` | `ceilingKey(x)` / `ceilingEntry(x)` |
| `higher(x)` | `higherKey(x)` / `higherEntry(x)` |
| `pollFirst()` | `pollFirstEntry()` |
| `pollLast()` | `pollLastEntry()` |
| `headSet()` | `headMap()` |
| `tailSet()` | `tailMap()` |
| `subSet()` | `subMap()` |
| `descendingSet()` | `descendingMap()` |

### Memory Trick

```text
Set side       Map side

first          firstKey / firstEntry
last           lastKey / lastEntry

lower          lowerKey / lowerEntry
floor          floorKey / floorEntry
ceiling        ceilingKey / ceilingEntry
higher         higherKey / higherEntry

headSet        headMap
tailSet        tailMap
subSet         subMap
```

---

# 35. EnumMap

`EnumMap` is optimized for enum keys.

Example:

```java
enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
```

```java
EnumMap<Day, String> map = new EnumMap<>(Day.class);

map.put(Day.MONDAY, "Work");
map.put(Day.SATURDAY, "Football");
```

Features:

```text
Keys must be enums
Unique keys
Very efficient
Enum declaration order
Null keys NOT allowed
Null values allowed
Thread-safe NO
```

---

# 36. EnumMap Time Complexity

| Method | Typical Complexity |
|---|---:|
| `put()` | `O(1)` |
| `get()` | `O(1)` |
| `getOrDefault()` | `O(1)` |
| `containsKey()` | `O(1)` |
| `containsValue()` | `O(N)` |
| `remove()` | `O(1)` |
| `putIfAbsent()` | `O(1)` |
| `replace()` | `O(1)` |
| `compute()` | `O(1)` + function |
| `computeIfAbsent()` | `O(1)` + function |
| `computeIfPresent()` | `O(1)` + function |
| `merge()` | `O(1)` + function |
| `size()` | `O(1)` |
| `isEmpty()` | `O(1)` |
| iteration | `O(N)` |
| `forEach()` | `O(N)` |
| `clear()` | `O(N)` |

### Memory

```text
EnumSet -> enum elements
EnumMap -> enum keys + values
```

---

# 37. EnumSet vs EnumMap

| EnumSet | EnumMap |
|---|---|
| Stores enum elements | Uses enums as keys |
| `EnumSet<Day>` | `EnumMap<Day, String>` |
| Highly compact representation | Enum-indexed storage |
| No null elements | No null keys |

Example:

```java
EnumSet<Day> days = EnumSet.of(Day.MONDAY, Day.FRIDAY);
```

vs:

```java
EnumMap<Day, String> schedule = new EnumMap<>(Day.class);

schedule.put(Day.MONDAY, "Java");
schedule.put(Day.FRIDAY, "Football");
```

---

# 38. ConcurrentSkipListMap

```java
ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
```

Features:

```text
Sorted keys
Thread-safe
Concurrent
NavigableMap implementation
```

Typical complexity:

```text
put()         -> O(log N)
get()         -> O(log N)
remove()      -> O(log N)
containsKey() -> O(log N)
```

### Memory

```text
ConcurrentSkipListMap
=
SORTED + NAVIGABLE + THREAD-SAFE
```

---

# 39. ConcurrentSkipListMap Time Complexity

| Method | Typical Complexity |
|---|---:|
| `put()` | `O(log N)` |
| `get()` | `O(log N)` |
| `containsKey()` | `O(log N)` |
| `containsValue()` | `O(N)` |
| `remove()` | `O(log N)` |
| `lowerKey()` | `O(log N)` |
| `floorKey()` | `O(log N)` |
| `ceilingKey()` | `O(log N)` |
| `higherKey()` | `O(log N)` |
| `pollFirstEntry()` | `O(log N)` typical |
| `pollLastEntry()` | `O(log N)` typical |
| iteration | `O(N)` |

---

# 40. Map Functions — Master List

Most important common functions:

```java
put()
get()
getOrDefault()

containsKey()
containsValue()

remove()
replace()
putIfAbsent()

size()
isEmpty()
clear()

keySet()
values()
entrySet()

forEach()
putAll()

compute()
computeIfAbsent()
computeIfPresent()
merge()
replaceAll()
```

---

# 41. HashMap — Most Important Functions to Memorize

```java
map.put(key, value);
map.get(key);
map.getOrDefault(key, defaultValue);

map.containsKey(key);
map.containsValue(value);

map.remove(key);
map.remove(key, value);

map.putIfAbsent(key, value);
map.replace(key, value);

map.size();
map.isEmpty();
map.clear();

map.keySet();
map.values();
map.entrySet();

map.forEach((key, value) -> System.out.println(key + " " + value));

map.computeIfAbsent(key, k -> value);
map.computeIfPresent(key, (k, v) -> v);
map.merge(key, 1, Integer::sum);
```

---

# 42. TreeMap — Most Important Special Functions

```java
treeMap.firstKey();
treeMap.lastKey();

treeMap.firstEntry();
treeMap.lastEntry();

treeMap.lowerKey(key);
treeMap.floorKey(key);
treeMap.ceilingKey(key);
treeMap.higherKey(key);

treeMap.lowerEntry(key);
treeMap.floorEntry(key);
treeMap.ceilingEntry(key);
treeMap.higherEntry(key);

treeMap.pollFirstEntry();
treeMap.pollLastEntry();

treeMap.headMap(key);
treeMap.tailMap(key);
treeMap.subMap(from, to);

treeMap.descendingMap();
treeMap.navigableKeySet();
treeMap.descendingKeySet();
```

---

# 43. TreeMap Navigation Memory

Suppose:

```text
10       20       30       40       50
                  ↑
                 key=30
```

Then:

```text
lowerKey(30)   -> 20      <
floorKey(30)   -> 30      <=

ceilingKey(30) -> 30      >=
higherKey(30)  -> 40      >
```

Same logic as TreeSet:

```text
lower   <
floor   <=
ceiling >=
higher  >
```

---

# 44. HashMap vs LinkedHashMap vs TreeMap vs EnumMap

| Feature | HashMap | LinkedHashMap | TreeMap | EnumMap |
|---|---|---|---|---|
| Unique keys | Yes | Yes | Yes | Yes |
| Duplicate values | Yes | Yes | Yes | Yes |
| Insertion order | No | Yes | No | Enum declaration order |
| Sorted keys | No | No | Yes | Enum declaration order |
| Null key | One allowed | One allowed | Usually not with natural ordering | No |
| Null values | Yes | Yes | Yes | Yes |
| `put/get/remove` | `O(1)` avg | `O(1)` avg | `O(log N)` | `O(1)` typical |
| Thread-safe | No | No | No | No |

---

# 45. Set vs Map Complexity Comparison

| Family | Set | Map | Typical Complexity |
|---|---|---|---:|
| Hash | `HashSet` | `HashMap` | `O(1)` average |
| Linked Hash | `LinkedHashSet` | `LinkedHashMap` | `O(1)` average |
| Tree | `TreeSet` | `TreeMap` | `O(log N)` |
| Enum | `EnumSet` | `EnumMap` | `O(1)` typical |
| Concurrent Sorted | `ConcurrentSkipListSet` | `ConcurrentSkipListMap` | `O(log N)` |

---

# 46. Why HashMap Is Usually O(1)

HashMap uses hashing on the key.

```text
key
 ↓
hashCode()
 ↓
spread hash
 ↓
bucket index
 ↓
bucket
```

Bucket index is conceptually calculated in Java HashMap using:

```java
index = (n - 1) & hash;
```

where:

```text
n = internal table length
```

So Java can jump to a bucket instead of scanning every entry.

Typical average:

```text
put()         -> O(1)
get()         -> O(1)
remove()      -> O(1)
containsKey() -> O(1)
```

---

# 47. Why `containsValue()` Is O(N)

HashMap hashes:

```text
KEY
```

not the:

```text
VALUE
```

So:

```java
map.containsValue(500);
```

usually requires scanning entries:

```text
entry 1
entry 2
entry 3
...
entry N
```

Therefore:

```text
O(N)
```

---

# 48. Why TreeMap Is O(log N)

TreeMap uses a balanced Red-Black Tree.

Conceptually:

```text
        30
       /  \
     10    50
       \   /
       20 40
```

Searching repeatedly decides:

```text
left or right
```

instead of checking every element.

Therefore:

```text
put()
get()
remove()
containsKey()

-> O(log N)
```

---

# 49. Which Map Should I Use?

```text
Need KEY -> VALUE pairs?
        |
       YES
        |
        v
Need sorted keys?
   /          \
 YES          NO
  |            |
TreeMap    Need insertion order?
             /       \
           YES       NO
            |         |
   LinkedHashMap   HashMap
```

Additional cases:

```text
Enum keys only?
     ↓
  EnumMap

Sorted + thread-safe?
     ↓
ConcurrentSkipListMap
```

---

# 50. Which Set Should I Use?

```text
Need unique elements?
        |
       YES
        |
        v
Need sorted values?
   /           \
 YES           NO
  |             |
TreeSet     Need insertion order?
              /       \
            YES       NO
             |         |
   LinkedHashSet    HashSet
```

Additional:

```text
Enum values only?
     ↓
 EnumSet

Sorted + thread-safe?
     ↓
ConcurrentSkipListSet
```

---

# 51. Most Important Big-O Memory

```text
HashSet / HashMap
add / put / get / remove / containsKey
-> O(1) average
```

```text
LinkedHashSet / LinkedHashMap
same basic hash operations
-> O(1) average
```

```text
TreeSet / TreeMap
add / put / get / remove / contains
-> O(log N)
```

```text
EnumSet / EnumMap
common key/element operations
-> O(1) typical
```

```text
containsValue()
-> O(N)
```

```text
iteration
-> O(N)
```

---

# 52. Final Hierarchy to Memorize

```text
SET                              MAP

Set                              Map
 |                                |
 +-- HashSet                      +-- HashMap
 |     |                          |     |
 |     +-- LinkedHashSet          |     +-- LinkedHashMap
 |
 +-- SortedSet                    +-- SortedMap
       |                                |
       +-- NavigableSet                 +-- NavigableMap
              |                                |
              +-- TreeSet                      +-- TreeMap

EnumSet                           EnumMap

ConcurrentSkipListSet             ConcurrentSkipListMap
```

---

# 53. Final Interview Memory Sentence

> **Hash means fast average lookup, LinkedHash means fast plus insertion order, Tree means sorted with O(log N), Enum means optimized for enum values/keys, and Sorted/Navigable are interfaces whose complexity depends on the implementation.**
