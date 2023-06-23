import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * The {@code Level1} class represents the first level of the game.
 * It handles the game logic and animations for this level.
 */
public class Level1 extends Levels {
    private ImageView birdView;
    private Timeline animation;
    private boolean movingRight = true;
    private Image[] birdImages = new Image[3];
    private int bullets = 3;
    private boolean birdHit = false;
    private Timeline fallDelayAnimation;
    private Text bulletsText;
    private Scene scene3;
    private Pane thirdPane;

    /**
     * Constructs a Level 1 object.
     *
     * @param scene3       The scene of Level 1.
     * @param thirdPane    The pane of Level 1.
     * @param bulletsText  the text displaying the number of bullets
     */
    public Level1(Scene scene3, Pane thirdPane, Text bulletsText) {
        this.scene3 = scene3;
        this.thirdPane = thirdPane;
        this.bulletsText = bulletsText;

        // Handle mouse click event of screen2
        scene3.setOnMouseClicked(event -> {
            if (bullets == 0) {
                return; // Stop the function if bullets == 0
            }
            if (event.getButton() == MouseButton.PRIMARY && birdView.getBoundsInParent().contains(event.getX(), event.getY())) {
                bullets--;
                bulletsText.setText("Ammo Left: " + bullets);
                birdHit = true;
                Media.fallsMedia();
                Media.gunMedia();
                Media.levelMedia();
                Text winText = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10 * DuckHunt.SCALE, Color.ORANGE, getLevel_text(), 60 * DuckHunt.SCALE, 100 * DuckHunt.SCALE);
                Text winText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10 * DuckHunt.SCALE, Color.ORANGE, getLevel_text2(), 60 * DuckHunt.SCALE, 100 * DuckHunt.SCALE);
                winText2 = DuckHunt.makeTextDisappear(winText2);
                thirdPane.getChildren().addAll(winText, winText2);

                Image bird_die = new Image(getClass().getResourceAsStream("assets/duck_black/7.png"));
                birdView.setImage(bird_die);
                animation.stop();
                bullets = 3;
                startFallDelayAnimation();
                scene3.setOnKeyPressed(event2 -> {
                    if (event2.getCode() == KeyCode.ENTER && birdHit) {
                        Media.fallsMedia.stop();
                        Media.gunMedia.stop();
                        Media.levelMedia.stop();
                        Text bulletsText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 7 * DuckHunt.SCALE, Color.ORANGE, "Ammo Left: 6", 200 * DuckHunt.SCALE, 10 * DuckHunt.SCALE);
                        Level2 c = new Level2(DuckHunt.scene5, DuckHunt.fifthPane, bulletsText2);
                        c.load();
                        c.initialize();
                        c.animation();
                        DuckHunt.setScene(DuckHunt.fifthPane, DuckHunt.scene5, bulletsText2, c.getBirdView(), c.getBird2View());
                        c.getAnimation().play();
                        c.getAnimation2().play();
                        DuckHunt.primaryStage.setScene(DuckHunt.scene5);
                    }
                });
            } else if (event.getButton() == MouseButton.PRIMARY && bullets > 0) {
                if (birdHit) {
                    return; // Stop the function if bullets == 0
                }
                Media.gunMedia();
                bullets--;
                bulletsText.setText("Ammo Left: " + bullets);
                controlBullet();
            }
        });
    }

    /**
     * Controls the bullet and handles the game over state.
     */
    private void controlBullet() {
        if (bullets == 0) {
            Media.gameOverMedia();
            Text loseText = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10 * DuckHunt.SCALE, Color.ORANGE, getGameover_text(), 60 * DuckHunt.SCALE, 100 * DuckHunt.SCALE);
            Text loseText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10 * DuckHunt.SCALE, Color.ORANGE, getGameover_text2(), 60 * DuckHunt.SCALE, 100 * DuckHunt.SCALE);
            loseText2 = DuckHunt.makeTextDisappear(loseText2);
            thirdPane.getChildren().addAll(loseText, loseText2);
            scene3.setOnKeyPressed(event2 -> {
                Media.gameOverMedia.stop();
                Media.gunMedia.stop();
                DuckHunt.start_number++;
                bullets = 3;
                DuckHunt.level = 0;
                animation.stop();
                if (event2.getCode() == KeyCode.ENTER) {
                    Text bulletsText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 7 * DuckHunt.SCALE, Color.ORANGE, "Ammo Left: 3", 200 * DuckHunt.SCALE, 10 * DuckHunt.SCALE);
                    Level1 c = new Level1(DuckHunt.scene3, DuckHunt.thirdPane, bulletsText2);
                    c.load();
                    c.initialize();
                    c.animation();
                    DuckHunt.setScene(DuckHunt.thirdPane, DuckHunt.scene3, bulletsText2, c.birdView);
                    c.animation.play();
                    DuckHunt.primaryStage.setScene(DuckHunt.scene3);
                } else if (event2.getCode() == KeyCode.ESCAPE) {
                    Media.titleMedia.play();
                    DuckHunt.scene1.setCursor(Cursor.NONE);
                    DuckHunt.scene2.setCursor(Cursor.NONE);
                    DuckHunt.primaryStage.setScene(DuckHunt.scene1);
                }
            });
        }
    }

    /**
     * Loads the bird images.
     */
    public void load() {
        // Load bird images
        birdImages = new Image[3];
        birdImages[0] = new Image(getClass().getResourceAsStream("assets/duck_black/4.png"));
        birdImages[1] = new Image(getClass().getResourceAsStream("assets/duck_black/5.png"));
        birdImages[2] = new Image(getClass().getResourceAsStream("assets/duck_black/6.png"));
    }

    /**
     * Initializes the bird view.
     */
    public void initialize() {
        birdView = new ImageView(birdImages[0]);
        birdView.setY(65 * DuckHunt.SCALE);
        birdView.setFitWidth(25 * DuckHunt.SCALE);
        birdView.setFitHeight(25 * DuckHunt.SCALE);
        birdView.setViewport(new Rectangle2D(0, 0, 0, 0));
    }

    /**
     * Sets up the bird animation.
     */
    public void animation() {
        // Set up animation
        KeyFrame keyFrame = new KeyFrame(Duration.millis(85), event -> flapWings());
        animation = new Timeline(keyFrame);
        animation.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Controls the bird wing flapping animation.
     */
    private void flapWings() {
        int currentFrame = getCurrentFrame();
        // Update bird image
        birdView.setImage(birdImages[currentFrame]);

        // Move bird horizontally
        double birdX = birdView.getLayoutX();
        double deltaX = movingRight ? 12 : -12; // Adjust the speed here
        birdX += deltaX;

        // Check if the bird has reached the edge of the screen
        if (birdX <= 0 || birdX + 90 >= DuckHunt.SCALE * 250) {
            movingRight = !movingRight;
            flipBirdImages();
            deltaX = -deltaX; // Reverse the direction
        }

        // Update bird position
        birdView.setLayoutX(birdX);
    }

    /**
     * Starts the falling delay animation.
     */
    private void startFallDelayAnimation() {
        if (fallDelayAnimation != null) {
            fallDelayAnimation.stop();
        }
        // Create a delay of 1 second before starting the falling animation
        Duration delayDuration = Duration.seconds(0.4);
        KeyValue startKeyValue = new KeyValue(birdView.imageProperty(), birdImages[2]);

        KeyFrame startKeyFrame = new KeyFrame(delayDuration, startKeyValue);
        fallDelayAnimation = new Timeline(startKeyFrame);

        fallDelayAnimation.setOnFinished(event -> {
            birdFallAnimation(scene3);
            fallDelayAnimation = null;
        });

        fallDelayAnimation.play();
    }

    /**
     * Retrieves the index of the current bird frame.
     *
     * @return The index of the current bird frame.
     */
    private int getCurrentFrame() {
        Image currentImage = birdView.getImage();
        for (int i = 0; i < birdImages.length; i++) {
            if (birdImages[i] == currentImage) {
                return (i + 1) % birdImages.length;
            }
        }
        return 0;
    }

    /**
     * Flips the bird images horizontally.
     */
    private void flipBirdImages() {
        for (int i = 0; i < birdImages.length; i++) {
            birdImages[i] = flipImage(birdImages[i]);
        }
    }

    /**
     * Flips the given image horizontally.
     *
     * @param image The image to be flipped.
     * @return The flipped image.
     */
    private Image flipImage(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage flippedImage = new WritableImage(width, height);
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = flippedImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelWriter.setArgb(width - x - 1, y, pixelReader.getArgb(x, y));
            }
        }

        return flippedImage;
    }

    /**
     * Performs the bird fall animation.
     *
     * @param scene3 The scene where the animation is performed.
     */
    private void birdFallAnimation(Scene scene3) {
        Image bird_die2 = new Image(Level1.class.getResourceAsStream("assets/duck_black/8.png"));

        birdView.setImage(bird_die2);

        // Calculate the distance to fall
        double initialY = birdView.getLayoutY();
        double groundY = scene3.getHeight() - birdView.getFitHeight();
        double distance = groundY - initialY;

        // Set up the fall animation
        Duration duration = Duration.seconds(2); // Adjust the duration here
        KeyValue keyValue = new KeyValue(birdView.layoutYProperty(), groundY);
        KeyFrame keyFrame = new KeyFrame(duration, keyValue);
        Timeline fallAnimation = new Timeline(keyFrame);

        // Handle animation completion
        fallAnimation.setOnFinished(event -> {
            birdView.setImage(null); // Remove the bird image
            birdView.setDisable(true);
        });

        fallAnimation.play();
    }

    /**
     * Gets the bird view.
     *
     * @return The bird view.
     */
    public ImageView getBirdView() {
        return birdView;
    }

    /**
     * Gets the animation.
     *
     * @return The animation.
     */
    public Timeline getAnimation() {
        return animation;
    }
}
