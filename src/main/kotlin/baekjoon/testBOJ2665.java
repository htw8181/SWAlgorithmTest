package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 알고리즘 2665 - 미로만들기
 *
 *
 *
 * @author HwangTaeWon
 *
 */
public class testBOJ2665 {
    private static final int WALL = 0;
    private static final int END = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/kotlin/baekjoon/sampleBOJ2665Input.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapSize = Integer.parseInt(st.nextToken());

        int[][] map = new int[mapSize][mapSize];
        for(int i=0;i<mapSize;i++) {
            //st = new StringTokenizer(br.readLine(),""); //StringTokenizer는 공백을 기준으로 분리할수는 없는가부다
            st = new StringTokenizer(br.readLine());
            String[] column = st.nextToken().split("");
            for(int j=0;j<mapSize;j++) {
                map[i][j] = Integer.parseInt(column[j]);
            }
        }
        map[mapSize-1][mapSize-1] = END;
        for(int i=0;i<mapSize;i++) {

            for(int j=0;j<mapSize;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }
}
