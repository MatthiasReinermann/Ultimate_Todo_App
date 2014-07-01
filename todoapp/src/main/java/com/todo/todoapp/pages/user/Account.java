package com.todo.todoapp.pages.user;

import com.todo.todoapp.entities.User;
import com.todo.todoapp.data.*;
import com.todo.todoapp.pages.Index;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

public class Account {

    @Inject
    private UserServiceDAO userService;

    @Inject
    private TodoServiceDAO todoService;

    @Inject
    private Request request;

    @Inject
    private Messages messages;

    @Property
    @SessionState
    private User loggedUser;

    @Property
    private String firstName;

    @Property
    private String lastName;

    @Property
    private String email;

    @Property
    @Component
    private Form changePasswordForm;

    @Property
    @Component
    private Form updateAccountForm;

    @Property
    private String error;

    @Property
    private String currentPassword;

    @Property
    private String newPassword;

    @Property
    private String confirmPassword;

    @Property
    @Persist(value = PersistenceConstants.FLASH)
    private String updateProfileSuccessMessage;

    @Property
    @Persist(value = PersistenceConstants.FLASH)
    private String updatePasswordSuccessMessage;

    @OnEvent(value = "activate")
    public void init() {
        firstName = loggedUser.getFirstname();
        lastName = loggedUser.getLastname();
        email = loggedUser.getEmail();
    }

    @OnEvent(value = "validate", component = "updateAccountForm")
    public void validateUpdateAccountForm() {
    	User user = userService.findUniqueWithNamedQuery(User.BY_EMAIL_AND_PASSWORD, 
				QueryParameters.with("email", email).parameters());
        if (user != null && !email.equals(loggedUser.getEmail())) {
            updateAccountForm.recordError(messages.format("account.email.alreadyUsed", email));
        }
    }

    @OnEvent(value = "success", component = "updateAccountForm")
    public Object updateAccount() {
        loggedUser.setFirstname(firstName);
        loggedUser.setLastname(lastName);
        loggedUser.setEmail(email);
        userService.update(loggedUser);
        updateProfileSuccessMessage = messages.get("account.profile.update.success");
        return null;
    }

    @OnEvent(value = "validate", component = "changePasswordForm")
    public void validateChangePasswordForm() {
        if (!currentPassword.equals(loggedUser.getPassword())) {
            changePasswordForm.recordError(messages.get("account.password.error"));
        } else {
            if (!newPassword.equals(confirmPassword))
                changePasswordForm.recordError(messages.get("account.password.confirmation.error"));
        }
    }

    @OnEvent(value = "success", component = "changePasswordForm")
    public Object changePassword() {
        loggedUser.setPassword(newPassword);
        userService.update(loggedUser);
        updatePasswordSuccessMessage = messages.get("account.password.update.success");
        return null;
    }

    @OnEvent(value= "action",component="deleteAccountLink")
    public Object deleteAccount(){
        userService.delete(loggedUser);
        request.getSession(false).invalidate();
        loggedUser = null;
        return Index.class;
    }

}
