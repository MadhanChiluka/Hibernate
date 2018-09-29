package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
        
        Student student= new Student();
        Laptop laptop = new Laptop();
        
        student.setRollno(1000);
        student.setSname("Himaamshu");
        student.setMarks(90);
        
      
        laptop.setLid(1001);
        laptop.setLname("MacBook");
        laptop.getStudent().add(student);
        student.getLaptop().add(laptop);
        
        
        
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
        
        ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        
        SessionFactory sf= cfg.buildSessionFactory(sr);
        
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(laptop);
        session.save(student);

        
        session.getTransaction().commit();
        session.close();
        
    }
}
