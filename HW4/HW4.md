# CS540 HW4

Mingyi Lu

9075876988



## Q1

1. By definition, the heuristic function h(B) that satisfiy h(B) <= h*(B), where h\*(B) is the true cost from node B to the goal, is considered admissible.

2. iteration 1:

   Put the start node A on the priority queue,called OPEN

   Remove A from OPEN and place it on CLOSED 

   Expand A, generating its successors B and C1, and set a pointer for each successor that points to A.

   B is not already on OPEN or CLOSED, so estimate h(B) = 100.

   g(B) = g(A) + c(A, B) = 1/2, f(B) = g(B) + h(B) = 100.5.

   Place it on OPEN.

   C1 is not already on OPEN or CLOSED, so estimate h(C1) = 0.

   g(C1) = g(A) + c(A, C1) = 1, f(C1) = g(C1) + h(C1) = 1.

   Place it on OPEN.

   ---

   states in OPEN: C1, B

   states in CLOSED: A

   B: f = 100.5, g = 1/2, h = 100, back pointer: to A

   C1: f = 1, g = 1, h = 0, back pointer: to A

   ​

   iteration 2:

   Remove C1 from OPEN and place it on CLOSED 

   Expand C1, generating its successor C2, and set a pointer for C2 that points to C1.

   C2 is not already on OPEN or CLOSED, so estimate h(C2) = 0.

   g(C2) = g(C1) + c(C1, C2) = 1.5, f(C2) = g(C2) + h(C2) = 1.5.

   Place it on OPEN.

   -----

   states in OPEN: C2, B

   states in CLOSED: A, C1

   C2: f = 1.5, g = 1.5, h = 0, back pointer: to C1

   ​

   iteration 3:

   Remove C2 from OPEN and place it on CLOSED 

   Expand C2, generating its successor C3, and set a pointer for C3 that points to C2.

   C3 is not already on OPEN or CLOSED, so estimate h(C3) = 0.

   g(C3) = g(C2) + c(C2, C3) = 1.75, f(C3) = g(C3) + h(C3) = 1.75.

   Place it on OPEN.

   ----

   states in OPEN: C3, B

   states in CLOSED: A, C1, C2

   C3: f = 1.75, g = 1.75, h = 0, back pointer: to C2

   ​

   iteration 4:

   Remove C3 from OPEN and place it on CLOSED 

   Expand C3, generating its successor C4, and set a pointer for C4 that points to C3.

   C4 is not already on OPEN or CLOSED, so estimate h(C4) = 0.

   g(C4) = g(C3) + c(C3, C4) = 1.875, f(C4) = g(C4) + h(C4) = 1.875.

   Place it on OPEN.

   ---

   states in OPEN: C4, B

   states in CLOSED: A, C1, C2, C3

   C4: f = 1.875, g = 1.875, h = 0, back pointer: to C3

   ​

   iteration 5:

   Remove C4 from OPEN and place it on CLOSED 

   Expand C4, generating its successor C5, and set a pointer for C5 that points to C4.

   C5 is not already on OPEN or CLOSED, so estimate h(C5) = 0.

   g(C5) = g(C4) + c(C4, C5) = 1.9375, f(C5) = g(C5) + h(C5) = 1.9375.

   Place it on OPEN.

   ----

   states in OPEN: C5, B

   states in CLOSED: A, C1, C2, C3, C4

   C5: f = 1.9375, g = 1.9375, h = 0, back pointer: to C4

3. limi→∞f(𝐶𝑖)= 1+1/2+1/4+1/8+⋯+1/2^𝑛 

   because

   ![Screen Shot 2018-03-05 at 1.57.16 AM](/Users/lmy/Desktop/Screen Shot 2018-03-05 at 1.57.16 AM.png)

   so limi→∞f(𝐶𝑖) = 1 + 1 = 2.

4. Since A* algorithm will always choose the node S with lowest f(s), which is equal to g(s) + h(s), and the parent of G, which is B, has a very high h(B) = 100, while other nodes are all has h(s) = 0. Because for the nodes on right branch, g(s) is always less than 1, then for a node S except B, f(s) are always less than 100 + 1/2. Therefore, A* algorithm will never choose node B, and also its successor, the goal state G.

5. If h(B) is inadmissible, it should higher than the true cost from B to the goal G, which is higher than 1/2. Then f(B) will higher than 1, which is equal to f(C1). So the algorithm will still go through right branch. Then we try limi→∞f(𝐶𝑖), which is equal to 2. If f(B) is less than 2, it will be chosen to find G. h(B) = 2 - 1/2 = 3/2.

   Therefore, h(B) should in the range of (1/2, 3/2).

6. It's a sufficient condition because admissible h allows the algorithm to choose B at the beginning, but if h is lower than 3/2, the algorithm could still find the goal state. So admissible h is a sufficient, while not necessary condition. (I'm not sure whether I can answer like this, since the question doesn't give the option. If the condition in Q5 shouldn't be considered, it's a necessary condition.)



## Q2

**iteration1**

current point: x = 2, f(x) = 2

temperature: T(i) = 2(0.9)^1 = 1.8

f(3) = 1 < f(2)

probability: p = e^(-|2 - 3|/1.8) = 0.574

since z = 0.102 < p, choose successor.

**iteration2**

current point: x = 3, f(x) = 1

temperature: T(i) = 2(0.9)^2 = 1.62

f(1) = 3 > f(3), choose successor.

probability: p = e^(-|1 - 3|/1.62) = 0.291

**iteration3**

current point: x = 1, f(x) = 3

temperature: T(i) = 2(0.9)^3 = 1.458

f(1) = 3  = f(1), choose current.

probability: p = e^(-|3 - 3|/1.8) = 1

**iteration4**

current point: x = 1, f(x) = 3

temperature: T(i) = 2(0.9)^4 = 1.312

f(4) = 0 < f(3).

probability: p = e^(-|3 - 0|/1.312) = 0.101

since z = 0.493 > p, choose current.

**iteration5**

current point: x = 1, f(x) = 3

temperature: T(i) = 2(0.9)^5 = 1.181

f(2) = 2 > f(3), choose successor.

probability: p = e^(-|3 - 2|/1.181) = 0.429

**iteration6**

current point: x = 2, f(x) = 2

temperature: T(i) = 2(0.9)^6 = 1.068

f(3) = 1 < f(2)

probability: p = e^(-|3 - 2|/1.068) = 0.392

since z = 0.508 > p, choose current.

**iteration7**

current point: x = 2, f(x) = 2

temperature: T(i) = 2(0.9)^7 = 0.957

f(4) = 0 < f(2)

probability: p = e^(-|2 - 0|/0.957) = 0.124

since z = 0.982 > p, choose current.

**iteration8**

current point: x = 2, f(x) = 2

temperature: T(i) = 2(0.9)^8 = 0.861

f(3) = 1 < f(2)

probability: p = e^(-|2 - 1|/0.861) = 0.313

since z = 0.887 > p, choose current.



## Q3

1. n!
2. (n - 1) / n! 
3. There are 112511 trees. There are 112511! states.
4. (112511 + 1) * 10 km = 3LD
5. 112511 + 1) * 10 m = 1125.12 km
6. since 25 miles = 40.23km, 24 * 40.23 = 965.52 km < 1125.12 km, so it will be impossible to finish the job in one day.