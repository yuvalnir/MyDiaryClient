package il.ac.hit.mvc.controller;

import il.ac.hit.mvc.utils.Settings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name="RouterServlet", urlPatterns = {"/controller/*"})

public class RouterServlet extends HttpServlet {

    public  RouterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String URI = request.getRequestURI();


        String[] splitedURL=URI.split("/");
        //try {

            // delete it at the end..used for debugging
            PrintWriter out = response.getWriter();
/*

the URL: http://localhost:8081/MyDiary/controller/user/action
the outcome:

 0:
1: MyDiary
2: controller
3: user
4: action

* */
            String controller = splitedURL[3];
            String action = splitedURL[4];

            out.println(URI+"</br>");



            out.flush();

            //getting a full qualified inorder to use reflection and call the right controller
            String temp = (controller + "Controller");
            String controllerClassName = Settings.CONTROLLERS_PACKAGE + "." + temp.substring(0, 1).toUpperCase() + temp.substring(1);

            out.println(controllerClassName+"</br>");

/*        Class controllerClass = Class.forName(controllerClassName);

        Method controllerMethod = controllerClass.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
        controllerMethod.invoke(controllerClass,request,response);
        out.println("</br>"+controllerClassName);
        out.flush();


        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (MVCException e) {
            //TODO needs to direct to an exception page
            e.printStackTrace();
        }
        catch (DAOException e) {
            //TODO needs to direct to an exception page
            //problem with the data base
            e.printStackTrace();
        }
        }*/
        }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
