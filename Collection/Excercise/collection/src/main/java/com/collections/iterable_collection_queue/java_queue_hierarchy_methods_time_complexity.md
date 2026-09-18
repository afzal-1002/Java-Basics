# Java Queue Hierarchy — Methods, Features, and Big-O Time Complexity

## 1. Core Idea

A `Queue` normally follows:

```text
FIFO = First In, First Out
```

Example:

```text
FRONT                     REAR
  ↓                         ↓
[10] [20] [30] [40]
  ↑                    add here
remove here
```

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll()); // 10
System.out.println(queue.poll()); // 20
```

### Important Exception

`PriorityQueue` is **not normal FIFO**. It removes according to priority.

---

# 2. Practical Queue Hierarchy

```text
Iterable
   |
Collection
   |
Queue                                  <- Interface
   |
   +-- PriorityQueue                   <- Class
   |
   +-- ConcurrentLinkedQueue           <- Class
   |
   +-- BlockingQueue                   <- Interface
   |      |
   |      +-- ArrayBlockingQueue       <- Class
   |      +-- LinkedBlockingQueue      <- Class
   |      +-- PriorityBlockingQueue    <- Class
   |      +-- DelayQueue               <- Class
   |      +-- SynchronousQueue         <- Class
   |      |
   |      +-- TransferQueue            <- Interface
   |             |
   |             +-- LinkedTransferQueue
   |
   +-- Deque                            <- Interface
          |
          +-- ArrayDeque                <- Class
          +-- LinkedList                <- Class
          +-- ConcurrentLinkedDeque     <- Class
          |
          +-- BlockingDeque             <- Interface
                 |
                 +-- LinkedBlockingDeque
```

### Short Version to Memorize

```text
Queue
├── PriorityQueue
├── Deque
│   ├── ArrayDeque
│   └── LinkedList
└── BlockingQueue
```

---

# 3. Queue Interface — Most Important Methods

| Operation | Exception Version | Safe/Special-Value Version |
|---|---|---|
| Insert | `add(E)` | `offer(E)` |
| Remove head | `remove()` | `poll()` |
| Look at head | `element()` | `peek()` |

### Memory Trick

```text
ADD      REMOVE      LOOK

add()    remove()    element()
offer()  poll()      peek()
```

The second row is usually the one to remember first:

```text
offer()
poll()
peek()
```

---

# 4. Common Queue Methods

```java
add(E);
offer(E);

remove();
poll();

element();
peek();

size();
isEmpty();

contains(Object o);
containsAll(Collection<?> c);

remove(Object o);

addAll(Collection<? extends E> c);
removeAll(Collection<?> c);
retainAll(Collection<?> c);

removeIf(Predicate<? super E> filter);

clear();

iterator();
forEach(...);

toArray();
```

> The `Queue` interface itself does not define one fixed Big-O complexity. Complexity depends on the concrete implementation.

---

# 5. PriorityQueue

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

### Features

```text
FIFO                  NO
Priority based        YES
Duplicates            YES
null                   NO
Thread-safe           NO
Internal structure    Binary Heap
```

Default integer behavior:

```text
smallest value first
```

Reverse priority:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
```

---

# 6. PriorityQueue Methods

```java
offer(E);
add(E);

poll();
remove();

peek();
element();

contains(Object o);
remove(Object o);

size();
isEmpty();
clear();

iterator();

comparator();
```

---

# 7. PriorityQueue Time Complexity

| Method | Complexity | Reason |
|---|---:|---|
| `offer(E)` | `O(log N)` | Heap insertion |
| `add(E)` | `O(log N)` | Heap insertion |
| `poll()` | `O(log N)` | Remove root + restore heap |
| `remove()` | `O(log N)` | Remove head/root |
| `peek()` | `O(1)` | Read root |
| `element()` | `O(1)` | Read root |
| `contains(Object)` | `O(N)` | Linear search |
| `remove(Object)` | `O(N)` | Find object first |
| `size()` | `O(1)` | Stored size |
| `isEmpty()` | `O(1)` | Stored size |
| `clear()` | `O(N)` | Clear references |
| iteration | `O(N)` | Visit elements |

