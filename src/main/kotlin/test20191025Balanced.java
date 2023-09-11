import java.util.HashMap;
import java.util.Map;

public class test20191025Balanced {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int count = solution("azABaabza");
		
		System.out.println(count);
	}

	//대문자 -> byte 65 ~ 90
	//소문자 -> byte 97 ~ 122
	public static int solution(String S) {
        // write your code in Java SE 8
		Map<Byte,Integer> charCountMap = new HashMap<Byte,Integer>();
		for(int i=0;i<S.length();i++)
		{
			char c=S.charAt(i);
			byte b = (byte) c;
			
			int count = charCountMap.get(b) == null ? 0 : charCountMap.get(b);
			//System.out.println(count);
			charCountMap.put(b,count+1);
		}
		
		int sum = 0;
		for(Map.Entry<Byte,Integer> entry : charCountMap.entrySet())
		{
			byte key = entry.getKey();
			int count = entry.getValue();
			
			int val1 = charCountMap.get(key) == null ? 0: charCountMap.get(key);
			int val2 = charCountMap.get((byte) (key+32)) == null ? 0 : charCountMap.get((byte) (key+32));
			if(val1 > 0 && val2 > 0)
			{
				sum = sum + val1 + val2;
				charCountMap.put(key,0);
				charCountMap.put((byte) (key+32),0);
			}
		}
		
		if(sum > 0)
		{
			return sum;
		}
		return -1;
    }
}
