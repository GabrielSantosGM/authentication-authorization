package br.com.akinicchi.authentication_authorization.controller;

import br.com.akinicchi.authentication_authorization.exception.response.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class PublicKeyControllerTest {

    @InjectMocks
    private PublicKeyController publicKeyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnPublicKey(){
        ResponseEntity<ResponseBody<String>> response = publicKeyController.returnPublicKey();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}