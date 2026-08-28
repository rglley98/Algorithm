class Solution {

    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        for(int idx = 1; idx < arrayA.length; idx++) {
            gcdA = getGCD(gcdA, arrayA[idx]);
        }
        
        int gcdB = arrayB[0];
        for(int idx = 1; idx < arrayB.length; idx++) {
            gcdB = getGCD(gcdB, arrayB[idx]);
        }
        
        int answer = 0;
        
        boolean isPossible = true;
        for(int num : arrayB) {
            if(num % gcdA == 0) {
                isPossible = false;
                break;
            }
        }
        
        if(isPossible) {
            answer = gcdA;
        }
        
        isPossible = true;
        for(int num : arrayA) {
            if(num % gcdB == 0) {
                isPossible = false;
                break;
            }
        }
        
        if(isPossible) {
            answer = Math.max(answer, gcdB);
        }
        
        return answer;
    }
    
    int getGCD(int num1, int num2) {
        while(true) {
            int remain = num1 % num2;
            num1 = num2;
            num2 = remain;
            
            if(num2 == 0) {
                break;
            }
        }
        
        return num1;
    }
}