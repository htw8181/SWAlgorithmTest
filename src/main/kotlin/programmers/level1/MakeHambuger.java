package programmers.level1;

import java.util.Stack;

/**
 * 햄버거 만들기
 * 시간초과 발생하는데 Stack을 활용해야 함!!
 */
public class MakeHambuger {

    /**
     * 시간초과나는 case
     */
    public static int wrongSolutionByTimeout(int[] ingredient) {
        StringBuffer sf = new StringBuffer();
        for (int i=0;i<ingredient.length;i++) {
            sf.append(ingredient[i]);
        }
        String ingredientStr = sf.toString();

        int answer = 0;
        while (ingredientStr.contains("1231")) {
            ingredientStr = ingredientStr.substring(0,ingredientStr.indexOf("1231")) + ingredientStr.substring(ingredientStr.indexOf("1231")+4,ingredientStr.length());
            answer++;
        }

        return answer;
    }

    /**
     * Stack를 활용하여 size가 4일때마다 1,2,3,1인지 체크하고 맞으면, pop을 하며 햄버기 +1 카운팅을 하도록 한다.
     */
    public static int solution(int[] ingredients) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int ingredient : ingredients) {
            stack.push(ingredient);

            if (stack.size() >= 4) {
                if (stack.get(stack.size()-4) == 1 && stack.get(stack.size()-3) == 2 && stack.get(stack.size()-2) == 3 && stack.get(stack.size()-1) == 1) {
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    count ++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int result = solution(new int[]{1, 3, 2, 1, 2,3, 1, 3, 1, 2,3,1});
        System.out.println(result);
    }
}
