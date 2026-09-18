# Time Complexity — Simple Guide

A useful order from **fastest to slowest** is:

| Complexity | General feeling | Simple meaning | Example |
|---|---|---|---|
| **O(1)** | Excellent | Same amount of work regardless of input size | `array[5]`, `HashMap.get()` average |
| **O(log n)** | Excellent | Problem becomes smaller by a factor each step | Binary Search |
| **O(n)** | Good | Visit elements once | One `for` loop |
| **O(n log n)** | Good / Moderate | `n` operations combined with logarithmic work | Merge Sort, Heap Sort |
| **O(n²)** | Bad for large `n` | Compare/process many pairs | Nested loops |
| **O(2ⁿ)** | Very bad | Number of possibilities doubles | Some subset problems |
| **O(n!)** | Extremely bad | Try every possible ordering | Permutations |

---

## 1. O(1) — Constant Time

`O(1)` means the amount of work stays approximately the same regardless of input size.

```java
int x = array[5];
```

Whether the array has:

```text
10 elements
1,000 elements
1,000,000 elements
```

accessing `array[5]` takes approximately the same amount of work.

### Remember

> **O(1) = Constant time**

---

## 2. O(log n) — Logarithmic Time

`O(log n)` is usually very efficient.

The classic example is **Binary Search**.

Suppose:

```text
n = 16
```

Binary Search keeps dividing the search space in half:

```text
16
8
4
2
1
```

So it takes only about 4 steps.

For:

```text
n = 1,000,000
```

Binary Search needs only about:

```text
20 steps
```

Example:

```java
while (n > 1) {
    n = n / 2;
}
```

### Remember

> **O(log n) = We reduce the remaining problem by a factor, usually half, every iteration.**

---

## 3. O(n) — Linear Time

`O(n)` means the amount of work grows directly with the input size.

Example:

```java
for (int i = 0; i < n; i++) {
    System.out.println(i);
}
```

If:

```text
n = 10
```

we do about 10 operations.

If:

```text
n = 1000
```

we do about 1000 operations.

### Remember

> **O(n) = One full pass through the input**

---

## 4. O(n log n)

`O(n log n)` often appears in efficient sorting algorithms such as **Merge Sort** and **Heap Sort**.

Think of it as:

```text
n × log n
```

Example:

```java
for (int i = 0; i < n; i++) {

    int j = n;

    while (j > 1) {
        j = j / 2;
    }
}
```

Outer loop:

```text
O(n)
```

Inner loop:

```text
O(log n)
```

Together:

```text
O(n) × O(log n)
= O(n log n)
```

### Important

This is **not** `n × n/2`.

If you have:

```text
n × n/2
```

then:

```text
n² / 2
```

and Big-O ignores constants:

```text
O(n² / 2)
= O(n²)
```

### Remember

> **O(n log n) = n work combined with logarithmic work**

---

## 5. O(n²) — Quadratic Time

A common example is two nested loops.

```java
for (int i = 0; i < n; i++) {

    for (int j = 0; j < n; j++) {
        System.out.println(i + " " + j);
    }
}
```

Outer loop:

```text
n
```

Inner loop:

```text
n
```

Together:

```text
n × n
= n²
```

So:

```text
O(n²)
```

### Common example

Bubble Sort can have:

```text
O(n²)
```

### Important

Two loops do **not automatically** mean `O(n²)`.

For example:

```java
for (int i = 0; i < n; i++) {
}

for (int j = 0; j < n; j++) {
}
```

These loops run one after another:

```text
O(n) + O(n)
= O(2n)
= O(n)
```

But nested loops usually multiply:

```text
O(n) × O(n)
= O(n²)
```

### Remember

> **O(n²) = Usually nested loops over the same input**

---

## 6. O(2ⁿ) — Exponential Time

`O(2ⁿ)` means the number of possibilities roughly doubles whenever `n` increases by 1.

For example:

```text
n = 1 → 2 possibilities
n = 2 → 4 possibilities
n = 3 → 8 possibilities
n = 4 → 16 possibilities
n = 10 → 1024 possibilities
```

This complexity can appear when generating all possible subsets.

### Remember

> **O(2ⁿ) = Number of possibilities doubles**

---

## 7. O(n!) — Factorial Time

`O(n!)` usually appears when an algorithm tries every possible ordering, or permutation, of elements.

For example, with:

```text
ABC
```

possible arrangements are:

```text
ABC
ACB
BAC
BCA
CAB
CBA
```

There are:

```text
3! = 6
```

With four items:

```text
4! = 24
```

With five:

```text
5! = 120
```

With ten:

```text
10! = 3,628,800
```

### Remember

> **O(n!) = Try every possible permutation/order**

---

# Easy Ranking to Remember

```text
BEST / FASTEST
      ↓

O(1)          Constant
O(log n)      Divide problem repeatedly
O(n)          One pass
O(n log n)    n × divide/reduce
O(n²)         Nested n × n
O(2ⁿ)         Exponential possibilities
O(n!)         All permutations

      ↓
WORST / SLOWEST
```

---

# Very Important: Big-O vs Best / Average / Worst Case

Big-O itself does **not** mean:

- Best case
- Average case
- Worst case

These are separate ideas.

For example, an algorithm may have:

```text
Best case:  O(1)
Worst case: O(n)
```

Big-O describes how the running time or space requirement grows as the input size grows.

---

# Quick Interview Memory Trick

```text
O(1)       → Direct access
O(log n)   → Divide by 2
O(n)       → One loop
O(n log n) → Loop + logarithmic work
O(n²)      → Nested loops
O(2ⁿ)      → All subsets / exponential possibilities
O(n!)      → All permutations
```
