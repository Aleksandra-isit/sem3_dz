package Model;

public class PhoneExc extends Exception{
    String phone;

    public PhoneExc(String phone) {
        this.phone = phone;
    }

    @Override
    public String getMessage() {
        return String.format("Некорректные данные! Телефонный номер должен состоять из 10 цифр без разделителей\n");
    }
}
