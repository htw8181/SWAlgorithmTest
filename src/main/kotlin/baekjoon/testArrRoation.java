package baekjoon;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 알고리즘 17406 배열돌리기 문제
 * 배열 돌리기는 직접 메모장에 각 케이스를 작성해가며 규칙을 캐치하여, 이를 함수로 빠른 시간안에 도출해 낼 줄 알아야 한다.
 * 총 걸린 시간 : 2시간 30분
 * @author HwangTaeWon
 *
 */
public class testArrRoation {

	/**
	 * 세로 길이 
	 */
	private static int N;
	
	/**
	 * 가로 길이 
	 */
	private static int M;
	
	/**
	 * 배열 회전 연산의 갯수 
	 */
	private static int K;
	
	/**
	 * 맵 원본 
	 */
	private static int[][] map;
	
	/**
	 * 맵 복사본 
	 */
	private static int[][] tmpMap;
	
	/**
	 * 최소값 
	 */
	private static int minValue = -1;
	
	/**
	 * 로테이션 연산식을 가지고 있는 배열 
	 */
	private static String[] kArr;
	private static boolean[] visited;
	private static String[] out;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/main/kotlin/baekjoon/sampleArrRotation.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		System.out.println(N);
		System.out.println(M);
		System.out.println(K);
		
		map = new int[N][M];
		tmpMap = new int[N][M];
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				map[i][j] = sc.nextInt();
			}
		}
		
		dispMap(map);
		
		kArr = new String[K];
		
		for(int i=0;i<K;i++)
		{
			StringBuilder sb = new StringBuilder();
			sb.append(sc.nextInt());
			sb.append("|");
			sb.append(sc.nextInt());
			sb.append("|");
			sb.append(sc.nextInt());
			kArr[i] = sb.toString();
		}
		
		/*
		for(String s : kArr)
		{
			String[] tmpArr = s.split("\\|");
			System.out.println(tmpArr[0]);
			System.out.println(tmpArr[1]);
			System.out.println(tmpArr[2]);
		}
		*/
		
		//K개 중에 K개를 뽑는 순열을 구하자.
		visited = new boolean[kArr.length];
		Arrays.fill(visited, false);
		out = new String[K];
		recursivePerm(0);
		
		System.out.println(minValue);
	}
	
	private static void recursivePerm(int depth)
	{
		if(depth == K)
		{
			tmpMap = cloneMap(map);
			
			for(int i=0;i<K;i++)
			{
				//순서쌍 도출 
				System.out.print(out[i] + " ");
				String[] rcsArr = out[i].split("\\|");
				int r = Integer.parseInt(rcsArr[0]);
				int c = Integer.parseInt(rcsArr[1]);
				int s = Integer.parseInt(rcsArr[2]);
				
				int startX = r-s-1;
				int startY = c-s-1;
				
				int endX = r+s-1;
				int endY = c+s-1;
				
				//순서쌍에 맞게 로테이션 연산을 수행한다.
				rotationMap(tmpMap,startX,startY,endX,endY);
			}
			System.out.println();
			
			//로테이션 수행후 결과값 도출
			int returnVal = resultMapMinValue(tmpMap);
			
			//결과값을 최소값과 비교한다.
			if(minValue == -1) minValue = returnVal;
			else if(minValue > returnVal) minValue = returnVal;
			
			return;
		}
		
		for(int i=0;i<kArr.length;i++)
		{
			if(visited[i] == false)
			{
				visited[i] = true;
				out[depth] = kArr[i];
				recursivePerm(depth+1);
				visited[i] = false;
			}
		}
	}
	
	private static void rotationMap(int[][] map , int startX, int startY, int endX, int endY)
	{
		if(startX == endX && startY == endY) return;
		
		//정사각형의 각 변의 길이는 endX-startX 또는 endY-startY 이다.
		int squareEachLine = endX-startX;
		
		int tmpStr = map[startX][startY];
		for(int i=0;i<squareEachLine;i++)
		{
			try {
				int tmpStr2 = map[startX][startY+i+1]; 
				//map[startX][startY+i+1] = map[startX][startY+i];
				map[startX][startY+i+1] = tmpStr;
				tmpStr = tmpStr2;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<squareEachLine;i++)
		{
			int tmpStr2 = map[startX+i+1][startY+squareEachLine];
			//map[startX+i+1][startY+squareEachLine] = map[startX+i][startY+squareEachLine];
			map[startX+i+1][startY+squareEachLine] = tmpStr;
			tmpStr = tmpStr2;
		}
		
		for(int i=squareEachLine-1;i>=0;i--)
		{
			int tmpStr2 = map[startX+squareEachLine][startY+i];
			//map[startX+squareEachLine][startY+i] = map[startX+squareEachLine][startY+i+1];
			map[startX+squareEachLine][startY+i] = tmpStr;
			tmpStr = tmpStr2;
		}
		
		for(int i=squareEachLine-1;i>=0;i--)
		{
			int tmpStr2 = map[startX+i][startY];
			//map[startX+i][startY] = map[startX+i+1][startY];
			map[startX+i][startY] = tmpStr;
			tmpStr = tmpStr2;
		}
		
		dispMap(map);
		rotationMap(map,startX+1,startY+1,endX-1,endY-1);
	}
	
	private static int[][] cloneMap(int[][] map)
	{
		int[][] result = new int[N][M];
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				result[i][j] = map[i][j];
			}
		}
		return result;
	}
	
	private static void dispMap(int[][] map)
	{
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private static int resultMapMinValue(int[][] map)
	{
		int returnVal = -1;
		int sum = 0;
		for(int i=0;i<N;i++)
		{
			sum = 0;
			for(int j=0;j<M;j++)
			{
				sum+=map[i][j];
			}
			
			if(returnVal == -1) returnVal = sum;
			else if(returnVal > sum) returnVal = sum;
		}
		
		return returnVal;
	}
}
