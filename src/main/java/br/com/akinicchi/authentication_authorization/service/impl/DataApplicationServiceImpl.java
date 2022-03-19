package br.com.akinicchi.authentication_authorization.service.impl;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;
import br.com.akinicchi.authentication_authorization.exception.SecretIdIncorrectException;
import br.com.akinicchi.authentication_authorization.repository.DataApplicationRepository;
import br.com.akinicchi.authentication_authorization.service.DataApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        DataApplication data = response.block();
        if (data != null) {
            if (!data.getSecretId().equals(secretId)) {
                throw new SecretIdIncorrectException();
            }
            return data;
        }
        throw new UsernameNotFoundException("APPLICATION NOT FOUND");
    }
}
