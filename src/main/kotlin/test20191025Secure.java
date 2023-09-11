
public class test20191025Secure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//char c = '!@#$%^&*()_';
		//System.out.println((byte) c);
		
		boolean result = solution("FooBar123");
		System.out.println(result);
	}

	/**
	 * 공백이 없을것 -> byte 32
	 * 적어도 6글자
	 * 적어도 1숫자 -> byte 48 ~ 57
	 * 적어도 1소문자 -> byte 97 ~ 122
	 * 적어도 1대문자 -> byte 65 ~ 90
	 * 적어도 1스페셜 문자 ->  !@#$%^&*()_ -> byte 33, 64, 35, 36, 37, 94, 38, 42, 40, 41, 95
	 * @param S
	 * @return
	 */
	public static boolean solution(String S) {
        // write your code in Java SE 8
		if(S.length() < 6)
		{
			//at least 6 characters
			return false;
		}
		
		boolean isExistDigit = false;
		boolean isExistUpperCase = false;
		boolean isExistLowerCase = false;
		boolean isExistSpecialChar = false;
		for(int i=0;i<S.length();i++)
		{
			char c = S.charAt(i);
			byte b = (byte) c;
			//System.out.println(b);
			
			if(b == 32)
			{
				//space!!
				return false;
			}
			
			//check digit
			//적어도 1숫자 -> byte 48 ~ 57
			if(b >= 48 && b <= 57)
			{
				isExistDigit = true;
			}
			
			//check UpperCase
			//적어도 1대문자 -> byte 65 ~ 90
			if(b >= 65 && b <= 90)
			{
				isExistUpperCase = true;
			}
			
			//check LoweCase
			//적어도 1소문자 -> byte 97 ~ 122
			if(b >= 97 && b <= 122)
			{
				isExistLowerCase = true;
			}
			
			//check SpecialCase
			//적어도 1스페셜 문자 ->  !@#$%^&*()_ -> byte 33, 64, 35, 36, 37, 94, 38, 42, 40, 41, 95
			if(b == 33 
			|| b == 64 
			|| b == 35 
			|| b == 36 
			|| b == 37 
			|| b == 94 
			|| b == 38 
			|| b == 42 
			|| b == 40
			|| b == 41
			|| b == 95)
			{
				isExistSpecialChar = true;
			}
		
		}
		
		if(isExistDigit == true
		&& isExistUpperCase == true
		&& isExistLowerCase == true
		&& isExistSpecialChar == true)
		{
			return true;
		}
		else
		{
			return false;
		}
    }
}
