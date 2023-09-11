package programmers.leve2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * 과제 진행하기
 * 너무 어려워서 풀다가 중단
 */
public class ProcessHomeWork {
    public static String[] solution(String[][] plans) {
        /**
         * plans를 시간 순서대로 정렬한다.
         */
        Arrays.sort(plans, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int o1Time = Integer.parseInt(o1[1].replace(":",""));
                int o2Time = Integer.parseInt(o2[1].replace(":",""));
                return o1Time - o2Time;
            }
        });

        String[] answer = new String[plans.length];
        ArrayList<String> answerList = new ArrayList<>();

        Stack<String> remainedWork = new Stack<>();
        String prvSubject = null;
        int prvEndHour = -1;
        int prvEndMinute = -1;
        for (int i=0;i<plans.length;i++) {
            String subject = plans[i][0];
            String[] startTimeStr = plans[i][1].split(":");
            int startHour = Integer.parseInt(startTimeStr[0]);
            int startMinute = Integer.parseInt(startTimeStr[1]);
            int workingMinute = Integer.parseInt(plans[i][2]);

            if (prvEndHour < 0 || prvEndMinute < 0) {
                prvSubject = subject;
                prvEndHour = (startMinute + workingMinute) / 60;
                prvEndMinute = (startMinute + workingMinute) % 60;
            } else {
                int prvEndTime = Integer.parseInt(prvEndHour + "" + prvEndMinute);
                int startTime = Integer.parseInt(startHour + "" +startMinute);
                if (startTime < prvEndTime) {
                    int remainedMinuteToShouldWork = ((prvEndHour*60) + prvEndMinute) - ((startHour*60) + startMinute);
                    remainedWork.push(prvSubject+"|"+remainedMinuteToShouldWork);
                } else {
                    answerList.add(prvSubject);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] results = solution(new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}});
        for(String result : results) {
            System.out.println(result);
        }
    }
}
