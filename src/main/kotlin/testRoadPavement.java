import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// time문구는 절대 쓰지 말것!!

/**
 * 백준 알고리즘 1162번 도로포장 문제
 * 시작 20:25
 * 종료 22:30
 * 그러나, 제출하면 런타임 에러가 발생하는데 이유를 모르겠음
 * 테스트 케이스 범위가 정말 큰가..
 * PRO 문제라 어렵긴 어렵네..
 * @author HwangTaeWon
 *
 */
public class testRoadPavement {

	/**
	 * 도시의 수 N(1 ≤ N ≤ 10,000)
	 */
	private static int N;
	
	/**
	 * 도로의 수 M(1 ≤ M ≤ 50,000)
	 */
	private static int M;
	
	/**
	 * 포장할 도로의 수 K(1 ≤ K ≤ 20)
	 */
	private static int K;
	
	/**
	 * key -> 시작 도시 번호
	 * value -> 시작 도시 번호, 도착 도시 번호 , 두 도시 간 걸리는 시간을 element로 가지는 ArrayList
	 */
	private static Map<Integer,ArrayList<int[]>> map;
	
	/**
	 * 현재까지 측정된 K개의 도로를 포장하였을때 걸릴수 있는 최소 시간
	 */
	private static int minTime;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/kotlin/sampleRoadPavement.txt")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.print(N + " ");
		System.out.print(M + " ");
		System.out.println(K);
		
		map = new HashMap<Integer, ArrayList<int[]>>();
		
		for(int i=0;i<M;i++)
		{
			st = new StringTokenizer(br.readLine());
			
			/**
			 * 시작 도시 번호
			 */
			int startCityNo = Integer.parseInt(st.nextToken());
			
			/**
			 * 도착 도시 번호
			 */
			int destCityNo = Integer.parseInt(st.nextToken());
			
			/**
			 * 두 도시 간 이동시 걸리는 시간
			 */
			int spendTime = Integer.parseInt(st.nextToken());
		
			System.out.print(startCityNo + " ");
			System.out.print(destCityNo + " ");
			System.out.println(spendTime);
			
			ArrayList<int[]> list = map.get(startCityNo);
			if(list == null || list.size() == 0)
			{
				list = new ArrayList<int[]>();
			}
			
			int[] arr = new int[] {startCityNo,destCityNo,spendTime};
			list.add(arr);
			
			map.put(startCityNo,list);
		}
		
		minTime = -1;
		
		//1번부터 N번까지 도시 이동
		/**
		 * 1번부터 N번까지 도시 이동을 하며 걸리는 시간을 저장하며, 정렬하자.
		 */
		ArrayList<Integer> timeSumList = new ArrayList<Integer>();
		
		recursiveCityMove(1,0,timeSumList);
		
		System.out.println(minTime);
	}
	
	private static void recursiveCityMove(int i, int sum, ArrayList<Integer> timeSumList)
	{
		if(i == N)
		{
			//timeSumList을 걸린 시간이 큰 순서대로 정렬한다.
			Collections.sort(timeSumList,Collections.reverseOrder());
			
			//1부터 N번 도시까지 가는동안 걸린시간 중에 K개 포장도로 갯수 만큼 시간값을 빼자.
			//그게 곧, K개 포장도로를 만들었을때 걸릴수 있는 최소의 시간이 된다.
			for(i=0;i<K;i++)
			{
				sum = sum - timeSumList.get(i);
			}
			
			//현재까지 측정된 최소시간과 비교
			if(minTime == -1) minTime = sum;
			else {
				minTime = Math.min(minTime, sum);
			}
			
			return;
		}
		
		ArrayList<int[]> list = map.get(i);
		for(int[] arr : list)
		{
			//시작 도시 번호
			int startCityNo = arr[0];
			
			//도착 도시 번호
			int destCityNo = arr[1];
			
			//두 도시 간 이동시 걸리는 시간
			int spendTime = arr[2];
			
			int newSum = sum + spendTime;
			
			/**
			 * 기본 자료형일때는 clone이 정상 동작한다.
			 * 단, 객체 자료형일때 clone을 하면 정상 복제가 아닌 그저 주소만 공유하는 복사이다.
			 */
			ArrayList<Integer> newTimeSumList = (ArrayList<Integer>) timeSumList.clone();
			newTimeSumList.add(spendTime);
			
			recursiveCityMove(destCityNo,newSum,newTimeSumList);
		}
	}
}
