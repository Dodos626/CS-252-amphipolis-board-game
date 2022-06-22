package Model;

import java.util.ArrayList;

public class Board {
    /**
     * @class Board has 5 fields to insert the different tiles
     * @param entrance holds the landslide tile . at entrance_number == 16 the game is finished
     */
    private ArrayList<Landslide_Tile> entrance = new ArrayList<Landslide_Tile>() ;
    private int entrance_number;
    /**
     * @param mosaic holds all the Mosaic_Tile that will enter on board
     */
    private ArrayList<Mosaic_Tile> mosaic = new ArrayList<Mosaic_Tile>();
    /**
     * @param amphora holds all the Amphora_Tile that will enter on board
     */
    private ArrayList<Amphora_Tile> amphora = new ArrayList<Amphora_Tile>();

    /**
     * @param statue holds all the Statue_Tile that will enter on board
     */
    private ArrayList<Statue_Tile> statue = new ArrayList<Statue_Tile>();

    /**
     * @param skeleton holds all the skeletons that will enter on board
     */
    private ArrayList<Skeleton_Tile> skeleton = new ArrayList<Skeleton_Tile>();




    /**
     * @pre board must be initialised before hand
     * @param to_put is put on the entrance
     * @post the count of the entrance goes up by 1
     */
    public void put_tile_on_board(Landslide_Tile to_put){
        entrance.add(to_put);
        entrance_number++;
    }

    /**
     * @pre the board must be initialized
     * @return entrance_number to check if the game is finished
     */
    public int Get_entrance_number (){
        return entrance_number;
    }

    /**
     * @pre board must be initialised before hand
     * @param to_put is added on the on board mosaics
     */
    public void put_tile_on_board(Mosaic_Tile to_put){
        mosaic.add(to_put);

    }

    /**
     * @pre there must be a tile with given color
     * @param color_of_mosaic is the color of the mosaic the player will take
     * @return the mosaic piece that the player choosed
     * @throws Exception if there is no color like the one choosen something went wrong
     */
    public Mosaic_Tile takeMosaic_onboard(int color_of_mosaic) throws Exception{
        for (int i=0;i<mosaic.size();i++){
            if (color_of_mosaic==mosaic.get(i).getColor()){
                Mosaic_Tile to_return = mosaic.get(i);
                mosaic.remove(i);
                return to_return;
            }
        }
        throw new Exception("MosaicWithGivenColorNotFound");
    }

    /**
     * @pre the board must be initialized
     * @param to_put is added on the on board amphora
     */
    public void put_tile_on_board(Amphora_Tile to_put){
        amphora.add(to_put);

    }


    /**
     * @pre there must be a tile with given color
     * @param color_of_amphora is the color of the amphora the player will take
     * @return the amphora piece that the player choosed
     * @throws Exception if there is no color like the one choosen something went wrong
     */
    public Amphora_Tile takeAmphora_onboard(int color_of_amphora) throws Exception{
        for (int i=0;i<amphora.size();i++){
            if (color_of_amphora==amphora.get(i).getColor()){
                Amphora_Tile to_return = amphora.get(i);
                amphora.remove(i);
                return to_return;
            }
        }
        throw new Exception("AmphoraWithGivenColorNotFound");
    }

    /**
     * @pre the board must be initialized
     * @param to_put is put at the statues on board
     */
    public void put_tile_on_board(Statue_Tile to_put){
        statue.add(to_put);
    }

    /**
     * @pre there must be a statue of the given type on board
     * @param to_get is the string with the type of the statue given
     * @return the statue piece that the player selected to take
     * @throws Exception if there is no statue with given type
     */
    public Statue_Tile takeStatue_onboard(String to_get) throws Exception{
        for (int i= 0 ; i<statue.size();i++){
            if (statue.get(i).StatueType().equals(to_get)){
                Statue_Tile to_return = statue.get(i);
                statue.remove(i);
                return to_return;
            }
        }
        throw new Exception("NoSuchStatueOnBoard");
    }

    /**
     * @pre the board must be initialized
     * @param to_put is put at the skeleton on board
     */
    public void put_tile_on_board(Skeleton_Tile to_put){
        skeleton.add(to_put);
    }

