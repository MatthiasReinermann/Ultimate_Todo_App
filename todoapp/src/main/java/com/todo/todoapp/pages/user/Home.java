package com.todo.todoapp.pages.user;

import com.todo.todoapp.entities.*;
import com.todo.todoapp.data.*;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("unused")
public class Home {

    @Inject
    private TodoServiceDAO todoService;

    @SessionState
    private User loggedUser;

    @Property
    private List<Todo> todoList;

    @Property
    private Todo currentTodo;

    @Property
    private int doneCount;

    @Property
    private int todoCount;

    @Property
    private int totalCount;
    
    @OnEvent(value = "activate")
    public void init() {
        Long loggedUserId = loggedUser.getId();
        todoList = todoService.findWithNamedQuery(Todo.BY_USER, QueryParameters.with("user", loggedUserId).parameters());
        totalCount = todoList.size();
        doneCount = todoService.findWithNamedQuery(Todo.BY_USER_AND_STATUS, QueryParameters.with("user", loggedUserId).and("status", Status.DONE).parameters()).size();//getTodosByUsersAndStatus(loggedUserId, Status.DONE).size();
        todoCount = todoService.findWithNamedQuery(Todo.BY_USER_AND_STATUS, QueryParameters.with("user", loggedUserId).and("status", Status.TODO).parameters()).size();
    }
    
    @OnEvent(value="action", component="deleteTodoLink")
    public void deleteTodo(long todoId){
        Todo todo = todoService.getTodoById(todoId);
        if (todo != null){
            todoService.delete(todo);
        }
    }
    
    public String getCurrentStatusLabel() {
        return currentTodo.getStatus().equals(Status.DONE) ? "label-success" : "";
    }
    
    public String getCurrentPriorityIcon() {
        String priorityIcon = "";
        if (currentTodo.getPriority().equals(Priority.HIGH)) {
            priorityIcon = "up";
        } else if (currentTodo.getPriority().equals(Priority.MEDIUM)) {
            priorityIcon = "right";
        } else if (currentTodo.getPriority().equals(Priority.LOW)) {
            priorityIcon = "down";
        }
        return priorityIcon;
    }

    public String getCurrentDueDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(currentTodo.getDueDate());
    }

    
}
