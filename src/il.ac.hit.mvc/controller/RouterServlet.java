package il.ac.hit.mvc.controller;

import il.ac.hit.mvc.model.DAOException;
import il.ac.hit.mvc.utils.Settings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "RouterServlet", urlPatterns = {"/controller/*"})

public class RouterServlet extends HttpServlet {

    public  RouterServlet() {
        super();
    }

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        response.setContentType("text/html");
        String URI = request.getRequestURI();
        String[] splitedURL=URI.split("/");

        PrintWriter out = response.getWriter(); // delete it at the end..used for debugging

        /* the URL: http://localhost:8081/MyDiary/controller/user/action
           0:
           1: MyDiary
           2: controller
           3: user
           4: action */
        String controller = splitedURL[3];
        String action = splitedURL[4];
        out.println(URI+"</br>"); //remove later

        //building the full qualified name of the controller in order to use reflection and call the right controller
        String temp = controller + "Controller";
        String controllerClassName = Settings.CONTROLLERS_PACKAGE + "." + temp.substring(0, 1).toUpperCase() + temp.substring(1);

              out.print("<br/>0... "+splitedURL[0]);
              out.print("<br/>1... "+splitedURL[1]);
			  out.print("<br/>2... "+splitedURL[2]);
			  out.print("<br/>3... "+splitedURL[3]);
			  out.print("<br/>4... "+splitedURL[4]);
        out.println("controller full qualified name: " + controllerClassName + "</br>"); //remove later

        // instantiating the controller class and calling
        // the action method on the controller object
        Class controllerClass = Class.forName(controllerClassName);
        Method controllerMethod = controllerClass.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
        controllerMethod.invoke(controllerClass.newInstance(), request ,response);
        out.println("</br>" + controllerClassName); //remove later

        // creating a RequestDispatcher object that points at the JSP document
        // which is view of our action
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/" + action + ".jsp");
        dispatcher.include(request,response);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (MVCException e) {
            //TODO needs to direct to an exception page
            e.printStackTrace();
        }
        //catch (DAOException e) {
        //    //TODO needs to direct to an exception page
        //    //problem with the data base
        //    e.printStackTrace();
        //}
         catch (InstantiationException e) {
            e.printStackTrace();
        }
     }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}