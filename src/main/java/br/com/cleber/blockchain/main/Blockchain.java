package br.com.cleber.blockchain.main;

import br.com.cleber.blockchain.utils.BlockchainUtils;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Blockchain implements Serializable {

    private List<Block> chain = new ArrayList<>();
    private transient List<Transaction> transactionsQueue = new ArrayList<>();

    public Blockchain() {
        Block block = new Block(chain.size(), System.currentTimeMillis(), 1, "0");
        this.chain.add(block);
    }

    /**
     * retorna o hash de um determinado bloco
     * @param block
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String hash(Block block) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        Gson gson = new Gson();
        return BlockchainUtils.bytesToHex(digest.digest(gson.toJson(block).getBytes()));
    }

    /**
     * Cria um novo bloco
     * @param proof
     * @return
     * @throws NoSuchAlgorithmException
     */
    public Block createBlock(long proof) throws NoSuchAlgorithmException {
        Block block = new Block(this.chain.size(), System.currentTimeMillis(), proof, this.hash(this.chain.get(this.chain.size()-1)));
        block.getTransactions().addAll(this.transactionsQueue);
        this.transactionsQueue.clear();
        this.chain.add(block);
        return block;
    }

    /**
     * adiciona uma nova transação
     *
     * @param transaction
     * @return
     */
    public int addTransaction(Transaction transaction) {
        transactionsQueue.add(transaction);
        return transactionsQueue.indexOf(transaction);
    }


    public boolean isChainValid() {
        return false;
    }
}
