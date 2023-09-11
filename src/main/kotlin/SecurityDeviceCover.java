/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = &apos;b&apos;;
// String var = &quot;ABCDEFG&quot;;
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
/**
 * 1. 방범장치는 최대 5개까지 가능. 설치 불가능 하다면 -1을 출력
 * 2. N은 최대 6
 * 3. K는 6까지 
 * @author HwangTaeWon
 *
 */
class SecurityDeviceCover
{
	//조합에 사용할 var들..
	private static int NN;
	private static int[] data;
	private static int RR;
	private static int[] out = new int[NN];
	private static int count = 0;
	
	/**
	 * 벽
	 */
	private static final char WALL = '#';
	
	/**
	 * 방범장치
	 */
	private static final char DEVICE = '*';
	
	/**
	 * 맵 정보(행렬) 
	 */
	private static char[][] map;
	
	/**
	 * 맵 정보(행렬) 원본
	 */
	private static char[][] mapOrigin;
	
	/**
	 * 방범장치의 최대 갯수 -> 최대 5개까지 
	 */
	private static final int DEVICE_MAX_COUNT = 5;
	
	/**
	 * 맵 크기 
	 */
    private static int N;
    
    /**
	 * 방범장치의 커버 범위 
	 */
    private static int K;
    
	private static void comb(int start, int end, int index)
	{
		/**
		 * comb 수행 도중에 isAllCover가 true로 설정되었다면, 잔여 comb 재귀 호출은 할 필요가 없으므로, 
		 * 잔여 수행시 바로바로 리턴 시켜버리자
		 */
		if(isAllCover == true)
		{
			return;
		}
		
		if(end == 0)
		{
			boolean combIsSuccessFul = true;
			/**
			 * 원본 맵을 실제 사용할 맵에 복사시키자
			 * 원본은 원본 그대로 보존해야하며,
			 * 실제 사용할 맵만 늘 새롭게 초기화 하자
			 */
			for(int x=0;x<N;x++)
			{
				/**
				 * ArrayList는 clone 함수가 정상 동작(기본 자료형일때만 정상동작하며, 객체 자료형은 정상 복사 아님)하지만,
				 * 배열은 1차원 배열에서만 clone 함수가 정상 동작한다. 
				 */
				map[x] = mapOrigin[x].clone();
			}
			startPointList.clear();
			for(int i=0;i<start;i++)
			{
				//System.out.print(out[i] + " ");
				
				//0부터 (N*N)-1까지의 수를 매핑하기 위해 N*N배열을 1차원으로 바꿔보자..
				//2차원 배열 map[x][y]라고 하면, 1차원으로 바꾼다면, (x*N)+y <- N은 배열의 y사이즈(가로길이)
				//ex) map[1][2] -> (1*6)+2 -> map[8]
				//이를 거꾸로 생각해서 계산해야한다면, 예를들어, 8이면, 2차원 배열로 바꾼다면,
				//8을 N(y가로길이)(6)으로 나누었을때, 몫은 x, 나머지는 y
				//ex) map[8] -> 8/6 -> 몫:x(1) , 8%6 -> 나머지:y(2) -> map[1][2]
				
				//out[i]를 2차원 배열에 매핑하자..
				int x = out[i] / N;
				int y = out[i] % N;
				
				//map[x][y]를 확인하여 #(Wall)이 아니면, 방범장치 설치가 가능하다.
				//그러나, #이라면, 해당 조합은 필요가 없으니 break처리하여 가지치기 하자..
				if(map[x][y] == WALL)
				{
					combIsSuccessFul = false;
					break;
				}
				else
				{
					map[x][y] = DEVICE;
					StartPoint startPoint = new StartPoint();
					startPoint.x = x;
					startPoint.y = y;
					startPointList.add(startPoint);
				}
			}
			//System.out.println();
			count++;
			
			//방법장치가 제대로 설치된 경우이면,
			if(combIsSuccessFul == true)
			{
				//BFS 시작
				checkSecurityArea();
				
				if(isAllCover == true)
				{
					return;
				}
			}
		}
		else if(index == NN)
		{
			return;
		}
		else
		{
			out[start] = data[index];
			comb(start+1,end-1,index+1);
			comb(start,end,index+1);
		}
	}
	
