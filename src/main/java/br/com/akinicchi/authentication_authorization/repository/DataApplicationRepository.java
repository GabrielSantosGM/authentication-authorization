package br.com.akinicchi.authentication_authorization.repository;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

public interface DataApplicationRepository extends FirestoreReactiveRepository<DataApplication> {

}
