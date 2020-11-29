package com.example.pokedex.cli;

public class HelpHandler implements ICommandCallbackHandler {
    public void call(String[] args) {
        System.out.println("Usage: pokedex <pokemon id> [sqlite database url]");
    }
    
}
