/**
 *
 * Тест на знание таблицы умножения
 * Multiplication Table Test
 * @author Aleksandr Polochkin
 * @version 1.0.1
 *
 *******************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MultiplyTableTest {

    static byte multiplier = 0;

    static byte answerNumber;
    static byte numberOfQuestion = 10;
    static byte counterOfQuestion;
    static byte counterOfError;
    static byte itemMenu;

    static ArrayList<Integer> numbers = new ArrayList<>();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static String[] declensionWordOfExample = {"примеров", "пример", "примера", "примера", "примера", "примеров", "примеров", "примеров", "примеров", "примеров"};
    static String[] declensionWordOfError = {"ошибок", "ошибка", "ошибки", "ошибки", "ошибки", "ошибок", "ошибок", "ошибок", "ошибок", "ошибок"};

    public static void main(String[] args) throws IOException {
        while (true) {
            cls();
            prln();
            start();
            prln();
            prln("Решим " + numberOfQuestion + " " + declensionWordOfExample[numberOfQuestion % 10] + " умножения на " + ((multiplier == 0) ? "случайное число" : multiplier));
            prln("Let's solve " + numberOfQuestion +" multiplication exercises by " + ((multiplier == 0) ? "a random number" : multiplier));
            prln();

            itemMenu = menu();

            switch (itemMenu) {
                case 1:
                    game();
                    break;
                case 2:
                    settings();
                    break;
                default:
                    System.exit(0);
            }
        }

    }

    static void start() {
        for (int i = 1; i < 11; i++) numbers.add(i);

        prln("**********************************");
        prln("*   Проверка таблицы умножения   *");
        prln("*   Multiplication Table Test    *");
        prln("**********************************");
    }

    static byte menu() {
        byte itemMenu;

        prln("1. Начало игры / Start game");
        prln("2. Настройки игры / Settings");
        prln("3. Выход / Exit");

        do {
            prln();
            itemMenu = getByteAnswer("Выберите пункт / Select an item (1-3): ");
        } while (itemMenu < 1 || itemMenu > 3);

        return itemMenu;
    }

    static void settings() {
        cls();
        prln();
        do {
            numberOfQuestion = getByteAnswer("Укажите количество примеров в раунде\nSelect the number of exercises (3-20): ");
        } while (numberOfQuestion < 3 || numberOfQuestion > 20);
        prln();
        do {
            multiplier = getByteAnswer("Укажите число на которое будем умножать (0-10, 0 - случайное число)\nChoose a multiplier (0-10, 0 - random multiplier): ");
        } while (multiplier < 0 || multiplier > 10);
        prln();
    }

    static void game() throws IOException {
        String answerString;
        do {
            counterOfQuestion = 0;
            counterOfError = 0;
            Collections.shuffle(numbers);

            cls();
            prln();
            prln("Решим " + numberOfQuestion + " " + declensionWordOfExample[numberOfQuestion % 10] + " умножения на " + ((multiplier == 0) ? "случайное число" : multiplier));
            prln("Let's solve " + numberOfQuestion +" multiplication exercises by " + ((multiplier == 0) ? "a random number" : multiplier));
            prln();

            mainLoop:
            while (true) {

                for (int i = 0; i < 10; i++) {
                    int n = ((multiplier == 0) ? (int) (Math.random() * 10) + 1 : multiplier);
                    if (Math.random() > 0.5) {
                        answerNumber = getByteAnswer(n + " * " + numbers.get(i) + " = ");
                    } else {
                        answerNumber = getByteAnswer(numbers.get(i) + " * " + n + " = ");
                    }

                    if (n * numbers.get(i) == answerNumber) {
                        prln("Правильно. Молодец! / Correctly. Well done!");
                        prln();
                    } else {
                        prln("Ошибка :( Правильный ответ " + n * numbers.get(i) + " / Error :( Correct answer: " + n * numbers.get(i));
                        prln();
                        counterOfError++;
                    }
                    counterOfQuestion++;
                    if (counterOfQuestion == numberOfQuestion) break mainLoop;
                }
            }

            prln("Из " + numberOfQuestion + " примеров " + counterOfError + " " + declensionWordOfError[counterOfError % 10]);
            prln("Solved " + numberOfQuestion + " exercises, " + counterOfError + " mistakes ");
            if (counterOfError == 0) {
                prln("Отлично. Молодчина!!! / Excellent. Well done!!!");
            } else {
                if (counterOfError < 3) {
                    prln("Хорошо. Можешь лучше ;) / Good. You can do better ;)");
                } else {
                    prln("Ты старался. Подучи ещё 8( / You tried. Learn more 8(");
                }
            }
            prln();
            pr("Ещё разок? / One more time? (Yes/No): ");
            answerString = reader.readLine();
        } while (answerString.equalsIgnoreCase("yes")
                || answerString.equalsIgnoreCase("y"));
    }

    static void pr(String str) {
        System.out.print(str);
    }

    static void prln() {
        System.out.println();
    }

    static void prln(String str) {
        System.out.println(str);
    }

    static void cls() {
        /// Реализовать для платформ отличных от Windows
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            prln(E.getMessage());
        }
    }

    static byte getByteAnswer(String str) {
        byte answer = -1;
        do {
            pr(str);

            try {
                String r = reader.readLine();
                answer = Byte.parseByte(r);
            } catch (NumberFormatException | IOException e) {
                prln("Набирайте только цифры! / Enter only numbers!");
            }
        } while (answer == -1);
        return answer;
    }
}