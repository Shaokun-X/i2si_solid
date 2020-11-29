package com.example.pokedex;

import com.example.pokedex.cli.ErrorHandler;
import com.example.pokedex.cli.HelpHandler;
import com.example.pokedex.cli.ICommandParser;
import com.example.pokedex.cli.OnePositionalHandler;
import com.example.pokedex.cli.SimpleCommandParser;
import com.example.pokedex.cli.TwoPositionalHandler;

public class Pokedex {
    public static void main(String[] args) {
        ICommandParser parser = new SimpleCommandParser(
            new OnePositionalHandler(),
            new TwoPositionalHandler(),
            new HelpHandler(),
            new ErrorHandler()
        );
        parser.parse(args);
    }
}
