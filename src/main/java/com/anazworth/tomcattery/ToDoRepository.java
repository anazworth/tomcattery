package com.anazworth.tomcattery;

import com.zaxxer.hikari.HikariConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class ToDoRepository implements ToDoStorage {

    private final SessionFactory sessionFactory;
    private Session session;

    public ToDoRepository() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://mysql-db/todoDB");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("password");
        hikariConfig.setMaximumPoolSize(1000);
//        hikariConfig.setIdleTimeout(1);

        sessionFactory = new org.hibernate.cfg.Configuration()
                .configure()
                .addProperties(hikariConfig.getDataSourceProperties())
                .buildSessionFactory();
        openSession();
    }

    public void openSession() {
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
            session.beginTransaction();
        }
    }

    public void closeSession() {
        if (session != null && session.isOpen()) {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<ToDoItem> getAllItems() {
        var items = session.createQuery("from ToDoItem", ToDoItem.class).list();
        return items;
    }

    @Override
    public void addItem(ToDoItem item) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(ToDoItem item) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(ToDoItem item) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ToDoItem getItemById(int id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            var item = session.get(ToDoItem.class, id);
            session.getTransaction().commit();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ToDoItem> getAllUncompletedItems() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            var items = session.createQuery("from ToDoItem where completed = false", ToDoItem.class).list();
            session.getTransaction().commit();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
