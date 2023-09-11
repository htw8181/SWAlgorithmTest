import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test20201010 {

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"one","one","two","one"};
		//Long[] answer = {};
		Long[] answer = new Long[words.length];
		
		Map<String,Long> wordRound = new HashMap<>();
		for(int i=0;i<words.length;i++) {
			Long round = wordRound.get(words[i]);
			if(round == null || round < 1) {
				wordRound.put(words[i],(long) i+1);
				answer[i] = (long) i+1;
			}
			else {
				answer[i] = round;
			}
		}
		
		System.out.print(answer.toString().toCharArray());
	}
*/
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 6; //1이상 10이하 
		String text = "hi bye"; //1이상 30이하 
		int second = 13; //1이상 1000이하 
	
		final char EMPTY = '_';
		final int TEXT_COUNT_MAX = text.length();
		
		ArrayList<Character> list = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			list.add(EMPTY);
		}
		
		int textCount = 0;
		
		for(int i=1;i<=second;i++) {
			list.remove(0);
			if(textCount < TEXT_COUNT_MAX) {
				list.add(text.charAt(textCount) == ' ' ? '_' : text.charAt(textCount));
			}
			else {
				list.add(EMPTY);
			}
			textCount++;
			if(textCount == n+TEXT_COUNT_MAX) textCount = 0;
		}
		
		String answer = "";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<list.size();i++)
		{
			sb.append(list.get(i));
		}
		System.out.print(sb.toString());
	}
}
