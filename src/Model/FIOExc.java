package Model;

public class FIOExc extends Exception{
    String fio;

    public FIOExc(String fio) {
        this.fio = fio;
    }

    @Override
    public String getMessage() {
        return String.format("Некорретные данные ФИО(%s). ФИО может содержать только буквы", fio);
    }
}
