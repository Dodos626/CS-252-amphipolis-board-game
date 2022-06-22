package Model;

public class Amphora_Tile extends Finding_Tile{
    /**
     * @param color is categorised by
     * 1 == blue
     * 2 == brown
     * 3 == red
     * 4 == green
     * 5 == yellow
     * 6 == purple
     */
    private int color;

    /**
     *
     * @param str is given as the image in the super constructor
     * @param color_to_set is set as color of the amphora
     * @post initializes the amphora
     */
    Amphora_Tile(String str,int color_to_set) {
        super(str);
        setColor(color_to_set);
    }

    /**
     * @pre ToSet must be an integer
     * @param ToSet is set as color for the amphora tile
     * @post gives to the amphora instance the ToSet
     */
    void setColor(int ToSet){
        color = ToSet;
    }

    /**
     * @return amphora tile color
     */
    int getColor(){
        return color;
    }
}
