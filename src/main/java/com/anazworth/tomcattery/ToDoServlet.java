package com.anazworth.tomcattery;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "todoServlet", value = "/")
public class ToDoServlet extends HttpServlet {
    private String url = "/Gradle___com_anazworth___tomcattery_1_0_SNAPSHOT_war/";
    private final ToDoService todo = new ToDoService();

    public void init() {
        var sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        sessionFactory.getSchemaManager().exportMappedObjects(true);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // Show all to-do items
        ArrayList<ToDoItem> items = todo.getAllItems();
        for (ToDoItem item : items) {
            out.println("<p>" + item.getTask() + "</p>");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String action = request.getParameter("action");

        if (action.equals("complete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            todo.completeItem(id);

            response.sendRedirect(url);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            todo.removeItem(id);

            response.sendRedirect(url);
        } else {

            String task = request.getParameter("task");
            todo.addItem(task);

            response.sendRedirect(url);
        }
    }

    public void destroy() {
    }
}