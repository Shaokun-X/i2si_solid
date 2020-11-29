package com.example.pokedex.adpator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.pokedex.entity.Pokemon;


public class SqlPokemonAdaptor implements IPokemonAdaptor {
    private String query = "SELECT * FROM pokemons WHERE id = ?";
    private Connection connection;
    private String url;
    private PreparedStatement statement;

    /**
     * Table name must be {@code pokemons} and complies with {@link com.example.pokedex.entity.Pokemon}
     *
     * @param url  
     */
    public SqlPokemonAdaptor(String url) {
        this.url = url;
    }
    
    @Override
    public Pokemon getPokemon(int id) {
        Pokemon pokemon = null;
        try {
            prepareQuery(id);
            ResultSet result = executeQuery();
            pokemon = mapResultToPokemon(result);
        } catch (SQLException e) {
            //TODO: handle exception
        }
        return pokemon;
    }

    private Pokemon mapResultToPokemon(ResultSet result) throws NumberFormatException, SQLException {
        return new Pokemon(
            Integer.parseInt(result.getString("id")),
            result.getString("name"),
            Integer.parseInt(result.getString("height")),
            Integer.parseInt(result.getString("weight")),
            result.getString("description")
        );
    }

    private ResultSet executeQuery() throws SQLException {
        ResultSet result = statement.executeQuery();
        result.next();
        return result;
    }

    private void prepareQuery(int id) throws SQLException {
        connection = DriverManager.getConnection(url);
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
    }
}
