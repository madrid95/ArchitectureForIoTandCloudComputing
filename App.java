package IGT.Hibernate_nested_Transaction_impossible;

import java.net.MalformedURLException;
import java.net.URL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws HibernateException, MalformedURLException
    {
    	Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());

    	Session session = null;  
    	Transaction tx1, tx2 = null;  
    	  
    	session = sessionFactory.openSession();  
    	tx1 = session.beginTransaction();  
    	tx2 = session.beginTransaction();//Throws exception
    }
}
