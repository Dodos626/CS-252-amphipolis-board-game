package Model;

public class Small_Skeleton_Tile extends Skeleton_Tile{
    Small_Skeleton_Tile(String str,Which_par ToSet){
        super(str,ToSet);
    }

    /**
     *
     * @return the type of the skeleton
     */
    public String Skeleton_Type() {
        return "Small_Skeleton";
    }
}
