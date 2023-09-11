import java.util.ArrayList;

/**
 * 1부터 N까지 소수를 구함
 * @author HwangTaeWon
 *
 */
public class PrimeTest {
	private static final int N = 100000;
	
	private static void getPrimeNum(int n)
	{
		ArrayList<Integer> primeList = new ArrayList<>();
		primeList.add(2); //소수는 2부터 시작이므로 미리 추가함
		
		for(int i=2;i<=n;i++)
		{
			for(int j=0;j<primeList.size();j++)
			{
				if(i%primeList.get(j) == 0)
				{
					//소수가 아님
					break;
				}
				
				if(j+1 == primeList.size())
				{
					//소수임이 확인이 되었으니 추가함
					primeList.add(i);
				}
			}
		}
		
		//추출된 소수 출력함
		for(int primeNum : primeList)
		{
			System.out.println(primeNum);
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		long startMills = System.currentTimeMillis();
		getPrimeNum(N);
		long endMills = System.currentTimeMillis();
		System.out.println("총 걸린시간 : " + (endMills - startMills) +"mills..");
	}
}
