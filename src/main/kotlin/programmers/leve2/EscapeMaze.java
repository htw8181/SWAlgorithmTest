package programmers.leve2;

import java.util.ArrayDeque;

/**
 * 미로 탈출
 * 힌트를 찾아보니, 출발지점부터 레버까지의 최단 거리 + 레버부터 출구까지의 최단 거리
 *
 * 그러나, 시간초과가 발생하는데, 왜 그런지 모르겠다.
 */
public class EscapeMaze {
    private static final String START = "S";
    private static final String EXIT = "E";
    private static final String LEVER = "L";
    private static final String WALL = "X";

    public static int solution(String[] maps) {
        int answer = -1;
        String[][] maze = new String[maps.length][maps[0].length()];

        int startX = -1;
        int startY = -1;

        int leverX = -1;
        int leverY = -1;
        for (int i=0;i < maps.length;i++) {
            for (int j=0;j < maps[0].length();j++) {
                maze[i][j] = Character.toString(maps[i].charAt(j));
                if (maze[i][j].equals(START)) {
                    startX = i;
                    startY = j;
                } else if (maze[i][j].equals(LEVER)) {
                    leverX = i;
                    leverY = j;
                }
            }
        }

        int resultLever = doBFS(cloneMap(maze),startX,startY,LEVER);
        int resultExit = doBFS(cloneMap(maze),leverX,leverY,EXIT);

        if (resultLever == -1 || resultExit == -1) {
            answer = -1;
        } else {
            answer = resultLever + resultExit;
        }

        return answer;
    }

    /**
     * 배열 복사
     */
    private static String[][] cloneMap(String[][] map) {
        String[][] cloneMap = new String[map.length][map[0].length];
        for (int i=0;i< map.length;i++) {
            //배열은 1차원 배열에서만 clone 함수가 정상 동작한다.
            cloneMap[i] = map[i].clone();
        }
        return cloneMap;
    }
    private static int doBFS(String[][] maze,int startX,int startY,String targerSymbol) {
        int answer = -1;

        /**
         * 위치(x:y) | 걸린시간
         */
        String status = startX + ":" + startY + "|" + 0;
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.offer(status);
        while (!queue.isEmpty()) {
            String[] curStatus = queue.poll().split("\\|");
            String[] curPos = curStatus[0].split(":");
            int curXPos = Integer.parseInt(curPos[0]);
            int curYPos = Integer.parseInt(curPos[1]);
            int count = Integer.parseInt(curStatus[1]);

            if (maze[curXPos][curYPos].equals(targerSymbol)) {
                if (answer == -1) answer = count;
                else {
                    answer = Math.min(answer,count);
                }
                continue;
            }

            //동서남북을 이동하되, 경계선 넘을수 없고, 벽도 넘을수 없고, 이전 위치로도 갈수 없음
            for (int i=0;i<4;i++) {
                switch (i) {
                    case 0:
                    {
                        //북
                        //경계선 체크
                        int willXPos = curXPos -1;
                        if (willXPos < 0) break;

                        //벽 체크
                        if (maze[willXPos][curYPos].equals(WALL)) break;

                        //이전 위치를 벽으로 표시하자
                        maze[curXPos][curYPos] = WALL;

                        String willStatus = willXPos + ":" + curYPos + "|" + (count + 1);
                        queue.offer(willStatus);
                    }
                    break;
                    case 1:
                    {
                        //남
                        //경계선 체크
                        int willXPos = curXPos +1;
                        if (willXPos > maze.length-1) break;

                        //벽 체크
                        if (maze[willXPos][curYPos].equals(WALL)) break;

                        //이전 위치를 벽으로 표시하자
                        maze[curXPos][curYPos] = WALL;

                        String willStatus = willXPos + ":" + curYPos + "|" + (count + 1);
                        queue.offer(willStatus);
                    }
                    break;
                    case 2:
                    {
                        //서
                        //경계선 체크
                        int willYPos = curYPos -1;
                        if (willYPos < 0) break;

                        //벽 체크
                        if (maze[curXPos][willYPos].equals(WALL)) break;

                        //이전 위치를 벽으로 표시하자
                        maze[curXPos][curYPos] = WALL;

                        String willStatus = curXPos + ":" + willYPos + "|" + (count + 1);
                        queue.offer(willStatus);
                    }
                    break;
                    case 3:
                    {
                        //동
                        //경계선 체크
                        int willYPos = curYPos +1;
                        if (willYPos > maze[0].length-1) break;

                        //벽 체크
                        if (maze[curXPos][willYPos].equals(WALL)) break;

                        //이전 위치를 벽으로 표시하자
                        maze[curXPos][curYPos] = WALL;

                        String willStatus = curXPos + ":" + willYPos + "|" + (count + 1);
                        queue.offer(willStatus);
                    }
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int answer = solution(new String[]{"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"});
        System.out.println(answer);
    }
}
