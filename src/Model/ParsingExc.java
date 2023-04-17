package Model;

public class ParsingExc extends Exception{
    String data;

    public ParsingExc(String data) {
        this.data = data;
    }

    @Override
    public String getMessage() {
        return this.data;
    }
}
