import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int idx = 0; idx < enemy.length; idx++) {
            queue.offer(enemy[idx]);
            n -= enemy[idx];
            
            if(n < 0) {
                if(k > 0) {
                    n += queue.poll();
                    k--;
                } else {
                    return idx;
                }
            }
        }
        
        return enemy.length;
    }
}