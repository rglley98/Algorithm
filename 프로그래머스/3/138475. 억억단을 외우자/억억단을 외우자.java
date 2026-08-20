import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        int[] count = new int[e + 1];
        for(int num = 1; num <= e; num++) {
            for(int countNum = num; countNum <= e; countNum += num) {
                count[countNum]++;
            }
        }
        
        int[] dp = new int[e + 1];
        dp[e] = e;
        for(int num = e - 1; num >= 1; num--) {
            if(count[num] >= count[dp[num + 1]]) {
                dp[num] = num;
            } else {
                dp[num] = dp[num + 1];
            }
        }
        
        for(int idx = 0; idx < starts.length; idx++) {
            answer[idx] = dp[starts[idx]];
        }
        
        return answer;
    }
}