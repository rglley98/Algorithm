class Solution {
     int count(int sec) {
        int result = 0;

        result += sec * 59 / 3600;

        result += sec * 719 / 43200;

        result -= sec / 43200;

        return result;
    }

    boolean isAlarm(int sec) {
        return sec * 59 % 3600 == 0 || sec * 719 % 43200 == 0;
    }
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int start = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;

        int answer = count(end) - count(start);
        
        if (isAlarm(start)) 
            answer++;

        return answer;
    }
}

