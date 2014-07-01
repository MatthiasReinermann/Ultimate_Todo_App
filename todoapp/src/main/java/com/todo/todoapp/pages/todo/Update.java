package com.todo.todoapp.pages.todo;
import com.todo.todoapp.entities.*;
import com.todo.todoapp.data.*;
import com.todo.todoapp.pages.user.Home;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.util.Date;

@SuppressWarnings("unused")
public class Update {

    @SessionState
    private User loggedUser;

    @Inject
    private TodoServiceDAO todoService;
    
    @InjectPage
    private com.todo.todoapp.pages.Error errorPage;

    @Inject
    private Messages messages;

    @Property
    private long id;

    @Property
    private String title;

    @Property
    private Priority priority;

    @Property
    private Status status;

    @Property
    private Date dueDate;

    @Persist
    @Property
    private Todo todo;

    @Property
    private String error;

   

    @Property
    @InjectComponent
    private Form updateTodoForm;

    @OnEvent(value = "activate")
    public Object init(long todoId) {
        todo = todoService.getTodoById(todoId);
        //TODO should use beanEditForm component, but must override all styles
        if (todo != null) {
            id = todo.getId();
            title = todo.getTitle();
            dueDate = todo.getDueDate();
            status = todo.getStatus();
            priority = todo.getPriority();
            return null;
        } else {
            errorPage.setError(messages.format("no.such.todo", todoId));
            return errorPage;
        }
    }

    @OnEvent(value = "success", component = "updateTodoForm")
    public Object updateTodo() {
        todo.setTitle(title);
        todo.setDueDate(dueDate);
        todo.setPriority(priority);
        todo.setStatus(status);
        todoService.update(todo);
        return Home.class;
    }

}