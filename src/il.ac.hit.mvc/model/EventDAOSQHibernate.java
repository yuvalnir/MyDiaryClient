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
            event.setId((Long)session.getIdentifier(event));
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
    public Event getEvent(User user, long eventId) throws DAOException {
        Session session = null;
        String query;
        List list=null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            query="from Event where email = :userEmail AND id = :userId";


            list = session.createQuery(query)
                    .setParameter("userEmail",user.getEmail())
                    .setParameter("userId",eventId)
                    .list();
            if(list.size()==1)
            {
                return (Event) list.get(0);

            }
            else
            {
               throw new DAOException("couldn't find event") ;
               //list cant be more than 1 because of the unique ID and if empty than event not found, shouldnt be handeld in controller

            }

        }
        catch (HibernateException e) {
            e.printStackTrace();
            throw new DAOException("problem with DAO",e);

        } finally {
            session.close();
        }
    }

    @Override
    public List<Event> getEvents(User user) throws DAOException {

        Session session = null;
        String query;


        List list=null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            query="from Event where email = :userEmail";
            list = session.createQuery(query)
                    .setParameter("userEmail",user.getEmail())
                    .list();

            if(list.isEmpty())
            {
                throw new DAOException("user have no events");
            }

            return list;

        }
        catch (HibernateException e) {
            e.printStackTrace();
            throw new DAOException("problem fetching the events from DAO",e);

        } finally {
            session.close();
        }
    }
    @Override
    public boolean deleteUser(User user) throws DAOException {
        Session session = null;

        try {
         List<Event> userEvents=getEvents(user);
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
            boolean userExists=false;


            String queryQuestion =("from User where email = :userEmail AND password = :userPassword");
            list=session.createQuery(queryQuestion)
                    .setParameter("userEmail",user.getEmail())
                    .setParameter("userPassword",user.getPassword()).list();
            if(!list.isEmpty())
            {
                userExists=true;
            }
            return userExists;
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
            throw new DAOException("couldn't verify user",e);

        } finally {
            session.close();
        }
    }
}

