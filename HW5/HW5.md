# HW5

Mingyi Lu

9075876988

## Q1

1. A: 6 (choose from B and C)

   B: 4 (choose from D and E)

   C: 6 (choose from F and G)

   D: 4 (choose from 3 and 4)

   E: 7 (choose from 7 and 2)

   F: 8 (choose from 8 amd1)

   G:6 (choose from 0 and 6)

   since Game theoretic value is the score of the terminal node that will be reached if both players play optimally optimal play, so every time we choose the best score for the player.

2. D: Minimax value = 4,  alpha value = 4, beta value =  ∞

   E: Minimax value = 7,  alpha value = 7, beta value = 4

   B: Minimax value = 4,  alpha value = - ∞, beta value = 4

   F: Minimax value = 8,  alpha value = 8, beta value =  ∞

   G: Minimax value = 6,  alpha value = 6, beta value = 8

   C: Minimax value = 6,  alpha value = 4, beta value = 6

   A: Minimax value = 6,  alpha value = 6, beta value =  ∞

3. the branch from E to 2 will be pruned.

   ![481520759747_.pic](/Users/lmy/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/cfad746857c7616a80c5680196b5b107/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/481520759747_.pic.jpg)

4. Because we have no need to explore an idea that is surely bad and take the time to see how truly awful it is, if we find the branch is not meaningful, we'd better to stop exploring it. Here the complexity will be deducted because alpha-beta pruning can help us to prune those branches.

5. The first coin will have 1/2 probability to choose the optimal solution. And the other situation, if the second time the coin choose the optimal solution(which is also 1/2 of this 1/2 probability), then it will be totally 1/2 * 1/2 probability in the other situation. So at each node, a player will have 1/2 + 1/4 = 3/4 probability to choose an optimal action.

6. ​

![491520818102_.pic](/Users/lmy/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/cfad746857c7616a80c5680196b5b107/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/491520818102_.pic.jpg)

7. A: 0.25*4.25 + 0.75\*4.94 = 4.77

   B: 0.75*3.75 + 0.25\*5.75 = 4.25

   C: 0.25*6.25 + 0.75\*4.5 = 4.94

   D: 0.25*3 + 0.75\*4 = 3.75

   E: 0.75*7 + 0.25\*2 = 5.75

   F: 0.75*8 + 0.25\*1 = 6.25

   G: 0.25*0 + 0.75\*6 = 4.5



## Q2

1. ​

I will use Java for this program. I will use scanner to scan each line, split it with " ", and every short string between " " will be regarded as a word

2. ​

Total words: 203845

Distinct words: 17834

3. ​

| word         | count |
| ------------ | ----- |
| the          | 10832 |
| to           | 6392  |
| of           | 5126  |
| in           | 4438  |
| and          | 4203  |
| a            | 4067  |
| AI           | 3859  |
| is           | 3472  |
| will         | 3295  |
| it           | 3028  |
| for          | 2502  |
| on           | 2015  |
| are          | 1994  |
| not          | 1536  |
| this         | 1252  |
| artificial   | 1236  |
| intelligence | 1182  |
| I            | 923   |
| can          | 896   |
| More         | 791   |



4. ​

| name            | count |
| --------------- | ----- |
| disgreement     | 1     |
| disenfranchised | 1     |
| discriminative  | 1     |
| discriminating  | 1     |
| discrete        | 1     |
| discrepancy     | 1     |
| discovering     | 1     |
| discouraging    | 1     |
| discouraged     | 1     |
| galileo         | 1     |
| gale            | 1     |
| gadgets         | 1     |
| ga              | 1     |
| fy              | 1     |
| fuzzy           | 1     |
| futusre         | 1     |
| futuristgerd    | 1     |
| futile          | 1     |
| furthering      | 1     |

5. ​

![501520842961_.pic_hd](/Users/lmy/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/cfad746857c7616a80c5680196b5b107/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/501520842961_.pic_hd.jpg)

6. ​

![511520842962_.pic_hd](/Users/lmy/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/cfad746857c7616a80c5680196b5b107/Message/MessageTemp/9e20f478899dc29eb19741386f9343c8/Image/511520842962_.pic_hd.jpg)



7. ​

first graph shows the count is inversely related to the rank, the second one is more linear but still reversely related.

8. ​

a. the word is not been carefully selected with taking care of the comma, and dots.

b. there are limited word in this research. Should inculde far more text to analyze.