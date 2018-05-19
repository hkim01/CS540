**CS540 HW2**

Mingyi Lu



**Question：**

An (m, n, k)-puzzle is a sliding puzzle with m columns, n rows, and k empty squares, where 1 ≤ k < mn.
As usual, one move is to move a single tile to an adjacent (up, down, left, right) empty square, if available.



1. (10 points) How many tiles are there in a (m, n, k)-puzzle in general? For example, there are 8 tiles ina (3, 3, 1)-puzzle.
2. (20 points) Let each tile have a distinct name. How many distinct states are there in the state space?Show your derivation.
3. (20 points) Draw a graph that corresponds to the state space of a (2, 2, 1)-puzzle, and briefly describeyour graph. This is not an art project: You may represent the states in ways easy for you to type,and you do not necessarily need drawing programs – you may even use plaintext. You can also handdraw the graph, but please clearly show the nodes and edges.

​			

**Answer**

1. There are (m*n – k) tiles in a (m, n, k)-puzzle.

2. Since we have (m*n - k) tiles that need to be placed in a m * n puzzle, so there are  P(m * n, m * n - k)  states, which is equal to (m * n)! / ((m * n) - (m * n - k))! = (m * n)!/k!

3. Number 1, 2, 3 represent the tiles, and the other square is empty. There are 24 states in total.

   （see the next page）

   ![431519000984_.pic](/Users/lmy/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/cfad746857c7616a80c5680196b5b107/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/431519000984_.pic.jpg)

