package br.com.akinicchi.authentication_authorization.mocks;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;
import com.google.cloud.Timestamp;

public class DataApplicationMock {

    public static final String APPLICATION_NAME = "applicationNameValue";
    public static final String SECRET_ID = "secretIdValue";
    public static final String SECRET_ID_INVALID = "secret";
    public static final Timestamp LAST_UPDATED = Timestamp.now();
    public static final boolean REGISTRATION_STATUS = true;

    public static DataApplication createDataApplication() {
        DataApplication dataApplication = new DataApplication();
        dataApplication.setApplicationName(APPLICATION_NAME);
        dataApplication.setSecretId(SECRET_ID);
        dataApplication.setLastUpdated(LAST_UPDATED);
        dataApplication.setRegistrationStatus(REGISTRATION_STATUS);
        return dataApplication;
    }
}
