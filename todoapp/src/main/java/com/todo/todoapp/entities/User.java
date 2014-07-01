package com.todo.todoapp.entities;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Date;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;


// user has many todos
@Entity
@NamedQueries(
		{
			@NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u where u.email = :email"),
			@NamedQuery(name = User.BY_EMAIL_AND_PASSWORD, query = "SELECT u FROM User u where u.email = :email and u.password = :password")
		}
		)
public class User {
	
	public static final String BY_EMAIL = "byEmail";
	public static final String BY_EMAIL_AND_PASSWORD = "byEmailAndPassword";
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstname;
	
	private String lastname; 
	
	private String email;
	
	private String password;
	
	@OneToMany
	private Set<Todo> todos;
	
	public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
	
	public User(){
		
	}
	
	public Set<Todo> getTodos(){
		return todos;
	}
	
	public void setTodos(Set<Todo> todos){
		this.todos = todos;
	}

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
