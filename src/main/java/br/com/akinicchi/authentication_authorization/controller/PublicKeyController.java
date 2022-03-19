package br.com.akinicchi.authentication_authorization.controller;

import br.com.akinicchi.authentication_authorization.utils.ConstantUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantUtil.PUBLIC_KEY_URL)
public class PublicKeyController {

    @GetMapping
    public ResponseEntity<String> returnPublicKey() {
        return ResponseEntity.status(HttpStatus.OK).body(ConstantUtil.MOCK_RETURN_PUBLIC_KEY);
    }
}
