package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import datamodel.BudgetItems;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class utilDB {
	static SessionFactory sessionFactory = null;

	   public static SessionFactory getSessionFactory() {
	      if (sessionFactory != null) {
	         return sessionFactory;
	      }
	      Configuration configuration = new Configuration().configure();
	      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	      sessionFactory = configuration.buildSessionFactory(builder.build());
	      return sessionFactory;
	   }
	   
	   public static List<BudgetItems> listBudgetItems() {
		      List<BudgetItems> resultList = new ArrayList<BudgetItems>();
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

		      try {
		         tx = session.beginTransaction();
		         List<?> EmployeeWrights = session.createQuery("FROM EmployeeWright").list();
		         for (Iterator<?> iterator = EmployeeWrights.iterator(); iterator.hasNext();) {
		            EmployeeWright EmployeeWright = (EmployeeWright) iterator.next();
		            resultList.add(EmployeeWright);
		         }
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		      return resultList;
		   } 
}
