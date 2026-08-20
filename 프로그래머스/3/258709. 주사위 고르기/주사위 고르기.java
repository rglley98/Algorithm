import java.util.*;

class Solution {
    int[][] dice;
    int size;
    boolean[] selected;
    int maxWins;
    int[] answer;
    
    void divideDice(int depth, int fromIdx) {
        if(depth == size / 2) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            
            for(int diceIdx = 1; diceIdx <= size; diceIdx++) {
                if(selected[diceIdx]) {
                    list1.add(diceIdx);
                } else {
                    list2.add(diceIdx);
                }
            }
            
            List<Integer> sum1 = getSum(list1);
            List<Integer> sum2 = getSum(list2);
            Collections.sort(sum2);
            
            int win = 0;
            for(int num : sum1) {
                win += lowerBound(sum2, num);
            }
            
            if(win > maxWins) {
                maxWins = win; 

                for(int listIdx = 0; listIdx < list1.size(); listIdx++) {
                    answer[listIdx] = list1.get(listIdx);
                }
            }
            
            return;
        }
        
        for(int diceIdx = fromIdx; diceIdx <= size; diceIdx++) {
            selected[diceIdx] = true;
            divideDice(depth + 1, diceIdx + 1);
            selected[diceIdx] = false;
        }
    }
    
    List<Integer> getSum(List<Integer> diceList) {
        List<Integer> result = new ArrayList<>();
        
        rollDice(0, 0, diceList, result);
        
        return result;
    }
    
    void rollDice(int depth, int sum, List<Integer> diceList, List<Integer> result) {
        if(depth == size / 2) {
            result.add(sum);
            return;
        }
        
        int diceIdx = diceList.get(depth);
        
        for(int faceIdx = 0; faceIdx < 6; faceIdx++) {
            rollDice(depth + 1, sum + dice[diceIdx][faceIdx], diceList, result);
        }
    }
    
    int lowerBound(List<Integer> sumList, int target) {
        int left = 0;
        int right = sumList.size() - 1;
        int answer = sumList.size();
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(sumList.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
    
    
    public int[] solution(int[][] dice) {
        size = dice.length;
        this.dice = new int[size + 1][6];
        for(int diceIdx = 1; diceIdx <= size; diceIdx++) {
            this.dice[diceIdx] = dice[diceIdx - 1];
        }
        
        selected = new boolean[size + 1];
        answer = new int[size / 2];
        divideDice(0, 1);
        
        return answer;
    }
}