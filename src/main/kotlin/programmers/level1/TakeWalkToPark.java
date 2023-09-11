package programmers.level1;

/**
 * 공원산책
 * 테스트 케이스 14/20 인데, 나머지 실패케이스는 아직 모르겠음.
 */
public class TakeWalkToPark {

    public static int[] solution(String[] park, String[] routes) {
        Character[][] map = new Character[park.length][park[0].length()];
        Character obstacle = 'X';
        int[] curPos = new int[2];
        for(int i=0;i< park.length;i++) {
            for(int j=0;j< park[0].length();j++) {
                map[i][j] = park[i].charAt(j);
                if (map[i][j] == 'S') {
                    curPos[0] = i;
                    curPos[1] = j;
                }
            }
        }

        for(String route : routes) {
            String[] routesArr = route.split(" ");
            String direction = routesArr[0];
            int movingCount = Integer.parseInt(routesArr[1]);

            // 경계선에 닿거나, 장애물에 닿는 경우 해당 턴은 무시하고, 다음턴을 수행한다.
            boolean isObstacle = false;
            switch (direction.charAt(0)) {
                case 'N': {
                    //북쪽
                    int willXPos = curPos[0]-movingCount;
                    if (willXPos >= 0) {
                        for(int i=0;i<movingCount;i++) {
                            if (map[curPos[0]-i][curPos[1]] == obstacle) {
                                isObstacle = true;
                                break;
                            }
                        }
                        if (!isObstacle) curPos[0] = willXPos;
                    }
                }
                    break;
                case 'S': {
                    //남쪽
                    int willXPos = curPos[0]+movingCount;
                    if (willXPos <= map.length-1) {
                        for(int i=0;i<movingCount;i++) {
                            if (map[curPos[0]+i][curPos[1]] == obstacle) {
                                isObstacle = true;
                                break;
                            }
                        }
                        if (!isObstacle) curPos[0] = willXPos;
                    }
                }
                    break;
                case 'W': {
                    //서쪽
                    int willYPos = curPos[1]-movingCount;
                    if (willYPos >= 0) {
                        for(int i=0;i<movingCount;i++) {
                            if (map[curPos[0]][curPos[1]-i] == obstacle) {
                                isObstacle = true;
                                break;
                            }
                        }
                        if (!isObstacle) curPos[1] = willYPos;
                    }
                }
                    break;
                case 'E': {
                    //동쪽
                    int willYPos = curPos[1]+movingCount;
                    if (willYPos <= map[0].length-1) {
                        for(int i=0;i<movingCount;i++) {
                            if (map[curPos[0]][curPos[1]+i] == obstacle) {
                                isObstacle = true;
                                break;
                            }
                        }
                        if (!isObstacle) curPos[1] = willYPos;
                    }
                }
                    break;
                default:
                    break;
            }
        }
        int[] answer = curPos;
        return answer;
    }

    public static void main(String[] args) {
        int[] results = solution(new String[]{"OSO","OOO","OXO","OOO"},new String[]{"E 2","S 3","W 1"});
        for(int result : results) {
            System.out.println(result);
        }
    }
}
