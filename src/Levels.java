public abstract class Levels {

    private final String win_text = "You have completed the game!!";
    private final String win_text2 ="\n    Press ENTER to play again\n\t  Press ESC to exit";
    private final String gameover_text = "\t   GAME OVER!";
    private final String gameover_text2 = "\n Press ENTER to play again\n\tPress ESC to exit";
    private final String level_text = "\t\tYOU WIN!";
    private final String level_text2 = "\nPRESS ENTER to play next level";

    public String getWin_text() {
        return win_text;
    }

    public String getWin_text2() {
        return win_text2;
    }

    public String getGameover_text() {
        return gameover_text;
    }

    public String getGameover_text2() {
        return gameover_text2;
    }

    public String getLevel_text() {
        return level_text;
    }

    public String getLevel_text2() {
        return level_text2;
    }
    public abstract void load();
    public abstract void initialize();
    public abstract void animation();
}
