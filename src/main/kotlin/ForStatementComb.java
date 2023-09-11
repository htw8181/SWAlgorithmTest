
public class ForStatementComb {
	public static void main(String[] args) throws Exception
	{
		/**
		 * 1부터 5까지 숫자 중에 3개를 중복조합으로 뽑는 경우..
		 */
		/*
		for(int x=1;x<=5;x++)
		{
			for(int y=x;y<=5;y++)
			{
				for(int z=y;z<=5;z++)
				{
					System.out.println(x+" "+y+" "+z);
				}
				System.out.println();
			}
			System.out.println();
		}
		*/
		
		/**
		 * 1부터 5까지 숫자 중에 3개를 조합으로 뽑는 경우..
		 */
		for(int x=1;x<=5;x++)
		{
			for(int y=x+1;y<=5;y++)
			{
				for(int z=y+1;z<=5;z++)
				{
					System.out.println(x+" "+y+" "+z);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
