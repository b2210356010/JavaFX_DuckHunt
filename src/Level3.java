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
 * Represents the Level 3 of the game.
 */
public class Level3 extends Levels {
    private ImageView birdView;
    private ImageView bird2View;
    private ImageView bird3View;
    private Timeline animation;
    private Timeline animation2;
    private Timeline animation3;
    private boolean movingRight = true;
    private boolean movingRight2 = true;
    private boolean movingRight3 = true;
    private Image[] birdImages = new Image[3];
    private Image[] bird2Images = new Image[3];
    private Image[] bird3Images = new Image[3];
    private int bullets =9;
    private  boolean birdHit = false;
    private  boolean bird2Hit = false;
    private  boolean bird3Hit = false;
    private Text bulletsText;
    private Timeline fallDelayAnimation;
    private Timeline fallDelayAnimation2;
    private Timeline fallDelayAnimation3;
    private Scene scene7;
    private Pane seventhPane;
    /**
     * Constructs a Level3 object.
     *
     * @param scene7       The scene of Level 3.
     * @param seventhPane   The pane of Level 3.
     * @param bulletsText  The text indicating the remaining bullets.
     */
    public Level3(Scene scene7, Pane seventhPane, Text bulletsText){
        this.scene7 = scene7;
        this.seventhPane = seventhPane;
        this.bulletsText = bulletsText;

        // Handle mouse click event
        scene7.setOnMouseClicked(event -> {
            Media.fallsMedia();
            Media.gunMedia();
            if (bullets == 0) {
                return; // Stop the function if bullets == 0
            }
            if (event.getButton() == MouseButton.PRIMARY && (birdView.getBoundsInParent().contains(event.getX(), event.getY()))) {
                bullets--;
                bulletsText.setText("Ammo Left: " + bullets);
                birdHit = true;
                Media.fallsMedia();
                Media.gunMedia();
                Image bird_die = new Image(getClass().getResourceAsStream("assets/duck_red/7.png"));
                birdView.setImage(bird_die);
                animation.stop();
                startFallDelayAnimation(birdImages,birdView,fallDelayAnimation);
                controlWin();
            }if (event.getButton() == MouseButton.PRIMARY && (bird2View.getBoundsInParent().contains(event.getX(), event.getY()))) {
                bullets--;
                bulletsText.setText("Ammo Left: " + bullets);
                bird2Hit = true;
                Media.fallsMedia();
                Media.gunMedia();
                Image bird_die = new Image(getClass().getResourceAsStream("assets/duck_blue/7.png"));
                bird2View.setImage(bird_die);
                animation2.stop();
                startFallDelayAnimation(bird2Images,bird2View,fallDelayAnimation2);
                controlWin();

            }if (event.getButton() == MouseButton.PRIMARY && (bird3View.getBoundsInParent().contains(event.getX(), event.getY()))) {
                bullets--;
                bulletsText.setText("Ammo Left: " + bullets);
                bird3Hit = true;
                Media.fallsMedia();
                Media.gunMedia();
                Image bird_die = new Image(getClass().getResourceAsStream("assets/duck_black/7.png"));
                bird3View.setImage(bird_die);
                animation3.stop();
                startFallDelayAnimation(bird3Images,bird3View,fallDelayAnimation3);
                controlWin();

            } else if (event.getButton() == MouseButton.PRIMARY && bullets > 0) {
                if ( birdHit && bird2Hit && bird3Hit) {
                    return; // Stop the function if bullets == 0
                }
                Media.gunMedia();
                bullets--;
                bulletsText.setText("Ammo Left: " + bullets);
                controlBullet();
            }
        });
    }/**
     * Loads bird images.
     */
    public void load(){
        // Load bird images
        birdImages = new Image[3];
        birdImages[0] = new Image(getClass().getResourceAsStream("assets/duck_red/4.png"));
        birdImages[1] = new Image(getClass().getResourceAsStream("assets/duck_red/5.png"));
        birdImages[2] = new Image(getClass().getResourceAsStream("assets/duck_red/6.png"));
        bird2Images = new Image[3];
        bird2Images[0] = new Image(getClass().getResourceAsStream("assets/duck_blue/4.png"));
        bird2Images[1] = new Image(getClass().getResourceAsStream("assets/duck_blue/5.png"));
        bird2Images[2] = new Image(getClass().getResourceAsStream("assets/duck_blue/6.png"));
        bird3Images = new Image[3];
        bird3Images[0] = new Image(getClass().getResourceAsStream("assets/duck_black/4.png"));
        bird3Images[1] = new Image(getClass().getResourceAsStream("assets/duck_black/5.png"));
        bird3Images[2] = new Image(getClass().getResourceAsStream("assets/duck_black/6.png"));
    }/**
     * Controls the behavior of the bullet.
     */
    private void controlBullet(){
        if (bullets == 0) {
            Media.gameOverMedia();
            Text loseText = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10*DuckHunt.SCALE, Color.ORANGE, getGameover_text(), 60*DuckHunt.SCALE, 100*DuckHunt.SCALE);
            Text loseText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10*DuckHunt.SCALE, Color.ORANGE, getGameover_text2(), 60*DuckHunt.SCALE, 100*DuckHunt.SCALE);
            loseText2 = DuckHunt.makeTextDisappear(loseText2);
            seventhPane.getChildren().addAll(loseText,loseText2);
            scene7.setOnKeyPressed(event2 -> {
                Media.gameOverMedia.stop();
                Media.gunMedia.stop();
                DuckHunt.start_number++;
                bullets = 9;
                DuckHunt.level =0;
                animation.stop();
                if (event2.getCode() == KeyCode.ENTER) {
                    Text bulletsText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 7*DuckHunt.SCALE, Color.ORANGE, "Ammo Left: 3", 200*DuckHunt.SCALE, 10*DuckHunt.SCALE);
                    Level1 c =  new Level1(DuckHunt.scene3, DuckHunt.thirdPane,bulletsText2);
                    c.load();
                    c.initialize();
                    c.animation();
                    DuckHunt.setScene(DuckHunt.thirdPane, DuckHunt.scene3,bulletsText2,c.getBirdView());
                    c.getAnimation().play();
                    DuckHunt.primaryStage.setScene(DuckHunt.scene3);
                }else if (event2.getCode() == KeyCode.ESCAPE) {
                    Media.titleMedia.play();
                    DuckHunt.scene1.setCursor(Cursor.NONE);
                    DuckHunt.scene2.setCursor(Cursor.NONE);
                    DuckHunt.primaryStage.setScene(DuckHunt.scene1);
                }
            });
        }

    }private void controlWin(){
        //loop for level 6 if you win the game
        if (birdHit && bird2Hit && bird3Hit) {
            bullets = 9;
            Media.completedMedia();
            Text winText = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10*DuckHunt.SCALE, Color.ORANGE, getWin_text(), 60*DuckHunt.SCALE, 100*DuckHunt.SCALE);
            Text winText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 10*DuckHunt.SCALE, Color.ORANGE, getWin_text2(), 60*DuckHunt.SCALE, 100*DuckHunt.SCALE);
            winText2 = DuckHunt.makeTextDisappear(winText2);
            seventhPane.getChildren().addAll(winText,winText2);
            scene7.setOnKeyPressed(event2 -> {
                Media.fallsMedia.stop();
                Media.gunMedia.stop();
                Media.completedMedia.stop();
                DuckHunt.start_number++;
                DuckHunt.level =0;
                birdHit =false;
                if (event2.getCode() == KeyCode.ESCAPE) {
                    Media.titleMedia.play();
                    DuckHunt.scene1.setCursor(Cursor.NONE);
                    DuckHunt.scene2.setCursor(Cursor.NONE);
                    DuckHunt.primaryStage.setScene(DuckHunt.scene1);
                }else if (event2.getCode() == KeyCode.ENTER) {
                    Text bulletsText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 7*DuckHunt.SCALE, Color.ORANGE, "Ammo Left: 3", 200*DuckHunt.SCALE, 10*DuckHunt.SCALE);
                    Level1 c =  new Level1(DuckHunt.scene3, DuckHunt.thirdPane,bulletsText2);
                    c.load();
                    c.initialize();
                    c.animation();
                    DuckHunt.setScene(DuckHunt.thirdPane, DuckHunt.scene3,bulletsText2,c.getBirdView());
                    c.getAnimation().play();
                    DuckHunt.primaryStage.setScene(DuckHunt.scene3);
                }
            });
        }
    }
    /**
     * Initializes the Level 3.
     */
    public void initialize(){
        birdView = new ImageView(birdImages[0]);
        birdView.setY(25*DuckHunt.SCALE);
        birdView.setFitWidth(25*DuckHunt.SCALE);
        birdView.setFitHeight(25*DuckHunt.SCALE);
        birdView.setViewport(new Rectangle2D(0, 0, 0, 0));
        bird2View = new ImageView(bird2Images[0]);
        bird2View.setY(50*DuckHunt.SCALE);
        bird2View.setFitWidth(25*DuckHunt.SCALE);
        bird2View.setFitHeight(25*DuckHunt.SCALE);
        bird2View.setViewport(new Rectangle2D(0, 0, 0, 0));
        bird3View = new ImageView(bird2Images[0]);
        bird3View.setY(60*DuckHunt.SCALE);
        bird3View.setFitWidth(25*DuckHunt.SCALE);
        bird3View.setFitHeight(25*DuckHunt.SCALE);
        bird3View.setViewport(new Rectangle2D(0, 0, 0, 0));
    }/**
     * Sets up the animation for Level 3.
     */
    public void animation(){
        // Set up animation
        KeyFrame keyFrame = new KeyFrame(Duration.millis(65), event -> flapWings(birdImages,birdView,movingRight));
        animation = new Timeline(keyFrame);
        animation.setCycleCount(Animation.INDEFINITE);

        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(75), event -> flapWings(bird2Images,bird2View,movingRight2));
        animation2 = new Timeline(keyFrame2);
        animation2.setCycleCount(Animation.INDEFINITE);

        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(85), event -> flapWings(bird3Images,bird3View,movingRight3));
        animation3 = new Timeline(keyFrame3);
        animation3.setCycleCount(Animation.INDEFINITE);

    }/**
     * Starts the fall delay animation.
     */
    private void startFallDelayAnimation(Image[] images,ImageView view,Timeline delayAnimation) {
        if (delayAnimation != null) {
            delayAnimation.stop();
        }

        // Create a delay of 1 second before starting the falling animation
        Duration delayDuration = Duration.seconds(0.4);
        KeyValue startKeyValue = new KeyValue(view.imageProperty(), images[2]);

        KeyFrame startKeyFrame = new KeyFrame(delayDuration, startKeyValue);
        delayAnimation = new Timeline(startKeyFrame);

        delayAnimation.setOnFinished(event -> {
            birdFallAnimation(scene7,view);
            if (view == birdView){
                fallDelayAnimation = null;
            }else if (view == bird2View){
                fallDelayAnimation2 = null;
            }else {
                fallDelayAnimation3 = null;
            }
        });

        delayAnimation.play();
    }/**
     * Flaps the bird wings during animation.
     */
    private void flapWings(Image[] images,ImageView view,Boolean way_right) {
        int currentFrame = getCurrentFrame(images,view);

        // Update bird image
        view.setImage(images[currentFrame]);

        // Move bird horizontally
        double birdX = view.getLayoutX();
        double deltaX = way_right ? 19 : -19; // Adjust the speed here
        birdX += deltaX;

        // Check if the bird has reached the edge of the screen
        if (birdX <= 0 || birdX + 90 >= DuckHunt.SCALE*250) {
            if (view == birdView){
                movingRight = !way_right;
            }else if (view == bird2View){
                movingRight2 = !way_right;
            }else {
                movingRight3 = !way_right;
            }
            flipBirdImages(images);
            deltaX = -deltaX; // Reverse the direction
        }

        // Update bird position
        view.setLayoutX(birdX);
    } /**
     * Gets the current frame index of the bird image.
     *
     * @return The current frame index.
     */
    private int getCurrentFrame(Image[] images,ImageView view) {
        Image currentImage = view.getImage();
        for (int i = 0; i < images.length; i++) {
            if (images[i] == currentImage) {
                return (i + 1) % images.length;
            }
        }
        return 0;
    }/**
     * Flips the bird images horizontally.
     */
    private void flipBirdImages(Image[] images) {
        for (int i = 0; i < images.length; i++) {
            images[i] = flipImage(images[i]);
        }

    }/**
     * Flips an image horizontally.
     *
     * @param image The image to flip.
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
    }/**
     * Performs the bird fall animation.
     *
     * @param scene7 The scene where the animation is performed.
     */
    private void birdFallAnimation(Scene scene7,ImageView view) {
        Image bird_die3 = new Image(getClass().getResourceAsStream("assets/duck_red/8.png"));
        Image bird_die4 = new Image(getClass().getResourceAsStream("assets/duck_blue/8.png"));
        Image bird_die5 = new Image(getClass().getResourceAsStream("assets/duck_black/8.png"));
        if (view == birdView){
            view.setImage(bird_die3);
        }else if (view == bird2View){
            view.setImage(bird_die4);
        }else{
            view.setImage(bird_die5);
        }


        // Calculate the distance to fall
        double initialY = view.getLayoutY();
        double groundY = scene7.getHeight() - view.getFitHeight();
        double fallDistance = groundY - initialY;

        // Create a new animation to make the bird fall
        Timeline fallAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(view.layoutYProperty(), initialY)),
                new KeyFrame(Duration.seconds(4), new KeyValue(view.layoutYProperty(), groundY))
        );
        fallAnimation.play();
    }

    public ImageView getBirdView() {
        return birdView;
    }

    public Timeline getAnimation() {
        return animation;
    }

    public ImageView getBird2View() {
        return bird2View;
    }

    public Timeline getAnimation2() {
        return animation2;
    }
    public ImageView getBird3View() {
        return bird3View;
    }

    public Timeline getAnimation3() {
        return animation3;
    }
}