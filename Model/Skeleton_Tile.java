package Model;

abstract public class Skeleton_Tile extends Finding_Tile{


    Skeleton_Tile(String str,Which_par ToSet) {
        super(str);
        setThis_Tile_is(ToSet);
    }


    protected enum Which_par {Up,Down}

    /**
     * Stores which part is this skeleton tile , big or small.
     */
    private Which_par This_Tile_is;

    /**
     * @pre skeleton must be initialized before hand
     * @param ToSet is to set which part is this tile
     */
    public void setThis_Tile_is (Which_par ToSet) {
        This_Tile_is = ToSet;
    }

    /**
     *  @pre skeleton must be initialized before hand and which_par must be initialized
     * @return the state of This_Tile_is AKA which part is this tile
     */
    public Which_par getThis_Tile_is(){
        return This_Tile_is;
    }

    /**
     *
     * @return Up state for comparing purposes
     */
    public Which_par Upper_skeleton_enum(){
        return Which_par.Up;
    }

    /**
     * @return Down state for comparing purposes
     */
    public Which_par Down_skeleton_enum(){
        return Which_par.Down;
    }

    /**
     *
     * @return the string containing the skeleton type
     */
    public abstract String Skeleton_Type();
}
