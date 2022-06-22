package Model;

abstract public class Statue_Tile extends Finding_Tile{
    Statue_Tile(String str) {
        super(str);
    }

    /**
     *
     * @return the string containing the type of the statue
     */
    abstract String StatueType();
}
