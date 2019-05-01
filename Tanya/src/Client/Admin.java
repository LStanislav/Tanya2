package Client;

import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

import static Client.Client.*;
import static Client.Client.reader;
import static Client.ClientFunctions.decoding;
import static Client.ClientFunctions.encrypt;
import static java.lang.Math.floorDiv;
import static java.lang.Math.min;

public class Admin extends BaseUser {

    public static void showResults() {

        StringBuilder msg = new StringBuilder("showResults;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            int numberExperts = Integer.parseInt(in.readLine());
            int numberGoals = Integer.parseInt(in.readLine());
            System.out.println(numberExperts);
            System.out.println(numberGoals);
            String goals[] = new String[numberGoals + 1];
            for (int i = 0; i < numberGoals + 1; i++) {
                goals[i] = in.readLine();
                System.out.println(goals[i]);
            }
            int marks[][] = new int[numberGoals][numberGoals];
            int expert_goals[][] = new int[numberExperts][numberGoals];
            String buf = in.readLine();
            int k = 0;
            int sum = 0;
            String expertsName[] = new String[numberExperts];
            int max = 0;
            while (!buf.equals(";;")) {
                String part[] = buf.split(";");
                String ratings[] = part[2].split(" ");
                expertsName[k] = decoding(part[0]);
                max = Math.max(max,part[0].length());
                System.out.println("Оценки эксперта " + (k + 1) + ": ");
                for (int i = 0; i < numberGoals; i++) {
                    for (int j = 0; j < numberGoals; j++) {
                        if (i * numberGoals + j < ratings.length) {
                            marks[i][j] = Integer.parseInt(ratings[i * numberGoals + j]);
                        } else {
                            marks[i][j] = 0;
                        }
                        System.out.printf("%3d ", marks[i][j]);
                        expert_goals[k][i] += marks[i][j];
                        sum += marks[i][j];
                    }
                    System.out.println();
                }
                System.out.println();
                k++;
                buf = in.readLine();
            }
            System.out.println();
            int sumGoals[] = new int[numberGoals];
            System.out.println("Таблица \"Зкперты - Цели:\" ");
            for (int i = 0; i < numberExperts; i++) {
                System.out.printf("%" + max + "s - ", expertsName[i]);
                for (int j = 0; j < numberGoals; j++) {
                    System.out.printf("%4d", expert_goals[i][j]);
                    sumGoals[j] += expert_goals[i][j];
                }
                System.out.println();
            }
            System.out.println("Суммарная оценка целей:");
            System.out.printf("%" + (max+3) + "s", "");
            for (int i = 0; i < numberGoals; i++) {
                System.out.printf("%4d", sumGoals[i]);
            }
            System.out.println();
            System.out.println("Итоговые веса каждой цели: ");
            ArrayList<Pair<Double, String>> ans = new ArrayList<>();
            for (int i = 0; i < numberGoals; i++) {
                Double x = (double) sumGoals[i] / (double) sum;
                String part[] = goals[i].split(";");
                ans.add(new Pair(x, part[0]));
                System.out.printf("%3.3f - %s\n", x, part[0]);
            }

            Comparator <Pair<Double,String>> comparator = new Comparator<Pair<Double, String>>() {
                @Override
                public int compare(Pair<Double, String> o1, Pair<Double, String> o2) {
                    if (o1.getKey()>o2.getKey())
                        return -1;
                    else return 1;
                }
            };

            ans.sort(comparator);
            System.out.println();
            System.out.println("Отсортированные веса каждой цели: ");
            for(int i=0; i< numberGoals; i++){
                String str = ans.get(i).getValue();
                System.out.printf("%3.3f - %s\n", ans.get(i).getKey(), str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addBake() {
        String title, description;
        System.out.println("Введите название:");
        title = reader.nextLine();

        System.out.println("Введите описание выпечки:");
        description = reader.nextLine();

        try {
            StringBuilder msg = new StringBuilder("addBake;");
            msg.append(title + ";");
            msg.append(description + ";");
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBake() {

        StringBuilder msg = new StringBuilder("deleteBake;");
        System.out.println("Введите название, удаляемой выпечки:");
        String tit = reader.nextLine();
        msg.append(tit + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showBakes() {
        StringBuilder msg = new StringBuilder("showBakes;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";");
                System.out.println(part[0] + "  |  " + part[1]);
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addExpert() {
        String log, pass1, pass2;

        System.out.println("Регистрация");
        System.out.println("Введите новый логин: ");
        log = reader.nextLine();

        System.out.println("Введите новый пароль: ");
        pass1 = reader.nextLine();

        System.out.println("Подтвердите пароль: ");
        pass2 = reader.nextLine();
        if (!pass1.equals(pass2)) {
            System.out.println("Пароли не совпадают!");
            return;
        }

        try {
            StringBuilder msg = new StringBuilder("addExpert;");
            msg.append(encrypt(log) + ";");
            msg.append(encrypt(pass1) + ";");
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showExpert() {
        StringBuilder msg = new StringBuilder("showExperts;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";");
                System.out.print(decoding(part[0]) + " | (");

                String ratings[] = part[2].split(" ");
                for (int i = 0; i < ratings.length - 1; i++) {
                    System.out.print(ratings[i] + ",");
                }
                System.out.println(ratings[ratings.length - 1] + ")");
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findExpert() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("findExpert;");
        System.out.println("Введите номер(логин) экперта: ");
        String log = reader.nextLine();
        msg.append(encrypt(log) + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            if (!buf.equals("")) {
                String part[] = buf.split(";");
                System.out.print(decoding(part[0]) + " | (");
                String ratings[] = part[2].split(" ");

                for (int i = 0; i < ratings.length - 1; i++) {
                    System.out.print(ratings[i] + ",");
                }
                System.out.println(ratings[ratings.length - 1] + ")");
            } else {
                System.out.println("Нет пользователя с таким номером!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteExpert() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("deleteExpert;");
        System.out.println("Введите логин, удаляемого экперта:");
        String log = reader.nextLine();
        msg.append(encrypt(log) + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editExpert() {

        StringBuilder msg = new StringBuilder("editExpert;");
        System.out.println("Введите логин, редактируемого экперта:");
        String log = reader.nextLine();

        String pass;

        System.out.println("Введите новый пароль эксперта: ");
        pass = reader.nextLine();

        msg.append(encrypt(log) + ";");
        msg.append(encrypt(pass) + ";");
        //connector.connect();
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addGoal() {
//        connector.connect();
        StringBuilder msg = new StringBuilder("addGoal;");
        String title;
        String description;
        System.out.println("Введите заголовок цели: ");
        title = reader.nextLine();
        System.out.println("Введите описание цели: ");
        description = reader.nextLine();
        msg.append(title + ";");
        msg.append(description + ";");

        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGoal() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("deleteGoal;");
        System.out.println("Введите заголовок, удаляемой цели: ");
        String title = reader.nextLine();
        msg.append(title + ";");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            System.out.println(in.readLine() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showGoals() {
        //connector.connect();
        StringBuilder msg = new StringBuilder("showGoals;");
        try {
            out.write(msg + System.lineSeparator());
            out.flush();
            String buf = in.readLine();
            while (!buf.equals(";;")) {
                String part[] = buf.split(";", 5);
                System.out.println(part[0] + "  |  " + part[1]);
                buf = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
