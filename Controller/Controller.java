package Controller;
import View.*;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
public class Controller {


    public Board board;
    public Bag bag;
    public Player []players = new Player[4];
    public View view;
    public int current_player = 0;
    /**
     * @param character_was_used holds if a character chard was played this turn
     */
    private boolean character_was_used=false;

    /**
     * @param archaologist_used holds if the card archaologist was played
     *                          0 is for not played
     *                          1 is for played with last region 1
     *                          2 is for played with last region 2
     *                          3 is for played with last region 3
     *                          4 is for played with last region 4
     */
    private int archaologist_used=0;
    /**
     * @param proffessor_used holds in 1 position if the card was used this turn
     *                        positions 2,3,4,5 if the last played region was the 1,2,3 or 4
     */
    private int []proffessor_used={0,0,0,0,0};

    /**
     * @post it will initialise the board and tie the view with the model . it will also start the game
     */
    public void initialize()  {
        bag=new Bag();
        board = new Board();

        Random rand = new Random(System.currentTimeMillis());
        int i ;

        i = rand.nextInt(27);
        board.put_tile_on_board(bag.initialize_draw(i));
        i = rand.nextInt(24)+26;
        board.put_tile_on_board(bag.initialize_draw(i));
        i = rand.nextInt(30)+26+23+24;
        board.put_tile_on_board(bag.initialize_draw(i));
        i= rand.nextInt(30)+29+26+23+24;
        board.put_tile_on_board(bag.initialize_draw(i));



        players[0]=new Player(1,1);
        players[1]=new Player(2,2);
        players[2]=new Player(3,3);
        players[3]=new Player(4,4);

        view = new View();
        view.update_board_after_draw(board);
        setListeners();

    }

