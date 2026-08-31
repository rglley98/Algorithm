import java.util.*;

class Solution {
    String[] orders;
    int[] course;
    Map<String, Integer> map = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        this.orders = orders;
        this.course = course;
        
        for(String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            makeComb(arr, 0, "");
        }
        
        Map<Integer, Integer> maxMap = new HashMap<>();
        for(int length : course) {
            int maxCount = 0;
            for(String key : map.keySet()) {
                if(key.length() == length) {
                    maxCount = Math.max(maxCount, map.get(key));
                }
            }
            
            
            maxMap.put(length, maxCount);
        }
        
        List<String> list = new ArrayList<>();
        for(int length : course) {
            int count = maxMap.get(length);
            for(String key : map.keySet()) {
                if(key.length() == length && count >= 2 && map.get(key) == count) {
                    list.add(key);
                }
            }
        }
        
        Collections.sort(list);
        
        String[] answer = new String[list.size()];
        for(int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        
        return answer;
    }
    
    void makeComb(char[] arr, int startIdx, String comb) {
        if(comb.length() >= 2) {
            map.put(comb, map.getOrDefault(comb, 0) + 1);
        }
        
        for(int idx = startIdx; idx < arr.length; idx++) {
            makeComb(arr, idx + 1, comb + arr[idx]);
        }
    }
}