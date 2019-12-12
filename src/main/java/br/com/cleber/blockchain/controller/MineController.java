package br.com.cleber.blockchain.controller;

import br.com.cleber.blockchain.main.Block;
import br.com.cleber.blockchain.main.Blockchain;
import br.com.cleber.blockchain.service.MineService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("mine")
public class MineController {

    private Blockchain blockchain;
    private MineService mineService;

    @Autowired
    public MineController(Blockchain blockchain, MineService mineService) {
        this.blockchain = blockchain;
        this.mineService = mineService;
    }

    @GetMapping("mining")
    public ResponseEntity<String> mining() throws NoSuchAlgorithmException {
        long previousProof = blockchain.getChain().get(blockchain.getChain().size() - 1).getProof();
        long proof = mineService.proofOfWork(previousProof);
        if (proof != -1) {
            Block blockCreated = blockchain.createBlock(proof);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return new ResponseEntity<>(gson.toJson(blockCreated), HttpStatus.OK);
        }
    }

}
