package il.ac.hit.mvc.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import il.ac.hit.mvc.utils.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class UserController {

    public void Login(HttpServletRequest request, HttpServletResponse response, String data) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.include(request, response);
    }

    public void Logout(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void Home(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void AddEvent(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void Graph(HttpServletRequest request, HttpServletResponse response, String data) {

    }

    public void UserSettings(HttpServletRequest request, HttpServletResponse response, String data) {

    }
    public void UserVerification(HttpServletRequest request, HttpServletResponse response)
    {
 /*       {
            String string = "";
            try {
                User user=new User((String)request.getAttribute("userEmail"),(String) request.getAttribute("password"));
                //
            } catch (IOException e) {
                e.printStackTrace();
            }
*/
        /*                String email="vahababen@gmail.com";
                String password="123";
                String tempRetString="";*//*

                JsonObject jsonObject=new JsonObject();

                jsonObject.add("email", JsonParser.parseString(email) );
                jsonObject.add("password", JsonParser.parseString(password) );

                System.out.println("our json: "+ jsonObject);

                try {
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setConnectTimeout(50000);
                    connection.setReadTimeout(50000);
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(jsonObject.toString());
                    out.close();

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String nextLine;
                    while ((nextLine = br.readLine())!=null) {
                        tempRetString+=nextLine;
                    }
                    System.out.println("\nMyDiary REST Service Invoked Successfully..\n output:"+ tempRetString+"\n");

                    br.close();

                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("\nError while calling MyDiary REST Service: "+tempRetString);
                }

                } catch (Exception e) {
                    e.printStackTrace();
                }*/
    }
}