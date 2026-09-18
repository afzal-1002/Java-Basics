# Java List Implementations - Big-O Time Complexity Summary Matrix

| Method / Operation | `ArrayList` | `LinkedList` | `Vector` / `Stack` | Primary Cause / Notes |
| :--- | :--- | :--- | :--- | :--- |
| `add(E)` / `addLast(E)` / `push(E)` | $O(1)$ amortized | $O(1)$ | $O(1)$ amortized | Dynamic array resizing vs. updating doubly-linked node pointers. |
| `add(index, E)` / `addFirst(E)` | $O(N)$ | $O(1)$ at head | $O(N)$ | Array memory element shifting vs. head node pointer re-linking. |
| `get(index)` / `elementAt(index)` | $O(1)$ | $O(N)$ | $O(1)$ | Direct array offset calculation vs. sequential node traversal. |
| `set(index, E)` / `setElementAt()` | $O(1)$ | $O(N)$ | $O(1)$ | Direct index overwrite vs. sequential node lookup. |
| `remove(index)` | $O(N)$ | $O(N)$ | $O(N)$ | Shifting array elements vs. traversing to index node. |
| `removeFirst()` / `pop()` / `poll()` | $O(N)$ | $O(1)$ | $O(N)$ / $O(1)$ | Array shift on index 0 removal vs. head pointer update (Stack pops top element in $O(1)$). |
| `removeLast()` / `pollLast()` | $O(1)$ | $O(1)$ | $O(1)$ | Deleting tail index directly without element shifting. |
| `getFirst()` / `peek()` / `peekFirst()` | $O(1)$ | $O(1)$ | $O(1)$ | Immediate reference retrieval for head/top item. |
| `getLast()` / `peekLast()` | $O(1)$ | $O(1)$ | $O(1)$ | Immediate reference retrieval for tail item. |
| `contains(E)` / `indexOf(E)` | $O(N)$ | $O(N)$ | $O(N)$ | Linear search through collection elements. |
| `search(E)` *(Stack-specific)* | N/A | N/A | $O(N)$ | Linear reverse search measuring 1-based distance from stack top. |
| `addAll(Collection)` | $O(K)$ | $O(K)$ | $O(K)$ | Appends $K$ elements from the source collection. |
| `removeAll()` / `retainAll()` | $O(N \times K)$ | $O(N \times K)$ | $O(N \times K)$ | Cross-references $N$ elements against target collection of size $K$. |
| `removeIf(Predicate)` | $O(N)$ | $O(N)$ | $O(N)$ | Single-pass predicate evaluation over $N$ elements. |
| `sort(Comparator)` | $O(N \log N)$ | $O(N \log N)$ | $O(N \log N)$ | Java TimSort implementation. |
| `clear()` | $O(N)$ | $O(N)$ | $O(N)$ | Unsets reference pointers for garbage collection. |
| `size()` / `isEmpty()` / `empty()` | $O(1)$ | $O(1)$ | $O(1)$ | Reads internal instance variables. |



N = number of elements already in your collection.
K = number of elements in another collection you are adding/checking against


| Operation       |    ArrayList |   LinkedList | Memory reason                   |
| --------------- | -----------: | -----------: | ------------------------------- |
| `addLast()`     |      `O(1)*` |       `O(1)` | End is easy                     |
| `addFirst()`    |       `O(N)` |       `O(1)` | Array shifts; List changes head |
| `get(index)`    |       `O(1)` |       `O(N)` | Array jumps; List walks         |
| `set(index)`    |       `O(1)` |       `O(N)` | Same as get                     |
| `remove(index)` |       `O(N)` |       `O(N)` | Shift vs search                 |
| `removeFirst()` |       `O(N)` |       `O(1)` | Shift vs head                   |
| `removeLast()`  |       `O(1)` |       `O(1)` | Nothing after last              |
| `contains()`    |       `O(N)` |       `O(N)` | Search                          |
| `size()`        |       `O(1)` |       `O(1)` | Size stored                     |
| `sort()`        | `O(N log N)` | `O(N log N)` | Sorting                         |
| `clear()`       |       `O(N)` |       `O(N)` | Touch references                |


1. ARRAY = INDEX FAST              → get/set O(1)
2. ARRAY = SHIFT BAD               → add/remove beginning/middle O(N)

3. LINKED LIST = ENDS FAST         → add/remove first/last O(1)
4. LINKED LIST = INDEX BAD         → get/set(index) O(N)

5. DON'T KNOW WHERE? SEARCH        → contains/indexOf O(N)


1. Sort           → O(N log N) 
2. One full loop  → O(N)
3. Nested N × K   → O(N × K)
4. Size           → O(1)