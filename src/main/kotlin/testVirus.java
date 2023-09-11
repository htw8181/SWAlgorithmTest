import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//22:40 start
//23:47 pause
//00:17 resume
//00:40 end
//총 걸린 시간 -> 대략 90분
public class testVirus {
	
	/**
	 * 세로 길이 
	 */
	private static int N;
	
	/**
	 * 가로 길이 
	 */
	private static int M;
	
	/**
	 * 빈칸 
	 */
	private static final int EMPTY = 0;
	
	/**
	 * 벽 
	 */
	private static final int WALL = 1;
	
	/**
	 * 바이러스 
	 */
	private static final int VIRUS = 2;
	
	/**
	 * 맵 지도 
	 */
	private static int[][] map;
	
	/**
	 * 맵 지도(temp)
	 */
	private static int[][] tempMap;
	
	/**
	 * 현재까지 측정된 안전영역의 최대 갯수
	 */
	private static int maxSafeAreaCount = -1;
	
	private static int[] data;
	private static int NN;
	private static int RR;
	private static int[] out;
	private static int count;
	
	/**
	 * 바이러스가 심어져 있는 초기 시작포인트 리스트 
	 */
	private static ArrayList<Integer> virusStartList;
	
	/**
	 * 세로 방향 가이드 
	 */
	private static final int[] dx = {0,0,1,-1};
	
	/**
	 * 가로 방향 가이드 
	 */
	private static final int[] dy = {1,-1,0,0};
	
	private static void combRecursive(int a, int depth)
	{
		if(depth == RR)
		{
			boolean wallAttachPossible = true;
			for(int i=0;i<RR;i++)
			{
				//System.out.print(out[i] + " ");
				//추출된 경우의 수가 xy좌표로 변환했을시, 3지역 모두 벽을 설치할수 있는지 확인한다
				int xyInt  = out[i];
				int x = xyInt/M;
				int y = xyInt%M;
				if(map[x][y] == WALL || map[x][y] == VIRUS)
				{
					wallAttachPossible = false;
					break;
				}	
			}
			//System.out.println();
			count++;
			
			if(wallAttachPossible == true)
			{
				tempMap = mapClone(map);
				
				//벽 3개를 설치한다 
				for(int i=0;i<RR;i++)
				{
					int xyInt  = out[i];
					int x = xyInt/M;
					int y = xyInt%M;
					
					tempMap[x][y] = WALL;
				}
				
				//BFS를 돌려서 바이러스를 퍼뜨리자.
				ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
				for(Integer xyInt : virusStartList)
				{
					queue.offer(xyInt);
				}
				
				while(!queue.isEmpty())
				{
					int curXyInt = queue.poll();
					
					int curX = curXyInt/1000;
					int curY = curXyInt%1000;
					
					//동,서,남,북 이동 
					for(int dir=0;dir<4;dir++)
					{
						//이동 방향 유효성 체크
						int nextX = curX + dx[dir];
						int nextY = curY + dy[dir];
						if(nextX >= 0 && nextX < N
						&& nextY >= 0 && nextY < M)
						{
							//이동할 곳이 EMPTY이면 바이러스 옮기자
							if(tempMap[nextX][nextY] == EMPTY)
							{
								tempMap[nextX][nextY] = VIRUS;
								int nextXyInt = (nextX*1000) + nextY;
								queue.offer(nextXyInt);
							}
						}
					}
					
				}
				
				//안전지역의 갯수를 count하고, 현재 최대치와 비교하여 갱신한다.
				int safeAreaCount = 0;
				for(int i=0;i<N;i++)
				{
					for(int j=0;j<M;j++)
					{
						if(tempMap[i][j] == EMPTY)
						{
							safeAreaCount++;
						}
					}
				}
				
				if(maxSafeAreaCount == -1) maxSafeAreaCount = safeAreaCount;
				else if(maxSafeAreaCount < safeAreaCount) maxSafeAreaCount = safeAreaCount;
			}
			
			return;
		}
		
		for(int i=a;i<data.length;i++)
		{
			out[depth] = data[i];
			combRecursive(i+1, depth+1);
		}
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("src/main/kotlin/sampleVirusInput.txt"));
	
		int T = sc.nextInt();
		
		for(int test_case=1;test_case<=T;test_case++)
		{
			maxSafeAreaCount = -1;
			
			N = sc.nextInt();
			M = sc.nextInt();
			
			System.out.println("N->"+N+" | M->"+M);
			
			map = new int[N][M];
			
			virusStartList = new ArrayList<Integer>();
			
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<M;j++)
				{
					map[i][j] = sc.nextInt();
					if(map[i][j] == VIRUS) {
						int xyInt = (i*1000) + j;
						virusStartList.add(xyInt);
					}
				}
			}
			
			//맵(N*M)에서 벽3개를 설치할수 있는 경우의 순서쌍을 뽑아내자.
			//즉, (N*M)개에서 3개를 뽑는 모든 경우의 순서쌍을 추출해낸다.
			//조합을 활용함!!
			NN = N*M;
			data = new int[NN];
			for(int i=0;i<NN;i++)
			{
				data[i] = i; //i+1이 아니므로 주의하자. i+1로 하면, ArrayOutOfBoundException 발생한다.
			}
			RR = 3;
			out = new int[RR];
			
			
			
			long startTime = System.currentTimeMillis();
			combRecursive(0, 0);
			long endTime = System.currentTimeMillis();
			System.out.println("elaped time is "+(endTime-startTime));
			System.out.println(maxSafeAreaCount);
		}
		
		
	}
	
	private static int[][] mapClone(int[][] map)
	{
		int[][] newMap = new int[N][M];
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				newMap[i][j] = map[i][j];
			}
		}
		
		return newMap;
	}
}
