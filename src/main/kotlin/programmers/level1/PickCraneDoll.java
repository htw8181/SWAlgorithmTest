package programmers.level1;

import java.util.Stack;

/**
 * 크레인 인형 뽑기
 */
public class PickCraneDoll {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        final int EMPTY = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i=0;i< moves.length;i++) {
            int moveY = moves[i]-1;
            for (int j=0;j< board.length;j++) {
                //해당 지점에 인형이 있으면,
                if (board[j][moveY] > 0) {
                    stack.push(board[j][moveY]);
                    board[j][moveY] = 0;

                    if (stack.size() > 1 && (stack.get(stack.size()-1) == stack.get(stack.size()-2))) {
                        stack.pop();
                        stack.pop();
                        answer += 2;
                    }
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        //moves가 모두 순회하는 동안 터트려 사라진 인형의 갯수
        int result = solution(new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}},new int[]{1,5,3,5,1,2,1,4});
        System.out.println(result);
    }
}
