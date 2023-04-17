package Model;

public class BirthDataExc extends Exception{
    String date;

    public BirthDataExc(String date) {
        this.date = date;
    }

    @Override
    public String getMessage() {
        return String.format("Некорректная дата рождения (%s), введите данные в формате дд.мм.гг.\n", date);
    }
}
