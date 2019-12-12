package br.com.cleber.blockchain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Message implements Serializable {
    private String message;
    private int errorCode;

    public Message(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
