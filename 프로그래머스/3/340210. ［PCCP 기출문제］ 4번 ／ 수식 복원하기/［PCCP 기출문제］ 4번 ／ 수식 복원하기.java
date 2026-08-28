import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        List<Integer> bases = findBase(expressions); // 수정됨
        
        List<String> list = new ArrayList<>();

        for(String expression : expressions) {
            if(!expression.contains("X")) {
                continue;
            }
            
            String[] arr = expression.split(" ");

            String answerResult = null;
            boolean isSame = true;     

            for(int base : bases) {     
                int num1 = convert(Integer.valueOf(arr[0]), base);
                int num2 = convert(Integer.valueOf(arr[2]), base);
                String sign = arr[1];

                int result = 0;

                if(sign.equals("+")) {
                    result = num1 + num2;
                } else {
                    result = num1 - num2;
                }

                String convertedResult = convertToBase(result, base); 

                if(answerResult == null) {
                    answerResult = convertedResult;
                } else if(!answerResult.equals(convertedResult)) {
                    isSame = false;
                    break;
                }
            }

            if(!isSame) { 
                answerResult = "?";
            }

            list.add(
                arr[0] + " " +
                arr[1] + " " +
                arr[2] + " " +
                arr[3] + " " +
                answerResult
            );
        }
        
        String[] answer = new String[list.size()];

        for(int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        
        return answer;
    }
    
    List<Integer> findBase(String[] expressions) { 
        List<Integer> bases = new ArrayList<>();    

        for(int base = 2; base <= 9; base++) {
            boolean isBase = true;
             
            for(String expression : expressions) {
                String[] arr = expression.split(" ");

                if(!isValidNumber(arr[0], base) ||
                   !isValidNumber(arr[2], base)) { 
                    isBase = false;
                    break;
                }

                if(expression.contains("X")) {
                    continue;
                }

                if(!isValidNumber(arr[4], base)) { 
                    isBase = false;
                    break;
                }

                int num1 = convert(Integer.valueOf(arr[0]), base);
                int num2 = convert(Integer.valueOf(arr[2]), base);
                String sign = arr[1];

                int result = 0;

                if(sign.equals("+")) {
                    result = num1 + num2;
                } else {
                    result = num1 - num2;
                }

                int num3 = convert(Integer.valueOf(arr[4]), base);

                if(result != num3) {
                    isBase = false;
                    break;
                }
            }

            if(isBase) {
                bases.add(base); 
            }
        }

        return bases;
    }

    boolean isValidNumber(String num, int base) { 
        for(int digitIdx = 0; digitIdx < num.length(); digitIdx++) {
            int digit = num.charAt(digitIdx) - '0';

            if(digit >= base) {
                return false;
            }
        }

        return true;
    }
    
    int convert(int num, int base) {
        int result = 0;
        
        int multiple = 1;

        while(num > 0) {
            int quotient = num % 10;
            result += multiple * quotient;
            num /= 10;
            multiple *= base;
        }
        
        return result;
    }

    String convertToBase(int num, int base) { 
        if(num == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        while(num > 0) {
            int remainder = num % base;
            result.append(remainder);
            num /= base;
        }

        return result.reverse().toString();
    }
}
