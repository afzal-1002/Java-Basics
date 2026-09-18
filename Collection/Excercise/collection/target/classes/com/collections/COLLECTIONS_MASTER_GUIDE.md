# Java Collections — The Complete Memory Guide

**One journey:** `Iterable → Collection → List / Set / Queue` and separately `Map`.

> This file is the **big picture map**. Each branch also has its own deep-dive file in this
> project if you want every detail:
> - List → [`iterable_collection_list/big_o_complexity_matrix.md`](iterable_collection_list/big_o_complexity_matrix.md)
> - Set → [`iterable_collection_set/set_time_complexity.md`](iterable_collection_set/set_time_complexity.md)
> - Queue → [`iterable_collection_queue/java_queue_hierarchy_methods_time_complexity.md`](iterable_collection_queue/java_queue_hierarchy_methods_time_complexity.md)
> - Map → [`iterable_collection_map/set_map_time_complexity.md`](iterable_collection_map/set_map_time_complexity.md)
> - Big-O basics → [`time_complexity.md`](time_complexity.md)

---

## 0. The Whole Family Tree (memorize this shape first)

```text
                         Iterable
                            |
                       Collection
              ┌─────────────┼─────────────┐
             List          Set           Queue
              |              |              |
        ArrayList        HashSet       PriorityQueue
        LinkedList   LinkedHashSet        Deque
        Vector/Stack     TreeSet      ┌────┴────┐
                        EnumSet   ArrayDeque  LinkedList
                                       |
                                 BlockingQueue
                                 (concurrent pkg)


                            Map                 <-- NOT a Collection!
              ┌─────────────┼─────────────┐
           HashMap       TreeMap        EnumMap
              |
        LinkedHashMap
```

### The one rule that explains everything

```text
List  -> ordered by INDEX,  duplicates allowed
Set   -> unique elements,   no index
Queue -> ordered for PROCESSING (FIFO / priority), ends matter
Map   -> KEY -> VALUE pairs, unique keys
```

### Memory trick: `Map` is the odd one out

```text
Iterable → Collection → List/Set/Queue    (you can for-each these directly)
Map                                       (you iterate map.keySet()/values()/entrySet())
```

---

## 1. `Iterable` — the root of everything

```java
public interface Iterable<T> {
    Iterator<T> iterator();
    default void forEach(Consumer<? super T> action);
    default Spliterator<T> spliterator();
}
```

**Why it exists:** it's the contract that lets you write `for (T item : myCollection)`.

| Method | Time Complexity | Notes |
|---|---:|---|
| `iterator()` | `O(1)` | Creates the iterator object |
| full traversal via iterator | `O(N)` | One `next()` call per element |
| `forEach(action)` | `O(N)` | Visits every element once |

> **Remember:** `Iterable` only promises "you can loop over me." It says nothing about
> order, uniqueness, or how fast lookups are — that's added by the interfaces below it.

---

## 2. `Collection` — the shared toolbox for List / Set / Queue

```text
Iterable
   |
Collection   <-- adds size, add/remove, bulk ops
```

Every `List`, `Set`, and `Queue` inherits these methods for free:

| Method | Average Time Complexity | Notes |
|---|---:|---|
| `add(E)` | depends on implementation | see branch tables below |
| `remove(Object)` | depends on implementation | usually needs a search first |
| `contains(Object)` | depends on implementation | `O(1)` hash, `O(log N)` tree, `O(N)` linear |
| `size()` | `O(1)` | stored counter, never recalculated |
| `isEmpty()` | `O(1)` | checks `size() == 0` |
| `addAll(Collection)` | `O(K)` | K = size of the collection being added |
| `removeAll(Collection)` | `O(N × K)` | cross-checks N items against K items |
| `retainAll(Collection)` | `O(N × K)` | same idea, keeps intersection |
| `removeIf(Predicate)` | `O(N)` | one pass, test every element |
| `clear()` | `O(N)` | unlinks/nulls every reference |
| `toArray()` | `O(N)` | copies every element |
| `stream()` | `O(1)` to create | laziness — work happens on terminal op |

