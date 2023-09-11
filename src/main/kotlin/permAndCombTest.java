
public class permAndCombTest {
	
	/**
	 * 순열(Permutation - nPr),
	 * 조합(Combination - nCr)
	 * 문제 : 1,2,3,4,5 숫자 5개가 주어졌을때, 3개의 숫자를 뽑아 순열, 중복 순열, 조합, 중복 조합을 출력
	 */
	private static final int N = 5; // nPr,nCr에서 n을 의미
	private static final int[] data = {1,2,3,4,5,6,7,8,9,10};
	private static final int R = 3; //추출할 갯수 nPr,nCr에서 r을 의미
	private static int[] out = new int[N];
	private static int count = 0;
	
	private static void swap(int x, int y)
	{
		int temp = out[x];
		out[x] = out[y];
		out[y] = temp;
	}
	
	//permAndCombTestEnhanced보다 빠름(사전순으로 정렬안됨)
	private static void perm(int start, int end)
	{
		if(start == end)
		{
			for(int i=0;i<end;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
			return;
		}
		
		for(int i=start;i<N;i++)
		{
			swap(i,start);
			perm(start+1,end);
			swap(i,start);
		}
	}
	
	//permAndCombTestEnhanced보다 빠름
	private static void dPerm(int start, int end)
	{
		if(start == end)
		{
			for(int i=0;i<end;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
			return;
		}
		
		for(int i=0;i<N;i++)
		{
			out[start] = data[i];
			dPerm(start+1,end);
		}
	}
	
	//permAndCombTestEnhanced보다 느림
	private static void comb(int start, int end, int index)
	{
		if(end == 0)
		{
			for(int i=0;i<start;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
		}
		else if(index == N)
		{
			return;
		}
		else
		{
			out[start] = data[index];
			comb(start+1,end-1,index+1); //여기가 다름
			comb(start,end,index+1);
		}
	}
	
	//permAndCombTestEnhanced보다 느림
	private static void dComb(int start, int end, int index)
	{
		if(end == 0)
		{
			for(int i=0;i<start;i++)
			{
				System.out.print(out[i] + " ");
			}
			System.out.println();
			count++;
		}
		else if(index == N)
		{
			return;
		}
		else
		{
			out[start] = data[index];
			dComb(start+1,end-1,index); //여기가 다름
			dComb(start,end,index+1);
		}
	}
	
	public static void main(String[] args) throws Exception{
		for(int i=0;i<N;i++)
		{
			out[i] = data[i];
		}
		
		count = 0;
		perm(0,R);
		System.out.println("순열 총 갯수 : " + count);
		
		count = 0;
		dPerm(0,R);
		System.out.println("중복 순열 총 갯수 : " + count);
		
		count = 0;
		comb(0,R,0);
		System.out.println("조합 총 갯수 : " + count);
		
		count = 0;
		dComb(0,R,0);
		System.out.println("중복 조합 총 갯수 : " + count);
	}
}
