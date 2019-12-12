package br.com.cleber.blockchain.controller;

import br.com.cleber.blockchain.main.Blockchain;
import br.com.cleber.blockchain.main.Transaction;
import br.com.cleber.blockchain.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private Blockchain blockchain;

    @Autowired
    public TransactionController(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @PostMapping(value = "send", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Message> send(@RequestBody Transaction transaction) {
        blockchain.getTransactionsQueue().add(transaction);
        return new ResponseEntity<Message>(new Message("Transação efetuada com sucesso!", -1), HttpStatus.OK);
    }

}
