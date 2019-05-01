package Server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerFunctions {

    public static String authorization(String type, String login, String password) {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/" + type + ".txt")));
            String log;
            String pass;
            System.out.println(login + " " + password);
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                if (type.equals("expert")) {
                    buffer.readLine();
                }
                System.out.println(log + " " + pass);
                if ((pass.equals(password)) && (log.equals(login))) {
                    return "Yes";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(new String(buf));
        return "No";
    }

    public static String addBake(String title, String description) {
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/bake.txt")));
            String tit;
            while ((tit = buffer.readLine()) != null) {
                buffer.readLine();
                if (tit.equals(title)) {
                    ans = "Есть булочка с таким названием!";
                    break;
                }
            }
            if (ans.equals("")) {
                ans = "Выпечка успешно добавлена!";
                File file = new File("D://JavaProject/Tanya/enter/bake.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/Tanya/enter/bake.txt", true);
                System.out.println(title + " " + description);
                fileWriter.write(title + "\n");
                fileWriter.write(description + "\n");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showBakes() {
        File file = new File("D://JavaProject/Tanya/enter/bake.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        ArrayList<String> res = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/bake.txt")));
            String tit;
            String descr;
            while ((tit = bufferedReader.readLine()) != null) {
                descr = bufferedReader.readLine();
                res.add(tit + ";" + descr + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

    public static String deleteBake(String title) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/bake.txt")));
            String tit;
            String desr;
            while ((tit = bufferedReader.readLine()) != null) {
                bufferedReader.readLine();
                if (tit.equals(title)) {
                    ans = "Выпечка успешно удалена!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/bake.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((tit = bufferedReader.readLine()) != null) {
                    desr = bufferedReader.readLine();
                    if (!tit.equals(title)) {
                        fileWriter.write(tit + "\n");
                        fileWriter.write(desr + "\n");
                    }
                }
                fileWriter.close();

                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/bake.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/bake.txt", true);
                while ((tit = bufferedReader.readLine()) != null) {
                    desr = bufferedReader.readLine();
                    fileWriter.write(tit + "\n");
                    fileWriter.write(desr + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет выпечки с таким названием!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String addExpert(String login, String password) {
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;
            while ((log = buffer.readLine()) != null) {
                pass = buffer.readLine();
                ratings = buffer.readLine();
                if (log.equals(login)) {
                    ans = "Есть эксперт с таким логином!";
                    break;
                }
            }
            if (ans.equals("")) {
                ans = "Эксперт успешно добавлен!";
                File file = new File("D://JavaProject/Tanya/enter/expert.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
                System.out.println(login + " " + password);
                fileWriter.write(login + "\n");
                fileWriter.write(password + "\n");
                fileWriter.write("0 0 0" + "\n");
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showExperts() {
        File file = new File("D://JavaProject/Tanya/enter/expert.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());

        ArrayList<String> res = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;

            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                res.add(log + ";" + pass + ";" + ratings + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

    public static String findExpert(String login) {
        File file = new File("D://JavaProject/Tanya/enter/expert.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        StringBuilder res = new StringBuilder("");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;

            while ((log = bufferedReader.readLine()) != null) {
                pass = bufferedReader.readLine();
                ratings = bufferedReader.readLine();
                if (log.equals(login)) {
                    res.append(log + ";" + pass + ";" + ratings + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public static String deleteExpert(String login) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;
            while ((log = bufferedReader.readLine()) != null) {
                bufferedReader.readLine();
                bufferedReader.readLine();
                if (log.equals(login)) {
                    ans = "Эксперт успешно удален!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();
                    if (!log.equals(login)) {
                        fileWriter.write(log + "\n");
                        fileWriter.write(pass + "\n");
                        fileWriter.write(ratings + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.write(ratings + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет эксперта с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String editExpert(String login, String password) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            String log;
            String pass;
            String ratings;
            while ((log = bufferedReader.readLine()) != null) {
                bufferedReader.readLine();
                bufferedReader.readLine();
                if (log.equals(login)) {
                    ans = "Информация об эксперте успешно отредактирована!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    if (!log.equals(login)) {
                        fileWriter.write(pass + "\n");
                    } else {
                        fileWriter.write(password + "\n");
                    }
                    fileWriter.write(ratings + "\n");

                }
                fileWriter.close();

                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    ratings = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.write(ratings + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет эксперта с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String addGoal(String title, String description) {
        String ans = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            String tit;
            String descr;
            while ((tit = buffer.readLine()) != null) {
                descr = buffer.readLine();
                System.out.println(tit + " " + descr);
                if (tit.equals(title)) {
                    ans = "Есть цель с таким заголовком!";
                    break;
                }
            }
            buffer.close();

            if (ans.equals("")) {
                ans = "Цель успешно добавлена!";
                File file = new File("D://JavaProject/Tanya/enter/goal.txt");
                System.out.println(file.exists() + " " + file.getAbsolutePath());

                FileWriter fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt", true);
                System.out.println(title + " " + description);
                fileWriter.write(title + "\n");
                fileWriter.write(description + "\n");
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String deleteGoal(String title) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            String tit;
            String descr;
            while ((tit = bufferedReader.readLine()) != null) {
                descr = bufferedReader.readLine();
                if (tit.equals(title)) {
                    ans = "Цель успешно удалена!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((tit = bufferedReader.readLine()) != null) {
                    descr = bufferedReader.readLine();
                    if (!tit.equals(title)) {
                        fileWriter.write(tit + "\n");
                        fileWriter.write(descr + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt", true);
                while ((tit = bufferedReader.readLine()) != null) {
                    descr = bufferedReader.readLine();
                    fileWriter.write(tit + "\n");
                    fileWriter.write(descr + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет цели с таким заголовком!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showGoals() {
        File file = new File("D://JavaProject/Tanya/enter/goal.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        ArrayList<String> res = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            String tit;
            String descr;
            while ((tit = bufferedReader.readLine()) != null) {
                descr = bufferedReader.readLine();
                res.add(tit + ";" + descr + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

    public static String editExpertMark(String login, String ratings) {
        String ans = "Оценки успешно занесены!";
        try {
            BufferedReader bufferedReader;
            ratings = ratings.trim();
            FileWriter fileWriter;
            String log;
            String pass;
            String rat;
            System.out.println(login + " " + ratings);
            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    rat = bufferedReader.readLine();
                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    if (!log.equals(login)) {
                        fileWriter.write(rat + "\n");
                    } else {
                        fileWriter.write(ratings + "\n");
                    }
                }
                fileWriter.close();

                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/expert.txt", true);
                while ((log = bufferedReader.readLine()) != null) {
                    pass = bufferedReader.readLine();
                    rat = bufferedReader.readLine();

                    fileWriter.write(log + "\n");
                    fileWriter.write(pass + "\n");
                    fileWriter.write(rat + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();
            } else {
                ans = "Нет эксперта с таким логином!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static String calculateNumberExperts() {
        Integer result = 0;
        String buf;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/expert.txt")));
            while ((buf = bufferedReader.readLine()) != null) {
                result++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        result /= 3;
        System.out.println(result);
        return result.toString();
    }

    public static String solveTask(String title) {
        String ans = "";
        try {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
            String tit;
            String descr, description = "";
            while ((tit = bufferedReader.readLine()) != null) {
                descr = bufferedReader.readLine();
                if (tit.equals(title)) {
                    description = descr;
                    ans = "Цель отмечена решенной!";
                }
            }

            if (!ans.equals("")) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/goal.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt", true);

                while ((tit = bufferedReader.readLine()) != null) {
                    descr = bufferedReader.readLine();
                    if (!tit.equals(title)) {
                        fileWriter.write(tit + "\n");
                        fileWriter.write(descr + "\n");
                    }
                }
                fileWriter.close();
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt");
                fileWriter.write("");
                fileWriter.close();

                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/buffer.txt")));
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/goal.txt", true);
                while ((tit = bufferedReader.readLine()) != null) {
                    descr = bufferedReader.readLine();
                    fileWriter.write(tit + "\n");
                    fileWriter.write(descr + "\n");
                    fileWriter.flush();
                }
                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/buffer.txt");
                fileWriter.write("");
                fileWriter.close();

                fileWriter = new FileWriter("D://JavaProject/Tanya/enter/solved_goals.txt", true);
                fileWriter.write(title + "\n");
                fileWriter.write(description + "\n");
                fileWriter.flush();
                fileWriter.close();

            } else {
                ans = "Нет цели с таким заголовком!";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<String> showDecisions() {
        File file = new File("D://JavaProject/Tanya/enter/solved_goals.txt");
        System.out.println(file.exists() + " " + file.getAbsolutePath());
        ArrayList<String> res = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D://JavaProject/Tanya/enter/solved_goals.txt")));
            String tit;
            String descr;
            while ((tit = bufferedReader.readLine()) != null) {
                descr = bufferedReader.readLine();
                res.add(tit + ";" + descr + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.add(";;");
        return res;
    }

}
