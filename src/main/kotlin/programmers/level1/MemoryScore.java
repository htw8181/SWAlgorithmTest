package programmers.level1;

import java.util.HashMap;

/**
 * 추억 점수
 */
public class MemoryScore {
    public static int[] solution(String[] name, int[] yearning, String[][] photo) {

        HashMap<String,Integer> nameToScore = new HashMap<>();
        for (int i=0;i<name.length;i++) {
            nameToScore.put(name[i],yearning[i]);
        }

        int[] answer = new int[photo.length]; //photo.length는 photo의 row 길이. 만약 column의 길이였다면, photo[0].length, photo[1].length, photo[i].length...
        for (int i=0;i< photo.length;i++) {
            int sum = 0;
            for (int j=0;j<photo[i].length;j++) {
                int score = nameToScore.getOrDefault(photo[i][j],0); //map.get 에서 해당 값이 없으면 null 예외가 뜨는데, getOrDefault를 사용해서 null일때 기본값 설정하면 됨
                sum += score;
            }
            answer[i] = sum;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] results = solution(new String[]{"may", "kein", "kain", "radi"},new int[]{5, 10, 1, 3},new String[][]{{"may", "kein", "kain", "radi"},{"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}});
        for(int result : results) {
            System.out.println(result);
        }
    }
}
