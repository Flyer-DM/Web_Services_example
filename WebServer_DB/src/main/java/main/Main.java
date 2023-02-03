package main;


import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import servlets.SignInServlet;
import servlets.SignUpServlet;


public class Main {
    public static void main(String[] args) throws Exception {


        startServer();

    }

    private static void exampleOfLesson() {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("tully2");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }

        dbService.close();

    }

    private static void startServer() throws Exception {

        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(SignUpServlet.class, "/signup");
        handler.addServletWithMapping(SignInServlet.class, "/signin");

        server.start();
        System.out.println("Server started");

        server.join();
    }
}