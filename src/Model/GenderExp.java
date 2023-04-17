package Model;

public class GenderExp extends Exception{
    String gender;

    public GenderExp(String gender) {
        this.gender = gender;
    }

    @Override
    public String getMessage() {
        return String.format("Некорректно введены данные! Пол может содержать только буквы 'f' или 'm'\n");
    }
}
