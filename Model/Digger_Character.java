package Model;

import javax.swing.*;

public class Digger_Character extends Character{

    /**
     * @pre the character must be not used
     * @post the ability of the character triggers
     * @post the character card is now used
     */
    public void character_ability(Player player) {

        if(player.draw_region_lock==0){
            JOptionPane.showMessageDialog(null,
                    "To use this ability you must first pick one tile from the board");
        }else{
            player.draws_left++;
            player.draws_left++;
            used();
        }

    }
}