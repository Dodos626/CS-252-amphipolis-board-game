package Model;

import java.util.ArrayList;

public class Player {
    /**
     * @param ID is which players is this
     */
    private int ID;
    /**
     * @param color is which color is the player
     *              1==yellow
     *              2==red
     *              3==blue
     *              4==black
     */
    private int color;

    /**
     * @param draw_region_lock will lock the play so he cant draw from 2 different regions
     *                         0==didnt draw yet
     *                         1==top left corner
     *                         2==top right corner
     *                         3==bottom left corner
     *                         4==bottom right corner
     */
    public int draw_region_lock=0;
    public String last_region="";
    public int draws_left=2;

    /**
     * @param has_drawn holds if the player has drawn
     */
    private boolean has_drawn;
    /**
     * @param playersTiles holds the tiles in the players hold
     */
    ArrayList<Finding_Tile> playersTiles = new ArrayList<Finding_Tile>();

    /**
     * @param playersCharacters holds the characters of the player
     *                          0 == Assistant
     *                          1 == Archaeologist
     *                          2 == Digger
     *                          3 == Professor
     */
    public ArrayList<Character> playersCharacters = new ArrayList<Character>();

    /**
     *
     * @param id_in is given as player id
     * @param color_in is given as player color
     * @post initializes the characters for the player
     */
    public Player(int id_in, int color_in){
        setID(id_in);
        setColor(color_in);
        setPlayersCharacters();
        setHas_drawn(false);
    }


    /**
     * @post initialise the special character cards for the player
     */
    void setPlayersCharacters(){
        playersCharacters.add(new Archaeologist_Character());
        playersCharacters.add(new Professor_Character());
        playersCharacters.add(new Assistant_Character());
        playersCharacters.add(new Digger_Character());


    }




    int getID(){
        return ID;
    }
    void setID(int to_put){
        ID=to_put;
    }

    int getColor(){
        return color;
    }
    void setColor(int to_put){
        color=to_put;
    }

    /**
     * @return if the player has draw this turn
     */
    public boolean get_has_drawn(){return has_drawn;}

    /**
     * @pre given variable must be boolean
     * @param to_put is set as drawn
     * @post successfully sets the draw as to_put
     */
    public void setHas_drawn(boolean to_put){has_drawn=to_put;}

    /**
     * @pre must be given a finding tile child class to avoid taking landslides
     * @param tile is put in the players bag
     */
    public void to_players_bag(Finding_Tile tile){
        playersTiles.add(tile);
    }

    /**
     * @return count of how many of this tile are in players possessions
     */

    public int amphora_blue_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Amphora_Tile){
                if (((Amphora_Tile) playersTiles.get(i)).getColor()==1){
                    count++;
                }
            }
        }
        return count;

    }
    public int amphora_brown_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Amphora_Tile){
                if (((Amphora_Tile) playersTiles.get(i)).getColor()==2){
                    count++;
                }
            }
        }
        return count;

    }
    public int amphora_red_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Amphora_Tile){
                if (((Amphora_Tile) playersTiles.get(i)).getColor()==3){
                    count++;
                }
            }
        }
        return count;

    }
    public int amphora_green_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Amphora_Tile){
                if (((Amphora_Tile) playersTiles.get(i)).getColor()==4){
                    count++;
                }
            }
        }
        return count;

    }
    public int amphora_yellow_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Amphora_Tile){
                if (((Amphora_Tile) playersTiles.get(i)).getColor()==5){
                    count++;
                }
            }
        }
        return count;

    }
    public int amphora_purple_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Amphora_Tile){
                if (((Amphora_Tile) playersTiles.get(i)).getColor()==6){
                    count++;
                }
            }
        }
        return count;

    }

    public int sphinx_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Sphinx_Statue_Tile){
                 count++;

            }
        }
        return count;
    }
    public int caryatid_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Caryad_Statue_Tile){
                count++;

            }
        }
        return count;
    }

    public int mosaic_green_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Mosaic_Tile){
                if (((Mosaic_Tile) playersTiles.get(i)).getColor()==1){
                    count++;
                }
            }
        }
        return count;

    }
    public int mosaic_yellow_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Mosaic_Tile){
                if (((Mosaic_Tile) playersTiles.get(i)).getColor()==2){
                    count++;
                }
            }
        }
        return count;

    }
    public int mosaic_red_count(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i=0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Mosaic_Tile){
                if (((Mosaic_Tile) playersTiles.get(i)).getColor()==3){
                    count++;
                }
            }
        }
        return count;

    }

    public int return_skeleton_big_top(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Big_Skeleton_Tile){
                if (((Skeleton_Tile)playersTiles.get(i)).Upper_skeleton_enum()==((Skeleton_Tile) playersTiles.get(i)).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }
    public int return_skeleton_big_bottom(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Big_Skeleton_Tile){
                if (((Skeleton_Tile)playersTiles.get(i)).Down_skeleton_enum()==((Skeleton_Tile) playersTiles.get(i)).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }
    public int return_skeleton_small_top(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Small_Skeleton_Tile){
                if (((Skeleton_Tile)playersTiles.get(i)).Upper_skeleton_enum()==((Skeleton_Tile) playersTiles.get(i)).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }
    public int return_skeleton_small_bottom(){
        if (playersTiles.size()==0){
            return 0;
        }
        int count = 0;
        for (int i = 0;i<playersTiles.size();i++){
            if (playersTiles.get(i) instanceof Small_Skeleton_Tile){
                if (((Skeleton_Tile)playersTiles.get(i)).Down_skeleton_enum()==((Skeleton_Tile) playersTiles.get(i)).getThis_Tile_is()){
                    count ++;
                }
            }
        }
        return count;
    }
}
