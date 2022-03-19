package br.com.akinicchi.authentication_authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public-key")
public class PublicKeyController {

    @GetMapping
    public ResponseEntity<String> returnPublicKey() {
        return ResponseEntity.status(HttpStatus.OK).body("<---- PUBLIC KEY RETURN ---->");
    }
}
