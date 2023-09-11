import java.util.ArrayList;

public class testPrime {

	private static final int N = 100000;
	
	private static ArrayList<Integer> getPrimeList(int num)
	{
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		
		for(int i=2;i<=num;i++)
		{
			boolean isPrime = true;
			for(int prime : primeList)
			{
				if(i == prime) continue;
				
				if(i%prime == 0) 
				{
					isPrime = false;
					break;
				}
			}
			if(isPrime == true) primeList.add(i);
		}
		return primeList;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startMills = System.currentTimeMillis();
		ArrayList<Integer> list = getPrimeList(N);
		for(Integer i : list)
		{
			System.out.println(i);
		}
		long endMills = System.currentTimeMillis();
		System.out.println("총 걸린시간 : " + (endMills - startMills) +"mills..");
	}

}
