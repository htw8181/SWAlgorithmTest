public class Combinatons {
    /**
     * N개중에 R개를 뽑는 조합(백트래킹)
     */
    private static void comb(int[] arr, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            for (int i = 0; i < n; i++) {
                if(visited[i]) {
                    System.out.print(arr[i] + " ");

                }
            }
            System.out.println();
            return;
        }

        for(int i = start; i < n; i++) {
            visited[i] = true;
            comb(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5};
        int R = 3;
        /**
         * Data 중에 R개를 뽑는 조합
         */
        comb(data,new boolean[data.length],0, data.length,R);
    }
}
