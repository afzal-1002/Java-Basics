# Java Set, Hashing, Buckets, and Time Complexity Notes

## 1. Set Interface — Core Idea

A `Set` stores **unique elements**.

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(10);

System.out.println(set);
```

The duplicate `10` is ignored.

### Important Rule

```text
List -> duplicates allowed
Set  -> duplicates NOT allowed
```

A `Set` does not normally provide index-based access like:

```java
set.get(0);   // Not available
```

---

# 2. Main Set Implementations

| Implementation | Order | Sorted | Duplicates | Typical add/remove/contains |
|---|---|---|---|---|
| `HashSet` | No guaranteed order | No | No | `O(1)` average |
| `LinkedHashSet` | Insertion order | No | No | `O(1)` average |
| `TreeSet` | Sorted order | Yes | No | `O(log N)` |
| `EnumSet` | Enum declaration order | Yes by enum declaration order | No | Very fast, usually near `O(1)` |
| `ConcurrentSkipListSet` | Sorted | Yes | No | `O(log N)` |

### Short Memory Trick

```text
HashSet       -> FAST
LinkedHashSet -> FAST + INSERTION ORDER
TreeSet       -> SORTED
EnumSet       -> ENUMS ONLY
```

---

# 3. HashSet Time Complexity

`HashSet` internally uses a `HashMap`.

Typical average-case complexities:

| Operation | Average Time Complexity |
|---|---:|
| `add(E)` | `O(1)` |
| `remove(E)` | `O(1)` |
| `contains(E)` | `O(1)` |
| `size()` | `O(1)` |
| `isEmpty()` | `O(1)` |
| iteration | `O(N)` |
| `clear()` | `O(N)` |

Worst-case lookup can degrade if many values collide in the same bucket.

Modern Java can convert a heavily populated bucket from a linked list into a Red-Black Tree, improving that bucket's lookup behavior toward:

```text
O(log N)
```

---

# 4. LinkedHashSet Time Complexity

`LinkedHashSet` is based on hashing, like `HashSet`, but it also remembers insertion order.

| Operation | Average Time Complexity |
|---|---:|
| `add(E)` | `O(1)` |
| `remove(E)` | `O(1)` |
| `contains(E)` | `O(1)` |
| `size()` | `O(1)` |
| iteration | `O(N)` |

### Important Feature

```text
HashSet
-> Fast
-> No guaranteed order

LinkedHashSet
-> Fast
-> Keeps insertion order
```

---

# 5. TreeSet Time Complexity

`TreeSet` uses a balanced Red-Black Tree.

| Operation | Time Complexity |
|---|---:|
| `add(E)` | `O(log N)` |
| `remove(E)` | `O(log N)` |
| `contains(E)` | `O(log N)` |
| `first()` | `O(log N)` conceptually |
| `last()` | `O(log N)` conceptually |
| `lower()` | `O(log N)` |
| `floor()` | `O(log N)` |
| `ceiling()` | `O(log N)` |
| `higher()` | `O(log N)` |
| `pollFirst()` | `O(log N)` |
| `pollLast()` | `O(log N)` |
| iteration | `O(N)` |

### Why `O(log N)`?

Because the tree is balanced.

Conceptually:

```text
        30
       /  \
     10    50
       \
       20
```

When searching, Java does not scan every element.

It repeatedly chooses:

```text
Go left
or
Go right
```

So the search space becomes smaller step by step.

### Short Memory Trick

```text
HASH -> O(1) average
TREE -> O(log N)
```

---

# 6. EnumSet Time Complexity

`EnumSet` is specially optimized for enum values.

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
EnumSet<Day> days = EnumSet.of(Day.MONDAY, Day.FRIDAY);
```

Internally, Java can represent enum membership using bits.

Conceptually:

```text
MON TUE WED THU FRI SAT SUN
 1   0   0   0   1   0   0
```

Common operations are extremely efficient.

| Operation | Typical Complexity |
|---|---:|
| `add()` | `O(1)` |
| `remove()` | `O(1)` |
| `contains()` | `O(1)` |
| `size()` | `O(1)` |
| `isEmpty()` | `O(1)` |
| iteration | `O(N)` |

### Important EnumSet Factory Methods

```java
EnumSet.of(...);
EnumSet.noneOf(...);
EnumSet.allOf(...);
EnumSet.range(...);
EnumSet.complementOf(...);
EnumSet.copyOf(...);
```

---

# 7. How Hashing Works

The basic flow is:

```text
Object
  ↓
hashCode()
  ↓
hash value
  ↓
Java spreads/mixes the hash
  ↓
bucket index
  ↓
bucket
  ↓
store/search element
```

Hashing does **not sort** elements.

It decides where an element should be stored inside the internal hash table.

---

# 8. What Is a Bucket?

A `HashMap` / `HashSet` uses an internal array.

Each position in that array is called a **bucket**.

Conceptually:

```text
Bucket Array

index 0 -> null
index 1 -> [A]
index 2 -> null
index 3 -> [B] -> [C]
index 4 -> [D]
index 5 -> null
...
```

