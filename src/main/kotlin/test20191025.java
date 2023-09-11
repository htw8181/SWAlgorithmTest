import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class test20191025 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a = solution(1,8,3,2);
		System.out.println(a);
	}

	
	private static final int N = 4;
	private static final int R = 4;
	private static int[] data = new int[N];
	private static boolean[] visited;
	private static int[] out;
	
	private static Set<String> set;
	
	/**
	 * A B C D를 입력받아 디지털 시간으로 나올수 있는 경우의 수를 리턴한다. 
	 * @param A
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
	public static int solution(int A, int B, int C, int D) {
        // write your code in Java SE 8
		
		data[0] = A;
		data[1] = B;
		data[2] = C;
		data[3] = D;
		
		visited = new boolean[N];
		
		out = new int[R];
		
		set = new HashSet<String>();
		
		perm(0);
		
		if(set.size() > 0)
		{
			return set.size();
		}
		else
		{
			return -1;
		}
    }
	
	private static void perm(int depth)
	{
		if(depth == R)
		{
			/*
			for(int i=0;i<R;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			*/
			
			//if this is clock value, I will count up..
			checkDigitalClock(out);
			return;
		}
		
		for(int i=0;i<N;i++)
		{
			if(visited[i] == false)
			{
				visited[i] = true;
				out[depth] = data[i];
				perm(depth+1);
				visited[i] = false;
			}
		}
	}
	
	private static void checkDigitalClock(int[] out)
	{
		StringBuilder sbHour = new StringBuilder();
		sbHour.append(out[0]);
		sbHour.append(out[1]);
		StringBuilder sbMinute = new StringBuilder();
		sbMinute.append(out[2]);
		sbMinute.append(out[3]);
		
		int hour = Integer.parseInt(sbHour.toString());
		int minute = Integer.parseInt(sbMinute.toString());
		
		if(hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59)
		{
			set.add(hour+""+minute);
		}
	}
}
