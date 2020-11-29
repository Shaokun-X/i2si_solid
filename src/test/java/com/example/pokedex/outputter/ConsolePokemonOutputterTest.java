package com.example.pokedex.outputter;

import com.example.pokedex.entity.Pokemon;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;


public class ConsolePokemonOutputterTest {
    
    @Test
    public void shouldPrintPokemon() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        IPokemonOutputter outputter = new ConsolePokemonOutputter();
        Pokemon pokemon = new Pokemon(5, "Test", 11, 190, "");
        outputter.output(pokemon);

        // line separator is platform dependant
        String separator = System.lineSeparator();
        String expectedOutput = "Pokemon # 5" + separator
            + "Name : Test" + separator
            + "Weight : 190" + separator
            + "Height : 11" + separator;
        String outContent = out.toString();

        Assert.assertEquals(expectedOutput, outContent.toString());
    }
}
