package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 알고리즘 15684 - 사다리 조작 
 * 3시간 제한 시간 넘김..
 * test case는 맞았으나, 서버 제출시 시간 초과 발생..
 * 
 * @author HwangTaeWon
 *
 */
public class testBOJ15684 {

	/**
	 * 세로 길이 
	 * 1 ≤ H ≤ 30
	 */
	private static int H;
	
	/**
	 * 가로 길이 
	 * 2 ≤ N ≤ 10
	 */
	private static int N;
	
	/**
	 * 가로선 
	 * 0 ≤ M ≤ (N-1)×H
	 */
	private static int M;
	
	/**
	 * 가로선 정보 저장한 배열
	 */
	private static int[][] mLineArr;
	
	private static int[][] map;
	
	private static int[][] tmpMap;
	
	private static final int[] dx = {0,0,1,-1};
	private static final int[] dy = {1,-1,0,0};
	
	private enum DirectionEnum {
		EAST(0),WEST(1),SOUTH(2),NORTH(3);
		
		int value;

		private DirectionEnum(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 배치 가능한 최대 가로선 갯수
	 */
	private static final int MaxLineCount = 3;
	
	/**
	 * 현재 배치한 가로선 갯수 
	 */
	private static int lineLocateCount;
	
	/**
	 * 조합에 의한 경우의 순서쌍을 뽑기 위한 표본 데이터를 저장하기 위한 배열 
	 */
	private static String[] data;
	
	private static int NN;
	private static int RR;
	
	private static String[] out;
	
	private static boolean isSuccessFul;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/kotlin/baekjoon/sampleBOJ15684Input.txt")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case=1;test_case<=T;test_case++)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			mLineArr = new int[M][2];
			
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				mLineArr[i][0] = a;
				mLineArr[i][1] = b;
			}
			
			/*
			for(int i=0;i<M;i++)
			{
				for(int j=0;j<2;j++)
				{
					System.out.print(mLineArr[i][j] + " ");
				}
				System.out.println();
			}
			*/
			
			map = new int[H+2][N+1];
			initMap(map);
			setLine(map,mLineArr);
			
			/**
			 * 0개부터 3개까지 가로선을 셋팅하고 각 번호마다 출발하여 제 번호에 맞게 도착하는지 체크한다.
			 */
			ArrayList<String> dataList = new ArrayList<>();
			//data = new String[H*(N-1)];
			//NN = data.length;
			//int dataCount = 0;
			for(int i=1;i<=H;i++)
			{
				for(int j=1;j<N;j++)
				{
					if(map[i][j] == 0)
					{
						String dataStr = i+"|"+j;
						//System.out.print(dataStr + " ");
						//data[dataCount] = dataStr;
						//dataCount++;
						dataList.add(dataStr);
					}
					
				}
			}
			data = new String[dataList.size()];
			data = (String[])dataList.toArray(data);
			NN = data.length;
			
			
			isSuccessFul = false;
			
			for(int lineCount=0;lineCount<=MaxLineCount;lineCount++)
			{
				//lineCount == 0 이면 바로 진행
				if(lineCount == 0)
				{
					tmpMap = cloneMap(map);
					//dispMap(tmpMap);
					//사다리 이동 진행
					lineMoveStart();
					if(isSuccessFul == true)
					{
						System.out.println(lineCount);
						break;
					}
				}
				else
				{
					//조합으로 lineCount갯수만큼 경우의 수를 추출한다.
					RR = lineCount;
					out = new String[RR];
					
					//lineLocateCount = 0;
					
					//dispMap(map);
					
					recursiveComb(0,0);
					
					//dispMap(tmpMap);
					
					if(isSuccessFul == true)
					{
						System.out.println(lineCount);
						break;
					}
				}
			}
		
