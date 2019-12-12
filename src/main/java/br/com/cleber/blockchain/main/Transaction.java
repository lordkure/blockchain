package br.com.cleber.blockchain.main;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Transaction {
    private String sender;
    private String receiver;
    private Object data;
    private double amount;
}