### Memory trick

```text
SIZE-LIKE METHODS (size, isEmpty)        -> O(1), always
BULK METHODS (addAll, removeAll, clear)  -> O(N) or O(N×K), always touch many elements
"Does it contain X?"                     -> depends entirely on the data structure
```

---

## 3. `List` — ordered, indexed, duplicates OK

```text
Collection
   |
  List
   |
   ├── ArrayList     (resizable array)
   ├── LinkedList     (doubly-linked nodes)  — also a Deque!
   └── Vector/Stack   (synchronized array, legacy)
```

### 3.1 Core idea

```java
List<String> list = new ArrayList<>();
list.add("Java");
list.add("Java");     // duplicates ARE allowed
list.get(0);           // index access
```

### 3.2 Implementation Cheat Sheet

| Need | Use |
|---|---|
| Fast random access by index | `ArrayList` |
| Frequent insert/remove at the **ends** | `LinkedList` (or better, `ArrayDeque`) |
| Legacy thread-safe list | `Vector` |
| Legacy LIFO stack | `Stack` (prefer `ArrayDeque` today) |

### 3.3 Time Complexity Matrix

| Operation | `ArrayList` | `LinkedList` | `Vector`/`Stack` | Why |
|---|---:|---:|---:|---|
| `add(E)` (append) | `O(1)` amortized | `O(1)` | `O(1)` amortized | append at a known end |
| `add(index, E)` / `addFirst()` | `O(N)` | `O(1)` at head | `O(N)` | array shift vs. pointer relink |
| `get(index)` | `O(1)` | `O(N)` | `O(1)` | direct offset vs. walking nodes |
| `set(index, E)` | `O(1)` | `O(N)` | `O(1)` | same reasoning as `get` |
| `remove(index)` | `O(N)` | `O(N)` | `O(N)` | shift array vs. traverse to node |
| `removeFirst()` / `pop()` | `O(N)` | `O(1)` | `O(N)`/`O(1)` | Stack pops top in `O(1)` |
| `removeLast()` | `O(1)` | `O(1)` | `O(1)` | tail is always known |
| `contains()` / `indexOf()` | `O(N)` | `O(N)` | `O(N)` | linear search |
| `sort()` | `O(N log N)` | `O(N log N)` | `O(N log N)` | Java TimSort |
| `size()` / `isEmpty()` | `O(1)` | `O(1)` | `O(1)` | stored counter |

### Memory trick

```text
ARRAY BACKED (ArrayList, Vector) -> INDEX FAST O(1), SHIFT SLOW O(N)
LINKED (LinkedList)              -> ENDS FAST O(1), INDEX SLOW O(N)
DON'T KNOW WHERE IT IS?          -> SEARCH = O(N), always
```

---

## 4. `Set` — unique elements, no index

```text
Collection
   |
  Set
   |
   ├── HashSet          (hash table)         -> no order
   │     └── LinkedHashSet                    -> hash + insertion order
   ├── SortedSet / NavigableSet (interfaces)
   │     └── TreeSet                          -> Red-Black Tree, sorted
   ├── EnumSet                                -> bit vector, enums only
   └── ConcurrentSkipListSet                  -> sorted + thread-safe
```

### 4.1 Core idea

```java
Set<Integer> set = new HashSet<>();
set.add(10);
set.add(10);      // ignored — duplicates NOT allowed
set.get(0);        // ❌ doesn't exist — Sets have no index
```

### 4.2 Implementation Cheat Sheet

