package br.com.akinicchi.authentication_authorization.service.impl;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;
import br.com.akinicchi.authentication_authorization.exception.HeaderParamsAuthenticationException;
import br.com.akinicchi.authentication_authorization.exception.MessageErrorEnum;
import br.com.akinicchi.authentication_authorization.repository.DataApplicationRepository;
import br.com.akinicchi.authentication_authorization.service.DataApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DataApplicationServiceImpl implements DataApplicationService {

    private final DataApplicationRepository dataApplicationRepository;

    @Autowired
    public DataApplicationServiceImpl(DataApplicationRepository dataApplicationRepository) {
        this.dataApplicationRepository = dataApplicationRepository;
    }

    @Override
    public DataApplication findByIdApplication(String clientId, String secretId) {
        Mono<DataApplication> response = dataApplicationRepository.findById(clientId);
        return this.validate(response, secretId);
    }

    private DataApplication validate(Mono<DataApplication> monoDataApplication, String secretId) {
        DataApplication dataApplication = monoDataApplication.block();
        if (dataApplication == null) {
            throw new HeaderParamsAuthenticationException(MessageErrorEnum.FIRST_ROLE);
        }

        if (!dataApplication.getSecretId().equals(secretId)) {
            throw new HeaderParamsAuthenticationException(MessageErrorEnum.SECOND_ROLE);
        }
        return dataApplication;
    }
}
