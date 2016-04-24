package by.perevertkin.mobile.serviceresult;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ServiceResult {
    private int id;
    private int userId;
    private String title;
    private String body;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public void setId(int id) {
        int oldId = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", oldId, id);
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        int oldUserId = this.userId;
        this.userId = userId;
        propertyChangeSupport.firePropertyChange("userId", oldUserId, userId);
    }

    public int getUserId() {
        return userId;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange("title", oldTitle, title);
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        String oldBody = this.body;
        this.body = body;
        propertyChangeSupport.firePropertyChange("body", oldBody, body);
    }

    public String getBody() {
        return body;
    }

    public ServiceResult() {
        super();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