### Memory

```text
PriorityQueue
ADD/REMOVE PRIORITY -> O(log N)
PEEK TOP            -> O(1)
SEARCH              -> O(N)
```

---

# 8. Deque

`Deque` means:

```text
Double Ended Queue
```

```text
FRONT                         BACK
  ↓                             ↓
[10] [20] [30] [40] [50]
 ↑                             ↑
add/remove                 add/remove
```

Declaration:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

---

# 9. Deque Methods

## Insert

```java
addFirst(E);
addLast(E);

offerFirst(E);
offerLast(E);
```

## Remove

```java
removeFirst();
removeLast();

pollFirst();
pollLast();
```

## Examine

```java
getFirst();
getLast();

peekFirst();
peekLast();
```

## Stack Style

```java
push(E);
pop();
peek();
```

## Other Useful Methods

```java
removeFirstOccurrence(Object o);
removeLastOccurrence(Object o);
descendingIterator();
```

---

# 10. Deque Method Table

| Operation | Front | Back |
|---|---|---|
| Add | `addFirst()` | `addLast()` |
| Safe add | `offerFirst()` | `offerLast()` |
| Remove | `removeFirst()` | `removeLast()` |
| Safe remove | `pollFirst()` | `pollLast()` |
| Look | `getFirst()` | `getLast()` |
| Safe look | `peekFirst()` | `peekLast()` |

---

# 11. ArrayDeque

```java
Deque<Integer> deque = new ArrayDeque<>();
```

### Features

```text
FIFO                  YES
LIFO/Stack            YES
Double-ended          YES
Resizable array       YES
null elements         NO
Thread-safe           NO
```

Good general-purpose choice for:

```text
Queue
Deque
Stack
```

---

# 12. ArrayDeque Time Complexity

| Method | Complexity |
|---|---:|
| `addFirst()` | `O(1)` amortized |
| `addLast()` | `O(1)` amortized |
| `offerFirst()` | `O(1)` amortized |
| `offerLast()` | `O(1)` amortized |
| `removeFirst()` | `O(1)` |
| `removeLast()` | `O(1)` |
| `pollFirst()` | `O(1)` |
| `pollLast()` | `O(1)` |
| `getFirst()` | `O(1)` |
| `getLast()` | `O(1)` |
| `peekFirst()` | `O(1)` |
| `peekLast()` | `O(1)` |
| `push()` | `O(1)` amortized |
| `pop()` | `O(1)` |
| `peek()` | `O(1)` |
| `add()` | `O(1)` amortized |
| `offer()` | `O(1)` amortized |
| `remove()` | `O(1)` |
| `poll()` | `O(1)` |
| `element()` | `O(1)` |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(1)` |
| `isEmpty()` | `O(1)` |
| `clear()` | `O(N)` |
| iteration | `O(N)` |

### Memory

```text
ArrayDeque
BOTH ENDS -> O(1)
SEARCH    -> O(N)
```

---

# 13. LinkedList as Queue / Deque

`LinkedList` implements both:

```text
List
and
Deque
```

Valid declarations:

```java
List<Integer> list = new LinkedList<>();
Queue<Integer> queue = new LinkedList<>();
Deque<Integer> deque = new LinkedList<>();
```

---

# 14. LinkedList Queue/Deque Methods

```java
offer(E);
poll();
peek();

add(E);
remove();
element();

addFirst(E);
addLast(E);

offerFirst(E);
offerLast(E);

removeFirst();
removeLast();

pollFirst();
pollLast();

getFirst();
getLast();

peekFirst();
peekLast();

push(E);
pop();

