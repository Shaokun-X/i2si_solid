package com.example.pokedex.cli;

public interface ICommandParser {

    /**
     * Set the handler that prompts help infomation.
     * @param handler
     */
    public void setHelpHandler(ICommandCallbackHandler handler);
    
    /**
     * Set the handler that deals with unexpected arguments input.
     * 
     * @param handler
     */
    public void setErrorHandler(ICommandCallbackHandler handler);
    public void parse(String[] args);
}
