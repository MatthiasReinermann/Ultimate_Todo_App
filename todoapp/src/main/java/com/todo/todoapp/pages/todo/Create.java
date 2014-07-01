package com.todo.todoapp.pages.todo;
import com.todo.todoapp.entities.*;
import com.todo.todoapp.data.TodoServiceDAO;
import com.todo.todoapp.pages.user.Home;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.util.Date;

public class Create {
	@SessionState
    private User loggedUser;

    @Inject
    private TodoServiceDAO todoService;

    @Property
    private String title;

    @Property
    private Priority priority;

    @Property
    private Date dueDate;

    @Property
    @InjectComponent
    private Form createTodoForm;
    
    @OnEvent(value = "activate")
    public void init() {
        dueDate = new Date();
    }
    
    @OnEvent(value="success",component="createTodoForm")
    public Object createTodo() {
        Todo todo = new Todo(loggedUser.getId(), title, Status.TODO, priority, dueDate);
        todoService.create(todo);
        return Home.class;
    }
    
    
}
