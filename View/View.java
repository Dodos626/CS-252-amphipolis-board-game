package View;
import Model.Board;
import Model.Player;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.ImageIcon;


public class View extends JFrame {

    //things that will be the same for all users------------------------------------------------------------------------
    /**
     * @param mosaic_tiles displays the mosaic tiles on board can hold the max value of the different components
     */
    public JButton[] mosaic_tiles = new JButton[3];


    private JLabel [] mosaic_numbers = new JLabel[3];

    /**
     * @param amphora_tiles displays the amphora tiles on board can hold the max value of the different components
     */
    public JButton [] amphora_tiles = new JButton[6];

    private JLabel [] amphora_numbers = new JLabel[6];

    /**
     * @param statue_tiles displays the statue tiles on board can hold the max value of the different components
     */
    public JButton[] statue_tiles = new JButton[2];

    private JLabel[] statue_numbers = new JLabel[2];

    /**
     * @param skeleton_tiles displays the skeleton tiles on board can hold the max value of the different components
     */
    public JButton[] skeleton_tiles = new JButton[4];

    private JLabel [] skeleton_numbers = new JLabel[4];

    /**
     * @param landslide_tiles displays the landslide tiles on board can hold the max value
     */
    public JButton landslide_tiles ;
    private JLabel landslide_number;

    /**
     * @param draw_tiles when pressed player draw tiles
     */
    public JButton draw_tiles;

    /**
     * @param end_turn ends the players turn
     */
    public JButton end_turn;

    /**
     * @param user_character holds  the use character phrase
     */
    JTextArea user_character;


    //things that will need to be updated with every turn---------------------------------------------------------------
    /**
     * @param character_cards displays the characters of each player
     */
    public JButton[] character_cards = new JButton[4];

    /**
     * @param players_turn displays the player that plays this turn
     */
    public JLabel players_turn;


    //Initialise -------------------------------------------------------------------------------------------------------

    /**
     * @post each class will take the representative graphic and everything will put in order
     */
    public View(){
        build_basic_board();
        build_user_side_board();
        build_user_possessions_board();
    }

    /**
     * @post builds the basic board
     */
    private JPanel top_right;
    private JPanel top_left;
    private JPanel middle;
    private JPanel bottom_right;
    private JPanel bottom_left;

    //they are global so i can use them in the players possessions board too
    //mosaic icons
    private Icon red_mosaic_icon ;
    private Icon yellow_mosaic_icon ;
    private Icon green_mosaic_icon;
    //statue icons
    private Icon caryatid_icon ;
    private Icon sphinx_icon ;
    //amphora icons
    private Icon blue_amphora_icon ;
    private Icon brown_amphora_icon ;
    private Icon red_amphora_icon ;
    private Icon green_amphora_icon ;
    private Icon yellow_amphora_icon ;
    private Icon purple_amphora_icon ;
    //skeleton icons
    private Icon skeleton_big_top_icon ;
    private Icon skeleton_big_bottom_icon ;
    private Icon skeleton_small_top_icon ;
    private Icon skeleton_small_bottom_icon ;

    private ClassLoader cldr;
    private URL image_url;

