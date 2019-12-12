package br.com.cleber.blockchain.service;

import br.com.cleber.blockchain.config.BlockchainConfigs;
import br.com.cleber.blockchain.main.Blockchain;
import br.com.cleber.blockchain.utils.BlockchainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class MineService {

    private Blockchain blockchain;

    @Autowired
    public MineService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    /**
     * Proof-of-Work da mineração
     *
     * @param previousProof
     * @return
     * @throws NoSuchAlgorithmException
     */
    public long proofOfWork(long previousProof) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        System.out.println(blockchain.getChain());
        long newProof = 1;
        boolean checkProof = false;
        long start = System.currentTimeMillis();
        while (!checkProof && (System.currentTimeMillis() - start) < 1000) {
            String hashOperation = BlockchainUtils.bytesToHex(digest.digest(String.valueOf(Math.pow(newProof, previousProof)).getBytes()));
            System.out.println("New Proof: " + newProof + ", Hash Operation: " + hashOperation);
            if (hashOperation.substring(0, 4).chars().filter(ch -> ch == '0').count() == BlockchainConfigs.difficulty) {
                checkProof = true;
            } else {
                newProof++;
            }
        }
        return checkProof ? newProof : -1;
    }



}
