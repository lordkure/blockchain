package br.com.cleber.blockchain.main;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * bloco básico de dados
 *
 * @author Cleber Santos
 */
@Setter
@Getter
@ToString
public class Block implements Serializable {

    private int index;
    private long timestamp;
    private long proof;
    private String previousHash;
    private List<Transaction> transactions = new ArrayList<>();
//    private String hash;

    public Block(int index, long timestamp, long proof, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.proof = proof;
        this.previousHash = previousHash;
    }
}
