import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 
 * inner 클래스를 활용하여 객체를 생성후 큐에 넣는 짓을 남발하다간.. 타임아웃에 걸린다.
 * inner 클래스 활용 및 생성을 지양하자..
 *
 */
public class testTomato {

	/**
	 * 가로길이 
	 */
	private static int M;
	
	/**
	 * 세로길이 
	 */
	private static int N;
	
	
	private static int count;
	private static int[][] map;
	private static final int NOT_ROTTEN = 0;
	private static final int EMPTY = -1;
	
	private static final int[] dx = {0,0,1,-1};
	private static final int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/kotlin/tomatoInputData.txt")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case=1;test_case<=T;test_case++)
		{
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			//System.out.println("N->"+N+" | "+"M->"+M);
			
			count = 1;
			map = new int[N][M];
			
			for(int i=0;i<N;i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++)
				{
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//dispMap(startStatus.map);
			
			if(isAllRotten(map) == true)
			{
				System.out.println("0");
			}
			else
			{
				/**
				 * 토마토가 모두 익었을시까지 걸린 시간중에 최소 시간 
				 */
				int minCount = -1;
				
				ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
				queue.offer(count);
				
				while(!queue.isEmpty())
				{
					count = queue.poll();
					
					if(isAllRotten(map) == true)
					{
						minCount = count;
						minCount -= 1;
						break;
					}
					
					boolean isChanged = false;
					for(int i=0;i<N;i++)
					{
						for(int j=0;j<M;j++)
						{
							if(map[i][j] == count)
							{
								//동,서,남,북으로 토마토 익어진다.
								for(int dir=0;dir<4;dir++)
								{
									//이동될 방향의 유효성 체크
									if(i+dx[dir] >= 0 && i+dx[dir] < N
									&& j+dy[dir] >= 0 && j+dy[dir] < M
									&& map[i+dx[dir]][j+dy[dir]] != EMPTY)
									{
										//아직 익지 않은 지점이면,
										if(map[i+dx[dir]][j+dy[dir]] == NOT_ROTTEN)
										{
											isChanged = true;
											map[i+dx[dir]][j+dy[dir]] = count + 1;
										}
									}
								}
								
							}
						}
					}
					
					if(isChanged == true)
					{
						
						count = count + 1;
						//newStatus.map = curStatus.map;
						queue.offer(count);
						//dispMap(curStatus.map);
					}
					
				}
				
				System.out.println(minCount);
			}
		}
	}
	
	private static boolean isAllRotten(int[][] map)
	{
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				if(map[i][j] == NOT_ROTTEN) return false;
			}
		}
		
		return true;
	}
	
	/*
	private static void dispMap(int[][] map)
	{
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	*/
}
