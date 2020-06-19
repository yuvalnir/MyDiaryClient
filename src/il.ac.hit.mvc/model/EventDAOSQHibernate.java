package il.ac.hit.mvc.model;

import il.ac.hit.mvc.controller.MVCException;
import il.ac.hit.mvc.utils.Event;
import il.ac.hit.mvc.utils.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

public class EventDAOSQHibernate implements IEventDAO {
    private SessionFactory factory;
    private static EventDAOSQHibernate instance=null;
    private EventDAOSQHibernate() throws DAOException
    {
        factory= new AnnotationConfiguration().configure().buildSessionFactory();

    }
    public static EventDAOSQHibernate getInstance() throws DAOException {
        if(EventDAOSQHibernate.instance==null)
        {
            instance=new EventDAOSQHibernate();
        }
        return EventDAOSQHibernate.instance;
    }




    @Override
    public void insertEvent(Event event) throws DAOException {
        Session session = null;


        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new DAOException("couldnt insert event",e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteEvent(Event event) throws DAOException {
        Session session =null;
        try {
            session= factory.openSession();
            session.beginTransaction();
            session.delete(event);
            session.getTransaction().commit();

        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new DAOException("couldnt delete event",e);

        } finally {
            session.close();
        }



    }

    @Override
    public void deleteEvents(List<Event> eventsList) throws DAOException
    {


        Session session =null;
        try {
            session= factory.openSession();
            session.beginTransaction();
            for (Event event:eventsList)
            {
                session.delete(event);
            }

            session.getTransaction().commit();

        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new DAOException("couldnt some or all of the events",e);

        } finally {
            session.close();
        }

    }

    @Override
    public Event getEvent(String email, long id) throws DAOException {
        Session session = null;
        List list=null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            list = session.createQuery("from Event where email = " + email + " AND id = "+id).list();
            return (Event) list.get(0);

        }
        catch (HibernateException e) {
            e.printStackTrace();
            throw new DAOException("couldnt get event",e);

        } finally {
            session.close();
        }
    }

    @Override
    public List<Event> getEvents(String email) throws DAOException {

        Session session = null;
        List list=null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            list = session.createQuery("from Event where email = " + email).list();
            return list;

        }
        catch (HibernateException e) {
            e.printStackTrace();
            throw new DAOException("couldnt get events",e);

        } finally {
            session.close();
        }
    }
    @Override
    public boolean deleteUser(User user) throws DAOException {
        Session session = null;

        try {
         List<Event> userEvents=getEvents(user.getEmail());
           deleteEvents(userEvents);


            session = factory.openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
           e.printStackTrace();
           throw new DAOException("couldnt delete user but deleted his events");

        }
        catch (DAOException e)
        {
            //re throwing
            e.printStackTrace();
            throw new DAOException("coudlnt delete user and events",e);
        }


        return false;
    }

    @Override
    public boolean createNewUser(User user) throws DAOException {
        Session session = null;

        if(userVerification(user))
        {
            return false;
            // aka user allready exists
        }
        else {
            try {
                session = factory.openSession();
                session.beginTransaction();
                session.saveOrUpdate(user);
                session.getTransaction().commit();
                return true;
                }
            catch (HibernateException e)
            {
                e.printStackTrace();
                throw new DAOException("DB problem, couldn't create new user even though he does not exists",e);
            }
            finally {
                session.close();
            }
        }

    }


    @Override
    public boolean userVerification(User user) throws DAOException {

        // checks if user exists, if he does true, if not false, exception in any other case
        Session session = null;
        List<User> list=null;

        try {
            session = factory.openSession();
            session.beginTransaction();
            list = session.createQuery("from User ").list();
            if(!list.isEmpty())
            {
                for (User tempUser:list) {
                    if(tempUser.getEmail()==user.getEmail() && tempUser.getPassword()==user.getPassword())
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (HibernateException e) {
            e.printStackTrace();
            throw new DAOException("at DAO: couldn't verify user",e);

        } finally {
            session.close();
        }
    }
}

