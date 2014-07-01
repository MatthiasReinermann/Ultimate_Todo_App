package com.todo.todoapp.pages.user;
import com.todo.todoapp.entities.User;
import com.todo.todoapp.data.QueryParameters;
import com.todo.todoapp.data.UserServiceDAO;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

@SuppressWarnings("unused")
public class Register {

    @SessionState
    private User loggedUser;

    @Inject
    private UserServiceDAO userService;
    
    @Inject
    private Messages messages;

	@Property
	private String firstname;

	@Property
	private String lastname;

	@Property
	private String email;

	@Property
	private String password;

	@Property
	private String confirmationPassword;

    @Property
    private String error;

    @Property
	@InjectComponent
	private Form registerForm;

	@OnEvent(value="submit",component="registerForm")
	public void validateForm() {
		User user = userService.findUniqueWithNamedQuery(User.BY_EMAIL, 
				QueryParameters.with("email", email).parameters());
        if (user != null)
            registerForm.recordError(messages.format("register.error.global.account", email));
        if (!password.equals(confirmationPassword)) {
            registerForm.recordError(messages.get("register.error.password.confirmation.error"));
        }
    }

	@OnEvent(value="success",component="registerForm")
	public Object onRegisterSuccess() {
        User user = new User(firstname, lastname, email, password);
        userService.create(user);
        //begin printf debugging
        System.out.println("USER CREATED SO FAR SO GOOD");
        // end debuggin
        loggedUser = userService.findUniqueWithNamedQuery(User.BY_EMAIL, 
				QueryParameters.with("email", email).parameters());
        return Home.class;
    }

}
