public class Lesson03 {

    public static int[] merge(int[] arrayFirst, int[] arraySecond){
        int[]sortedArray = new int[arrayFirst.length+arraySecond.length];
        int count = 0;

        for(int i = 0; i<arrayFirst.length; i++) {
            sortedArray[i] = arrayFirst[i];
            count++;
        }
        for(int j = 0;j<arraySecond.length;j++) {
            sortedArray[count++] = arraySecond[j];
        }

        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = sortedArray.length - 1; j > i; j--) {
                if (sortedArray[j - 1] > sortedArray[j]) {
                    int tmp = sortedArray[j - 1];
                    sortedArray[j - 1] = sortedArray[j];
                    sortedArray[j] = tmp;
                }
            }
        }
        return sortedArray;
    }


}
