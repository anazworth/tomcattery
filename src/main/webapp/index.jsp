<%@ page import="java.util.ArrayList" %>
<%@ page import="com.anazworth.tomcattery.ToDoItem" %>
<%@ page import="com.anazworth.tomcattery.ToDoService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>To-Dooli</title>
        <style type="text/css">
            /* styles.css */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0 auto;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
            }

            h1 {
                text-align: center;
            }

            h2 {
                margin-top: 20px;
            }

            table {
                width: 80%;
                margin: 0 auto;
                border-collapse: collapse;
            }

            table, th, td {
                border: 1px solid #ccc;
            }

            th, td {
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: steelblue;
                color: #fff;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #ddd;
            }

            form {
                margin: 10px 0;
            }

            input[type="text"] {
                width: 60%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="submit"] {
                background-color: #333;
                color: #fff;
                padding: 8px 16px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #555;
            }

            .create-form {
                margin: 20px auto;
                text-align: center;
            }

            .create-form label {
                margin-right: 10px;
            }

            .create-form form {
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .create-form input[type="submit"] {
                margin-left: 10px;
            }

        </style>
    </head>
    <body>
    <h1>Welcome to To-Dooli!</h1>
    <br/>
    <h2>All todos</h2>
    <%
        ToDoService todoService = new ToDoService();
        ArrayList<ToDoItem> fetchedTodos = todoService.getAllItems();
        request.setAttribute("todos", fetchedTodos);
    %>
    <table>
        <tr>
            <th>Id</th>
            <th>Task</th>
            <th>Completed</th>
            <th>Complete</th>
            <th>Delete</th>
        </tr>
        <%
            ArrayList<ToDoItem> todos = (ArrayList<ToDoItem>) request.getAttribute("todos");
            for (ToDoItem todo : todos) {
        %>
        <tr>
            <td><%= todo.getId() %></td>
            <td><%= todo.getTask() %></td>
            <td style="font-size: x-large"><%= (todo.getCompleted()) ? "✅" : "❌" %></td>
            <td>
                <form action="todo-servlet" method="post">
                    <!-- Add a hidden input to store the item ID -->
                    <input type="hidden" name="action" value="complete">
                    <input type="hidden" name="id" value="<%= todo.getId() %>">
                    <input type="submit" value="Complete" style="background-color: #333">
                </form>
            </td>
            <td>
                <form action="todo-servlet" method="post">
                    <!-- Add a hidden input to store the item ID -->
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= todo.getId() %>">
                    <input type="submit" value="Delete" style="background-color: crimson">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <h2>Create a new todo</h2>
    <div class="create-form">
        <form action="todo-servlet" method="post">
            <input type="hidden" name="action" value="create">
            <label for="task">Task:</label>
            <input type="text" name="task" placeholder="Enter a new task" required>
            <input type="submit" value="Add Task">
        </form>
    </div>
    </body>
</html>