package com.todo.todoapp.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@NamedQueries(
		{
			@NamedQuery(name = Todo.BY_USER, query = "SELECT t FROM Todo t where t.userId = :user order by t.dueDate"),
			@NamedQuery(name = Todo.BY_TITLE_AND_USER, query = "SELECT t FROM Todo t where t.userId = :user and upper(t.title) like :title order by t.dueDate" ),
			@NamedQuery(name = Todo.BY_USER_AND_PRIORITY, query = "SELECT t FROM Todo t where t.userId = :user and t.priority = :priority order by t.dueDate"),
			@NamedQuery(name = Todo.BY_USER_AND_STATUS, query = "SELECT t FROM Todo t where t.userId = :user and t.status = :status order by t.dueDate"),
			@NamedQuery(name = Todo.BY_USER_AND_STATUS_PRIORITY, query = "SELECT t FROM Todo t where t.userId = :user and t.status = :status and t.priority = :priority order by t.dueDate"),
		}
		)
public class Todo {
	
	public static final String BY_USER = "byUser";
	public static final String BY_USER_AND_STATUS = "byUserAndStatus";
	public static final String BY_USER_AND_PRIORITY = "byUserAndPriority";
	public static final String BY_USER_AND_STATUS_PRIORITY = "byUserAndStatusAndPriority";
	public static final String BY_TITLE_AND_USER = "byTitleAndUser";
    @Id
    @GeneratedValue
    private long id;

    private long userId;

    @Column(length = 512)
    private String title;

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    @Enumerated(value = EnumType.ORDINAL)
    private Priority priority;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    public Todo() {
        status = Status.TODO;
        priority = Priority.LOW;
    }

    public Todo(long userId, String title, Status status, Priority priority, Date dueDate) {
        this.userId = userId;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Todo{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", status=").append(status);
        sb.append(", priority=").append(priority);
        sb.append(", dueDate=").append(dueDate);
        sb.append('}');
        return sb.toString();
    }
}