	private static final int[] dx = {0,0,1,-1};
	private static final int[] dy = {1,-1,0,0};
	private static char[][] newMap;
	private static class StartPoint
	{
		/**
		 * 시작지점 x좌표
		 */
		int x;
		
		/**
		 * 시작지점 y좌표
		 */
		int y;
	}
	/**
	 * 방범장치 설치된 지점
	 */
	private static ArrayList<StartPoint> startPointList = new ArrayList<SecurityDeviceCover.StartPoint>();
	private static ArrayList<StartPoint> newStartPointList = new ArrayList<SecurityDeviceCover.StartPoint>();
	
	/**
	 * 맵의 모든 지역이 커버가 되었는지 체크
	 */
	private static boolean isAllCover = false;
	
	private static void checkSecurityArea()
	{
		for(int kCnt=1;kCnt<=K;kCnt++)
		{
			newStartPointList.clear();
			for(int startPointCnt=0;startPointCnt<startPointList.size();startPointCnt++)
			{
				int startX = startPointList.get(startPointCnt).x;
				int startY = startPointList.get(startPointCnt).y;
				
				for(int drc=0;drc<4;drc++)
				{
					//방향 유효성 체크
					if( (startX + dx[drc] >=0) && (startX + dx[drc] <N) && (startY + dy[drc] >=0) && (startY + dy[drc] <N) )
					{
						//이동할 곳이 WALL(#)인지 체크
						if(map[startX + dx[drc]][startY + dy[drc]] != WALL)
						{
							map[startX + dx[drc]][startY + dy[drc]] = DEVICE;
							
							//다음 kCnt횟차에 새로운 시작지점으로 설정
							StartPoint newStartPoint = new StartPoint();
							newStartPoint.x = startX + dx[drc];
							newStartPoint.y = startY + dy[drc];
							newStartPointList.add(newStartPoint);
						}
					}
				}				
			}
			
			//맵의 모든 지역이 커버가 되었는지 체크한다
			isAllCover = true;
			for(int x=0;x<N;x++)
			{
				for(int y=0;y<N;y++)
				{
					if(map[x][y] != WALL && map[x][y] != DEVICE)
					{
						isAllCover = false;
						break;
					}
				}
				if(isAllCover == false)
				{
					break;
				}
			}
			if(isAllCover == true)
			{
				return;
			}
			
			/**
			 * ArrayList Clone한다.
			 * 기본 자료형이 아닌 객체 자료형이므로 정상 복사가 아닌 주소만 공유하는 복사이다.
			 */
			startPointList = (ArrayList<StartPoint>) newStartPointList.clone();
		}
	}
	
	private static int answer = -1;
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("src/main/kotlin/SecurityDeviceCoverTestCase.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			answer = -1;
			isAllCover = false;
			
			N = sc.nextInt();
			
			K = sc.nextInt();
			
			System.out.println(N + " " + K);
			map = new char[N][N];
			mapOrigin = new char[N][N];
			sc.nextLine();
			//초기 맵 설정
			for(int x=0;x<N;x++)
			{
				String strLine = sc.nextLine();
				//System.out.println(strLine);
				
				for(int y=0;y<N;y++)
				{
					map[x][y] = strLine.charAt(y);
					mapOrigin[x][y] = map[x][y];
					//System.out.print(map[x][y]);
				}
				//System.out.println();
			}
			
			//방범장치를 사용한 갯수 case 별 조사 시작..
			for(int deviceCnt=1;deviceCnt<=5;deviceCnt++)
			{
				//System.out.println(deviceCnt);
				
				//맵 행렬(N*N)에서 deviceCnt갯수만큼 배치하는 경우의 수를 구한다 -> 조합 활용 
				NN = N*N;
				RR = deviceCnt;
				
				count = 0;
				out = new int[NN];
				data = new int[NN];
				for(int i=0;i<NN;i++)
				{
					data[i] = i;
				}
				comb(0,RR,0);
				
				if(isAllCover == true)
				{
					answer = deviceCnt;
					dispMap();
					break;
				}
			}
			
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	private static void dispMap()
	{
		for(int x=0;x<N;x++)
		{
			for(int y=0;y<N;y++)
			{
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}
}

