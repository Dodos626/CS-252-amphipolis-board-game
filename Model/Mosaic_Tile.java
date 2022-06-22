package Model;

public class Mosaic_Tile extends Finding_Tile{
    /**
     * color is categorised by
     * 1 == green
     * 2 == yellow
     * 3 == red
     */
    private int color;
    Mosaic_Tile(String str,int color_to_set) {
        super(str);
        setColor(color_to_set);
    }

    /**
     * @pre the mosaic instance must be initialized
     * @param to_set is set as mosaic tile color
     */
    void setColor(int to_set){
        color = to_set;
    }

    /**
     * @pre the mosaic instance must be initialized
     * @return the color of the mosaic tile
     */
    int getColor(){
        return color;
    }
}