    /**
     * @pre the buttons must be beforehand be initialised
     * @post it sets the listeners from every button in the game
     */
    public void setListeners(){

        view.draw_tiles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //checks if the player has drawn this turn
                if (!players[current_player].get_has_drawn()){
                    //if not draws 4 pieces
                    for (int i = 0 ; i < 4 ; i++){
                        Tile tile= null;

                        try {
                            tile = bag.gettilefromBag();
                            board.put_tile_on_board(tile);
                            System.out.println(tile.getPicture());
                            view.tiles_left.setText("Tiles Remaining : "+bag.how_many_left());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        //sets the has drawn to true
                        players[current_player].setHas_drawn(true);
                        //updates the board after draw
                        view.update_board_after_draw(board);
                        IsGameFinished();
                    }
                }else{
                    //otherwise it plays an error message
                    JOptionPane.showMessageDialog(null, "You have already draw this turn!!!");
                }
            }
        });
        view.end_turn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (players[current_player].get_has_drawn()){
                    //resets the region lock for the player
                    players[current_player].draw_region_lock=0;
                    players[current_player].draws_left=2;
                    players[current_player].setHas_drawn(false);
                    character_was_used=false;
                    if (current_player==3){
                        current_player=0;
                    }else{
                        current_player++;
                    }
                    view.players_turn.setText("Current Player "+(current_player+1));
                    view.update_player_sidebar(players[current_player]);
                    archaologist_used=0;
                    IsGameFinished();
                }else{
                    JOptionPane.showMessageDialog(null, "You must draw tiles before ending your turn");
                }

            }
        });


        view.character_cards[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!character_was_used){
                    if (!players[current_player].playersCharacters.get(0).isIs_used()){
                        if (players[current_player].draw_region_lock!=0){
                            players[current_player].playersCharacters.get(0).character_ability(players[current_player]);
                            view.update_player_sidebar(players[current_player]);
                            archaologist_used=players[current_player].draw_region_lock;
                            players[current_player].draw_region_lock=0;
                            players[current_player].draws_left=2;
                            proffessor_used[0]=0;
                            for (int i = 0 ; i <4 ; i++){
                                proffessor_used[i+1]=0;
                            }
                            character_was_used=true;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You need to pick a tile before using this character");
                        }


                    }else{
                        JOptionPane.showMessageDialog(null, "This character is already used");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "A character was already used this turn");
                }

            }

        });
        view.character_cards[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!character_was_used){
                    if (!players[current_player].playersCharacters.get(1).isIs_used()){
                        players[current_player].playersCharacters.get(1).character_ability(players[current_player]);
                        view.update_player_sidebar(players[current_player]);
                        archaologist_used=0;
                        proffessor_used[0]=3;
                        for (int i = 0 ; i <4 ; i++){
                            proffessor_used[i+1]=1;
                        }
                        proffessor_used[players[current_player].draw_region_lock]=0;
                        character_was_used=true;
                    }else{
                        JOptionPane.showMessageDialog(null, "This character is already used");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "A character was already used this turn");
                }


            }
        });
        view.character_cards[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!character_was_used){
                    if (!players[current_player].playersCharacters.get(2).isIs_used()){
                        players[current_player].playersCharacters.get(2).character_ability(players[current_player]);
                        view.update_player_sidebar(players[current_player]);
                        archaologist_used=0;
                        proffessor_used[0]=0;
                        for (int i = 0 ; i <4 ; i++){
                            proffessor_used[i+1]=0;
                        }
                        character_was_used=true;
                    }else{
                        JOptionPane.showMessageDialog(null, "This character is already used");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "A character was already used this turn");
                }


            }
        });
        view.character_cards[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!character_was_used){
                    if (!players[current_player].playersCharacters.get(3).isIs_used()){
                        players[current_player].playersCharacters.get(3).character_ability(players[current_player]);
                        view.update_player_sidebar(players[current_player]);
                        archaologist_used=0;
                        proffessor_used[0]=0;
                        for (int i = 0 ; i <4 ; i++){
                            proffessor_used[i+1]=0;
                        }
                        character_was_used=true;
                    }else{
                        JOptionPane.showMessageDialog(null, "This character is already used");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "A character was already used this turn");
                }


            }
        });

        view.statue_tiles[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[2]==1){
                        players[current_player].to_players_bag(board.takeStatue_onboard("Caryad"));
                        System.out.println("success in taking caryad");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[2]--;
                    }
                    else if (archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==2){
                                players[current_player].to_players_bag(board.takeStatue_onboard("Caryad"));
                                System.out.println("success in taking caryad");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="statue";
                                players[current_player].draw_region_lock=2;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=2){
                            players[current_player].to_players_bag(board.takeStatue_onboard("Caryad"));
                            System.out.println("success in taking caryad");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="statue";
                            players[current_player].draw_region_lock=2;
                            players[current_player].draws_left--;
                            archaologist_used=0;

                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.statue_tiles[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[2]==1){
                        players[current_player].to_players_bag(board.takeStatue_onboard("Sphinx"));
                        System.out.println("success in taking sphinx");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[2]--;
                    }
                    else if (archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==2){
                                players[current_player].to_players_bag(board.takeStatue_onboard("Sphinx"));
                                System.out.println("success in taking sphinx");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="statue";
                                players[current_player].draw_region_lock=2;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=2){
                            players[current_player].to_players_bag(board.takeStatue_onboard("Sphinx"));
                            System.out.println("success in taking sphinx");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="statue";
                            players[current_player].draw_region_lock=2;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });



        view.mosaic_tiles[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[1]==1){
                        players[current_player].to_players_bag(board.takeMosaic_onboard(1));
                        System.out.println("success in taking green mosaic");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[1]--;
                    }
                    else if (archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==1){
                                players[current_player].to_players_bag(board.takeMosaic_onboard(1));
                                System.out.println("success in taking green mosaic");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].draw_region_lock=1;
                                players[current_player].last_region="mosaic";
                                players[current_player].draws_left--;
                                archaologist_used=0;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=1){
                            players[current_player].to_players_bag(board.takeMosaic_onboard(1));
                            System.out.println("success in taking green mosaic");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].draw_region_lock=1;
                            players[current_player].last_region="mosaic";
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.mosaic_tiles[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[1]==1){
                        players[current_player].to_players_bag(board.takeMosaic_onboard(2));
                        System.out.println("success in taking yellow mosaic");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[1]--;
                    }
                    else if (archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==1){
                                players[current_player].to_players_bag(board.takeMosaic_onboard(2));
                                System.out.println("success in taking yellow mosaic");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].draw_region_lock=1;
                                players[current_player].last_region="mosaic";
                                players[current_player].draws_left--;
                                archaologist_used=0;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=1){
                            players[current_player].to_players_bag(board.takeMosaic_onboard(2));
                            System.out.println("success in taking green mosaic");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].draw_region_lock=1;
                            players[current_player].last_region="mosaic";
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.mosaic_tiles[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[1]==1){
                        players[current_player].to_players_bag(board.takeMosaic_onboard(3));
                        System.out.println("success in taking red mosaic");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[1]--;
                    }
                    else if (archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==1){
                                players[current_player].to_players_bag(board.takeMosaic_onboard(3));
                                System.out.println("success in taking red mosaic");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].draw_region_lock=1;
                                players[current_player].last_region="mosaic";
                                players[current_player].draws_left--;
                                archaologist_used=0;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=1){
                            players[current_player].to_players_bag(board.takeMosaic_onboard(3));
                            System.out.println("success in taking green mosaic");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].draw_region_lock=1;
                            players[current_player].last_region="mosaic";
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });



        view.skeleton_tiles[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[4]==1){
                        players[current_player].to_players_bag(board.takeSkeleton_onboard("big","up"));
                        System.out.println("success in taking big top skeleton");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[4]--;
                        proffessor_used[0]--;
                    }
                    else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==4){
                                players[current_player].to_players_bag(board.takeSkeleton_onboard("big","up"));
                                System.out.println("success in taking big top skeleton");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Skeleton";
                                players[current_player].draw_region_lock=4;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=4){
                            players[current_player].to_players_bag(board.takeSkeleton_onboard("big","up"));
                            System.out.println("success in taking big top skeleton");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Skeleton";
                            players[current_player].draw_region_lock=4;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.skeleton_tiles[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[4]==1){
                        players[current_player].to_players_bag(board.takeSkeleton_onboard("big","down"));
                        System.out.println("success in taking big down skeleton");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[4]--;
                        proffessor_used[0]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==4){
                                players[current_player].to_players_bag(board.takeSkeleton_onboard("big","down"));
                                System.out.println("success in taking big down skeleton");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Skeleton";
                                players[current_player].draw_region_lock=4;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=4){
                            players[current_player].to_players_bag(board.takeSkeleton_onboard("big","down"));
                            System.out.println("success in taking big down skeleton");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Skeleton";
                            players[current_player].draw_region_lock=4;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.skeleton_tiles[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (proffessor_used[0]!=0 && proffessor_used[4]==1){
                        players[current_player].to_players_bag(board.takeSkeleton_onboard("small","up"));
                        System.out.println("success in taking small top skeleton");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[4]--;
                        proffessor_used[0]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==4){
                                players[current_player].to_players_bag(board.takeSkeleton_onboard("small","up"));
                                System.out.println("success in taking small top skeleton");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Skeleton";
                                players[current_player].draw_region_lock=4;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=4){
                            players[current_player].to_players_bag(board.takeSkeleton_onboard("small","up"));
                            System.out.println("success in taking small top skeleton");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Skeleton";
                            players[current_player].draw_region_lock=4;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.skeleton_tiles[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[4]==1){
                        players[current_player].to_players_bag(board.takeSkeleton_onboard("small","down"));
                        System.out.println("success in taking small down skeleton");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[4]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==4){
                                players[current_player].to_players_bag(board.takeSkeleton_onboard("small","down"));
                                System.out.println("success in taking small down skeleton");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Skeleton";
                                players[current_player].draw_region_lock=4;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=4){
                            players[current_player].to_players_bag(board.takeSkeleton_onboard("small","down"));
                            System.out.println("success in taking small down skeleton");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Skeleton";
                            players[current_player].draw_region_lock=4;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });

        view.amphora_tiles[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[3]==1){
                        players[current_player].to_players_bag(board.takeAmphora_onboard(1));
                        System.out.println("success in taking blue amphora");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[3]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==3){
                                players[current_player].to_players_bag(board.takeAmphora_onboard(1));
                                System.out.println("success in taking blue amphora");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Amphora";
                                players[current_player].draw_region_lock=3;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=3){
                            players[current_player].to_players_bag(board.takeAmphora_onboard(1));
                            System.out.println("success in taking blue amphora");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Amphora";
                            players[current_player].draw_region_lock=3;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.amphora_tiles[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[3]==1){
                        players[current_player].to_players_bag(board.takeAmphora_onboard(2));
                        System.out.println("success in taking brown amphora");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[3]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==3){
                                players[current_player].to_players_bag(board.takeAmphora_onboard(2));
                                System.out.println("success in taking brown amphora");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Amphora";
                                players[current_player].draw_region_lock=3;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=3){
                            players[current_player].to_players_bag(board.takeAmphora_onboard(2));
                            System.out.println("success in taking brown amphora");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Amphora";
                            players[current_player].draw_region_lock=3;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.amphora_tiles[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[3]==1){
                        players[current_player].to_players_bag(board.takeAmphora_onboard(3));
                        System.out.println("success in taking red amphora");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[3]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==3){
                                players[current_player].to_players_bag(board.takeAmphora_onboard(3));
                                System.out.println("success in taking red amphora");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Amphora";
                                players[current_player].draw_region_lock=3;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=3){
                            players[current_player].to_players_bag(board.takeAmphora_onboard(3));
                            System.out.println("success in taking red amphora");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Amphora";
                            players[current_player].draw_region_lock=3;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.amphora_tiles[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[3]==1){
                        players[current_player].to_players_bag(board.takeAmphora_onboard(4));
                        System.out.println("success in taking green amphora");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[3]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==3){
                                players[current_player].to_players_bag(board.takeAmphora_onboard(4));
                                System.out.println("success in taking green amphora");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Amphora";
                                players[current_player].draw_region_lock=3;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=3){
                            players[current_player].to_players_bag(board.takeAmphora_onboard(4));
                            System.out.println("success in taking green amphora");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Amphora";
                            players[current_player].draw_region_lock=3;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.amphora_tiles[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[3]==1){
                        players[current_player].to_players_bag(board.takeAmphora_onboard(5));
                        System.out.println("success in taking yellow amphora");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[3]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==3){
                                players[current_player].to_players_bag(board.takeAmphora_onboard(5));
                                System.out.println("success in taking yellow amphora");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Amphora";
                                players[current_player].draw_region_lock=3;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=3){
                            players[current_player].to_players_bag(board.takeAmphora_onboard(5));
                            System.out.println("success in taking yellow amphora");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Amphora";
                            players[current_player].draw_region_lock=3;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });
        view.amphora_tiles[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proffessor_used[0]!=0 && proffessor_used[3]==1){
                        players[current_player].to_players_bag(board.takeAmphora_onboard(6));
                        System.out.println("success in taking purple amphora");
                        view.update_player_sidebar(players[current_player]);

                        proffessor_used[0]--;
                        proffessor_used[3]--;
                    }else if(archaologist_used==0){
                        if (players[current_player].draws_left!=0){
                            if (players[current_player].draw_region_lock==0 ||players[current_player].draw_region_lock==3){
                                players[current_player].to_players_bag(board.takeAmphora_onboard(6));
                                System.out.println("success in taking purple amphora");
                                view.update_player_sidebar(players[current_player]);
                                players[current_player].last_region="Amphora";
                                players[current_player].draw_region_lock=3;
                                players[current_player].draws_left--;
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "You must choose again from the "+players[current_player].last_region +" region");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You are out of moves , please pass turn to the next one");
                        }
                    }else{
                        if (archaologist_used!=3){
                            players[current_player].to_players_bag(board.takeAmphora_onboard(6));
                            System.out.println("success in taking purple amphora");
                            view.update_player_sidebar(players[current_player]);
                            players[current_player].last_region="Amphora";
                            players[current_player].draw_region_lock=3;
                            players[current_player].draws_left--;
                            archaologist_used=0;
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "You cant pick from the same region");
                        }
                    }




                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                view.update_board_after_draw(board);
            }

        });

    }



    /**
     * @post checks if game is finished , and if it is it ends its counting points
     */
    public void IsGameFinished(){
        if (board.Get_entrance_number()>15){
            JOptionPane.showMessageDialog(null,"Winner is player: "+ (ReturneWinner()+1));
            JOptionPane.showMessageDialog(null,
                    "Game finished , none won , life will perpetually go on ,in this aeanon futile circle. " +
                            "Congratulations there is nothing to cheer for as entropy will eventually reach us all");

            System.exit(0);
        }
    }

    /**
     * @pre the game must be finished
     * @return the id of the winner
     */
    public int ReturneWinner(){
        int []players_points = {0,0,0,0};
        int []players_statues={0,0,0,0};
        int min_id = 0 ;
        int max_id = 0;
        int min = 100;
        int max = 0;
        for (int i = 0 ; i < 4 ; i++){
                //counts the mosaic points
            players_points[i]+=(players[i].mosaic_red_count()/4)*4+(players[i].mosaic_green_count()/4)*4+
                    (players[i].mosaic_yellow_count()/4)*4+((players[i].mosaic_green_count()%4 + players[i].mosaic_red_count()%4 +
                    players[i].mosaic_yellow_count()%4)/4)*2;

                //counts the skeletons
                    //finds the adult skeleton pair
            int adult_skeleton = players[i].return_skeleton_big_top();
            if (adult_skeleton>players[i].return_skeleton_big_bottom()){
                adult_skeleton=players[i].return_skeleton_big_bottom();
            }
                    //finds the young skeleton pair
            int young_skeleton = players[i].return_skeleton_small_top();
            if (young_skeleton>players[i].return_skeleton_small_bottom()){
                young_skeleton=players[i].return_skeleton_small_bottom();
            }
                    //while there are 2 adult and 1 young give 6 point and subtract the family
            while (adult_skeleton/2!=0&&young_skeleton!=0){
                    adult_skeleton--;
                    adult_skeleton--;
                    young_skeleton--;
                    players_points[i]+=6;
            }
                    //adds the remaining non family pairs into the points
            players_points[i]+=young_skeleton+adult_skeleton;

                //counts the amphora points
            int []amphora_points={0,0,0,0,0,0};
            amphora_points[0]=players[i].amphora_blue_count();
            amphora_points[1]=players[i].amphora_red_count();
            amphora_points[2]=players[i].amphora_brown_count();
            amphora_points[3]=players[i].amphora_yellow_count();
            amphora_points[4]=players[i].amphora_green_count();
            amphora_points[5]=players[i].amphora_purple_count();

            Arrays.sort(amphora_points);
                    //enters the while loop only if there are 3 or more amphoras
            while (amphora_points[3]!=0){
                    //in case there are 6 different
                if (amphora_points[0]!=0){
                    players_points[i]+=6;
                    for (int j=0;j<6;j++){
                        amphora_points[j]--;
                    }
                    //in case there are 5 different
                }else if (amphora_points[1]!=0){
                    players_points[i]+=4;
                    for (int j=1;j<6;j++){
                        amphora_points[j]--;
                    }
                    //in case there are 4 different
                }else if (amphora_points[2]!=0){
                    players_points[i]+=2;
                    for (int j=2;j<6;j++){
                        amphora_points[j]--;
                    }
                    //in case there are 3 different
                }else if (amphora_points[3]!=0){
                    players_points[i]+=1;
                    for (int j=3;j<6;j++){
                        amphora_points[j]--;
                    }
                }
            }


                //counts the statues points
            players_statues[i]=players[i].sphinx_count()+players[i].caryatid_count();

        }
            //finds the min and the max statue point player
        for (int i =0 ; i < 4 ; i++){

            if (players_statues[i]>max){
                max_id=i;
                max = players_statues[i];
            }
            if (players_statues[i]<min){
                min_id=i;
                min = players_statues[i];
            }
        }

            //gives the max statues player 6 point the min 0 and the others 3 points
        for (int i = 0 ; i < 4 ; i++){
            if (i==max_id){

                players_points[i]+=6;
            }else if (i==min_id){

            }else {

                players_points[i]+=3;
            }

        }

        max = 0 ;
        max_id= 0;
        for (int i = 0 ; i <4 ; i++){
            if (players_points[i]>max){
                max = players_points[i];
                max_id=i;
            }
        }

        return max_id;
    }


    public static void main(String[] args){
        Controller c = new Controller();
        c.initialize();
    }
}
