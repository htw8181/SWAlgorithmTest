import java.util.Arrays;

/* Arrays.sort함수는 기본적으로 오름차순이나, 
 * 2번째 인자에 Collections.reverseOrder()함수를 붙이면 내림차순으로 정렬이 가능하다.
 * 단, 문자열이 아닌 int 같은 기본 자료형의 배열은 wrapper클래스인 Integer를 사용해야 한다.
*/

/*
 * 데이터를 저장하는 속도는 list가 빠르나, 저장된 데이터를 검색하는 것은 hash가 더 빠르다.
 */

/*
 * 브레이크 포인터 찍고, 마커에 대고 마우스 우클릭 후, Breakpoint Properties 클릭 ,
 * Method BreakPoint의 Conditional 사용법 익혀둘것.
 * https://blog.naver.com/ggg3374/221444329348
 */

/* 백준 알고리즘 순열/조합 문제풀이 참고
 * https://baelanche.tistory.com/194
 */

/*
 char 데이터를 int로 변환
 char c = '5';
 int i = Character.getNumericValue(c);
 System.out.println(i); 
 */
/*
	int 의 범위는 -20억 ~ 20억
	unsignedInt 일경우 40억
 */
/*
	1억번 반복시 1초 소요
 */
/*
 //int 데이터를 char로 변환
 //char의 범위는 -128에서 127까지임
 //int i = -128;
   int i = 127;
   char c = (char)i;
   System.out.println((byte)c);
 */

/*
 //int 데이터를 char로 변환
   int i = 5;
   char c = Integer.toString(i).charAt(0); //char은 늘 1자리의 문자임.
   System.out.println(c); 
 */

/*
   파일 입력 받을시 데이터가 많을시 scanner보다는 BufferedReader가 수행속도가 빠르다.
   
   //로컬 파일 테스트..
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("파일")));
   
   //서버 수행시..
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
   
   StringTokenizer st = new StringTokenizer(br.readLine());
   int T = Integer.parseInt(st.nextToken());
 */

public class permAndCombTestEnhanced {

	private static int[] data;
	private static int N;
	private static int R;
	private static boolean[] visited;
	private static int[] out;
	private static int count;
	
	public static void main(String[] args) throws Exception {
		
		data = new int[]{1,2,3,4,5};
		N=data.length;
		
		visited = new boolean[N];
		Arrays.fill(visited, false);
		
		R=3;
		out = new int[R];
		Arrays.fill(out, -1);
		
		count=0;
		
		permRecursive(0);
		//dPermRecursive(0);
		//combRecursive(0,0);
		//dCombRecursive(0,0);
		
		System.out.println("count => "+count);
	}
	
	/**
	 * 순열(재귀+백트래킹)
	 * permAndCombTest보다 느림(사전순으로 정렬)
	 * @param depth
	 */
	private static void permRecursive(int depth)
	{
		if(depth == R)
		{
			for(int i=0;i<R;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
			return;
		}
		
		for(int i=0;i<N;i++)
		{
			if(visited[i] == false)
			{
				visited[i] = true;
				out[depth] = data[i];
				permRecursive(depth+1);
				visited[i] = false;
			}
		}
	}
	
	/**
	 * 중복 순열(재귀+백트래킹)
	 * permAndCombTest보다 느림
	 * @param depth
	 */
	private static void dPermRecursive(int depth)
	{
		if(depth == R)
		{
			for(int i=0;i<R;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
			return;
		}
		
		for(int i=0;i<N;i++)
		{
			out[depth] = data[i];
			dPermRecursive(depth+1);
		}
	}
	
	/**
	 * 조합(재귀)
	 * permAndCombTest보다 빠름
	 * @param x
	 * @param depth
	 */
	private static void combRecursive(int x, int depth)
	{
		if(depth == R)
		{
			for(int i=0;i<R;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
			return;
		}
		
		for(int i=x;i<N;i++)
		{
			out[depth] = data[i]; 
			combRecursive(i+1,depth+1);
		}
	}
	
	/**
	 * 중복 조합(재귀)
	 * permAndCombTest보다 빠름
	 * @param x
	 * @param depth
	 */
	private static void dCombRecursive(int x, int depth)
	{
		if(depth == R)
		{
			for(int i=0;i<R;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
			return;
		}
		
		for(int i=x;i<N;i++)
		{
			out[depth] = data[i]; 
			dCombRecursive(i,depth+1);
		}
	}
}
