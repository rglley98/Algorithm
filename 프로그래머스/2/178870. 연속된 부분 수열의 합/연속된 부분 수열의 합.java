class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        int length = Integer.MAX_VALUE;
        
        int answerLeft = -1;
        int answerRight = -1;
        
        while(true) {
            if(sum < k) {
                right++;
                
                if(right == sequence.length) {
                    break;
                }
                
                sum += sequence[right];
                continue;
            }
            
            if(sum > k) {
                sum -= sequence[left];
                left++;
                continue;
            }
            
            if(right - left + 1 < length) {
                length = right - left + 1;
                answerLeft = left;
                answerRight = right;
            }
            
            sum -= sequence[left];
            left++;
        }
        
        return new int[]{answerLeft, answerRight};
    }
}