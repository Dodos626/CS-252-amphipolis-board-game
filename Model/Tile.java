package Model;
import java.lang.*;

public abstract class Tile {
    /**
     * @param picture
     * is used to store the string of the picture of the tile
     */
    private String picture;

    /**
     * @param str is set as the picture at the constructor
     */
    Tile(String str){
        setPicture(str);
    }
    /**
     *
     * @param str
     * is set as the picture of the tile
     */
    void setPicture(String str) {
        picture = str;
    }

    /**
     *
     * @return the string of the picture of tile
     */
    public String getPicture(){
        return  picture;
    }
}
