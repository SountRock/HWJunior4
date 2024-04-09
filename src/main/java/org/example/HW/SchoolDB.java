package org.example.HW;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SchoolDB {
    SessionFactory factory;

    public SchoolDB(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Courses.class)
                .buildSessionFactory();
    }

    public void addData(String title, int duration){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();

            Courses course = new Courses(title, duration);
            session.save(course);

            session.getTransaction().commit();
        }
    }

    public void removeData(int ID){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();

            List<Courses> courses = session.createQuery("FROM Courses", Courses.class).getResultList();
            Courses temp = courses.stream().filter(course -> course.getID() == ID).findFirst().get();
            session.delete(temp);

            session.getTransaction().commit();
        }
    }

    public void updateData(int ID, String title, int duration){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();

            List<Courses> courses = session.createQuery("FROM Courses", Courses.class).getResultList();
            Courses temp = courses.stream().filter(c -> c.getID() == ID).findFirst().get();
            temp.setTitle(title);
            temp.setDuration(duration);

            session.update(temp);

            /*
            String updateData = "UPDATE Courses SET title=:title, duration=:duration WHERE ID=:ID;";
            org.hibernate.query.Query update = session.createQuery(updateData);
            update.setParameter(":title", course.getTitle());
            update.setParameter(":duration", course.getDuration());
            update.setParameter(":ID", course.getID());
             */

            session.getTransaction().commit();
        }
    }

    public String readAll(){
        String res = "Courses: \n";
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();

            List<Courses> courses = session.createQuery("FROM Courses", Courses.class).getResultList();
            for (Courses c : courses) {
                res += "\t" + c + "\n";
            }

            session.getTransaction().commit();
        }

        return res;
    }

    public String read(int ID){
        String res = "Course: ";
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();

            List<Courses> courses = session.createQuery("FROM Courses", Courses.class).getResultList();
            res += courses.stream().filter(c -> c.getID() == ID).findFirst().get().toString();

            session.getTransaction().commit();
        }

        return res;
    }

    public void removeAllData(){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();

            session.createSQLQuery("TRUNCATE TABLE Courses;").executeUpdate(); //что бы сбрасывался счетчик ID

            session.getTransaction().commit();
        }
    }

}
