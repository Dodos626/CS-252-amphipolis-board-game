package Model;

abstract public class Character {
    /**
     * @param is_used to see if the card was used
     */
    boolean is_used=false;


    abstract public void character_ability(Player player);

    /**
     * is_used is changed to true when character used
     */
    public void used(){
        is_used=true;
    }

    /**
     * @pre the character must be beforehand initialized
     * @return the state of is used
     */
    public boolean isIs_used(){return is_used;}
}
