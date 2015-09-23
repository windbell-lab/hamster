package org.windbell.lab.hamster.hibernate.test;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.windbell.lab.hamster.hibernate.entity.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[]args){
		Configuration cfg = new Configuration();
		String hibrenateConfigPaht = ".\\org\\windbell\\lab\\hamster\\hibernate\\conf\\hibernate.cfg.xml";
		cfg.configure(hibrenateConfigPaht);
//		cfg.addClass(Student.class);
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
////		Student stu=new  Student();
//		stu.setAge(30);
//		stu.setName("HXX");
//		stu.setNumber("aa123");
//		Transaction ts = session.beginTransaction();
//		ts.begin();
//		session.save(stu);
//		ts.commit();
		String queryStudent = "SELECT s.id,s.createTime,s.creater,s.updateTime,s.updater,s.version,s.age,s.`name`,s.number FROM student AS s WHERE s.id = 1";
		@SuppressWarnings("unchecked")
		List<Student> list = session.createSQLQuery(queryStudent).addEntity(Student.class).list();
		Student stu = list.get(0);
		stu.setAge(stu.getAge()+1);
		stu.setName("HXX");
		stu.setNumber("aa123");
		Transaction ts = session.beginTransaction();
		ts.begin();
		session.save(stu);
		ts.commit();
//		Transaction ts = session.beginTransaction();
//		ts.begin();
		session.save(stu);
//		ts.commit();
		Query createQuery = session.createQuery("FROM Student as s");
//		createQuery.setLockMode("studnet", LockMode.UPGRADE);
		List list2 = createQuery.list();
		JSONArray json=JSONArray.fromObject(list2);
		System.out.println(json.toString());
	}
}
