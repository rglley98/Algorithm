class Solution {
    public int solution(String s) {
        int size = s.length();
        
        int answer = 1;
        for(int idx = 0; idx < size; idx++) {
            int left = idx - 1;
            int right = idx + 1;
            
            int length = 1;
            while(true) {
                if(left < 0 || right >= size)
                    break;
                
                if(s.charAt(left) != s.charAt(right))
                    break;
                
                left--;
                right++;
                length += 2;
            }
            
            answer = Math.max(answer, length);
            
            left = idx;
            right = idx + 1;
            
            length = 0;
            while(true) {
                if(left < 0 || right >= size)
                    break;
                
                if(s.charAt(left) != s.charAt(right))
                    break;
                
                left--;
                right++;
                length += 2;
            }
            
            answer = Math.max(answer, length);
        }
        
        return answer;
    }
}
