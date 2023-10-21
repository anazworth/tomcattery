package com.anazworth.tomcattery;

import java.util.List;

public interface ToDoStorage {
    List<ToDoItem> getAllItems();
    void addItem(ToDoItem item);
    public void updateItem(ToDoItem item);
    public void deleteItem(ToDoItem item);
    public ToDoItem getItemById(int id);
    List<ToDoItem> getAllUncompletedItems();
}
