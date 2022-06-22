package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * @class Bag will hold the 135 tiles and will initialise them
 */
public class Bag {

    /**
     * @param rand takes the seed of time for the most effective random
     */
    static Random rand = new Random(System.currentTimeMillis());

    /**
     * @param bag_tiles stores all the tiles
     */
    static ArrayList<Tile> bag_tiles = new ArrayList<Tile>();

    /**
     * @post initializes the bag calling the initialize funxtion
     */
    public Bag(){
        initialise_bag();

    }

    /**
     * @post initialises the bag with all the 135 components
     */
    void initialise_bag(){
        for (int i=0;i<9;i++){
            bag_tiles.add(new Mosaic_Tile("resources/images/mosaic_green.png",1));
            bag_tiles.add(new Mosaic_Tile("resources/images/mosaic_yellow.png",2));
            bag_tiles.add(new Mosaic_Tile("resources/images/mosaic_red.png",3));
        }
        for (int i=0;i<12;i++){
            bag_tiles.add(new Caryad_Statue_Tile("resources/images/caryatid.png"));
            bag_tiles.add(new Sphinx_Statue_Tile("resources/images/sphinx.png"));
        }
        for (int i=0;i<24;i++){
            bag_tiles.add(new Landslide_Tile("resources/images/landslide.png"));
        }
        for (int i=0;i<5;i++){
            bag_tiles.add(new Big_Skeleton_Tile("resources/images/skeleton_big_top.png", Skeleton_Tile.Which_par.Up));
            bag_tiles.add(new Big_Skeleton_Tile("resources/images/skeleton_big_top.png", Skeleton_Tile.Which_par.Up));

            bag_tiles.add(new Big_Skeleton_Tile("resources/images/skeleton_big_bottom.png", Skeleton_Tile.Which_par.Down));
            bag_tiles.add(new Big_Skeleton_Tile("resources/images/skeleton_big_bottom.png", Skeleton_Tile.Which_par.Down));

            bag_tiles.add(new Small_Skeleton_Tile("resources/images/skeleton_small_top.png", Skeleton_Tile.Which_par.Up));
            bag_tiles.add(new Small_Skeleton_Tile("resources/images/skeleton_small_bottom.png", Skeleton_Tile.Which_par.Down));
        }

        for (int i=0;i<5;i++){
            bag_tiles.add(new Amphora_Tile("resources/images/amphora_blue.png",1));
            bag_tiles.add(new Amphora_Tile("resources/images/amphora_brown.png",2));
            bag_tiles.add(new Amphora_Tile("resources/images/amphora_red.png",3));
            bag_tiles.add(new Amphora_Tile("resources/images/amphora_green.png",4));
            bag_tiles.add(new Amphora_Tile("resources/images/amphora_yellow.png",5));
            bag_tiles.add(new Amphora_Tile("resources/images/amphora_purple.png",6));

        }

        System.out.println("Bag initiated successfully total tiles in bag : "+bag_tiles.size());

    }


    /**
     * @pre there must be tiles in the bag still
     * @post after returning the tile it deletes it from the bag
     * @return returns the tile
     * @throws Exception if 0 tiles are in the bag and someone tries to draw
     */
    public Tile gettilefromBag() throws  Exception{
        if (bag_tiles.size()==0){
            throw new Exception("EmptyBagException");
        }
        //chooses a number randomly from 0 till the max size of the bag
        int i = rand.nextInt(bag_tiles.size());
        Tile to_ret=bag_tiles.get(i);
        bag_tiles.remove(i);



        return  to_ret;
    }

    /**
     * @pre a tile is given
     * @param to_put is the tile to be put in the bag
     * @post a tile is put in the bag
     */
    public void tobag(Tile to_put){
        bag_tiles.add(to_put);
    }


    /**
     * @pre bag must be initialized
     * @return how many tiles are left in the bag
     */
    public int how_many_left(){
        return bag_tiles.size();
    }

    /**
     * @pre must be only called in the 4 for tiles
     * @param i is which tile will be returned
     * @return a tile is returned
     */
    public Tile initialize_draw(int i){
        Tile to_ret=bag_tiles.get(i);
        bag_tiles.remove(i);
        System.out.println(to_ret.getPicture());
        return to_ret;
    }

}
