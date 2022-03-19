package br.com.akinicchi.authentication_authorization.entity;

import br.com.akinicchi.authentication_authorization.utils.ConstantUtil;
import com.google.cloud.Timestamp;
import com.google.cloud.spring.data.firestore.Document;
import lombok.Data;

@Data
@Document(collectionName = ConstantUtil.ACCESS_APPLICATION)
public class DataApplication {

    private String secretId;

    private String applicationName;

    private Timestamp lastUpdated;

    private boolean registrationStatus;
}
