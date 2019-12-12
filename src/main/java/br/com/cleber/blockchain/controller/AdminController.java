package br.com.cleber.blockchain.controller;

import br.com.cleber.blockchain.main.Blockchain;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    private Blockchain blockchain;

    @Autowired
    public AdminController(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @GetMapping(value = "chain", produces = "application/json")
    public ResponseEntity<String> chain() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String response = gson.toJson(blockchain);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
