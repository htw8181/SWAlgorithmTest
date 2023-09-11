package programmers.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 신고 결과 받기
 * 뭐가 틀린건지 잘 모르겠다.
 */
public class ReceiveReportResult {
    public static int[] solution(String[] id_list, String[] report, int k) {

        /**
         * 유저가 결과 메일 받아야 되는 개수
         */
        HashMap<String,Integer> manToReceiveMailCount = new HashMap<>();

        /**
         * 유저와 유저가 신고한 사람들 리스트
         */
        HashMap<String, HashSet<String>> manAndWarnedUser = new HashMap<>();

        /**
         * 신고받은 사람과 그 신고횟수
         */
        HashMap<String,Integer> warnedManToWarningCount = new HashMap<>();

        //report 배열내에 중복을 제거하기위해 set으로 변환
        HashSet<String> setReport = new HashSet<>(Arrays.asList(report));
        String[] newReport = setReport.toArray(new String[setReport.size()]);
        for (int i=0;i<newReport.length;i++) {
            String[] users = report[i].split(" ");
            String user = users[0];
            String warnedUser = users[1];

            warnedManToWarningCount.put(warnedUser,warnedManToWarningCount.getOrDefault(warnedUser,0)+1);

            HashSet<String> setWarnedUser = manAndWarnedUser.getOrDefault(user,new HashSet<>());
            setWarnedUser.add(warnedUser);
            manAndWarnedUser.put(user,setWarnedUser);
        }

        int[] answer = new int[id_list.length];
        for (int i=0; i<id_list.length; i++) {
            String id = id_list[i];
            HashSet<String> setWarnedUser = manAndWarnedUser.getOrDefault(id,new HashSet<>());
            Iterator iterator = setWarnedUser.iterator();
            while (iterator.hasNext()) {
                String warnedUser = (String) iterator.next();
                int warnedCount = warnedManToWarningCount.getOrDefault(warnedUser,0);
                if (warnedCount >= k) {
                    manToReceiveMailCount.put(id,manToReceiveMailCount.getOrDefault(id,0)+1);
                }
            }
            answer[i] = manToReceiveMailCount.getOrDefault(id,0);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] results = solution(new String[]{"con", "ryan"},new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},3);
        for (int result : results) {
            System.out.println(result);
        }
    }
}
