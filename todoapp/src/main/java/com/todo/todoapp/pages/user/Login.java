package com.todo.todoapp.pages.user;
import com.todo.todoapp.entities.User;
import com.todo.todoapp.data.QueryParameters;
import com.todo.todoapp.data.UserServiceDAO;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

@SuppressWarnings("unused")
public class Login {
	@Inject
	private UserServiceDAO userService;
	
	@SessionState
	private User loggedUser;
	
	@Inject
	private Messages messages;
	
	@Property
	private String email;
	
	@Property
	private String password;
	
	@Property
	private String error;
	
	@Property
	@Component
	private Form loginForm;
	
	@OnEvent(value = "validate", component = "loginForm")
	public void validateLoginForm(){
		User user =  userService.findUniqueWithNamedQuery(User.BY_EMAIL_AND_PASSWORD, 
				QueryParameters.with("email", email).and("password", password).parameters()); 
		if(user == null){
			loginForm.recordError(messages.get("login.erro.global.invalid"));
		}
	}
	
	@OnEvent(value = "success", component = "loginForm")
	public Object onSuccess(){
		loggedUser = userService.findUniqueWithNamedQuery(User.BY_EMAIL_AND_PASSWORD, 
				QueryParameters.with("email", email).parameters());
		return Home.class;
	}
	
}
