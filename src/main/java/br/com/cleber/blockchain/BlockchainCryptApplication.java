package br.com.cleber.blockchain;

import br.com.cleber.blockchain.main.Blockchain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlockchainCryptApplication {

    private Blockchain blockchain = new Blockchain();

    public static void main(String[] args) {
        SpringApplication.run(BlockchainCryptApplication.class, args);
    }

    @Bean("blockchain")
    public Blockchain blockchain(){
        return this.blockchain;
    }

}