contains(Object o);
remove(Object o);
size();
isEmpty();
clear();
```

---

# 15. LinkedList Queue/Deque Time Complexity

| Method | Complexity |
|---|---:|
| `offer()` | `O(1)` |
| `add()` at end | `O(1)` |
| `poll()` | `O(1)` |
| `peek()` | `O(1)` |
| `addFirst()` | `O(1)` |
| `addLast()` | `O(1)` |
| `offerFirst()` | `O(1)` |
| `offerLast()` | `O(1)` |
| `removeFirst()` | `O(1)` |
| `removeLast()` | `O(1)` |
| `pollFirst()` | `O(1)` |
| `pollLast()` | `O(1)` |
| `getFirst()` | `O(1)` |
| `getLast()` | `O(1)` |
| `peekFirst()` | `O(1)` |
| `peekLast()` | `O(1)` |
| `push()` | `O(1)` |
| `pop()` | `O(1)` |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `get(index)` | `O(N)` |
| `size()` | `O(1)` |
| `clear()` | `O(N)` |

### Memory

```text
LinkedList
ENDS   -> O(1)
SEARCH -> O(N)
INDEX  -> O(N)
```

---

# 16. BlockingQueue

Package:

```java
java.util.concurrent
```

Used mainly for:

```text
Producer / Consumer
Multiple Threads
Waiting when full or empty
```

Extra methods:

```java
put(E);
take();

offer(E, timeout, unit);
poll(timeout, unit);

remainingCapacity();

drainTo(Collection);
drainTo(Collection, maxElements);
```

---

# 17. BlockingQueue Method Behavior

| Operation | Throws Exception | Special Value | Blocks | Timeout |
|---|---|---|---|---|
| Insert | `add()` | `offer()` | `put()` | `offer(e,time,unit)` |
| Remove | `remove()` | `poll()` | `take()` | `poll(time,unit)` |
| Examine | `element()` | `peek()` | — | — |

### Memory

```text
put()  -> wait for SPACE
take() -> wait for DATA
```

---

# 18. ArrayBlockingQueue

```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
```

### Features

```text
Bounded capacity
FIFO
Array-based
Thread-safe
Blocking
```

### Time Complexity

| Method | Complexity |
|---|---:|
| `offer()` | `O(1)` |
| `put()` | `O(1)` excluding wait time |
| `poll()` | `O(1)` |
| `take()` | `O(1)` excluding wait time |
| `peek()` | `O(1)` |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(1)` |
| `remainingCapacity()` | `O(1)` |
| iteration | `O(N)` |
| `clear()` | `O(N)` |
| `drainTo()` | `O(K)` for K transferred elements |

---

# 19. LinkedBlockingQueue

```java
BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
```

or bounded:

```java
BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(100);
```

### Features

```text
FIFO
Linked nodes
Thread-safe
Blocking
Optionally bounded
```

### Time Complexity

| Method | Complexity |
|---|---:|
| `offer()` | `O(1)` |
| `put()` | `O(1)` excluding wait |
| `poll()` | `O(1)` |
| `take()` | `O(1)` excluding wait |
| `peek()` | `O(1)` |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(1)` |
| `remainingCapacity()` | `O(1)` |
| iteration | `O(N)` |
| `clear()` | `O(N)` |
| `drainTo()` | `O(K)` |

---

# 20. PriorityBlockingQueue

```java
BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
```

Think:

```text
PriorityQueue + thread-safe blocking behavior
```

### Time Complexity

| Method | Complexity |
|---|---:|
| `offer()` | `O(log N)` |
| `put()` | `O(log N)` |
| `poll()` | `O(log N)` |
| `take()` | `O(log N)` excluding wait |
| `peek()` | `O(1)` |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(1)` |
| iteration | `O(N)` |

---

# 21. DelayQueue

```java
DelayQueue<MyDelayedObject> queue = new DelayQueue<>();
```

Elements become available only after their delay expires.

Elements must implement:

```java
Delayed
```

### Typical Time Complexity