| Implementation | Order | Sorted? | Typical `add`/`remove`/`contains` |
|---|---|---|---:|
| `HashSet` | none guaranteed | No | `O(1)` average |
| `LinkedHashSet` | insertion order | No | `O(1)` average |
| `TreeSet` | sorted order | Yes | `O(log N)` |
| `EnumSet` | enum declaration order | Yes (by enum) | `O(1)` (bit-based) |
| `ConcurrentSkipListSet` | sorted | Yes | `O(log N)` |

### 4.3 TreeSet Navigation Methods (all `O(log N)`)

```text
lower(x)    strictly <  x
floor(x)    <=         x
ceiling(x)  >=         x
higher(x)   strictly >  x

first()/last()          smallest / largest
pollFirst()/pollLast()  remove + return smallest / largest
```

### 4.4 Why HashSet is (usually) O(1) — the hashing pipeline

```text
element -> hashCode() -> spread bits (h ^ (h >>> 16)) -> bucket = (n-1) & hash -> bucket array
```

- Default capacity: **16 buckets**, default load factor: **0.75** → resize threshold = `16 × 0.75 = 12`.
- Collisions inside one bucket are a small linked list; if a bucket grows large (≈8 entries)
  **and** the table is big enough (≈64), Java converts that bucket into a Red-Black Tree,
  turning a worst-case `O(N)` bucket scan into `O(log N)`.
- `HashSet` is literally a `HashMap` under the hood — the "value" is a dummy constant.

### Memory trick

```text
SET = UNIQUE, ALWAYS
HASH  -> O(1) average    (bucket lookup)
TREE  -> O(log N)        (binary search down a balanced tree)
ENUM  -> O(1), bit trick (fastest of all, but enums only)
```

---

## 5. `Queue` / `Deque` — ordered for processing

```text
Collection
   |
  Queue
   ├── PriorityQueue          -> binary heap, NOT FIFO
   ├── ConcurrentLinkedQueue  -> thread-safe, non-blocking FIFO
   ├── BlockingQueue (interface, java.util.concurrent)
   │     ├── ArrayBlockingQueue
   │     ├── LinkedBlockingQueue
   │     ├── PriorityBlockingQueue
   │     ├── DelayQueue
   │     ├── SynchronousQueue
   │     └── TransferQueue -> LinkedTransferQueue
   └── Deque (double-ended)
         ├── ArrayDeque            -> best general Queue/Stack
         ├── LinkedList             -> also implements List!
         ├── ConcurrentLinkedDeque
         └── BlockingDeque -> LinkedBlockingDeque
```

### 5.1 Core idea — FIFO by default

```text
FRONT                     REAR
  ↓                         ↓
[10] [20] [30] [40]
  ↑                    add here (offer)
remove here (poll)
```

`PriorityQueue` is the **exception** — it removes by priority, not insertion order.

### 5.2 The method-naming pattern (memorize once, applies everywhere)

| Action | Throws Exception | Returns Special Value |
|---|---|---|
| Insert | `add(E)` | `offer(E)` |
| Remove head | `remove()` | `poll()` |
| Peek head | `element()` | `peek()` |

Deque doubles this for both ends:

| Action | Front | Back |
|---|---|---|
| Add | `addFirst()` / `offerFirst()` | `addLast()` / `offerLast()` |
| Remove | `removeFirst()` / `pollFirst()` | `removeLast()` / `pollLast()` |
| Peek | `getFirst()` / `peekFirst()` | `getLast()` / `peekLast()` |

Stack-style on a `Deque`: `push()` = `addFirst()`, `pop()` = `removeFirst()`.

BlockingQueue adds a **blocking** version: `put()` waits for space, `take()` waits for data.

### 5.3 Time Complexity Matrix

