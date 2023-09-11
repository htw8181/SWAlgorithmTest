package programmers.level1;

import java.util.HashMap;
import java.util.Map;

/**
 * 완주하지 못한 선수
 * HashMap을 활용해야함!!
 */
public class NotCompletedPlayer {
    public static String solution(String[] participant, String[] completion) {

        HashMap<String,Integer> participantMap = new HashMap<>();
        for (String player : participant) {
            participantMap.put(player,participantMap.getOrDefault(player,0)+1);
        }
        for (String player : completion) {
            participantMap.put(player,participantMap.getOrDefault(player,0)-1);
        }

        // Map의 EntrySet
        for (Map.Entry<String,Integer> entry : participantMap.entrySet()) {
            if (entry.getValue() > 0) return entry.getKey();
        }
        String answer = "";
        return answer;
    }

    public static void main(String[] args) {
        String result = solution(new String[]{"mislav", "stanko", "mislav", "ana"},new String[]{"stanko", "ana", "mislav"});
        System.out.println(result);
    }
}
