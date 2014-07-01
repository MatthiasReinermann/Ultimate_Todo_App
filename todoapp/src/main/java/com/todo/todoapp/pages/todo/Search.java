package com.todo.todoapp.pages.todo;
import com.todo.todoapp.data.*;
import com.todo.todoapp.entities.*;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Search {

    @Inject
    private TodoServiceDAO todoService;

    @SessionState
    private User loggedUser;

    @Property
    private List<Todo> todoList;

    @Property
    private Todo currentTodo;

    @Persist
    private String query;
    
    @OnEvent(value = "activate")
    public void init() {
    	String title = query.toLowerCase();
        todoList = todoService.findWithNamedQuery(Todo.BY_TITLE_AND_USER, QueryParameters.with("title", title).and("user", loggedUser).parameters());
    }
    
    @OnEvent(value = "action", component = "deleteTodoLink")
    public void deleteTodo(long todoId) {
        Todo todo = todoService.getTodoById(todoId);
        if (todo != null) {
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCurrentHighlightedTitle() {
        return doHighlight(currentTodo.getTitle(), query);
    }
    
    /**
     *  Method doHighlight matches a query pattern to the title of a todo
     *  highlights the matched part by applying a css style to it 
     */
    
    private String doHighlight(final String input, final String pattern) {

        String cssClass = "label label-warning";
        String startSpanTag = "<span class=\"" + cssClass + "\">";
        String endSpanTag = "</span>";

        StringBuilder stringBuilder = new StringBuilder(startSpanTag);
        stringBuilder.append(pattern);
        stringBuilder.append(endSpanTag);

        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);

        return matcher.replaceAll(stringBuilder.toString());

    }
    
}
