package br.com.akinicchi.authentication_authorization.service;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;

public interface DataApplicationService {

    DataApplication findByIdApplication(String clientId, String secretId);
}
