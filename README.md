# Pokedex

## Build

```shell
$ ./gradlew build
```

## Usage

```shell
$ java -jar Pokedex.jar <pokemon id> [sqlite database url]
```

Examples:

```shell
$ java -jar Pokedex.jar 1

# output:

# Pokemon # 1
# Name : bulbasaur
# Weight : 69
# Height : 7

$ java -jar Pokedex.jar 4 ./pokemondatabase.sqlite

# output:

# Pokemon # 4
# Name : Salamèche
# Weight : 85
# Height : 6
# Description : Il préfère les endroits chauds. En cas de pluie, de la vapeur se forme autour de sa queue

```

## S.O.L.I.D. in Practice
### 1. Single-responsibility Principle

In this project, the functionalities have been divided into:
- [**adaptor**](./src/main/java/com/example/pokedex/adpator), those who retrieve pokemon data
- [**entity**](./src/main/java/com/example/pokedex/entity), the static data models
- [**outputter**](./src/main/java/com/example/pokedex/outputter), those who output data
- [**command parser**](./src/main/java/com/example/pokedex/cli), those who parse input args
- [**command handler**](./src/main/java/com/example/pokedex/cli), those who execute the command

Every single class with these differen roles has been asigned single responsibility. For example, the [`ConsolePokemonOutputter`](./src/main/java/com/example/pokedex/outputter/ConsolePokemonOutputter.java) class is only responsible for print pokemon data into standard system out, the [`PokeApiHttpPokemonAdaptor`](./src/main/java/com/example/pokedex/adpator/PokeApiHttpPokemonAdaptor.java) class takes the duty only to get pekemon data from the [pokeapi](https://pokeapi.co/api/v2/pokemon/) site.


### 2. Open-closed principle
By introducing `interface` to every role, the classes would be open to extention and close to modification. For example if there is another set of web APIs that provides pokemon data, instead of modifying [`PokeApiHttpPokemonAdaptor`](./src/main/java/com/example/pokedex/adpator/PokeApiHttpPokemonAdaptor.java) directly, we can make another implementaion of interface [`IPokemonAdaptor`](./src/main/java/com/example/pokedex/adpator/IPokemonAdaptor.java).

```java
public interface AnotherApiPokemonAdaptor implements IpokemonAdaptor{
    @Overrride
    public Pokemon getPokemon(int id) {
        // other implementation here
    }
}
```

Furthermore, if we want to extend function of [`PokeApiHttpPokemonAdaptor`](./src/main/java/com/example/pokedex/adpator/PokeApiHttpPokemonAdaptor.java), we can create an subclass of it and add new functions:

```java
public interface AugmentedApiPokemonAdaptor extends PokeApiPokemonAdaptor {
    public Pokemon getPokemon(int id) {
        // implementation
    }
    public Pokemon getPokemonByName(String name) {
        // implementation
    }
    public Pokemon getPokemonBy4thGenerationId(int id) {
        // implementation
    }
}
```

### 3. Liskov substitution principle
Since each role relies on the abstraction (interface) of each other, even when implementation of the interface is replaced with something different, every part would still work properly. Therefore in the case we extend a more complex command parser `ComplexCommandParser` from [`SimpleCommandParser`](./src/main/java/com/example/pokedex/cli/SimpleCommandParser.java):

```java
public class ComplexCommandParser extends SimpleCommandParser {
    @Override
    public void parse(String[] args) {
        // code
    }
}
```
In the [`Pokedex`](./src/main/java/com/example/pokedex/Pokedex.java) main function, replace the `SimpleCommandParser` with its subclass `ComplexCommandParser`:

```java
    public static void main(String[] args) {
        ICommandParser parser = new ComplexCommandParser(
            new OnePositionalHandler(),
            new TwoPositionalHandler(),
            new HelpHandler(),
            new ErrorHandler()
        );
        parser.parse(args);
    }
```

The app should still work.

### 4. Interface segregation principle
Assume that one day the application should able to not only retrieve data from source, but also push data to the source. Then we would need to separate the [`IPokemonAdaptor`](./src/main/java/com/example/pokedex/adpator/IPokemonAdaptor.java) to two interfaces `IPullPokemonAdaptor` and `IPushPokemonAdaptor`.

```java
public interface IPullPokemonAdaptor {
    public Pokemon getPokemon(int id){
        // ...
    }
}

public interface IPushPokemonAdaptor {
    public void putPokemon(Pokemon pokemon) {
        // ...
    }
}
```

### 5. Dependency Inversion Principle
As mentioned before, every role denpends on abstraction istead of implementation of each other. For example take a look at [`SimpleCommandParser`](./src/main/java/com/example/pokedex/cli/SimpleCommandParser.java), it totally depends on the interface of four command callback handlers, and the dependencies are injected in [`Pokedex`](./src/main/java/com/example/pokedex/Pokedex.java).

```java
// SimpleCommandParser.java
public class SimpleCommandParser implements ICommandParser {
    private ICommandCallbackHandler onePosHandler;
    private ICommandCallbackHandler twoPosHandler;
    private ICommandCallbackHandler helpHandler;
    private ICommandCallbackHandler errorHandler;
    // ...
}

// Pokedex.java
public static void main(String[] args) {
    ICommandParser parser = new SimpleCommandParser(
        new OnePositionalHandler(),
        new TwoPositionalHandler(),
        new HelpHandler(),
        new ErrorHandler()
    );
    parser.parse(args);
}
```