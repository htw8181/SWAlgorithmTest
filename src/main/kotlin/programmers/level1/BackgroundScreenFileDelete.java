package programmers.level1;

import java.util.Arrays;

/**
 * 바탕화면 정리
 */
public class BackgroundScreenFileDelete {
    public static int[] solution(String[] wallpaper) {
        String[][] map = new String[wallpaper.length][wallpaper[0].length()];
        for (int i=0;i<wallpaper.length;i++) {
            for (int j=0;j<wallpaper[0].length();j++) {
                map[i][j] = Character.toString(wallpaper[i].charAt(j));
            }
        }

        int minX = -1;
        int minY = -1;
        int maxX = -1;
        int maxY = -1;

        for (int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++) {
                if (map[i][j].equals("#")) {
                    if (minX == -1) {
                        minX = i;
                    } else {
                        minX = Math.min(minX,i);
                    }

                    if (minY == -1) {
                        minY = j;
                    } else {
                        minY = Math.min(minY,j);
                    }

                    if (maxX == -1) {
                        maxX = i;
                    } else {
                        maxX = Math.max(maxX,i);
                    }

                    if (maxY == -1) {
                        maxY = j;
                    } else {
                        maxY = Math.max(maxY,j);
                    }
                }
            }
        }

        int[] answer = {minX,minY,maxX+1,maxY+1};
        return answer;
    }

    public static void main(String[] args) {
        int[] results = solution(new String[]{".#...", "..#..", "...#."});
        System.out.println(Arrays.toString(results));
    }
}
