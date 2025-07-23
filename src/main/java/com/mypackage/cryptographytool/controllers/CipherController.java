package com.mypackage.cryptographytool.controllers;

import com.mypackage.cryptographytool.models.CipherRequest;
import com.mypackage.cryptographytool.service.CipherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cipher")
@CrossOrigin(origins = "*")
public class CipherController {

    @Autowired
    private CipherService cipherService;

    @PostMapping
    public String handleCipher(@RequestBody CipherRequest request) {
        return cipherService.process(request);
    }

}