			if(isSuccessFul == false) System.out.println("-1");
		}
	}
	
	private static void lineMoveStart()
	{
		//각 번호마다 사다리 출발
	    for(int no=1;no<=N;no++)
	    {
	    	int[] startPos = new int[] {0,no};
	    	
	    	ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
	    	queue.offer(startPos);
	    	
	    	while(!queue.isEmpty())
	    	{
	    		int[] curPos = queue.poll();
	    		
	    		int curPosX = curPos[0];
	    		int curPosY = curPos[1];
	    		
	    		if(curPosX == H+1)
	    		{
	    			if(curPosY == no)
	    			{
	    				continue;
	    			}
	    			else
	    			{
	    				isSuccessFul = false;
	    				return;
	    			}
	    		}
	    		
	    		//남쪽으로 한칸 이동후..
	    		int nextPosX = curPosX + 1;
	    		int nextPosY = curPosY;
	    		
	    		//좌우를 살펴서 가로 이동이 가능하면 가로 이동하자.
	    		if(tmpMap[nextPosX][nextPosY] != 0)
	    		{
	    			//좌(서쪽)를 살핌 
	    			if(nextPosY + dy[DirectionEnum.WEST.value] >= 1 
	    			&& nextPosY + dy[DirectionEnum.WEST.value] < (N+1)
	    			&& tmpMap[nextPosX][nextPosY] == tmpMap[nextPosX][nextPosY + dy[DirectionEnum.WEST.value]])
	    			{
	    				nextPosY = nextPosY + dy[DirectionEnum.WEST.value];
	    			}
	    			//우(동쪽)를 살핌
	    			else if(nextPosY + dy[DirectionEnum.EAST.value] >= 1 
			    	&& nextPosY + dy[DirectionEnum.EAST.value] < (N+1)
			    	&& tmpMap[nextPosX][nextPosY] == tmpMap[nextPosX][nextPosY + dy[DirectionEnum.EAST.value]])
	    			{
	    				nextPosY = nextPosY + dy[DirectionEnum.EAST.value];
	    			}
	    		}
	    		
	    		int[] newPos = new int[]{nextPosX, nextPosY};
	    		queue.offer(newPos);
	    	}
	    	
	    	
	    }
	    
	    isSuccessFul = true;
	    return;
	}
	
	private static void recursiveComb(int x, int depth)
	{
		if(isSuccessFul == true) return;
		
		if(depth == RR)
		{
			tmpMap = cloneMap(map);
			lineLocateCount = 0;
			for(int i=0;i<RR;i++)
			{
				//System.out.print(out[i] + " ");
				
				//사다리 라인 추가 배치 
				String[] tmpArr = out[i].split("\\|");
				
				int a = Integer.parseInt(tmpArr[0]);
				int b = Integer.parseInt(tmpArr[1]);
				
				//dispMap(map);
				
				if(tmpMap[a][b] == 0 && tmpMap[a][b+1] == 0)
				{
					lineLocateCount++;
					tmpMap[a][b] = M+lineLocateCount;
					tmpMap[a][b+1] = M+lineLocateCount;
				}
				
			}
			//System.out.println();
			
			//사다리 이동 진행
			//dispMap(tmpMap);
			if(lineLocateCount == RR)
			{
				lineMoveStart();
			}
			
			return;
		}
		
		for(int i=x;i<NN;i++)
		{
			out[depth] = data[i];
			recursiveComb(i+1,depth+1);
		}
	}
	
	private static int[][] cloneMap(int[][] map)
	{
		int[][] result = new int[H+2][N+1];
		
		for(int i=0;i<(H+2);i++)
		{
			for(int j=0;j<(N+1);j++)
			{
				result[i][j] = map[i][j];
			}
		}
		
		return result;
	}
	
	private static void initMap(int[][] map)
	{
		for(int i=0;i<(H+2);i++)
		{
			for(int j=0;j<(N+1);j++)
			{
				map[i][j] = 0;
			}
		}
	}
	
	private static void setLine(int[][] map,int[][] lineArr)
	{
		for(int i=0;i<M;i++)
		{
			int a = mLineArr[i][0];
			int b = mLineArr[i][1];
			
			map[a][b] = i+1;
			map[a][b+1] = i+1;
		}
	}
	
	private static void dispMap(int[][] map)
	{
		for(int i=0;i<(H+2);i++)
		{
			for(int j=0;j<(N+1);j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
