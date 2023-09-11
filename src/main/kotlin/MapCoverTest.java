import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class MapCoverTest {
	
	/**
	 * 커버가능한 범위
	 */
	private static final int K = 5;
	
	/**
	 * 커버됨을 의미
	 */
	private static final int COVER = 1;
	
	private static class StartPos
	{
		int x;
		int y;
	}
	
	private static ArrayList<StartPos> startList = new ArrayList<MapCoverTest.StartPos>();
	private static ArrayList<StartPos> newStartList = new ArrayList<MapCoverTest.StartPos>();
	
	private static final int[] dx = {0,0,1,-1};
	private static final int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception
	{
		final int N = 5;
		int[][] map = new int[N][N];
		for(int i=0;i<N;i++)
		{
			Arrays.fill(map[i], 0);
		}
		
		//초기 맴 상태 출력
		/*
		for(int x=0;x<N;x++)
		{
			for(int y=0;y<N;y++)
			{
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
		*/
		
		StartPos startPos = new StartPos();
		startPos.x = 2;
		startPos.y = 2;
		map[startPos.x][startPos.y] = COVER;
		startList.add(startPos);
		
		for(int kCnt=0;kCnt<K;kCnt++)
		{
			newStartList.clear();
			
			//시작지점 설정
			for(int i=0;i<startList.size();i++)
			{
				int startX = startList.get(i).x;
				int startY = startList.get(i).y;				
				
				for(int drc=0;drc<4;drc++)
				{
					//방향 유효성 체크
					if( (startX + dx[drc] >=0) && (startX + dx[drc] <N) && (startY + dy[drc] >=0) && (startY + dy[drc] <N) )
					{
						//지정된 좌표에 커버함을 표시한다
						map[startX + dx[drc]][startY + dy[drc]] = COVER;
						
						//다음 회차 새로운 시작점으로 설정한다
						StartPos newStartPos = new StartPos();
						newStartPos.x = startX + dx[drc];
						newStartPos.y = startY + dy[drc];
						newStartList.add(newStartPos);
					}
				}
			}
			
			/**
			 * ArrayList Clone한다.
			 * 정말 복제된 새로운 리스트가 생성된다.
			 */
			startList = (ArrayList<StartPos>) newStartList.clone();
			
			//결과 맵 정보 출력한다.
			/*
			for(int x=0;x<N;x++)
			{
				for(int y=0;y<N;y++)
				{
					System.out.print(map[x][y]);
				}
				System.out.println();
			}
			*/
		}
		
		//결과 맵 정보 출력한다.
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