| Implementation | Insert | Remove head | Peek | Search | Internals |
|---|---:|---:|---:|---:|---|
| `PriorityQueue` | `O(log N)` | `O(log N)` | `O(1)` | `O(N)` | Binary heap |
| `ArrayDeque` | `O(1)` amortized | `O(1)` | `O(1)` | `O(N)` | Resizable array, both ends |
| `LinkedList` (as Queue/Deque) | `O(1)` at ends | `O(1)` | `O(1)` | `O(N)` | Doubly-linked nodes |
| `ArrayBlockingQueue` | `O(1)` | `O(1)` | `O(1)` | `O(N)` | Bounded array, blocking |
| `LinkedBlockingQueue` | `O(1)` | `O(1)` | `O(1)` | `O(N)` | Linked nodes, blocking |
| `PriorityBlockingQueue` | `O(log N)` | `O(log N)` | `O(1)` | `O(N)` | Heap + thread-safe |
| `ConcurrentLinkedQueue` | `O(1)` amortized | `O(1)` amortized | `O(1)` amortized | `O(N)` | Non-blocking, concurrent |

### Memory trick

```text
NORMAL QUEUE          -> FIFO, offer/poll/peek
PRIORITYQUEUE         -> HEAP, O(log N) add/remove, O(1) peek
DEQUE                 -> both ends O(1), search O(N)
BLOCKINGQUEUE         -> put() waits for space, take() waits for data
```

---

## 6. `Map` — key → value pairs (the sibling, not a Collection)

```text
Map
   ├── HashMap             -> hash table, no order
   │     └── LinkedHashMap  -> hash + insertion (or access) order
   ├── SortedMap / NavigableMap (interfaces)
   │     └── TreeMap        -> Red-Black Tree, sorted by key
   ├── EnumMap              -> array indexed by enum ordinal
   └── ConcurrentSkipListMap -> sorted + thread-safe
```

### 6.1 Core idea

```java
Map<String, Integer> map = new HashMap<>();
map.put("Java", 10);
map.put("Java", 100);   // overwrites — KEYS are unique, VALUES can repeat
```

```text
Set side       Map side          Family
HashSet        HashMap           hashing, unordered
LinkedHashSet  LinkedHashMap     hashing + insertion order
TreeSet        TreeMap           sorted, Red-Black Tree
EnumSet        EnumMap           enum-optimized
```

### 6.2 Time Complexity Matrix

| Method | `HashMap` | `LinkedHashMap` | `TreeMap` | `EnumMap` |
|---|---:|---:|---:|---:|
| `put()` | `O(1)` avg | `O(1)` avg | `O(log N)` | `O(1)` |
| `get()` | `O(1)` avg | `O(1)` avg | `O(log N)` | `O(1)` |
| `containsKey()` | `O(1)` avg | `O(1)` avg | `O(log N)` | `O(1)` |
| `containsValue()` | `O(N)` | `O(N)` | `O(N)` | `O(N)` |
| `remove()` | `O(1)` avg | `O(1)` avg | `O(log N)` | `O(1)` |
| iteration / `forEach` | `O(N)` | `O(N)` | `O(N)` | `O(N)` |
| `keySet()`/`values()`/`entrySet()` (view creation) | `O(1)` | `O(1)` | `O(1)` | `O(1)` |

> **Why `containsValue()` is always `O(N)`:** hashing/trees index by **key**, never by value.
> To find a value, Java has no shortcut — it must scan every entry.

### 6.3 TreeMap navigation (mirrors TreeSet, all `O(log N)`)

| TreeSet | TreeMap |
|---|---|
| `first()` / `last()` | `firstKey()` / `lastKey()` |
| `lower(x)` / `floor(x)` | `lowerKey(x)` / `floorKey(x)` |
| `ceiling(x)` / `higher(x)` | `ceilingKey(x)` / `higherKey(x)` |
| `pollFirst()` / `pollLast()` | `pollFirstEntry()` / `pollLastEntry()` |

### 6.4 Java 8+ "smart" methods worth memorizing

```java
map.getOrDefault(key, fallback);                  // O(1)/O(log N), no null checks
map.putIfAbsent(key, value);                      // insert only if missing
map.computeIfAbsent(key, k -> new ArrayList<>());  // classic "group by" pattern
map.merge(key, 1, Integer::sum);                  // classic "counter" pattern
```

