package by.perevertkin.mobile.beans;

import by.perevertkin.mobile.serviceresult.ServiceResult;

import by.perevertkin.mobile.serviceresult.dc.ServiceResultDC;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.api.MafExecutorService;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class MainBean {
    private  int postId;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public MainBean() {
    }

    public String getPost() {
        ServiceResultDC serviceResultDC=new ServiceResultDC();
        serviceResultDC.getPost(getPost().toString());
        return "details";
    }

    public void setPostId(int postId) {
        int oldPostId = this.postId;
        this.postId = postId;
        propertyChangeSupport.firePropertyChange("postId", oldPostId, postId);
    }

    public int getPostId() {
        return postId;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
