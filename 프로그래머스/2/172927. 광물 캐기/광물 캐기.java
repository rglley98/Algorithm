class Solution {
    int[] picks;
    String[] minerals;
    
    int size;
    int[] perm;
    
    int answer;
    
    void makePerm(int depth) {
       if(depth == size) {
           int mineralIdx = 0;
           int fatigue = 0;
           for(int pick : perm) {
               int count = 5;
               
               while(count > 0 && mineralIdx < minerals.length) {
                   String mineral = minerals[mineralIdx++];
                   
                   if(pick == 0) {
                       fatigue++;
                   } else if(pick == 1) {
                       if(mineral.equals("diamond"))
                           fatigue += 5;
                       else
                           fatigue += 1;
                   } else {
                       if(mineral.equals("diamond"))
                           fatigue += 25;
                       else if(mineral.equals("iron"))
                           fatigue += 5;
                       else 
                           fatigue += 1;
                   }
                   
                   count--;
                   
                   if(fatigue > answer)
                       return;
               }
           }
           
           answer = Math.min(answer, fatigue);
           
           return;
       } 
        
        for(int pick = 0; pick < 3; pick++) {
            if(picks[pick] == 0)
                continue;
            
            perm[depth] = pick;
            picks[pick]--;
            makePerm(depth + 1);
            picks[pick]++;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
        
        int totalPicks = 0;
        for(int num : picks) {
            totalPicks += num;
        }
        
        int requirePicks = minerals.length / 5;
        if(minerals.length % 5 != 0) {
            requirePicks++;
        }
        
        size = Math.min(totalPicks, requirePicks);
        perm = new int[size];
        answer = Integer.MAX_VALUE;
        makePerm(0);
        
        return answer;
    }
}