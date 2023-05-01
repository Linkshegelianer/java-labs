package exercise;

// BEGIN
public class MinThread extends Thread {

    int[] numbers;

    int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public void run() { // run() must return void
        min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
    }

    public int getMin() {
        return min;
    }
}
// END
