import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(int idx = 0; idx < enemy.length; idx++) {
            queue.offer(enemy[idx]);
            
            if(queue.size() > k)
                n -= queue.poll();
            
            if(n < 0)
                return idx;
        }
        
        return enemy.length;
    }
}