The bucket index tells Java where to start looking.

---

# 9. How Java Calculates the Hash

Suppose:

```java
String value = "Java";
```

Java first calls:

```java
int h = value.hashCode();
```

But Java `HashMap` does not use that raw hash directly.

It spreads the bits:

```java
int hash = h ^ (h >>> 16);
```

So conceptually:

```text
hashCode()
   ↓
spread hash
   ↓
bucket calculation
```

### Why spread the hash?

It helps distribute values more evenly across buckets.

---

# 10. How the Bucket Index Is Calculated

Modern Java `HashMap` uses:

```java
index = (n - 1) & hash;
```

Where:

```text
n = current table length
hash = spread hash
```

Example:

```text
table length = 16
n - 1       = 15
```

Binary:

```text
15 = 0000 1111
```

Suppose:

```text
hash = 37
```

Binary:

```text
37 = 0010 0101
15 = 0000 1111
     ---------
&  = 0000 0101
```

Result:

```text
0000 0101 = 5
```

So:

```text
bucket index = 5
```

The element goes to:

```text
bucket 5
```

### Memory Trick

```text
HASH -> MASK -> BUCKET
```

---

# 11. Simplified Hashing Formula

In general hashing explanations, you may also see:

```text
bucket = hash % numberOfBuckets
```

Example:

```text
hash = 37
buckets = 16

37 % 16 = 5
```

So bucket:

```text
5
```

This is useful for understanding hashing.

However, Java `HashMap` specifically uses:

```java
(n - 1) & hash
```

because the table size is maintained as a power of two.

---

# 12. Why Bucket Sizes Are Powers of Two

Typical internal table lengths are:

```text
16
32
64
128
256
...
```

This makes:

```java
(n - 1) & hash
```

efficient.

For example:

```text
n = 16
n - 1 = 15
```

Binary:

```text
16 = 0001 0000
15 = 0000 1111
```

The mask extracts the lower bits needed for the bucket index.

---

# 13. Default Initial Capacity

For a normal Java `HashMap`:

```java
new HashMap<>();
```

the commonly remembered default capacity is:

```text
16 buckets
```

Important detail:

The internal table is generally allocated lazily, meaning Java may wait until the first insertion before creating the array.

For interview purposes, remember:

```text
Default initial capacity = 16
Default load factor      = 0.75
```

---

# 14. What Is Load Factor?

Load factor controls when Java should resize the hash table.

Default:

```text
0.75
```

Formula:

```text
threshold = capacity × loadFactor
```

Example:

```text
capacity   = 16
loadFactor = 0.75
```

Then:

```text
threshold = 16 × 0.75
          = 12
```

So when the number of entries grows beyond the threshold, Java resizes.

Conceptually:

```text
capacity 16
threshold 12

After enough entries are inserted:
16 -> 32 buckets
```

### Memory Trick

```text
16 buckets
75% full
resize
```

---

# 15. Why Java Resizes

Imagine only four buckets:

```text
0 -> A -> B -> C
1 -> D -> E
2 -> F
3 -> G -> H
```

Too many elements per bucket means more collisions.

More collisions mean slower searching.

Java resizes to spread elements across more buckets:

```text
Before:
4 buckets

After:
8 buckets
```

This reduces the average number of elements per bucket.

---

# 16. What Is a Hash Collision?

A collision happens when different elements map to the same bucket.

Example:

```text
Object A -> hash -> bucket 3
Object B -> hash -> bucket 3
Object C -> hash -> bucket 3
```

Then conceptually:

```text
bucket 3
   ↓
[A] -> [B] -> [C]
```

This is called a **collision chain**.

---

# 17. Important Correction: Same Bucket Does Not Mean Same Hash

Different hashes can still map to the same bucket.

Example:

```text
hash 19 -> bucket 3
hash 35 -> bucket 3
hash 51 -> bucket 3
```

So:

```text
same bucket != necessarily same hashCode
```

The bucket count is much smaller than the number of possible integer hash values.

---

# 18. How Java Handles Collisions

Suppose:

```text
bucket 3
   ↓
[A] -> [B] -> [C]
```

When Java wants to add another element, it roughly does:

```text
1. Calculate hashCode()
2. Spread the hash
3. Calculate bucket index
4. Go to that bucket
5. Compare hashes
6. Use equals() when needed
7. Add or reject the element
```

---

# 19. Why `equals()` Is Important

Suppose:

```java
set.add("Java");
set.add("Java");
```

For the second value:

```text
"Java"
   ↓
same logical value
   ↓
same bucket
   ↓
equals()
   ↓
true
   ↓
DO NOT ADD
```

That is how `HashSet` prevents duplicates.

---

# 20. `hashCode()` and `equals()` Contract

Important interview rule:

```text
If a.equals(b) == true
then
a.hashCode() == b.hashCode()
must also be true.
```

But the reverse is not guaranteed.

This is possible:

```text
a.hashCode() == b.hashCode()
```

while:

```text
a.equals(b) == false
```

That is simply a hash collision.

### Memory Trick

```text
equals true
=> same hash required

same hash
!= necessarily equals true
```

