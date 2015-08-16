package org.windbell.lab.hamster.hibernate.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.windbell.lab.hamster.hibernate.entity.Student;

public class Test {
	public static void main(String[]args){
		Configuration cfg = new Configuration();
		String hibrenateConfigPaht = ".\\org\\windbell\\lab\\hamster\\hibernate\\conf\\hibernate.cfg.xml";
		cfg.configure(hibrenateConfigPaht);
//		cfg.addClass(Student.class);
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Student stu=new  Student();
		stu.setAge(30);
		stu.setName("HXX");
		stu.setNumber("aa123");
		Transaction ts = session.beginTransaction();
		ts.begin();
		session.save(stu);
		ts.commit();
	}
}
