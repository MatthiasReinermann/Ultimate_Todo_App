package com.todo.todoapp.components;
import com.todo.todoapp.entities.User;
import com.todo.todoapp.pages.user.Login;
import com.todo.todoapp.pages.user.Register;
import com.todo.todoapp.pages.About;
import com.todo.todoapp.pages.user.Home;
import com.todo.todoapp.pages.Index;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.Request;

@SuppressWarnings("unused")
public class NavigationBar {

    @Inject
    private ComponentResources resources;

    @Property
    @SessionState
    private User loggedUser;

    @Inject
    private Request request;

    @Property
    private boolean loggedUserExists;

    public String getHomeTabStyle(){
        Component page = resources.getPage();
        if (page instanceof Home || page instanceof Index)
        return "active";
        else
            return "";
    }

    public String getAboutTabStyle(){
        Component page = resources.getPage();
        return page instanceof About? "active" : "";
    }

    public String getRegisterTabStyle(){
        Component page = resources.getPage();
        return page instanceof Register? "active" : "";
    }

    public String getLoginTabStyle(){
        Component page = resources.getPage();
        return page instanceof Login? "active" : "";
    }



    @OnEvent(value = EventConstants.ACTION, component = "logoutLink")
    public Object logout() {
        request.getSession(false).invalidate();
        loggedUser = null;
        return Index.class;
    }

}