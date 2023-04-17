package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CheckData {
    public static int dataCount = 6;

    private String firstName;
    private String lastName;
    private String patronymicName;
    private LocalDate birthDate;
    private Long phoneNumber;
    private Gender gender;

    public void CheckData(String[] splitedString) throws ParsingExc {
        if (splitedString == null) {
            throw new NullPointerException("Нет данных");
        }
        StringBuilder errors = new StringBuilder();
        for (String str: splitedString) {
            if (Character.isLetter(str.charAt(0))) {
                if (str.length() == 1) {
                    if (this.gender == null) {
                        try {
                            this.gender = checkGender(str);
                        } catch (GenderExp e) {
                            errors.append(e.getMessage());
                        }
                    } else {
                        errors.append("Гендер указан больше 1 раза\n");
                    }
                }else {
                    if (this.lastName == null) {
                        try {
                            this.lastName = checkFIO(str);
                        } catch (FIOExc e) {
                            errors.append(e.getMessage());
                        }
                    } else if (this.firstName == null) {
                        try {
                            this.firstName = checkFIO(str);
                        } catch (FIOExc e) {
                            errors.append(e.getMessage());
                        }
                    } else if (this.patronymicName == null) {
                        try {
                            this.patronymicName = checkFIO(str);
                        } catch (FIOExc e) {
                            errors.append(e.getMessage());
                        }
                    } else {
                        errors.append("Слишком много элементов распознаны как ФИО.\n");
                    }
                }
            } else {

                if (str.matches("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}")) {
                    if (this.birthDate == null) {
                        try {
                            this.birthDate = checkBirthDate(str);
                        } catch (BirthDataExc e) {
                            errors.append(e.getMessage());
                        }
                    } else {
                        errors.append("Дата рождения должна быть только одна, а обнаружены две: '"
                                + this.birthDate + "','" + str + "'\n");
                    }
                } else {
                    if (this.phoneNumber == null) {
                        try {
                            this.phoneNumber = checkPhoneNumber(str);
                        } catch (PhoneExc e) {
                            errors.append(e.getMessage());
                        }
                    } else {
                        errors.append("Должен быть только один телефонный норме, а не несколько: '"
                                + this.phoneNumber + "','" + str + "'\n");
                    }
                }

            }
        }
        if (!errors.isEmpty()) {
            throw new ParsingExc(errors.toString());
        }
    }

    public String getLastName() {
        return lastName;
    }
    private String checkFIO(String inputString) throws FIOExc {
        if (inputString.toLowerCase().matches("^[a-zа-яё]*$")) {
            return inputString;
        } else {
            throw new FIOExc(inputString);
        }
    }
    private long checkPhoneNumber(String inpuString) throws PhoneExc {
        if (inpuString.length() == 10) {
            try {
                return Long.parseLong(inpuString);
            } catch (NumberFormatException e) {
                throw new PhoneExc(inpuString);
            }
        } else {
            throw new PhoneExc(inpuString);
        }
    }
    private Gender checkGender(String inputString) throws GenderExp {
        try {
            return Gender.valueOf(inputString);
        } catch (IllegalArgumentException e) {
            throw new GenderExp(inputString);
        }
    }
    private LocalDate checkBirthDate(String inputString) throws BirthDataExc {
        try {
            return LocalDate.parse(inputString,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            throw new BirthDataExc(inputString);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(lastName).append(">")
                .append("<").append(firstName).append(">")
                .append("<").append(patronymicName).append(">")
                .append("<").append(birthDate.toString()).append(">")
                .append("<").append(phoneNumber).append(">")
                .append("<").append(gender).append(">");
        return sb.toString();
    }
}




































