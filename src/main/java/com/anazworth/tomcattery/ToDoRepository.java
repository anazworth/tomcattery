package com.anazworth.tomcattery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class ToDoRepository implements ToDoStorage {

    private final SessionFactory sessionFactory;

    public ToDoRepository() {
        sessionFactory = new org.hibernate.cfg.Configuration()
                .configure()
                .buildSessionFactory();
    }
    @Override
    public List<ToDoItem> getAllItems() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            var items = session.createQuery("from ToDoItem", ToDoItem.class).list();
            session.getTransaction().commit();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
