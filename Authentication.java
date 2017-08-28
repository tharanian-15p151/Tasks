package tharan;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Authentication {
	public static boolean check(Login login)
	{
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		Session se=sf.openSession();
		se.beginTransaction();
		Query query=se.createQuery("from Login");
		List<Login> li=query.getResultList();
		System.out.println("Valid");
		for(Login each:li)
		{
			String u1=each.getName();
			String u2=login.getName();
			String p1=each.getPassword();
			String p2=login.getPassword();
			if(u1.compareTo(u2)==0 && p1.compareTo(p2)==0)
			{
				return true;
			}
		}
		return false;
		
	}

}
