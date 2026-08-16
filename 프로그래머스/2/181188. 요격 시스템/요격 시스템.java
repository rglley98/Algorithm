import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int prevTo = -1;
        
        for(int[] target : targets) {
            int from = target[0];
            int to = target[1];
            
            if(from >= prevTo) {
                answer++;
                prevTo = to;
            }
        }
        
        return answer;
    }
}


