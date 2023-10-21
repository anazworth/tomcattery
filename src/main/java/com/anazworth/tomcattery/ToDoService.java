package com.anazworth.tomcattery;


import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class ToDoService {

    private static ToDoRepository repository;

    public ToDoService() {
        repository = new ToDoRepository();
        var sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        sessionFactory.getSchemaManager().exportMappedObjects(true);
    }

    public ToDoService(ToDoRepository repository) {
        ToDoService.repository = repository;
    }

    public static ArrayList<ToDoItem> getAllItems() {
        return new ArrayList<>(repository.getAllItems());
    }

    public ToDoItem addItem(String task) {
        ToDoItem item = new ToDoItem();
        item.setTask(task);
        item.setDateCreated(new java.util.Date().toString());
        item.setCompleted(false);
        repository.addItem(item);
        return item;
    }

    public void completeItem(int id) {
        ToDoItem item = repository.getItemById(id);
        item.setCompleted(true);
        item.setDateCompleted(new java.util.Date().toString());
        repository.updateItem(item);
    }

    public void removeItem(int id) {
        ToDoItem item = repository.getItemById(id);
        repository.deleteItem(item);
    }
}
