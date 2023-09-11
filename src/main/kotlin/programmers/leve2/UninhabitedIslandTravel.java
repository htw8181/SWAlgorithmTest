package programmers.leve2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 무인도 여행
 * ArrayList -> Array 변환 : String[] array = arrayList.toArray(new String[arrayList.size()]);
 * Array -> ArrayList 변환 : ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(array));
 */
public class UninhabitedIslandTravel {
    private static final String SEA = "X";
    public static int[] solution(String[] maps) {
        String[][] islands = new String[maps.length][maps[0].length()];
        for(int i=0;i<maps.length;i++) {
            for(int j=0;j<maps[0].length();j++) {
                islands[i][j] = Character.toString(maps[i].charAt(j));
            }
        }

        //맵을 0,0부터 column방향으로 탐색하며 무인도(숫자가 적힌 지점)를 찾으면 그때부터 BFS로 점수를 합산하자.
        ArrayList<Integer> scoreList = new ArrayList<>();
        for (int i=0;i<islands.length;i++) {
            for (int j=0;j<islands[i].length;j++) {
                if (!islands[i][j].equals(SEA)) {
                    scoreList.add(doBfs(islands,i,j));
                }
            }
        }

        if (scoreList.isEmpty()) return new int[]{-1};
        else {
            //ArrayList -> Array
            int[] answer = Arrays.stream(scoreList.toArray(new Integer[scoreList.size()])).mapToInt(i->i).toArray();
            Arrays.sort(answer);
            //answer 오름차순 정렬
            return answer;
        }
    }

    public static int doBfs(String[][] islands, int startX, int startY) {
        int sum = Integer.parseInt(islands[startX][startY]);
        islands[startX][startY] = SEA;
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.offer(startX+"|"+startY);
        while (!queue.isEmpty()) {
            String[] curPos = queue.poll().split("\\|");
            int xPos = Integer.parseInt(curPos[0]);
            int yPos = Integer.parseInt(curPos[1]);

            //동서남북으로 1칸씩 이동하며 점수를 합산하며, 합산한 지역은 X로 표기하자.
            for (int i=0;i<4;i++) {
                switch (i) {
                    case 0:
                        //북
                        {
                            int willXPos = xPos -1;
                            if (willXPos >= 0 && !islands[willXPos][yPos].equals(SEA)) {
                                sum += Integer.parseInt(islands[willXPos][yPos]);
                                islands[willXPos][yPos] = SEA;
                                queue.offer(willXPos+"|"+yPos);
                            }
                        }
                        break;
                    case 1:
                        //남
                        {
                            int willXPos = xPos +1;
                            if (willXPos <= islands.length-1 && !islands[willXPos][yPos].equals(SEA)) {
                                sum += Integer.parseInt(islands[willXPos][yPos]);
                                islands[willXPos][yPos] = SEA;
                                queue.offer(willXPos+"|"+yPos);
                            }
                        }
                        break;
                    case 2:
                        //서
                        {
                            int willYPos = yPos -1;
                            if (willYPos >= 0 && !islands[xPos][willYPos].equals(SEA)) {
                                sum += Integer.parseInt(islands[xPos][willYPos]);
                                islands[xPos][willYPos] = SEA;
                                queue.offer(xPos+"|"+willYPos);
                            }
                        }
                        break;
                    case 3:
                        //동
                        {
                            int willYPos = yPos +1;
                            if (willYPos <= islands[0].length-1 && !islands[xPos][willYPos].equals(SEA)) {
                                sum += Integer.parseInt(islands[xPos][willYPos]);
                                islands[xPos][willYPos] = SEA;
                                queue.offer(xPos+"|"+willYPos);
                            }
                        }
                        break;
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] results = solution(new String[]{"XXX","XXX","XXX"});
        for(int result : results) {
            System.out.println(result);
        }
    }
}
