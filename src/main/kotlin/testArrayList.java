import java.util.ArrayList;
import java.util.Arrays;

/**
 * ArrayList -> toArray 예제
 * @author HwangTaeWon
 *
 */
public class testArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<>();
		list.add("AAA");
		list.add("BBB");
		list.add("CCC");
		
		String[] arr = new String[list.size()];
		arr = (String[]) list.toArray(arr);
		
		System.out.println(Arrays.toString(arr));
	}

}
