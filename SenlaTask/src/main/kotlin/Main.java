import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //lesson-03
        System.out.println("Lesson-03");
        int[] list1 = new int[]{2, 3, 5, 7, 11};
        int[] list2 = new int[]{2, 4, 6, 8, 10, 12, 14};
        int[] result = Lesson03.merge(list1, list2);

        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();

        //lesson-04
        //Task 1.2
        System.out.println("Lesson-04");
        System.out.println(Lesson04.sumRandomNumbers(573,192,467));


        ArrayList<String> movies = new ArrayList<String>();
        movies.add("Green Mile");
        movies.add("Green Mile");
        movies.add("Lord of the Rings");
        movies.add("Forest Gump");
        movies.add("Klaus");
        movies.add("Klaus");
        movies.add("Klaus");
        movies.add("Back to the future");
        //Task 2.2
        System.out.println(Lesson04.bindingString(movies));
        //Task 2.3
        Lesson04.sortByWordRepeat(movies);
    }

}
