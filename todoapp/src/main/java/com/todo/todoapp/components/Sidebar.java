package com.todo.todoapp.components;

import com.todo.todoapp.entities.User;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import com.todo.todoapp.pages.todo.Search;


public class Sidebar {

	@Property
	@SessionState
	private User loggedUser;

    @Component
	private Form searchForm;

    @Property
    private String searchInput;

    @InjectPage
    private Search searchPage;

    @OnEvent(value = EventConstants.ACTION, component = "searchForm")
    public Object doSearch(){
        searchPage.setQuery(searchInput); //the query field in Search page must be @Persist
        return searchPage;
    }
}
