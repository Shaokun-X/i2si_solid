package com.example.pokedex.cli;

public class ErrorHandler implements ICommandCallbackHandler{
    public void call(String[] args) {
        System.out.println("Usage: pokedex <pokemon id> [sqlite database url]");
    }
}
