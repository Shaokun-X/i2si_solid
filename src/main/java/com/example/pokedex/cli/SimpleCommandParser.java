package com.example.pokedex.cli;

public class SimpleCommandParser implements ICommandParser {
    private ICommandCallbackHandler onePosHandler;
    private ICommandCallbackHandler twoPosHandler;
    private ICommandCallbackHandler helpHandler;
    private ICommandCallbackHandler errorHandler;

    public SimpleCommandParser() {}
    public SimpleCommandParser(ICommandCallbackHandler onePosHandler, ICommandCallbackHandler twoPosHandler,
        ICommandCallbackHandler helpHandler, ICommandCallbackHandler erroHandler) {
        this.onePosHandler = onePosHandler;
        this.twoPosHandler = twoPosHandler;
        this.helpHandler = helpHandler;
        this.errorHandler = erroHandler;    
    }
    
    public void parse(String[] args) {
        if (args.length == 1) {
            // call api
            if (args[0] == "-h" || args[0] == "--help") {
                helpHandler.call(args);
                return;
            }
            onePosHandler.call(args);
        } else if (args.length == 2) {
            // query database
            twoPosHandler.call(args);
        } else {
            errorHandler.call(args);
            return;
        }
    }

    public void setHelpHandler(ICommandCallbackHandler handler) {
        helpHandler = handler;
    }
    
    public void setErrorHandler(ICommandCallbackHandler handler) {
        errorHandler = handler;
    }

    public void setOnePosHandler(ICommandCallbackHandler onePosHandler) {
        this.onePosHandler = onePosHandler;
    }

    public void setTwoPosHandler(ICommandCallbackHandler twoPosHandler) {
        this.twoPosHandler = twoPosHandler;
    }
}