| Method | Complexity |
|---|---:|
| `offer()` | `O(log N)` |
| `put()` | `O(log N)` |
| `poll()` | `O(log N)` when removing available head |
| `take()` | `O(log N)` excluding wait |
| `peek()` | `O(1)` |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(1)` |
| iteration | `O(N)` |

---

# 22. SynchronousQueue

```java
BlockingQueue<Integer> queue = new SynchronousQueue<>();
```

Special property:

```text
capacity = 0
```

It does not keep buffered elements.

```text
Producer
   ↓
direct handoff
   ↓
Consumer
```

Important methods:

```java
put(E);
take();

offer(E);
poll();

offer(E, timeout, unit);
poll(timeout, unit);
```

### Complexity Note

Normal Big-O is not very meaningful here because operations are synchronization/handoff operations and may block.

Remember:

```text
No internal buffer
Direct producer-consumer handoff
```

---

# 23. TransferQueue

Hierarchy:

```text
Queue
 ↓
BlockingQueue
 ↓
TransferQueue
```

Important methods:

```java
transfer(E);
tryTransfer(E);

tryTransfer(E, timeout, unit);

hasWaitingConsumer();
getWaitingConsumerCount();
```

Main implementation:

```text
LinkedTransferQueue
```

### Key Difference

```text
put()
-> waits until insertion can happen

transfer()
-> waits until a consumer actually receives the element
```

---

# 24. LinkedTransferQueue

```java
TransferQueue<Integer> queue = new LinkedTransferQueue<>();
```

### Typical Complexity

| Method | Typical Complexity |
|---|---:|
| `offer()` | `O(1)` amortized |
| `put()` | `O(1)` amortized |
| `poll()` | `O(1)` amortized |
| `take()` | `O(1)` amortized excluding wait |
| `transfer()` | Coordination-dependent |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(N)` |

---

# 25. BlockingDeque

Hierarchy:

```text
Queue
 ↓
Deque
 ↓
BlockingDeque
```

Main implementation:

```text
LinkedBlockingDeque
```

Extra methods:

```java
putFirst(E);
putLast(E);

takeFirst();
takeLast();

offerFirst(E, timeout, unit);
offerLast(E, timeout, unit);

pollFirst(timeout, unit);
pollLast(timeout, unit);
```

---

# 26. LinkedBlockingDeque Time Complexity

| Method | Complexity |
|---|---:|
| `offerFirst()` | `O(1)` |
| `offerLast()` | `O(1)` |
| `putFirst()` | `O(1)` excluding wait |
| `putLast()` | `O(1)` excluding wait |
| `pollFirst()` | `O(1)` |
| `pollLast()` | `O(1)` |
| `takeFirst()` | `O(1)` excluding wait |
| `takeLast()` | `O(1)` excluding wait |
| `peekFirst()` | `O(1)` |
| `peekLast()` | `O(1)` |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(1)` |
| iteration | `O(N)` |

---

# 27. ConcurrentLinkedQueue

```java
Queue<Integer> queue = new ConcurrentLinkedQueue<>();
```

### Features

```text
FIFO
Thread-safe
Non-blocking
Concurrent
Unbounded
```

### Time Complexity

| Method | Typical Complexity |
|---|---:|
| `offer()` | `O(1)` amortized |
| `add()` | `O(1)` amortized |
| `poll()` | `O(1)` amortized |
| `peek()` | `O(1)` amortized |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(N)` |
| iteration | `O(N)` |

Important:

```text
ConcurrentLinkedQueue != BlockingQueue
```

It does not provide:

```java
put();
take();
```

---

# 28. ConcurrentLinkedDeque

```java
Deque<Integer> deque = new ConcurrentLinkedDeque<>();
```

### Features

```text
Double-ended
Thread-safe
Non-blocking
Concurrent
```

### Time Complexity

| Method | Typical Complexity |
|---|---:|
| `offerFirst()` | `O(1)` amortized |
| `offerLast()` | `O(1)` amortized |
| `pollFirst()` | `O(1)` amortized |
| `pollLast()` | `O(1)` amortized |
| `peekFirst()` | `O(1)` amortized |
| `peekLast()` | `O(1)` amortized |
| `push()` | `O(1)` amortized |
| `pop()` | `O(1)` amortized |
| `contains()` | `O(N)` |
| `remove(Object)` | `O(N)` |
| `size()` | `O(N)` |
| iteration | `O(N)` |

