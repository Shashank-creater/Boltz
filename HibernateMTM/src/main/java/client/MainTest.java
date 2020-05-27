package client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entity.Employee;
import entity.Meeting;

public class MainTest 
{
	static Session sessionObj;
    static SessionFactory sessionFactoryObj;
 
private static SessionFactory sessionFactory = null;
	
	static
	{
		try 
		{
			loadSessionFactory();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void loadSessionFactory()
	{
		Configuration configuration = new Configuration();
		configuration.configure("/hibernate.cfg.xml");
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(Meeting.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	
    public static Session getSession() throws HibernateException
    {
	  
    	Session retSession=null;
        try 
        {
            retSession=sessionFactory.openSession();
        }
        catch(Throwable t)
        {
        	System.err.println("Exception while getting session.. ");
        	t.printStackTrace();
        }
        
        if(retSession == null)
        {
            System.err.println("session is discovered null");
        }
 
        return retSession;
    }

 
    public static void main(String[] args) {        
        try {
            sessionObj = getSession();
            sessionObj.beginTransaction();
 
            Meeting quaterlyMeet = new Meeting("Quaterly Status Meeting");
            Meeting weeklyMeet = new Meeting("Weekly Status Meeting");
            Meeting dailyMeet  = new Meeting("Daily Status Meeting");
 
            Employee empObj1 = new Employee("Happy", "Potter");
            empObj1.getMeetings().add(quaterlyMeet);
            empObj1.getMeetings().add(weeklyMeet);
            sessionObj.save(empObj1);
 
            Employee empObj2 = new Employee("Lucifer", "Morningstar");
            empObj2.getMeetings().add(quaterlyMeet);
            sessionObj.save(empObj2);
 
            Employee empObj3 = new Employee("April O'", "Neil");            
            empObj3.getMeetings().add(weeklyMeet);
            empObj3.getMeetings().add(dailyMeet);
            sessionObj.save(empObj3);
 
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
 
            System.out.println("\n.......Records Saved Successfully To The Database.......");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}
