package il.ac.hit.mvc.controller;

    import il.ac.hit.mvc.utils.Settings;

    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.lang.reflect.InvocationTargetException;
    import java.lang.reflect.Method;


    @WebServlet(name = "RouterServlet", urlPatterns = {"/controller/*", "/site/*"})

    public class RouterServlet extends HttpServlet {

        public  RouterServlet() {
            super();
        }

         protected void doGet(HttpServletRequest request, HttpServletResponse response) {
             try {
                 String requestURI = request.getRequestURI();

                 if (isJSPPageRequest(requestURI)) {
                     serveJSPPageFromURL(request, response);
                     return;
                 }

                 String[] uriParts = requestURI.split("/");
                 String filename = uriParts[uriParts.length - 1];
                 if (isStaticFile(filename)) {
                     RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/" + filename);
                     dispatcher.forward(request, response);
                     return;
                 }

                 invokeAPIAction(request, response);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

        private boolean isStaticFile(String filename) {
            String[] staticFileExtensions = {".jpeg", ".jpg", ".css", ".js", ".png"};
            System.out.println("file name: " + filename);
            for (String extension : staticFileExtensions) {
                if (filename.endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }

        private void invokeAPIAction(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            String requestURI = request.getRequestURI();
            String controllerClassName = createControllerNameFromRoute(requestURI);
            String action = getActionFromURL(requestURI);

            Class controllerClass = Class.forName(controllerClassName);
            Method controllerMethod = controllerClass.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            controllerMethod.invoke(controllerClass.getDeclaredConstructor().newInstance(), request ,response); //was controllerClass.newInstance()
        }

        private void serveJSPPageFromURL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            String view = getJSPPageFromURL(request.getRequestURI());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/" + view.toLowerCase() + ".jsp");
            dispatcher.forward(request, response);
        }

        private boolean isJSPPageRequest(String requestUrl) {
            return getJSPPageFromURL(requestUrl).equals("site");
        }

        private String getJSPPageFromURL(String requestUrl) {
            String[] URLParts = requestUrl.split("/");
            return URLParts[3];
        }

        private String getActionFromURL(String requestUrl) {
            String[] URLParts = requestUrl.split("/");
            return URLParts[4];
        }

        private String createControllerNameFromRoute(String requestUrl) {
            String[] URLParts = requestUrl.split("/");
            String controllerPrefix = URLParts[3];
            String temp = controllerPrefix + "Controller";
            return Settings.CONTROLLERS_PACKAGE + "." + temp.substring(0, 1).toUpperCase() + temp.substring(1);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }
    }