package fr.crabbe.restaurant.exosTests;

public class FakeClass {

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    public static double average(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }
}