---

# 29. Master Complexity Table

| Implementation | Insert | Remove Head | Peek | Search | Main Idea |
|---|---:|---:|---:|---:|---|
| `PriorityQueue` | `O(log N)` | `O(log N)` | `O(1)` | `O(N)` | Binary heap |
| `ArrayDeque` | `O(1)` amortized | `O(1)` | `O(1)` | `O(N)` | Resizable array |
| `LinkedList` | `O(1)` at ends | `O(1)` | `O(1)` | `O(N)` | Doubly-linked nodes |
| `ArrayBlockingQueue` | `O(1)` | `O(1)` | `O(1)` | `O(N)` | Bounded blocking array |
| `LinkedBlockingQueue` | `O(1)` | `O(1)` | `O(1)` | `O(N)` | Blocking linked nodes |
| `PriorityBlockingQueue` | `O(log N)` | `O(log N)` | `O(1)` | `O(N)` | Thread-safe priority heap |
| `DelayQueue` | `O(log N)` | `O(log N)` | `O(1)` | `O(N)` | Delayed priority queue |
| `ConcurrentLinkedQueue` | `O(1)` amortized | `O(1)` amortized | `O(1)` amortized | `O(N)` | Concurrent queue |
| `ConcurrentLinkedDeque` | `O(1)` amortized | `O(1)` amortized | `O(1)` amortized | `O(N)` | Concurrent deque |
| `LinkedBlockingDeque` | `O(1)` at ends | `O(1)` | `O(1)` | `O(N)` | Blocking deque |

---

# 30. Best Memory Table

## Queue

```text
             Exception        Safe

ADD          add()            offer()
REMOVE       remove()         poll()
LOOK         element()        peek()
```

## Deque

```text
FRONT:
addFirst()
offerFirst()
removeFirst()
pollFirst()
getFirst()
peekFirst()

BACK:
addLast()
offerLast()
removeLast()
pollLast()
getLast()
peekLast()
```

## Stack with Deque

```text
push()
pop()
peek()
```

## BlockingQueue

```text
put()
take()

offer(timeout)
poll(timeout)
```

## TransferQueue

```text
transfer()
tryTransfer()
```

---

# 31. Which Queue Should I Use?

```text
Normal FIFO queue?
-> ArrayDeque
```

```text
Priority ordering?
-> PriorityQueue
```

```text
Need both front and back?
-> ArrayDeque / Deque
```

```text
Need List + Queue behavior?
-> LinkedList
```

```text
Need producer/consumer blocking?
-> BlockingQueue
   -> ArrayBlockingQueue
   -> LinkedBlockingQueue
```

```text
Need priority + blocking?
-> PriorityBlockingQueue
```

```text
Need thread-safe non-blocking FIFO?
-> ConcurrentLinkedQueue
```

---

# 32. Final Interview Memory

```text
QUEUE
-> normally FIFO
-> offer / poll / peek
```

```text
PRIORITYQUEUE
-> priority first
-> insert/remove O(log N)
-> peek O(1)
```

```text
DEQUE
-> Double Ended Queue
-> front + back
```

```text
ARRAYDEQUE
-> fast Queue + Stack
-> both ends O(1)
```

```text
LINKEDLIST
-> List + Deque
-> ends O(1)
-> search/index O(N)
```

```text
BLOCKINGQUEUE
-> multithreading
-> put() waits for space
-> take() waits for data
```

```text
CONCURRENTLINKEDQUEUE
-> thread-safe
-> non-blocking
-> size() O(N)
```

---

# 33. One-Line Summary

> **Queue normally means FIFO, PriorityQueue means priority order, Deque means both ends, ArrayDeque is a fast general-purpose Queue/Stack implementation, LinkedList supports both List and Deque behavior, and BlockingQueue is designed for producer-consumer multithreaded workflows.**
