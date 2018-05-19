# CS540 HW8

Mingyi Lu

9075876988

###### 

## Q1

**Knowledge Base:**

starting sentence: $p \Longrightarrow (q \Longrightarrow r)$

implication elimination: $p\Longrightarrow (\neg q \vee r)$

implication elimination: $\neg p \vee \neg q \vee r$

**Query:**

starting sentence: $\neg ((p \Longrightarrow q) \Longrightarrow (p \Longrightarrow r))$

implication elimination: $\neg((\neg p \vee q)\Longrightarrow (\neg p \vee r))$

implication elimination: $\neg (\neg(\neg p \vee q) \vee (\neg p \vee r))$

Move negations inward: $(\neg p \vee q) \wedge \neg(\neg p \vee r)$(De Morgan's rule)

​					    $(\neg p \vee q) \wedge (p \wedge \neg r)$ (De Morgan's rule)

**Resolution steps**

- Add $\neg \beta$ (query) to KB, convert to CNF:

  a: $\neg p \vee \neg q \vee r$

  b1: $\neg p \vee q$

  b2: $p$

  b3: $\neg r$

- Step 1: resolve a, b1: $\neg p \vee r$

- Step 2: resolve above and b2: $r$

- Step 3: resolve above and b3: empty

Therefore, the query is proved to be true.



## Q2

**Riddles 1**

1. Everything that is not a building jumps higher than a building.

2. Variable x: things, its domain: everything

3. Predicate: NotBuilding(term) — true if term is not a building

   ​		   Higher(term1, term2) — true if term1 jumps higher than term2

4. FOL sentence: $\forall xNotBuilding(x) \Longrightarrow Higher(x, building)$

**Riddles 2**

1. There exist at least one honest in the 100 politicians, and others are liars. For any two politicians, at least one is a liar.

2. Variable x: politicians, domain: 100 politicians

3. Predicate: IsLiar(term) — true if term is liar; false if term is honest

   ​		   Choose(term1, term2) — true if term1, term2 is chosen from x's domain

   ​		   ExistHonest(term) — true if there is at least one honest in the domain of term

4. FOL sentence: $\forall x1, x2Choose(x1, x2) \wedge ExistHonest(x) \Longrightarrow IsLiar(x1) \vee IsLiar(x2)$



## Q3

1.

|               | Madison | Seattle | Boston | Vancouver | Winnipeg | Montreal |
| :------------ | ------- | ------- | ------ | --------- | -------- | -------- |
| **Madison**   | 0       | 1617    | 931    | 1654      | 597      | 800      |
| **Seattle**   | 1617    | 0       | 2486   | 121       | 1153     | 2283     |
| **Boston**    | 931     | 2486    | 0      | 2500      | 1344     | 250      |
| **Vancouver** | 1654    | 121     | 2500   | 0         | 1159     | 2291     |
| **Winnipeg**  | 597     | 1153    | 1344   | 1159      | 0        | 1132     |
| **Montreal**  | 800     | 2283    | 250    | 2291      | 1132     | 0        |

2. ​

**iteration 1:**

Closet pair: d(A, B) = d (Seattle, Vancouver) =121

All clusters: Madison, Boston, Winnipeg, Montreal, (Seattle, Vancouver)

**Iteration 2:**

Closet pair: d(A, B) = d (Boston, Montreal) = 250

All clusters: Madison, Winnipeg, (Boston, Montreal), (Seattle, Vancouver)

**Iteration 3:**

Closet pair: d(A, B) = d(Madison, Winnipeg) = 597

All clusters: (Madison, Winnipeg), (Boston, Montreal), (Seattle, Vancouver)

**Iteration 4:**

Closet pair: d(A, B) =((Madison, Winnipeg), (Boston, Montreal)) = 1344

All clusters: (Madison, Winnipeg, Boston, Montreal), (Seattle, Vancouver)



3. ​

**iteration 1:**

Closet pair: d(A, B) = d(Madison, Boston) = 931

All clusters: (Madison, Boston), Winnipeg, Montreal, Seattle, Vancouver

**iteration 2:**

Closet pair: d(A, B) = d(Winnipeg, Montreal) = 1132

All clusters: (Madison, Boston), (Winnipeg, Montreal), Seattle, Vancouver

**iteration 3:**

Closet pair: d(A, B) = d((Winnipeg, Montreal), Vancouver) = 2291

All clusters: (Madison, Boston), (Winnipeg, Montreal, Vancouver), Seattle

**iteration 4:**

Closet pair: d(A, B) = ((Madison, Boston), Seattle) = 2486

All clusters: (Madison, Boston, Seattle), (Winnipeg, Montreal, Vancouver)



## Q4

1. ​

**iteration 1:**

assignments of c1: x1, x2, x3

assignments of c2: x4, x5, x6

Updated cluster center: $c1 = {(0+2+4)\over 3} = 2$, $c2 = {(6+7+8)\over 3} = 7$

Energy = $2^2 + 0^2 + 2^2 + (-1)^2 + 0^2 + 1^2 = 10$

**iteration 2:**

assignments of c1: x1, x2, x3

assignments of c2: x4, x5, x6

Updated cluster center:$c1 = {(0+2+4)\over 3} = 2$, $c2 = {(6+7+8)\over 3} = 7$

Energy = $(-2)^2 + 0^2 + 2^2 + (-1)^2 + 0^2 + 1^2 = 10$

Since the cluster centers no longer change, so the iteration stops.



2. ​

**iteration 1:**

Assignments of c1: x1

assignments of c2: x2, x3, x4, x5, x6

Updated cluster center: $c1 = {0 \over 1} = 0$, $c2 = {(2+4+6+7+8)\over5} =5.4$ 

Energy = $0^2 + (-3.4)^2 + (-1.4)^2 + (0.6)^2 + (1.6)^2 + (2.6)^2 = 23.2$

**iteration 2:**

Assignments of c1: x1, x2

assignments of c2: x3, x4, x5, x6

updated cluster center: $c1 = {(0+2)\over2} = 1$, $c2 = {(4+6+7+8)\over4} = 6.25$

Energy = $(-1)^2 + 1^2 + (-2.25)^2 + (-0.25)^2 + 0.75^2 + 1.75^2 = 10.75$

**iteration 3:**

Assignments of c1: x1, x2

assignments of c2: x3, x4, x5, x6

updated cluster center: $c1 = {(0+2)\over2} = 1$, $c2 = {(4+6+7+8)\over4} = 6.25$

Energy = $(-1)^2 + 1^2 + (-2.25)^2 + (-0.25)^2 + 0.75^2 + 1.75^2 = 10.75$

Since the cluster centers no longer change, so the iteration stops.



3. The first one is better, because the random center can better divided the six items to two clusters, and then the final energy is smaller than the second one.