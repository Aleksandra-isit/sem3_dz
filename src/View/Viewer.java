package View;

import java.util.Scanner;

public class Viewer implements IIO{
    private Scanner in = new Scanner(System.in);

    @Override
    public void output(String message) {
        System.out.printf("%s", message);
    }

    @Override
    public String input(String message) {
        System.out.printf("%s", message);
        return in.nextLine();
    }
}