    /**
     * @post builds the main frame and all of its components
     */
    void build_basic_board(){
        cldr = this.getClass().getClassLoader();

        //Creating the Main frame of the game---------------------------------------------------------------------------
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(815, 830));
        JFrame frame = new JFrame("Main Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(806, 829);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        //takes the image for the background and resizes it while giving it at the ImagePanel

        image_url = cldr.getResource("resources.images/background.png");
        ImagePanel thumb = new ImagePanel(new ImageIcon(image_url).getImage().getScaledInstance(800,800,Image.SCALE_SMOOTH));

        //BACKGROUND IMAGE SET AT LEVEL 0

        thumb.setBounds(0,0,815,830);
        layeredPane.add(thumb,0,1);

        //end of main frame---------------------------------------------------------------------------------------------

        //creates the top left components-------------------------------------------------------------------------------
        top_left = new JPanel();

            //the box to contain horizontal boxes that will contain both buttons and counters
        Box box = Box.createVerticalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();

            //creates the icons
        image_url = cldr.getResource("resources.images/mosaic_red.png");
        red_mosaic_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/mosaic_green.png");
        green_mosaic_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/mosaic_yellow.png");
        yellow_mosaic_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));


            //creates the mosaic buttons with the icons
        mosaic_tiles[0]=new JButton(red_mosaic_icon);
        mosaic_tiles[1]=new JButton(green_mosaic_icon);
        mosaic_tiles[2]=new JButton(yellow_mosaic_icon);

            //creates the JLabel containing the numbers of each tile
        mosaic_numbers[0]=new JLabel("0");
        mosaic_numbers[1]=new JLabel("0");
        mosaic_numbers[2]=new JLabel("0");

            //adds the red mosaic button and text to the horizontal box
        box2.add(mosaic_tiles[0]);
        box2.add(new JLabel("  "));
        box2.add(mosaic_numbers[0]);

            //adds the green mosaic button and text to the horizontal box
        box3.add(mosaic_tiles[1]);
        box3.add(new JLabel("  "));
        box3.add(mosaic_numbers[1]);

            //adds the yellow mosaic button and text to the horizontal box
        box4.add(mosaic_tiles[2]);
        box4.add(new JLabel("  "));
        box4.add(mosaic_numbers[2]);

            //adds the horizontal boxes into the vertical box
        box.add(box2);
        box.add(box3);
        box.add(box4);
            //sets bounds for the top left panel sets it to transparent background and adds the box
            //containing the buttons
        top_left.setBounds(23, 21, 234, 200);
        top_left.setOpaque(false);
        top_left.add(box);

            //adds the pane to the main frame
        layeredPane.add(top_left,10,10);
        //end of top left pane------------------------------------------------------------------------------------------

        //creates the top right components------------------------------------------------------------------------------

            //creates the top right panel
        top_right = new JPanel();
        top_right.setBounds(544, 21, 234, 200);
        top_right.setOpaque(false);

            //creates the one vertical and 2 horizontal box
        Box statue_main_box = Box.createVerticalBox();
        Box caryatid_box = Box.createHorizontalBox();
        Box sphinx_box = Box.createHorizontalBox();

            //resizes the images for the buttons and creates the icons
        image_url = cldr.getResource("resources.images/caryatid.png");
        caryatid_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/sphinx.png");
        sphinx_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));


            //creates the statue buttons with the icons
        statue_tiles[0] = new JButton(caryatid_icon);
        statue_tiles[1] = new JButton(sphinx_icon);

            //creates the JLabels for the statues
        statue_numbers[0] = new JLabel("0");
        statue_numbers[1] = new JLabel("0");

            //puts the contents into the boxes
        caryatid_box.add(statue_tiles[0]);
        caryatid_box.add(new JLabel("  "));
        caryatid_box.add(statue_numbers[0]);

        sphinx_box.add(statue_tiles[1]);
        sphinx_box.add(new JLabel("  "));

        sphinx_box.add(statue_numbers[1]);

        statue_main_box.add(caryatid_box);
        statue_main_box.add(new JLabel(" "));
        statue_main_box.add(sphinx_box);

            //ads the main box into the top right
        top_right.add(statue_main_box);

            //adds the top_right pane at the layered pane
        layeredPane.add(top_right,9,1);

        //end of top right pane-----------------------------------------------------------------------------------------

        //start of the middle pane--------------------------------------------------------------------------------------
            //creates the middle pane
        middle = new JPanel();
        middle.setBounds(284, 349, 232, 200);
        middle.setOpaque(false);

            //resizes the images for the buttons
        image_url = cldr.getResource("resources.images/landslide.png");
        Icon landslide_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));


            //creates the button with the icon
        landslide_tiles = new JButton(landslide_icon);

            //creates the JLabel for the landslide also
            //creates a pane with grid layout to put the number count in the middle

        JPanel for_letters = new JPanel();
        landslide_number = new JLabel("0");

        for_letters.setLayout(new GridBagLayout());
        for_letters.setOpaque(false);

        for_letters.add(landslide_number);

            //creates the box for the landslide
        Box landslide_main_box = Box.createVerticalBox();

            //puts the button and the panel containing the count at the box
        landslide_main_box.add(for_letters);
        landslide_main_box.add(new JLabel(" "));
        landslide_main_box.add(landslide_tiles);

            //puts the box into the pane
        middle.add(landslide_main_box);

            //adds the middle pane to the layered pane
        layeredPane.add(middle,9,1);
        //end of the middle pane----------------------------------------------------------------------------------------

        //start of the bottom left pane---------------------------------------------------------------------------------
            //creates the bottom left pane
        bottom_left = new JPanel();
        bottom_left.setBounds(23, 580, 234, 200);
        bottom_left.setOpaque(false);

            //resizes the images for the buttons
        image_url = cldr.getResource("resources.images/amphora_blue.png");
        blue_amphora_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/amphora_brown.png");
        brown_amphora_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/amphora_red.png");
        red_amphora_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/amphora_green.png");
        green_amphora_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/amphora_yellow.png");
        yellow_amphora_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/amphora_purple.png");
        purple_amphora_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));


            //creates the buttons from the icons
        amphora_tiles[0] = new JButton(blue_amphora_icon);
        amphora_tiles[1] = new JButton(brown_amphora_icon);
        amphora_tiles[2] = new JButton(red_amphora_icon);
        amphora_tiles[3] = new JButton(green_amphora_icon);
        amphora_tiles[4] = new JButton(yellow_amphora_icon);
        amphora_tiles[5] = new JButton(purple_amphora_icon);


            //creates the JLabels
        amphora_numbers[0] = new JLabel("0");
        amphora_numbers[1] = new JLabel("0");
        amphora_numbers[2] = new JLabel("0");
        amphora_numbers[3] = new JLabel("0");
        amphora_numbers[4] = new JLabel("0");
        amphora_numbers[5] = new JLabel("0");

            //Creates the boxes
        Box main_amphora_box = Box.createVerticalBox();
        Box amphora_box1= Box.createHorizontalBox();
        Box amphora_box2= Box.createHorizontalBox();
        Box amphora_box3= Box.createHorizontalBox();

            //puts in the box the things
        amphora_box1.add(amphora_numbers[0]);
        amphora_box1.add(new JLabel(" "));
        amphora_box1.add(amphora_tiles[0]);
        amphora_box1.add(new JLabel("  "));
        amphora_box1.add(amphora_tiles[1]);
        amphora_box1.add(new JLabel(" "));
        amphora_box1.add(amphora_numbers[1]);


        amphora_box2.add(amphora_numbers[2]);
        amphora_box2.add(new JLabel(" "));
        amphora_box2.add(amphora_tiles[2]);
        amphora_box2.add(new JLabel("  "));
        amphora_box2.add(amphora_tiles[3]);
        amphora_box2.add(new JLabel(" "));
        amphora_box2.add(amphora_numbers[3]);

        amphora_box3.add(amphora_numbers[4]);
        amphora_box3.add(new JLabel(" "));
        amphora_box3.add(amphora_tiles[4]);
        amphora_box3.add(new JLabel("  "));
        amphora_box3.add(amphora_tiles[5]);
        amphora_box3.add(new JLabel(" "));
        amphora_box3.add(amphora_numbers[5]);

            //puts in the main box the other boxes
        main_amphora_box.add(amphora_box1);
        main_amphora_box.add(amphora_box2);
        main_amphora_box.add(amphora_box3);

            //puts the main box into the pane
        bottom_left.add(main_amphora_box);

            //adds the middle pane to the layered pane
        layeredPane.add(bottom_left,9,1);
        //end of the bottom left pane-----------------------------------------------------------------------------------

        //start of the bottom right pane--------------------------------------------------------------------------------
        bottom_right = new JPanel();
        bottom_right.setBounds(543, 580, 234, 200);
        bottom_right.setOpaque(false);

            //resizes the images for the buttons
        image_url = cldr.getResource("resources.images/skeleton_big_top.png");
        skeleton_big_top_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/skeleton_big_bottom.png");
        skeleton_big_bottom_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/skeleton_small_top.png");
        skeleton_small_top_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/skeleton_small_bottom.png");
        skeleton_small_bottom_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(35,35,Image.SCALE_SMOOTH));


            //creates the buttons
        skeleton_tiles[0]= new JButton(skeleton_big_top_icon);
        skeleton_tiles[1]= new JButton(skeleton_big_bottom_icon);
        skeleton_tiles[2]= new JButton(skeleton_small_top_icon);
        skeleton_tiles[3]= new JButton(skeleton_small_bottom_icon);

            //creates the JLabels tha will count
        skeleton_numbers[0] = new JLabel("0");
        skeleton_numbers[1] = new JLabel("0");
        skeleton_numbers[2] = new JLabel("0");
        skeleton_numbers[3] = new JLabel("0");

            //creates the boxes
        Box skeleton_main_box = Box.createVerticalBox();
        Box skeleton_1_box = Box.createHorizontalBox();
        Box skeleton_2_box = Box.createHorizontalBox();

            //puts the buttons and the labels into the boxes
        skeleton_1_box.add(skeleton_tiles[0]);
        skeleton_1_box.add(new JLabel(" "));
        skeleton_1_box.add(skeleton_numbers[0]);
        skeleton_1_box.add(new JLabel("   "));
        skeleton_1_box.add(skeleton_tiles[2]);
        skeleton_1_box.add(new JLabel(" "));
        skeleton_1_box.add(skeleton_numbers[2]);
        skeleton_1_box.add(new JLabel(" "));

        skeleton_2_box.add(skeleton_tiles[1]);
        skeleton_2_box.add(new JLabel(" "));
        skeleton_2_box.add(skeleton_numbers[1]);
        skeleton_2_box.add(new JLabel("   "));
        skeleton_2_box.add(skeleton_tiles[3]);
        skeleton_2_box.add(new JLabel(" "));
        skeleton_2_box.add(skeleton_numbers[3]);
        skeleton_2_box.add(new JLabel(" "));

        skeleton_main_box.add(skeleton_1_box);
        skeleton_main_box.add(skeleton_2_box);

            //adds the main box to the bottom right panel
        bottom_right.add(skeleton_main_box);

            //adds the middle pane to the layered pane
        layeredPane.add(bottom_right,9,1);

        //end of the bottom right pane----------------------------------------------------------------------------------

            //adds the layered pane into the frame and initializes it
        frame.add(layeredPane);
        frame.setResizable(false);

        frame.setVisible(true);

    }


    /**
     * image is a url that will paint the background with the given image
     */
        //for the purpose of building the background
    class ImagePanel extends JComponent {
        private Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }



    public JLabel tiles_left;
    public Icon archaeologist_icon ;
    public Icon professor_icon;
    public Icon assistant_icon;
    public Icon digger_icon;
    /**
     * @pre must be built after the main board is initialised
     * @post builds the user side board and all of its components
     */
    void build_user_side_board(){

        cldr = this.getClass().getClassLoader();

        //creates the main JFrame
        JFrame frame = new JFrame("Player interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 829);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2+600, dim.height/2-frame.getSize().height/2);

        //creates the main jpanel and the two boxes
        JPanel main = new JPanel();
        Box main_box = Box.createVerticalBox();
        Box button_box = Box.createHorizontalBox();
        Box character_box1 = Box.createHorizontalBox();
        Box character_box2 = Box.createHorizontalBox();

        //adds the buttons to the button box
        button_box.add(draw_tiles=new JButton("Draw Tiles"));
        button_box.add(end_turn=new JButton("End Turn"));

        //creates a jpanel so i can have the players turn in the center
        JPanel text_panel = new JPanel();
        text_panel.setLayout(new GridBagLayout());
        text_panel.setOpaque(false);
        text_panel.add(players_turn = new JLabel("Player 1 turn"));

        //creates a jpanel to have in the center the tiles remaining
        JPanel for_tiles_left = new JPanel();
        for_tiles_left.setLayout(new GridBagLayout());
        for_tiles_left.setOpaque(false);
        for_tiles_left.add(tiles_left = new JLabel("Tiles Remaining : 131"));

        //resizes the pictures for the characters and creates the icons

        image_url = cldr.getResource("resources.images/archaeologist.png");
        archaeologist_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(130,210,Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/professor.png");
        professor_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(130,210,Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/assistant.png");
        assistant_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(130,210,Image.SCALE_SMOOTH));

        image_url = cldr.getResource("resources.images/digger.png");
        digger_icon = new ImageIcon(new ImageIcon(image_url).getImage().getScaledInstance(130,210,Image.SCALE_SMOOTH));


        //creates the button for the character
        character_cards[0]=new JButton(archaeologist_icon);
        character_cards[1]=new JButton(professor_icon);
        character_cards[2]=new JButton(assistant_icon);
        character_cards[3]=new JButton(digger_icon);

        //adds the character cards into the boxes
        character_box1.add(character_cards[0]);
        character_box1.add(character_cards[1]);

        character_box2.add(character_cards[2]);
        character_box2.add(character_cards[3]);

        //adds the components to the main box
        main_box.add(text_panel);
        main_box.add(button_box);
        main_box.add(for_tiles_left);
        main_box.add(character_box1);
        main_box.add(character_box2);

        //adds the box to the main panel
        main.add(main_box);
        //and the main panel to the frame
        frame.add(main);







        frame.setResizable(false);
        frame.setVisible(true);

    }


    //amphora numbers in players possessions
    public JLabel blue_amphora_player;
    public JLabel brown_amphora_player;
    public JLabel green_amphora_player;
    public JLabel red_amphora_player;
    public JLabel yellow_amphora_player;
    public JLabel purple_amphora_player;
    //skeleton numbers in players possessions
    public JLabel skeleton_big_top_player;
    public JLabel skeleton_big_down_player;
    public JLabel skeleton_small_top_player;
    public JLabel skeleton_small_down_player;
    //statue numbers in players possessions
    public JLabel sphinx_player;
    public JLabel caryatid_player;
    //mosaic numbers in players possessions
    public JLabel mosaic_red_player;
    public JLabel mosaic_green_player;
    public JLabel mosaic_yellow_player;


    /**
     * @pre the icons must be already build at the main board and the side board
     * @post builds the user possessions and all its components
     */

    void build_user_possessions_board(){
            //builds a new JFrame
        JFrame frame = new JFrame("Player possessions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,400 );
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2-600, dim.height/2-frame.getSize().height/2);

        //creates the board for the amphora
        JPanel main_amphora_pane = new JPanel();
        Box main_amphora = Box.createVerticalBox();
        Box amphora_horizontal1= Box.createHorizontalBox();
        Box amphora_horizontal2= Box.createHorizontalBox();
        Box amphora_horizontal3= Box.createHorizontalBox();

        amphora_horizontal1.add(new JLabel(blue_amphora_icon));
        amphora_horizontal1.add(blue_amphora_player =new JLabel("0"));

        amphora_horizontal1.add(new JLabel(brown_amphora_icon));
        amphora_horizontal1.add(brown_amphora_player =new JLabel("0"));

        amphora_horizontal2.add(new JLabel(red_amphora_icon));
        amphora_horizontal2.add(red_amphora_player =new JLabel("0"));

        amphora_horizontal2.add(new JLabel(green_amphora_icon));
        amphora_horizontal2.add(green_amphora_player =new JLabel("0"));

        amphora_horizontal3.add(new JLabel(yellow_amphora_icon));
        amphora_horizontal3.add(yellow_amphora_player =new JLabel("0"));

        amphora_horizontal3.add(new JLabel(purple_amphora_icon));
        amphora_horizontal3.add(purple_amphora_player =new JLabel("0"));

        main_amphora.add(amphora_horizontal1);
        main_amphora.add(amphora_horizontal2);
        main_amphora.add(amphora_horizontal3);
        main_amphora_pane.add(main_amphora);
        main_amphora_pane.setBorder(BorderFactory.createLineBorder(Color.black));
        main_amphora_pane.setOpaque(true);

        //creates the board for the skeletons
        JPanel main_skeleton_pane = new JPanel();
        Box main_skeleton_box = Box.createVerticalBox();
        Box skeleton_horizontal1 = Box.createHorizontalBox();
        Box skeleton_horizontal2 = Box.createHorizontalBox();

        skeleton_horizontal1.add(new JLabel(skeleton_big_top_icon));
        skeleton_horizontal1.add(skeleton_big_top_player=new JLabel("0"));

        skeleton_horizontal1.add(new JLabel(skeleton_small_top_icon));
        skeleton_horizontal1.add(skeleton_small_top_player=new JLabel("0"));

        skeleton_horizontal2.add(new JLabel(skeleton_big_bottom_icon));
        skeleton_horizontal2.add(skeleton_big_down_player=new JLabel("0"));

        skeleton_horizontal2.add(new JLabel(skeleton_small_bottom_icon));
        skeleton_horizontal2.add(skeleton_small_down_player=new JLabel("0"));


        main_skeleton_box.add(skeleton_horizontal1);
        main_skeleton_box.add(skeleton_horizontal2);

        main_skeleton_pane.add(main_skeleton_box);
        main_skeleton_pane.setOpaque(true);
        main_skeleton_pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //creates the board for the statues
        JPanel main_statue_pane=new JPanel();
        Box main_statue_box = Box.createVerticalBox();
        Box horizontal_statue_box = Box.createHorizontalBox();
        Box horizontal_statue_box2 = Box.createHorizontalBox();

        horizontal_statue_box.add(new JLabel(sphinx_icon));
        horizontal_statue_box.add(sphinx_player=new JLabel("0"));

        horizontal_statue_box2.add(new JLabel(caryatid_icon));
        horizontal_statue_box2.add(caryatid_player=new JLabel("0"));

        main_statue_box.add(horizontal_statue_box);
        main_statue_box.add(new JLabel(" "));
        main_statue_box.add(horizontal_statue_box2);

        main_statue_pane.add(main_statue_box);
        main_statue_pane.setOpaque(true);
        main_statue_pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //creates the board for the mosaic
        JPanel main_mosaic_pane = new JPanel();
        Box main_mosaic_box = Box.createVerticalBox();
        Box horizontal_mosaic_box1 = Box.createHorizontalBox();
        Box horizontal_mosaic_box2 = Box.createHorizontalBox();
        Box horizontal_mosaic_box3 = Box.createHorizontalBox();


        horizontal_mosaic_box1.add(new JLabel(red_mosaic_icon));
        horizontal_mosaic_box1.add(new JLabel("  "));
        horizontal_mosaic_box1.add(mosaic_red_player = new JLabel("0"));


        horizontal_mosaic_box2.add(new JLabel(green_mosaic_icon));
        horizontal_mosaic_box2.add(new JLabel("  "));
        horizontal_mosaic_box2.add(mosaic_green_player = new JLabel("0"));

        horizontal_mosaic_box3.add(new JLabel(yellow_mosaic_icon));
        horizontal_mosaic_box3.add(new JLabel("  "));
        horizontal_mosaic_box3.add(mosaic_yellow_player = new JLabel("0"));

        main_mosaic_box.add(horizontal_mosaic_box1);
        main_mosaic_box.add(horizontal_mosaic_box2);
        main_mosaic_box.add(horizontal_mosaic_box3);

        main_mosaic_pane.add(main_mosaic_box);
        main_mosaic_pane.setOpaque(true);
        main_mosaic_pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));




        //Creates the main two boxes
        Box main_vertical_box = Box.createVerticalBox();
        Box main_horizontal_box = Box.createHorizontalBox();
        Box main_horizontal_box2 = Box.createHorizontalBox();

        main_horizontal_box.add(main_amphora_pane);
        main_horizontal_box.add(main_skeleton_pane);

        main_horizontal_box2.add(main_statue_pane);
        main_horizontal_box2.add(main_mosaic_pane);


            //JPane for the title
        JPanel main_title = new JPanel();
        main_title.setLayout(new GridBagLayout());
        main_title.setOpaque(true);
        main_title.setBounds(0,0,350,20);
        main_title.add(new JLabel("Players Possessions"));

            //adds the components to the main boxes
        main_vertical_box.add(main_title);
        main_vertical_box.add(main_horizontal_box);
        main_vertical_box.add(main_horizontal_box2);


        frame.add(main_vertical_box);




            //initiates the frame
        frame.setResizable(false);
        frame.setVisible(true);
    }


    /**
     * @pre every board must be initialised beforehand
     * @post refreshes the graphics of the board after the draw button was pressed or when the initial
     *              tiles where placed
     */
    public void update_board_after_draw(Board board){

        amphora_numbers[0].setText(Integer.toString(board.return_amphora_blue()));
        amphora_numbers[1].setText(Integer.toString(board.return_amphora_brown()));
        amphora_numbers[2].setText(Integer.toString(board.return_amphora_red()));
        amphora_numbers[3].setText(Integer.toString(board.return_amphora_green()));
        amphora_numbers[4].setText(Integer.toString(board.return_amphora_yellow()));
        amphora_numbers[5].setText(Integer.toString(board.return_amphora_purple()));

        statue_numbers[0].setText(Integer.toString(board.return_caryatid()));
        statue_numbers[1].setText(Integer.toString(board.return_sphinx()));

        mosaic_numbers[0].setText(Integer.toString(board.return_mosaic_red()));
        mosaic_numbers[1].setText(Integer.toString(board.return_mosaic_green()));
        mosaic_numbers[2].setText(Integer.toString(board.return_mosaic_yellow()));

        skeleton_numbers[0].setText(Integer.toString(board.return_skeleton_big_top()));
        skeleton_numbers[1].setText(Integer.toString(board.return_skeleton_big_bottom()));
        skeleton_numbers[2].setText(Integer.toString(board.return_skeleton_small_top()));
        skeleton_numbers[3].setText(Integer.toString(board.return_skeleton_small_bottom()));

        landslide_number.setText(Integer.toString(board.Get_entrance_number()));

        for (int i = 0 ; i < 6 ; i++){
            if (amphora_numbers[i].getText().equals("0")){
                amphora_tiles[i].setEnabled(false);
            }else{
                amphora_tiles[i].setEnabled(true);
            }
        }
        for (int i = 0 ; i < 4 ; i++){
            if (skeleton_numbers[i].getText().equals("0")){
                skeleton_tiles[i].setEnabled(false);
            }else{
                skeleton_tiles[i].setEnabled(true);
            }
        }
        for (int i = 0 ; i < 2 ; i++){
            if (statue_numbers[i].getText().equals("0")){
                statue_tiles[i].setEnabled(false);
            }else{
                statue_tiles[i].setEnabled(true);
            }
        }
        for (int i = 0 ; i < 3 ; i++){
            if (mosaic_numbers[i].getText().equals("0")){
                mosaic_tiles[i].setEnabled(false);
            }else{
                mosaic_tiles[i].setEnabled(true);
            }
        }
        
    }



    /**
     * @pre every board must be initialised beforehand
     * @post  refreshes the graphics of the user when a character is used
     */
    public void update_player_sidebar(Player player){
        for (int i = 0 ; i<4;i++){
            if (player.playersCharacters.get(i).isIs_used()){
                character_cards[i].setEnabled(false);
            }else{
                character_cards[i].setEnabled(true);
            }
        }
        red_amphora_player.setText(Integer.toString(player.amphora_red_count()));
        blue_amphora_player.setText(Integer.toString(player.amphora_blue_count()));
        brown_amphora_player.setText(Integer.toString(player.amphora_brown_count()));
        yellow_amphora_player.setText(Integer.toString(player.amphora_yellow_count()));
        green_amphora_player.setText(Integer.toString(player.amphora_green_count()));
        purple_amphora_player.setText(Integer.toString(player.amphora_purple_count()));

        mosaic_green_player.setText(Integer.toString(player.mosaic_green_count()));
        mosaic_red_player.setText(Integer.toString(player.mosaic_red_count()));
        mosaic_yellow_player.setText(Integer.toString(player.mosaic_yellow_count()));

        sphinx_player.setText(Integer.toString(player.sphinx_count()));
        caryatid_player.setText(Integer.toString(player.caryatid_count()));

        skeleton_big_top_player.setText(Integer.toString(player.return_skeleton_big_top()));
        skeleton_big_down_player.setText(Integer.toString(player.return_skeleton_big_bottom()));
        skeleton_small_top_player.setText(Integer.toString(player.return_skeleton_small_top()));
        skeleton_small_down_player.setText(Integer.toString(player.return_skeleton_small_bottom()));





    }
}
