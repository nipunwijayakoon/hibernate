import entity.Book;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppInitializer {
    public static void main(String[] args) {
//        printMySQlVersion();
//        printMySQlDateAndTime();

        Student student = new Student(1,"Namal Gunarathna",90);
        Book book = new Book(1,"hibernate");
        //saveStudent(student);
//        saveBook(book);
//        findStudent(1);
       // findAllStudent();
       // updateStudentName("mahinda",1);
        deleteStudent(2);


    }

        private static void saveStudent(Student student){

            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                //save[it returns a serializable object, saved object primary key]
                //persist, [return type void]
                //saveOrUpdate [record ekk tibne netnm save wenwa, tynwa nm update wenwa]
                Transaction transaction = session.beginTransaction();//for... save, update , delete
                long primaryKey = (Long) session.save(student);
                transaction.commit();
//                System.out.println(primaryKey);
            }

        }


    private static void saveBook(Book book){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //save[it returns a serializable object, saved object primary key]
            //persist,saveOeUpdate
            Transaction transaction = session.beginTransaction();//for... save, update , delete
            long primaryKey = (Long) session.save(book);
            transaction.commit();
            System.out.println(primaryKey);
        }

    }

    private  static void findStudent(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Student student =session.find(Student.class,id);
            if(student != null){
                System.out.println(student.toString());
            }else{
                System.out.println("can't find data");
            }

        }



    }

    private  static void findAllStudent(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("FROM Student");//HQL - hibernate query language
            List<Student> students = query.list();
            System.out.println(students);
        }

    }

    private static void updateStudentName(String name, long id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
           Student selectedStudent = session.find(Student.class,id);
           if(selectedStudent !=null){
               selectedStudent.setStudentName(name);
               Transaction transaction = session.beginTransaction();
               session.update(selectedStudent);
               transaction.commit();

           }else {
               System.out.println("can't find data");
           }
        }

    }

    private static void deleteStudent(long id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Student selectedStudent = session.find(Student.class,id);
            if(selectedStudent !=null){
                Transaction transaction = session.beginTransaction();
                session.delete(selectedStudent);
                transaction.commit();

            }else {
                System.out.println("can't find data");
            }
        }

    }
//        private static void printMySQlVersion(){
//
//
////        Session session =null;
////        try{
////            session = sessionFactory.openSession();
////            Object result = session.createNativeQuery("SELECT NOW()").getSingleResult();
////            System.out.println(result);
////
////        }catch (Exception e){
////            e.printStackTrace();
////        }finally {
////            session.close();
////            sessionFactory.close();
////        } dont use like this you can use try resource like bellow, but it doesn't close the factory
//
//            try(Session session = HibernateUtil.getSessionFactory().openSession()){;
//                Object result = session.createNativeQuery("SELECT VERSION()").getSingleResult();
//                System.out.println(result);
//            }
//
//
//        }
//
//        private static void printMySQlDateAndTime(){
//
////        Session session =null;
////        try{
////            session = sessionFactory.openSession();
////            Object result = session.createNativeQuery("SELECT NOW()").getSingleResult();
////            System.out.println(result);
////
////        }catch (Exception e){
////            e.printStackTrace();
////        }finally {
////            session.close();
////            sessionFactory.close();
////        } dont use like this you can use try resource like bellow, but it doesn't close the factory
//
//            try(Session session = HibernateUtil.getSessionFactory().openSession()){;
//                Object result = session.createNativeQuery("SELECT NOW()").getSingleResult();
//                System.out.println(result);
//            }
//
//
//        }



}
