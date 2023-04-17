package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Model.ParsingExc;
import Model.CheckData;
import View.IIO;

public class Controll<V extends IIO> {
    private CheckData checker;
    private V iio;

    public Controll(V v){
        iio = v;
    }

    public void start(){
        boolean working = true;
        do {
            String input = iio.input(
                    "Введите данные через пробел (Фамилию Имя Отчество ДатуРождения НомерТелефона Пол), или Exit для прекращения программы:\n");
            if (input.equals("Exit")) {
                working = false;
                break;
            } else {
                String[] splitedInput = input.replaceAll("\\s+", " ").split(" ");
                int inputDataCount = checkInputDataCount(splitedInput.length);
                if (inputDataCount == -1) {
                    iio.output("Слишком мало данных на вводе (должно быть " + CheckData.dataCount
                            + " разделённых пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
                } else if (inputDataCount == 0) {
                    iio.output("Слишком много данных на вводе (должно быть " + CheckData.dataCount
                            + " разделённых пробелом ' ': Фамилия Имя Отчество НомерТелефона ДатаРождения Пол)\n");
                } else {
                    try {
                        checker = new CheckData();
                        checker.CheckData(splitedInput);
                        writePersonData(checker);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParsingExc e) {
                        iio.output(e.getMessage());
                    }
                }
            }
        } while (working);
    }
    private int checkInputDataCount(int inputDataCount) {
        if (inputDataCount < CheckData.dataCount) {
            return -1;
        } else if (inputDataCount > CheckData.dataCount) {
            return 0;
        } else {
            return inputDataCount;
        }
    }

    //создаём или подключаемся к фаилу по фамилии и дописываем туда новые данные
    private void writePersonData(CheckData data) throws IOException {
        File filepath = new File(data.getLastName());
        try (FileWriter fw = new FileWriter(filepath, true)) {
            fw.append(data.toString() + "\n");
        } catch (IOException e) {
            throw e;
        }
    }
}
