package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static Server.ServerFunctions.*;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(4004);
            System.out.println("Server are started!");
            clientSocket = server.accept();

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            while (true) {
                String message = in.readLine();
                String msg[];
                msg = message.split(";");
                switch (msg[0]) {
                    case "authorization":
                        out.write(authorization(msg[1], msg[2], msg[3]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addBake":
                        out.write(addBake(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showBakes":
                        List<String> list = showBakes();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "deleteBake":
                        out.write(deleteBake(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addExpert":
                        out.write(addExpert(msg[1], msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showExperts":
                        list = showExperts();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "findExpert":
                        out.write(findExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "deleteExpert":
                        out.write(deleteExpert(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "editExpert":
                        out.write(editExpert(msg[1],msg[2]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "addGoal":
                        out.write(addGoal(msg[1],msg[2]) +  System.lineSeparator());
                        out.flush();
                        break;
                    case "deleteGoal":
                        out.write(deleteGoal(msg[1]) +  System.lineSeparator());
                        out.flush();
                        break;
                    case "showGoals":
                        list = showGoals();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "estimateGoals":
                        list = showGoals();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        out.write(editExpertMark(msg[1], in.readLine()) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showResults":
                        out.write(calculateNumberExperts()+System.lineSeparator());
                        out.flush();
                        list = showGoals();
                        out.write((list.size()-1) + System.lineSeparator());
                        out.flush();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        list = showExperts();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "makeDecision":
                        out.write(solveTask(msg[1]) + System.lineSeparator());
                        out.flush();
                        break;
                    case "showDecisions":
                        list = showDecisions();
                        for (String i : list) {
                            out.write(i + System.lineSeparator());
                            out.flush();
                        }
                        break;
                    case "exit":
                        clientSocket.close();
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.close();
            clientSocket.close();
        }
    }
}
