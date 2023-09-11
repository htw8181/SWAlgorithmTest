package programmers.level1;

import java.util.HashMap;

/**
 * 성격 유형 검사하기
 */
public class CharacterTypeTest {

    public static String solution(String[] survey, int[] choices) {
        HashMap<String,Integer> symbolScoreMap = new HashMap<>();
        for (int i=0;i<survey.length;i++) {
            String disagreeSymbol = Character.toString(survey[i].charAt(0));
            String agreeSymbol = Character.toString(survey[i].charAt(1));

            int choice = choices[i];
            switch (choice) {
                case 1:
                {
                    //매우_비동의
                    symbolScoreMap.put(disagreeSymbol,symbolScoreMap.getOrDefault(disagreeSymbol,0)+3);
                }
                    break;
                case 2:
                {
                    //비동의
                    symbolScoreMap.put(disagreeSymbol,symbolScoreMap.getOrDefault(disagreeSymbol,0)+2);
                }
                    break;
                case 3:
                {
                    //약간_비동의
                    symbolScoreMap.put(disagreeSymbol,symbolScoreMap.getOrDefault(disagreeSymbol,0)+1);
                }
                    break;
                case 4:
                {
                    //모르겟음
                }
                    break;
                case 5:
                {
                    //약간_동의
                    symbolScoreMap.put(agreeSymbol,symbolScoreMap.getOrDefault(agreeSymbol,0)+1);
                }
                    break;
                case 6:
                {
                    //동의
                    symbolScoreMap.put(agreeSymbol,symbolScoreMap.getOrDefault(agreeSymbol,0)+2);
                }
                    break;
                case 7:
                {
                    //매우 동의
                    symbolScoreMap.put(agreeSymbol,symbolScoreMap.getOrDefault(agreeSymbol,0)+3);
                }
                    break;
            }
        }

        StringBuffer sf = new StringBuffer();
        int scoreR = symbolScoreMap.getOrDefault("R",0);
        int scoreT = symbolScoreMap.getOrDefault("T",0);
        if(scoreR >= scoreT) {
            sf.append("R");
        } else {
            sf.append("T");
        }

        int scoreC = symbolScoreMap.getOrDefault("C",0);
        int scoreF = symbolScoreMap.getOrDefault("F",0);
        if (scoreC >= scoreF) {
            sf.append("C");
        } else {
            sf.append("F");
        }

        int scoreJ = symbolScoreMap.getOrDefault("J",0);
        int scoreM = symbolScoreMap.getOrDefault("M",0);
        if (scoreJ >= scoreM) {
            sf.append("J");
        } else {
            sf.append("M");
        }

        int scoreA = symbolScoreMap.getOrDefault("A",0);
        int scoreN = symbolScoreMap.getOrDefault("N",0);
        if (scoreA >= scoreN) {
            sf.append("A");
        } else {
            sf.append("N");
        }

        String answer = sf.toString();
        return answer;
    }

    public static void main(String[] args) {
        String result = solution(new String[]{"TR", "RT", "TR"},new int[]{7, 1, 3});
        System.out.println(result);
    }
}
