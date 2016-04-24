package by.perevertkin.mobile.beans;

import by.perevertkin.mobile.serviceresult.ServiceResult;

import by.perevertkin.mobile.serviceresult.dc.ServiceResultDC;

import java.util.ArrayList;

import javax.el.ValueExpression;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.api.MafExecutorService;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class MainBean {

    public MainBean() {
    }

    public String getPost() {
        
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.postId}", String.class);
        String postId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        ArrayList parameterNames = new ArrayList();
        parameterNames.add("id");
        ArrayList parameterTypes = new ArrayList();
        parameterTypes.add(String.class);
        ArrayList parameterValues = new ArrayList();
        parameterValues.add(postId);
        try {
            AdfmfJavaUtilities.invokeDataControlMethod("ServiceResultDC", null, "getPost", parameterNames,
                                                       parameterValues, parameterTypes);

        } catch (AdfInvocationException e) {
            System.out.println("Error in action 'getPost':"+e); 
        }
        return "details";
    }

}
