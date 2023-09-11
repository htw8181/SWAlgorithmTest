package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 개인정보 수집 유효기간
 */
public class ValidPeriodForCollectingPersonInfo {
    public static int[] solution(String today, String[] terms, String[] privacies) {
        String[] todays = today.split("\\.");
        int todayYear = Integer.parseInt(todays[0]);
        int todayMonth = Integer.parseInt(todays[1]);
        int todayDate = Integer.parseInt(todays[2]);

        HashMap<String,Integer> termMap = new HashMap<>();
        for (int i=0;i<terms.length;i++) {
            String[] termArr = terms[i].split(" ");
            String term = termArr[0];
            int validPeriod = Integer.parseInt(termArr[1]);
            termMap.put(term,validPeriod);
        }

        ArrayList<Integer> shouldDestroyIndexList = new ArrayList<>();
        for (int i=0;i<privacies.length;i++) {
            String[] privacy = privacies[i].split(" ");
            String[] startDates = privacy[0].split("\\.");
            int startYear = Integer.parseInt(startDates[0]);
            int startMonth = Integer.parseInt(startDates[1]);
            int startDate = Integer.parseInt(startDates[2]);
            String term = privacy[1];

            //해당 약관의 유효기간을 일(date)수로 변환
            int termVaildDate = termMap.getOrDefault(term,0) * 28;

            int startDays = (startYear * 12 * 28) + (startMonth * 28) + startDate;
            int todayDays = (todayYear * 12 * 28) + (todayMonth * 28) + todayDate;

            if ((startDays + termVaildDate) <= todayDays) shouldDestroyIndexList.add(i+1);
        }

        int[] answer = Arrays.stream(shouldDestroyIndexList.toArray(new Integer[shouldDestroyIndexList.size()])).mapToInt(i->i).toArray();
        return answer;
    }

    public static void main(String[] args) {
        int[] results = solution("2020.01.01",new String[]{"Z 3", "D 5"},new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});
        for (int result : results) {
            System.out.println(result);
        }
    }
}
