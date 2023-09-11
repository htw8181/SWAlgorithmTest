package programmers.level1;

import java.util.HashMap;

/**
 * 달리기 경주
 */
public class RunningRace {
    public void swap(HashMap<String,Integer> mapNameToRank, HashMap<Integer,String> mapRankToName, String calling) {
        int curRank = mapNameToRank.get(calling);
        int willRank = curRank-1;
        String versusCalling = mapRankToName.get(willRank);
        mapRankToName.put(willRank,calling);
        mapRankToName.put(curRank,versusCalling);
        mapNameToRank.put(versusCalling,curRank);
        mapNameToRank.put(calling,willRank);
    }

    public String[] solution(String[] players, String[] callings) {

        /**
         * 범위가 너무 넓은 범위를 for문으로 일일이 돌리면 시간초과가 난다.
         * 따라서, 범위가 커져도 그 검색 시간이 일정한 HashMap을 활용하자.
         */
        HashMap<String,Integer> mapNameToRank = new HashMap<>();
        HashMap<Integer,String> mapRankToName = new HashMap<>();
        for(int index=0;index<players.length;index++) {
            mapNameToRank.put(players[index],index);
            mapRankToName.put(index,players[index]);
        }
        for(String calling : callings) {
            swap(mapNameToRank, mapRankToName, calling);
        }
        String[] answers = new String[mapRankToName.size()];
        for (int i=0;i<mapRankToName.size();i++) {
            answers[i] = mapRankToName.get(i);
        }
        return answers;
    }
}
