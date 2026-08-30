import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Arrays.sort(weights);
        
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int weight : weights) {
            answer += countMap.getOrDefault(weight, 0);
            
            if(weight % 2 == 0) 
                answer += countMap.getOrDefault(weight / 2, 0);
            
            if(weight % 3 == 0) 
                answer += countMap.getOrDefault(weight / 3 * 2, 0);
            
            if(weight % 4 == 0) 
                answer += countMap.getOrDefault(weight / 4 * 3, 0);
            
            countMap.put(weight, countMap.getOrDefault(weight, 0) + 1);
        }
        
        return answer;
    }
}

