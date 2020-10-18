/**
 */
package util;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import datamodel.Item;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
	static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static List<Item> listItem() {
		List<Item> resultList = new ArrayList<Item>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> Items = session.createQuery("FROM Item").list();
			for (Iterator<?> iterator = Items.iterator(); iterator.hasNext();) {
				Item Item = (Item) iterator.next();
				resultList.add(Item);
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

	public static List<Item> listItem(int month) {
		List<Item> resultList = new ArrayList<Item>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			List<?> Items = session.createQuery("FROM Item").list();
			for (Iterator<?> iterator = Items.iterator(); iterator.hasNext();) {
				Item Item = (Item) iterator.next();
				if (Item.thisMonth(month))
					resultList.add(Item);
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

	public static void createItems(String itemname, Double cost, Date date) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Item(itemname, cost, date));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void togglePaid(int i) {
		List<Item> resultList = new ArrayList<Item>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null; // each process needs transaction and commit the changes in DB.

		try {
			tx = session.beginTransaction();
			String query = "FROM Item WHERE id = " + i;
			System.out.println(query);
			List<?> Items = session.createQuery(query).list();
			for (Iterator<?> iterator = Items.iterator(); iterator.hasNext();) {
				Item Item = (Item) iterator.next();
				Item.setPaid(!Item.getPaid());
			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
	}
}
