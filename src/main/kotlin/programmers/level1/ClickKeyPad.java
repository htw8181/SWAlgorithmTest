package programmers.level1;

import java.util.HashMap;

/**
 * 키패드 누르기
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * * 0 #
 */
public class ClickKeyPad {
    public static String solution(int[] numbers, String hand) {
        StringBuffer sb = new StringBuffer();

        HashMap<String,int[]> numberPosMap = new HashMap<>();
        numberPosMap.put("1",new int[]{0,0});
        numberPosMap.put("2",new int[]{0,1});
        numberPosMap.put("3",new int[]{0,2});
        numberPosMap.put("4",new int[]{1,0});
        numberPosMap.put("5",new int[]{1,1});
        numberPosMap.put("6",new int[]{1,2});
        numberPosMap.put("7",new int[]{2,0});
        numberPosMap.put("8",new int[]{2,1});
        numberPosMap.put("9",new int[]{2,2});
        numberPosMap.put("*",new int[]{3,0});
        numberPosMap.put("0",new int[]{3,1});
        numberPosMap.put("#",new int[]{3,2});

        String prvLeftNumber = "*";
        String prvRightNumber = "#";

        for (int i=0;i< numbers.length;i++) {
            int number = numbers[i];

            switch (number) {
                case 1:
                case 4:
                case 7:
                    sb.append("L");
                    prvLeftNumber = Integer.toString(number);
                    break;
                case 3:
                case 6:
                case 9:
                    sb.append("R");
                    prvRightNumber = Integer.toString(number);
                    break;
                case 2:
                case 5:
                case 8:
                case 0:
                {
                    int numberXPos = numberPosMap.get(Integer.toString(number))[0];
                    int numberYPos = numberPosMap.get(Integer.toString(number))[1];

                    int prvLeftNumberXPos = numberPosMap.get(prvLeftNumber)[0];
                    int prvLeftNumberYPos = numberPosMap.get(prvLeftNumber)[1];

                    int prvRightNumberXPos = numberPosMap.get(prvRightNumber)[0];
                    int prvRightNumberYPos = numberPosMap.get(prvRightNumber)[1];

                    int prvLeftNumberAndCurPosDistance = Math.abs(numberXPos - prvLeftNumberXPos) + Math.abs(numberYPos - prvLeftNumberYPos);
                    int prvRightNumberAndCurPosDistance = Math.abs(numberXPos - prvRightNumberXPos) + Math.abs(numberYPos - prvRightNumberYPos);

                    if (prvLeftNumberAndCurPosDistance > prvRightNumberAndCurPosDistance) {
                        sb.append("R");
                        prvRightNumber = Integer.toString(number);
                    } else if (prvLeftNumberAndCurPosDistance < prvRightNumberAndCurPosDistance) {
                        sb.append("L");
                        prvLeftNumber = Integer.toString(number);
                    } else {
                        if (hand.equals("left")) {
                            sb.append("L");
                            prvLeftNumber = Integer.toString(number);
                        } else {
                            sb.append("R");
                            prvRightNumber = Integer.toString(number);
                        }
                    }
                }
                    break;
            }
        }
        String answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        String result = solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},"right");
        System.out.println(result);
    }
}
