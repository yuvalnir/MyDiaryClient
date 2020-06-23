package il.ac.hit.mvc.utils;

import java.sql.Date;
import java.sql.Time;

import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

import il.ac.hit.mvc.model.EventDAOSQHibernate;
import il.ac.hit.mvc.model.IEventDAO;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class TestIt {

    public static void main(String[] args) {

/*        //creating factory for getting sessions
        SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
        //creating a new session for adding products
*//*        Session session = factory.openSession();
        session.beginTransaction();
        Event p1 = new Event("vahababen@gmail.com","BBQ","Rehovot",16.00,18.00,2000400216,new Date(2020,6,19),"gonna be fun");
        session.save(p1);

        session.getTransaction().commit();
        session.close();*//*

        //creating a new session for getting all products
        Session anotherSession = factory.openSession();
        anotherSession.beginTransaction();
        List Events = anotherSession.createQuery("from Event ").list();
        System.out.println("There are " + Events.size() + " Events(s)");

        for (Object object:Events) {
            Event ev=(Event)object;


        }
        anotherSession.close();*/
        try {
            IEventDAO DAO=EventDAOSQHibernate.getInstance();
             User userBen= new User("vahababen@gmail.com","123");
             if(DAO.createNewUser(userBen)) {
                 System.out.println("\n new User added: " + userBen.toString());
             }
             Scanner scanner = new Scanner(System. in);
             scanner. nextLine();

            System.out.println("\n Validating The user: "+userBen.toString());
            if(DAO.userVerification(userBen))
            {
                System.out.println("\n user exists");
            }
            else
            {
                System.out.println("\n user DON'T exists");

            }
            System.out.println("inserting new event under the user");


            Event eventBen=new Event(
                    userBen.getEmail()
                    , 99L
                    ,"party"
                    ,"Tel Aviv"
                    ,Time.valueOf("23:00:00")
                    ,Time.valueOf("03:30:00")
                    , Date.valueOf("2020-6-19")
                    ,"gonna be great");


            System.out.println("\n Event: "+eventBen.toString());
            scanner.nextLine();

            System.out.println("\n inserting the event");
            DAO.insertEvent(eventBen);
            System.out.println("\n event in database");
            scanner.nextLine();
            System.out.println("\ngetting the event");
            System.out.println("\nEvent: "+DAO.getEvent(userBen,eventBen.getId()));
            scanner.nextLine();

            System.out.println("\n\nget events\n\n");
            List<Event> list = DAO.getEvents(userBen);
            for (Event event:list) {
                System.out.println("\nevent: "+event.toString());
            }
            scanner.nextLine();

            System.out.println("Deleting user");
            DAO.deleteUser(userBen);
            System.out.println("\nuser deleted");


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}