All of these cost the same as a normal `get`/`put` for that map type, **plus** the cost of
your lambda.

### Memory trick

```text
HASH  -> O(1) average          (put/get/remove/containsKey)
TREE  -> O(log N)              (sorted, Red-Black Tree)
ENUM  -> O(1)                  (array indexed by ordinal)
containsValue() and iteration  -> ALWAYS O(N), no matter which Map
```

---

## 7. The Master Cheat Sheet (everything on one page)

| Family | Implementation | Add | Remove | Search/Get | Ordered? | Notes |
|---|---|---:|---:|---:|---|---|
| **List** | `ArrayList` | `O(1)`* | `O(N)` | `O(1)` by index | insertion | resizable array |
| **List** | `LinkedList` | `O(1)` at ends | `O(1)` at ends | `O(N)` by index | insertion | doubly-linked |
| **Set** | `HashSet` | `O(1)` avg | `O(1)` avg | `O(1)` avg | none | hash table |
| **Set** | `LinkedHashSet` | `O(1)` avg | `O(1)` avg | `O(1)` avg | insertion | hash + links |
| **Set** | `TreeSet` | `O(log N)` | `O(log N)` | `O(log N)` | sorted | Red-Black Tree |
| **Queue** | `ArrayDeque` | `O(1)`* | `O(1)` | `O(N)` | insertion (ends) | best general Queue/Stack |
| **Queue** | `PriorityQueue` | `O(log N)` | `O(log N)` | `O(1)` peek | priority | binary heap |
| **Map** | `HashMap` | `O(1)` avg | `O(1)` avg | `O(1)` avg | none | hash table |
| **Map** | `LinkedHashMap` | `O(1)` avg | `O(1)` avg | `O(1)` avg | insertion | hash + links |
| **Map** | `TreeMap` | `O(log N)` | `O(log N)` | `O(log N)` | sorted by key | Red-Black Tree |

<sub>* amortized — occasional resize costs `O(N)` but averages out to `O(1)` per call.</sub>

### The 3-second version

```text
HASH   -> O(1) average          "jump straight to the bucket"
TREE   -> O(log N)              "cut the search space in half each step"
LINKED ENDS -> O(1)             "just move a pointer"
ANYTHING IN THE MIDDLE/BY INDEX ON A LINKED STRUCTURE -> O(N)  "walk node by node"
LINEAR SEARCH (no hash, no tree) -> O(N)   "check everything"
```

---

## 8. "Which One Should I Use?" Decision Tree

```text
Need index-based access, duplicates OK?
        -> List
             fast random access?      -> ArrayList
             mostly add/remove ends?  -> LinkedList / ArrayDeque

Need uniqueness, no index?
        -> Set
             need sorted order?       -> TreeSet
             need insertion order?    -> LinkedHashSet
             just fast, don't care?   -> HashSet
             enum values only?        -> EnumSet

Need FIFO / priority / two-ended processing?
        -> Queue / Deque
             plain FIFO?              -> ArrayDeque
             priority ordering?       -> PriorityQueue
             producer/consumer threads? -> BlockingQueue

Need key -> value pairs?
        -> Map
             need sorted keys?        -> TreeMap
             need insertion order?    -> LinkedHashMap
             just fast, don't care?   -> HashMap
             enum keys only?          -> EnumMap
```

---

## 9. Final One-Line Summary (say this out loud once)

> **`Iterable` lets you loop, `Collection` adds size/add/remove, `List` keeps order and index,
> `Set` keeps uniqueness, `Queue` keeps processing order, and `Map` pairs keys with values —
> and everywhere in Java, `Hash` means `O(1)` average, `Tree` means `O(log N)` sorted, and
> "search without a hash or a tree" always means `O(N)`.**
