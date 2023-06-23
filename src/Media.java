import javafx.scene.media.MediaPlayer;

/**
 * The Media class provides static MediaPlayer instances for various game sound effects.
 */
public class Media {

    public static MediaPlayer titleMedia;
    public static MediaPlayer introMedia;
    public static MediaPlayer fallsMedia;
    public static MediaPlayer gunMedia;
    public static MediaPlayer levelMedia;

    public static MediaPlayer gameOverMedia;

    public static MediaPlayer completedMedia;

    /**
     * Constructs a new Media object and initializes the MediaPlayer instances for title and intro sound effects.
     */
    public Media() {
        javafx.scene.media.Media media = new javafx.scene.media.Media(Media.class.getResource("assets/effects/Intro.mp3").toExternalForm());
        introMedia = new MediaPlayer(media);
        introMedia.setCycleCount(1);
        introMedia.setVolume(DuckHunt.volume);

        javafx.scene.media.Media media2 = new javafx.scene.media.Media(Media.class.getResource("assets/effects/Title.mp3").toExternalForm());
        titleMedia = new MediaPlayer(media2);
        titleMedia.setCycleCount(MediaPlayer.INDEFINITE);
        titleMedia.setVolume(DuckHunt.volume);
    }

    /**
     * Plays the duck falls sound effect.
     */
    public static void fallsMedia() {
        javafx.scene.media.Media media = new javafx.scene.media.Media(Media.class.getResource("assets/effects/DuckFalls.mp3").toExternalForm());
        fallsMedia = new MediaPlayer(media);
        fallsMedia.setCycleCount(1);
        fallsMedia.setVolume(DuckHunt.volume);
        fallsMedia.play();
    }

    /**
     * Plays the gunshot sound effect.
     */
    public static void gunMedia() {
        javafx.scene.media.Media media = new javafx.scene.media.Media(Media.class.getResource("assets/effects/Gunshot.mp3").toExternalForm());
        gunMedia = new MediaPlayer(media);
        gunMedia.setCycleCount(1);
        gunMedia.setVolume(DuckHunt.volume);
        gunMedia.play();
    }

    /**
     * Plays the level completed sound effect.
     */
    public static void levelMedia() {
        javafx.scene.media.Media media = new javafx.scene.media.Media(Media.class.getResource("assets/effects/LevelCompleted.mp3").toExternalForm());
        levelMedia = new MediaPlayer(media);
        levelMedia.setCycleCount(1);
        levelMedia.setVolume(DuckHunt.volume);
        levelMedia.play();
    }

    /**
     * Plays the game over sound effect.
     */
    public static void gameOverMedia() {
        javafx.scene.media.Media media = new javafx.scene.media.Media(Media.class.getResource("assets/effects/GameOver.mp3").toExternalForm());
        gameOverMedia = new MediaPlayer(media);
        gameOverMedia.setCycleCount(1);
        gameOverMedia.setVolume(DuckHunt.volume);
        gameOverMedia.play();
    }

    /**
     * Plays the game completed sound effect.
     */
    public static void completedMedia() {
        javafx.scene.media.Media media = new javafx.scene.media.Media(Media.class.getResource("assets/effects/GameCompleted.mp3").toExternalForm());
        completedMedia = new MediaPlayer(media);
        completedMedia.setCycleCount(1);
        completedMedia.setVolume(DuckHunt.volume);
        completedMedia.play();
    }
}