---

# 21. Example with Custom Objects

```java
class Student {
    private int id;
    private String name;
}
```

Suppose:

```java
Student s1 = new Student(1, "Ali");
Student s2 = new Student(1, "Ali");
```

If you want `HashSet` to consider them duplicates, you should correctly override:

```java
equals()
hashCode()
```

Otherwise, they may be treated as different objects.

---

# 22. HashSet Internally Uses HashMap

A useful mental model:

```java
HashSet<String> set = new HashSet<>();
```

internally behaves approximately like:

```text
HashMap<String, dummyValue>
```

Example:

```text
HashSet element:

"Java"

Internally conceptually:

"Java" -> PRESENT
```

So:

```text
HashSet element
=
HashMap key
```

This is why `HashSet` gets hashing behavior from `HashMap`.

---

# 23. Linked List Inside a Bucket

Historically, collisions inside one bucket are stored as linked nodes.

Conceptually:

```text
bucket 4
   ↓
[A] -> [B] -> [C] -> [D]
```

Searching inside this bucket may require checking multiple nodes.

If all `N` elements were in one bucket, naive linked-list searching could become:

```text
O(N)
```

---

# 24. Treeification in Modern Java

Modern Java `HashMap` can convert a heavily populated bucket from a linked list into a Red-Black Tree.

Conceptually:

Before:

```text
[A] -> [B] -> [C] -> [D] -> [E] -> [F] -> [G] -> [H]
```

After treeification:

```text
        D
      /   \
     B     F
    / \   / \
   A   C E   G
              \
               H
```

This improves searching within that heavily-collided bucket toward:

```text
O(log N)
```

A commonly remembered treeification threshold is:

```text
8 nodes in a bucket
```

But Java also considers the total table capacity before treeifying.

A commonly remembered minimum table capacity for treeification is:

```text
64
```

If the table is still small, Java may resize instead of treeifying immediately.

### Interview Memory

```text
Bucket chain gets large
        ↓
Red-Black Tree
        ↓
O(log N) lookup in that bucket
```

---

# 25. Complete HashSet Lookup Flow

When calling:

```java
set.contains(value);
```

Think:

```text
value
  ↓
hashCode()
  ↓
spread hash
  ↓
(n - 1) & hash
  ↓
bucket index
  ↓
go directly to bucket
  ↓
compare hash / equals()
  ↓
found or not found
```

That is why average lookup is:

```text
O(1)
```

Java usually does not scan the entire Set.

---

# 26. Complete HashSet Add Flow

When calling:

```java
set.add(value);
```

Think:

```text
value
  ↓
hashCode()
  ↓
spread hash
  ↓
calculate bucket
  ↓
bucket empty?
 /       \
YES       NO
 |         |
store      collision
           |
           compare existing nodes
           |
        equals?
        /    \
      true   false
       |       |
    reject    add
```

---

# 27. HashSet vs TreeSet Search

## HashSet

```text
value
 ↓
hash
 ↓
bucket
 ↓
check small bucket
```

Average:

```text
O(1)
```

## TreeSet

```text
        30
       /  \
     10    50
```

Search:

```text
compare
 ↓
left/right
 ↓
compare
 ↓
left/right
```

Time:

```text
O(log N)
```

---

# 28. Most Important Interview Summary

### HashSet

```text
Unique values
No guaranteed order
Hash table
HashMap internally
add/remove/contains = O(1) average
```

### LinkedHashSet

```text
Unique values
Insertion order preserved
Hashing + linked ordering
add/remove/contains = O(1) average
```

### TreeSet

```text
Unique values
Sorted
Red-Black Tree
add/remove/contains = O(log N)
```

### EnumSet

```text
Only enum values
Very efficient
Bit-based internal representation
Common operations approximately O(1)
```

---

# 29. Best Short Memory Notes

```text
SET = UNIQUE

HASHSET
HASH -> BUCKET -> COLLISION -> EQUALS
Average O(1)

LINKEDHASHSET
HASHSET + INSERTION ORDER
Average O(1)

TREESET
SORTED + RED-BLACK TREE
O(log N)

ENUMSET
ENUMS ONLY + BIT-BASED
Very fast
```

---

# 30. Best Hashing Memory Formula

```text
Object
  ↓
hashCode()
  ↓
h ^ (h >>> 16)
  ↓
(n - 1) & hash
  ↓
BUCKET
```

And remember:

```text
Default capacity = 16
Default load factor = 0.75
Typical resize threshold = capacity × 0.75
```

Example:

```text
16 × 0.75 = 12

Around threshold:
resize 16 -> 32
```

---

# 31. Final One-Line Interview Answer

> Java `HashSet` uses hashing through an internal `HashMap`. Java calculates the object's `hashCode()`, spreads the hash bits, converts the hash into a bucket index using `(n - 1) & hash`, then searches that bucket using the stored hash and `equals()`. Collisions are handled using linked nodes and, for heavily populated buckets in modern Java, a Red-Black Tree. This gives `HashSet` `O(1)` average time for `add`, `remove`, and `contains`.
