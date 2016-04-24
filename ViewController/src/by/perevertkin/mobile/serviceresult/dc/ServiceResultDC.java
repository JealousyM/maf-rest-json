package by.perevertkin.mobile.serviceresult.dc;

import by.perevertkin.mobile.serviceresult.ServiceResult;


import java.util.ArrayList;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class ServiceResultDC {
    private ServiceResult[] serviceResults = null;
    private ServiceResult serviceResult = null;

    public ServiceResult[] getServiceResults() {
        if (serviceResults == null) {
            try {
                String result = invokeRestJsonService(RestServiceAdapter.REQUEST_TYPE_GET, "/posts");
                JSONArray inputArray = new JSONArray(result);
                ArrayList resultsArray = new ArrayList();
                for (int i = 0; i < 10; i++) {
                    try {
                        ServiceResult serviceResult = new ServiceResult();
                        JSONObject resultObject = inputArray.getJSONObject(i);
                        serviceResult.setTitle(resultObject.getString("title"));
                        serviceResult.setBody(resultObject.getString("body"));
                        serviceResult.setId(resultObject.getInt("id"));
                        resultsArray.add(serviceResult);
                    } catch (Exception e) {
                    }
                }
                serviceResults = (ServiceResult[]) resultsArray.toArray(new ServiceResult[resultsArray.size()]);
                return serviceResults;
            } catch (Exception e) {
                System.out.println("Error getServiceResults:" + e.getLocalizedMessage());
            }
        }

        return serviceResults;
    }

    public ServiceResult getPost(String id) {
        try {
            JSONBeanSerializationHelper jsonHelper = new JSONBeanSerializationHelper();
            String result = invokeRestJsonService(RestServiceAdapter.REQUEST_TYPE_GET, "/posts/" + id);
            serviceResult = (ServiceResult) jsonHelper.fromJSON(ServiceResult.class, result);
            providerChangeSupport.fireProviderRefresh("serviceResult");
            return serviceResult;
        } catch (Exception e) {
            System.out.println("Error get post:" + e);
        }
        return null;
    }

    public ServiceResult getServiceResult() {
        return serviceResult;
    }


    private String invokeRestJsonService(String requestType, String requestUri) {
        System.out.println("invokeRestJsonService---->" + requestType + "   " + requestUri);
        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();
        restServiceAdapter.clearRequestProperties();
        restServiceAdapter.setConnectionName("jsonplaceholder");
        restServiceAdapter.setRequestType(requestType);
        restServiceAdapter.setRetryLimit(0);
        restServiceAdapter.setRequestURI(requestUri);

        String response = "";
        try {
            response = restServiceAdapter.send("");
        } catch (Exception e) {

            e.printStackTrace();
        }

        return response;
    }

    //***** provider change support *****//
    //Enable provider change support
    protected transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

}
