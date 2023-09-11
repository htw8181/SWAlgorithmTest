package programmers.leve2;

import java.util.ArrayDeque;

/**
 * 숫자 변환하기
 *
 * 시간초과 발생함..
 */
public class NumberConvert {
    enum CalCase {
        /**
         * x에 n을 더합니다
         */
        First(0),
        /**
         * x에 2를 곱합니다
         */
        Second(1),
        /**
         * x에 3을 곱합니다.
         */
        Third(2);

        int val;

        CalCase(int val) {
            this.val = val;
        }
    }
    public static int solution(int x, int y, int n) {
        int answer = -1;
        ArrayDeque<String> queue = new ArrayDeque<>();
        String curStatus = x+"|"+0;
        queue.offer(curStatus);
        boolean isSuccessful = false;
        while (!queue.isEmpty()) {
            String[] tempArr = queue.poll().split("\\|");

            for(int i=0;i<CalCase.values().length;i++) {
                int result = Integer.parseInt(tempArr[0]);
                int count = Integer.parseInt(tempArr[1]);

                if(result == y) {
                    answer = count;
                    isSuccessful = true;
                    break;
                }

                if(i == CalCase.First.val) {
                    result = result + n;
                } else if(i == CalCase.Second.val) {
                    result = result * 2;
                } else if(i == CalCase.Third.val) {
                    result = result * 3;
                }

                count++;

                if(result == y) {
                    answer = count;
                    isSuccessful = true;
                    break;
                } else if(result < y) {
                    String nextStatus = result+"|"+count;
                    queue.offer(nextStatus);
                }
            }

            if(isSuccessful) break;
        }

        return answer;
    }

    public static void main(String[] args) {
        int result = solution(2,5,4);
        System.out.println(result);
    }
}