    /**
     * @pre there must be a skeleton of the selected type on board
     * @param size is the string with the choosen type of skeleton
     * @param part is which part is the type of skeleton
     * @return the skeleton piece that the player selected to take
     * @throws Exception if there is no skeleton with given type
     */
    public Skeleton_Tile takeSkeleton_onboard(String size,String part) throws Exception{
        String to_get;
        if (size.equals("small")){
            to_get="Small_Skeleton";
        }else{
            to_get="Big_Skeleton";
        }
        Skeleton_Tile.Which_par which_part;
        if (part.equals("down")){
            which_part=Skeleton_Tile.Which_par.valueOf("Down");
        }else{
            which_part=Skeleton_Tile.Which_par.valueOf("Up");
        }


        for (int i=0 ; i<skeleton.size();i++){
            if (skeleton.get(i).Skeleton_Type().equals(to_get) && skeleton.get(i).getThis_Tile_is()==which_part){
                Skeleton_Tile to_return = skeleton.get(i);
                skeleton.remove(i);
                return to_return;
            }
        }
        throw new Exception("NoSuchSkeletonOnBoard");
    }

    /**
     * @pre the board must be initialized
     * @param tile is handled and passed on the right method to put on board
     */
    public void put_tile_on_board(Tile tile) {
        if (tile instanceof Statue_Tile){
            put_tile_on_board((Statue_Tile)tile);
        }else if (tile instanceof Amphora_Tile){
            put_tile_on_board((Amphora_Tile)tile);
        }else if (tile instanceof Landslide_Tile){
            put_tile_on_board((Landslide_Tile)tile);
        }else if (tile instanceof Skeleton_Tile){
            put_tile_on_board((Skeleton_Tile)tile);
        }else{
            put_tile_on_board((Mosaic_Tile)tile);
        }
    }

    /**
     * the next few methods
     * @return the numbers of the tiles on board
     */

    //amphoras
    public int return_amphora_red(){
        if (amphora.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<amphora.size();i++){
            if (amphora.get(i).getColor()==3){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_amphora_blue(){
        if (amphora.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<amphora.size();i++){
            if (amphora.get(i).getColor()==1){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_amphora_brown(){
        if (amphora.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<amphora.size();i++){
            if (amphora.get(i).getColor()==2){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_amphora_green(){
        if (amphora.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<amphora.size();i++){
            if (amphora.get(i).getColor()==4){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_amphora_yellow(){
        if (amphora.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<amphora.size();i++){
            if (amphora.get(i).getColor()==5){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_amphora_purple(){
        if (amphora.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<amphora.size();i++){
            if (amphora.get(i).getColor()==6){
                count ++ ;
            }
        }
        return  count;
    }

    //statue
    public int return_sphinx(){
        if (statue.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<statue.size();i++){
            if (statue.get(i) instanceof Sphinx_Statue_Tile){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_caryatid(){
        if (statue.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<statue.size();i++){
            if (statue.get(i) instanceof Caryad_Statue_Tile){
                count ++ ;
            }
        }
        return  count;
    }

    //mosaic
    public int return_mosaic_green(){
        if (mosaic.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<mosaic.size();i++){
            if (mosaic.get(i).getColor()==1){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_mosaic_yellow(){
        if (mosaic.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<mosaic.size();i++){
            if (mosaic.get(i).getColor()==2){
                count ++ ;
            }
        }
        return  count;
    }
    public int return_mosaic_red(){
        if (mosaic.size()==0){
            return 0;
        }
        int count = 0 ;
        for (int i=0;i<mosaic.size();i++){
            if (mosaic.get(i).getColor()==3){
                count ++ ;
            }
        }
        return  count;
    }

    //skeleton
    public int return_skeleton_big_top(){
        if (skeleton.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<skeleton.size();i++){
            if (skeleton.get(i) instanceof Big_Skeleton_Tile){
                if (skeleton.get(i).Upper_skeleton_enum()==skeleton.get(i).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }
    public int return_skeleton_big_bottom(){
        if (skeleton.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<skeleton.size();i++){
            if (skeleton.get(i) instanceof Big_Skeleton_Tile){
                if (skeleton.get(i).Down_skeleton_enum()==skeleton.get(i).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }
    public int return_skeleton_small_top(){
        if (skeleton.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<skeleton.size();i++){
            if (skeleton.get(i) instanceof Small_Skeleton_Tile){
                if (skeleton.get(i).Upper_skeleton_enum()==skeleton.get(i).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }
    public int return_skeleton_small_bottom(){
        if (skeleton.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<skeleton.size();i++){
            if (skeleton.get(i) instanceof Small_Skeleton_Tile){
                if (skeleton.get(i).Down_skeleton_enum()==skeleton.get(i).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }


}
