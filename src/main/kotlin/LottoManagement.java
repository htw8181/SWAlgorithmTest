import java.util.*;

/**
 * 금주 주말마다 시행하는 로또 예상번호를 뽑기 위해 사용
 */
public class LottoManagement {
    public static void main(String[] args) {
        int number = -1;
        Map<Integer,Integer> lottoNumberMap = new HashMap<>();
        Map<Integer, Set<Integer>> rankMap = new HashMap<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            number = sc.nextInt();
            if (number == 0) {
                for (Map.Entry<Integer,Integer> entry : lottoNumberMap.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
            } else if (number == 99) {
                //결산하고 종료
                for (Map.Entry<Integer,Integer> entry : lottoNumberMap.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                    Set<Integer> set = rankMap.getOrDefault(entry.getValue(), new HashSet<>());
                    set.add(entry.getKey());
                    rankMap.put(entry.getValue(), set);
                }

                List<Integer> keySet = new ArrayList<>(rankMap.keySet());
                Collections.reverse(keySet);
                for (int key : keySet) {
                    System.out.print(key + "개 --> ");
                    Set<Integer> set = rankMap.get(key);
                    Iterator iterator = set.iterator();
                    while (iterator.hasNext()) {
                        System.out.print(iterator.next() + " ");
                    }
                    System.out.println();
                }
//                for (Map.Entry<Integer,Set<Integer>> entry : rankMap.entrySet()) {
//                    System.out.print(entry.getKey() + "개 --> ");
//                    Set<Integer> set = entry.getValue();
//                    Iterator iterator = set.iterator();
//                    while (iterator.hasNext()) {
//                        System.out.print(iterator.next() + " ");
//                    }
//                    System.out.println();
//                }
                break;
            } else {
                lottoNumberMap.put(number,lottoNumberMap.getOrDefault(number,0) + 1);
            }
        }
    }
}
