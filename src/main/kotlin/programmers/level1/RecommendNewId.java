package programmers.level1;

/**
 * 신규 아이디 추천
 * 테스트 케이스 17/26 인데, 나머지 실패케이스는 아직 모르겠음.
 */
public class RecommendNewId {
    public static String solution(String new_id) {
        //stage1 소문자 치환
        new_id = new_id.toLowerCase();

        //stage2 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<new_id.length();i++) {
            if ((new_id.charAt(i) >= 97 && new_id.charAt(i) <= 122 ) || (new_id.charAt(i) >= 48 && new_id.charAt(i) <= 57 ) || new_id.charAt(i) == '-' || new_id.charAt(i) == '_' || new_id.charAt(i) == '.')
            {
                //stage3 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
                if (i > 0 && new_id.charAt(i-1) == '.' && new_id.charAt(i) == '.') {
                    continue;
                }

                sb.append(new_id.charAt(i));
            }
        }
        new_id = sb.toString();

        //stage4 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        if (new_id.charAt(0) == '.') {
            new_id = new_id.substring(1,new_id.length());
        }
        if (!new_id.isEmpty() && new_id.charAt(new_id.length()-1) == '.') {
            new_id = new_id.substring(0,new_id.length()-1);
        }

        //stage5 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if (new_id.isEmpty()) new_id = "a";

        //stage6 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        //     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0,15);
        }
        if (!new_id.isEmpty() && new_id.charAt(new_id.length()-1) == '.') {
            new_id = new_id.substring(0,new_id.length()-1);
        }

        //stage7 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if (new_id.length() <= 2) {
            while (new_id.length() < 3) {
                new_id = new_id + new_id.charAt(new_id.length()-1);
            }
        }

        String answer = new_id;
        return answer;
    }

    public static void main(String[] args) {
        String result = solution("abcdefghijklmn.p");
        System.out.println(result);
    }
}
