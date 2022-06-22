package Model;

public class Big_Skeleton_Tile extends Skeleton_Tile{
    Big_Skeleton_Tile(String str, Which_par ToSet) {
        super(str, ToSet);
    }

    /**
     *
     * @return the type of the skeleton
     */
    public String Skeleton_Type() {
        return "Big_Skeleton";
    }
}
