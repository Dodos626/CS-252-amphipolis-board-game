package Model;

public class Professor_Character extends Character{

    /**
     * @pre the character must be not used
     * @post the ability of the character triggers
     * @post the character card is now used
     */
    public void character_ability(Player player) {
        used();
    }
}