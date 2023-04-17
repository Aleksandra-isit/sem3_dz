import Controller.Controll;
import View.Viewer;
import View.IIO;

public class Main {
    public static void main(String[] args) {
        Controll<IIO> task = new Controll<IIO>(new Viewer());
        task.start();
    }
}