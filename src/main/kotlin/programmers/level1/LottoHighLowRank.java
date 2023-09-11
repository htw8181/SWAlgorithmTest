package programmers.level1;

/**
 * 로또 최고 순위와 최저 순위
 * 이걸 엉뚱하게 조합(comb)를 활용하여 풀려고 했다.
 * 생각의 발상만 전환하면 의외로 쉽게 풀 수 있는 문제였다.
 * 결국 인터넷 참고하여 풀었음..
 */
public class LottoHighLowRank {

    public static int[] solution(int[] lottos, int[] win_nums) {
        int wrongNumber = 0;
        int matchCount = 0;
        for (int i=0;i<lottos.length;i++) {
            if (lottos[i] == 0) wrongNumber++;
            for (int j=0;j<win_nums.length;j++) {
                if (lottos[i] == win_nums[j]) matchCount++;
            }
        }

        int min = matchCount;
        int max = matchCount + wrongNumber;

        int highRank = 7-max;
        if (highRank > 6) highRank = 6;
        int lowRank = 7-min;
        if (lowRank > 6) lowRank = 6;

        int[] answer = {highRank,lowRank};
        return answer;
    }

    public static void main(String[] args) {
        int[] results = solution(new int[]{45, 4, 35, 20, 3, 9},new int[]{20, 9, 3, 45, 4, 35});
        for (int result : results) {
            System.out.println(result);
        }
    }
}
