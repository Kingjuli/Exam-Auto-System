/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maseno.online.examination.system.client.utility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import maseno.online.examination.system.server.bean.Question;
import maseno.online.examination.system.server.bean.Student;

/**
 *
 * @author Kasun Kodagoda
 */
public class Client {

    private static int PORT = 6789;
    private static String HOST;

    public static Student studentLoginRequest(String username, String password) {
        try {
            // Create the socket
            Socket clientSocket = new Socket(HOST, PORT);
            // Create the input & output streams to the server
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            // Create the student object
            Student newStd = new Student();
            newStd.setUserName(username);
            newStd.setPassword(password);
            newStd.setLogged(false);

            // Create the carrier object
            Carrier cObject = new Carrier();
            cObject.setType(CarrierType.LOGIN_REQ);
            cObject.setLoginReq(newStd);

            /* Send the Carrier Object to the server */
            outToServer.writeObject(cObject);

            /* Retrive the Carrier Object from server */
            Carrier replyFromServer = null;
            replyFromServer = (Carrier) inFromServer.readObject();
            newStd = replyFromServer.getLoginReq();

            if (newStd.isLogged()) {
                clientSocket.close();
                return newStd;
            } else {
                clientSocket.close();
                return null;
            }

        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            System.err.println("Localized: " + e.getLocalizedMessage());
            System.err.println("Stack Trace: " + e.getStackTrace());
            System.err.println("To String: " + e.toString());
            return null;
        }
    }

    public static LinkedList<Question> requestQuestionList() {
        try {
            // Create the socket
            Socket clientSocket = new Socket(HOST, PORT);
            // Create the input & output streams to the server
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            // Create the carrier object
            Carrier cObject = new Carrier();
            cObject.setType(CarrierType.QUESTION_LIST);

            /* Send the Carrier Object to the server */
            outToServer.writeObject(cObject);

            /* Retrive the Carrier Object from server */
            Carrier replyFromServer = null;
            replyFromServer = (Carrier) inFromServer.readObject();
            LinkedList<Question> questionList = replyFromServer.getQuestionList();

            if (questionList != null) {
                clientSocket.close();
                return questionList;
            } else {
                clientSocket.close();
                return null;
            }

        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            System.err.println("Localized: " + e.getLocalizedMessage());
            System.err.println("Stack Trace: " + e.getStackTrace());
            System.err.println("To String: " + e.toString());
            return null;
        }
    }

    public static Result sendAnswerstoServer(LinkedList<Answer> answers) {
        try {
            // Create the socket
            Socket clientSocket = new Socket(HOST, PORT);
            // Create the input & output streams to the server
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            // Create the carrier object
            Carrier cObject = new Carrier();
            cObject.setType(CarrierType.ANSWERS);
            cObject.setAnswers(answers);

            /* Send the Carrier Object to the server */
            outToServer.writeObject(cObject);

            /* Retrive the Carrier Object from server */
            Carrier replyFromServer = null;
            replyFromServer = (Carrier) inFromServer.readObject();
            Result resultFromServer = replyFromServer.getResults();

            /* Do something with the result object */
            return resultFromServer;

        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            System.err.println("Localized: " + e.getLocalizedMessage());
            System.err.println("Stack Trace: " + e.getStackTrace());
            System.err.println("To String: " + e.toString());
            return null;
        }
    }

    public static Result requestResults(int stdId) {
        try {
            // Create the socket
            Socket clientSocket = new Socket(HOST, PORT);
            // Create the input & output streams to the server
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            // Create the carrier object
            Carrier cObject = new Carrier();
            cObject.setType(CarrierType.RESULT);
            cObject.setStudentId(stdId);

            /* Send the Carrier Object to the server */
            outToServer.writeObject(cObject);

            /* Retrive the Carrier Object from server */
            Carrier replyFromServer = null;
            replyFromServer = (Carrier) inFromServer.readObject();
            Result resultFromServer = replyFromServer.getResults();

            /* Do something with the result object */
            return resultFromServer;

        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            System.err.println("Localized: " + e.getLocalizedMessage());
            System.err.println("Stack Trace: " + e.getStackTrace());
            System.err.println("To String: " + e.toString());
            return null;
        }

    }

    public static String getHOST() {
        if (HOST != null) {
            return HOST;
        } else {
            return "localhost";
        }
    }

    public static void setHOST(String aHOST) {
        HOST = aHOST;
    }

    public static boolean server_exists(String host, int port) {
        Socket sockets;
        try {
            sockets = new Socket(host, port);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            sockets.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
