import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Assignment4
 * @author OKTAY KAPLAN
 * @since 03.06.2023
 * The Duckhunt class for the Duck Hunt game.
 */

public class DuckHunt extends Application {

	public static Stage primaryStage;
	public static final  double volume = 1; // volume value

	public static final double SCALE = 3; // Scale factor value

	boolean flag = false;  //It is used to stop the keyboard activity after pressing enter on the selection screen.

	private Pane rootPane;
	private Pane secondPane;
	public static Pane thirdPane;
	public static  Pane fourthPane;
	public static  Pane fifthPane;
	public static  Pane sixthPane;
	public static  Pane seventhPane;
	public static  Pane eighthPane;

	public static int level =0;
	public static int start_number = 0; //number of restarts after the game is over

	public static  Scene scene1;
	public static Scene scene2;
	public static Scene scene3;
	public static Scene scene4;
	public static Scene scene5;
	public static Scene scene6;
	public static Scene scene7;
	public static Scene scene8;

	public static Image[] backgrounds = new Image[6];
	public static Image[] foregrounds = new Image[6];
	public static Image[] plusSigns = new Image[7];

	public static int selectedBackgroundIndex = 0;
	public static int selectedPlusSignIndex = 0;

	public static ImageView background;
	public static ImageView foreground;

	private String textContent = "USE ARROW KEYS TO NAVIGATE\n     PRESS ENTER TO START\n\t PRESS ESC TO EXIT";
	private String textContent2 = "PRESS ENTER TO START\n   PRESS ESC TO EXIT";

	private Text bulletsText;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		// First Screen
		rootPane = new Pane();
		scene1 = new Scene(rootPane, 250*SCALE, 200*SCALE);

		Media media = new Media();
		Media.titleMedia.play();

		primaryStage.getIcons().add(new Image("assets/favicon/1.png"));


		Image initialImage = new Image("assets/welcome/1.png");
		ImageView imageView1 = new ImageView(initialImage);
		imageView1.setFitWidth(250*SCALE);
		imageView1.setFitHeight(200*SCALE);

		rootPane.getChildren().add(imageView1);

		Text text1 = createStyledText("Arial", FontWeight.BOLD, 12*SCALE, Color.ORANGE, textContent2, 60*SCALE, 135*SCALE);
		rootPane.getChildren().add(makeTextDisappear(text1));


		//loop of title screen
		scene1.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				flag = false;
				selectedPlusSignIndex =0;
				setBackgroundImage(backgrounds[0]);
				setPlusSignImage(scene2.getWidth() / 2, scene2.getHeight() / 2);
				Text text = createStyledText("Verdana", FontWeight.BOLD, 8*SCALE, Color.ORANGE, textContent, 65*SCALE, 17*SCALE);
				secondPane.getChildren().add(text);
				primaryStage.setScene(scene2);
			} else if (event.getCode() == KeyCode.ESCAPE) {
				primaryStage.close();
			}
		});

		// Second Screen run load methods
		loadBackgroundImages();
		loadPlusSignImages();
		loadForegroundImages();

		secondPane = new Pane();
		scene2 = new Scene(secondPane, 250*SCALE, 200*SCALE);

		//go select screen
		selectBackgroundAndPlusSign(backgrounds, foregrounds);

		// Third Screen
		thirdPane = new Pane();
		scene3 = new Scene(thirdPane, 250*SCALE, 200*SCALE);

		bulletsText = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 7*SCALE, Color.ORANGE, "Ammo Left: 3", 200*SCALE, 10*SCALE);

		// fourth Screen
		fourthPane = new Pane();
		scene4 = new Scene(fourthPane, 250*SCALE, 200*SCALE);

		// fifth Screen
		fifthPane = new Pane();
		scene5 = new Scene(fifthPane, 250*SCALE, 200*SCALE);

		// sixty Screen
		sixthPane = new Pane();
		scene6 = new Scene(sixthPane, 250*SCALE, 200*SCALE);

		// seventy Screen
		seventhPane = new Pane();
		scene7 = new Scene(seventhPane, 250*SCALE, 200*SCALE);

		// eighty Screen
		eighthPane = new Pane();
		scene8 = new Scene(eighthPane, 250*SCALE, 200*SCALE);

		primaryStage.setScene(scene1);
		primaryStage.setTitle("HUBBMDuckHunt");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); // Disable full screen mode
		primaryStage.setResizable(false); // Prevent window resize
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Creates a flashing label with the given text.
	 *
	 * @param text The text to be displayed in the label.
	 * @return The created Text object.
	 */
	public static Text makeTextDisappear(Text text) {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, e -> text.setVisible(true)),
				new KeyFrame(Duration.seconds(1), e -> text.setVisible(false)),
				new KeyFrame(Duration.seconds(2), e -> text.setVisible(true))
		);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		return text;
	}/**
	 * Sets the scene with a single bird view.
	 *
	 * @param pane        The Pane object to add the elements to.
	 * @param scene       The Scene object to set the cursor for.
	 * @param bulletsText The Text object representing the bullets count.
	 * @param birdView    The ImageView object representing the bird.
	 */
	public static void setScene(Pane pane,Scene scene,Text bulletsText,ImageView birdView){
		level++;
		background = new ImageView(backgrounds[selectedBackgroundIndex]);
		background.setFitHeight(200*SCALE);
		background.setFitWidth(250*SCALE);
		foreground = new ImageView(foregrounds[selectedBackgroundIndex]);
		foreground.setFitHeight(200*SCALE);
		foreground.setFitWidth(250*SCALE);
		pane.getChildren().addAll(background, birdView, foreground);
		// Add bullets text
		pane.getChildren().add(bulletsText);

		Text levelText = createStyledText("Arial", FontWeight.BOLD, 7*SCALE, Color.ORANGE, "Level "+level+"/3", 115*SCALE, 10*SCALE);
		pane.getChildren().add(levelText);

		scene.setCursor(new ImageCursor(plusSigns[selectedPlusSignIndex], 165*SCALE, 165*SCALE));

	}/**
	 * Sets the scene with a two bird view.
	 */
	public static void setScene(Pane pane,Scene scene,Text bulletsText,ImageView birdView,ImageView birdView2){
		level++;
		background = new ImageView(backgrounds[selectedBackgroundIndex]);
		background.setFitHeight(200*SCALE);
		background.setFitWidth(250*SCALE);
		foreground = new ImageView(foregrounds[selectedBackgroundIndex]);
		foreground.setFitHeight(200*SCALE);
		foreground.setFitWidth(250*SCALE);
		pane.getChildren().addAll(background, birdView,birdView2, foreground);
		// Add bullets text
		pane.getChildren().add(bulletsText);

		Text levelText = createStyledText("Arial", FontWeight.BOLD, 7*SCALE, Color.ORANGE, "Level "+level+"/3", 115*SCALE, 10*SCALE);
		pane.getChildren().add(levelText);

		scene.setCursor(new ImageCursor(plusSigns[selectedPlusSignIndex], 165*SCALE, 165*SCALE));

	}/**
	 * Sets the scene with a three  bird view.
	 */
	public static void setScene(Pane pane,Scene scene,Text bulletsText,ImageView birdView,ImageView birdView2,ImageView birdView3){
		level++;
		background = new ImageView(backgrounds[selectedBackgroundIndex]);
		background.setFitHeight(200*SCALE);
		background.setFitWidth(250*SCALE);
		foreground = new ImageView(foregrounds[selectedBackgroundIndex]);
		foreground.setFitHeight(200*SCALE);
		foreground.setFitWidth(250*SCALE);
		pane.getChildren().addAll(background, birdView,birdView2,birdView3, foreground);
		// Add bullets text
		pane.getChildren().add(bulletsText);

		Text levelText = createStyledText("Arial", FontWeight.BOLD, 7*SCALE, Color.ORANGE, "Level "+level+"/3", 115*SCALE, 10*SCALE);
		pane.getChildren().add(levelText);

		scene.setCursor(new ImageCursor(plusSigns[selectedPlusSignIndex], 165*SCALE, 165*SCALE));

	}
	/**
	 * Loads the background images.
	 */
	private void loadBackgroundImages() {
		// Load background images here
		backgrounds[0] = new Image("assets/background/1.png");
		backgrounds[1] = new Image("assets/background/2.png");
		backgrounds[2] = new Image("assets/background/3.png");
		backgrounds[3] = new Image("assets/background/4.png");
		backgrounds[4] = new Image("assets/background/5.png");
		backgrounds[5] = new Image("assets/background/6.png");
	}/**
	 * Creates a styled Text object with the given properties.
	 *
	 * @param fontName    The name of the font.
	 * @param fontWeight  The weight of the font.
	 * @param fontSize    The size of the font.
	 * @param fillColor   The fill color of the text.
	 * @param content     The content of the text.
	 * @param x           The x-coordinate of the text.
	 * @param y           The y-coordinate of the text.
	 * @return The created Text object.
	 */
	public static Text createStyledText(String fontName, FontWeight fontWeight, double fontSize, Color fillColor, String content, double x, double y) {
		Text text = new Text(content);
		text.setFont(Font.font(fontName, fontWeight, fontSize));
		text.setFill(fillColor);
		text.setX(x);
		text.setY(y);
		return text;
	}/**
	 * Loads the foreground images.
	 */

	private void loadForegroundImages() {
		// Load background images here
		foregrounds[0] = new Image("assets/foreground/1.png");
		foregrounds[1] = new Image("assets/foreground/2.png");
		foregrounds[2] = new Image("assets/foreground/3.png");
		foregrounds[3] = new Image("assets/foreground/4.png");
		foregrounds[4] = new Image("assets/foreground/5.png");
		foregrounds[5] = new Image("assets/foreground/6.png");
	}
	/**
	 * Loads the plus sign images.
	 */
	private void loadPlusSignImages() {
		// Load plus sign images here
		plusSigns[0] = new Image("assets/crosshair/1.png");
		plusSigns[1] = new Image("assets/crosshair/2.png");
		plusSigns[2] = new Image("assets/crosshair/3.png");
		plusSigns[3] = new Image("assets/crosshair/4.png");
		plusSigns[4] = new Image("assets/crosshair/5.png");
		plusSigns[5] = new Image("assets/crosshair/6.png");
		plusSigns[6] = new Image("assets/crosshair/7.png");
	}/**
	 * Selects the background and plus sign images and sets them.
	 *
	 * @param backgrounds  The array of background images.
	 * @param foregrounds  The array of foreground images.
	 */
	// ı set background and crosshair at this method.
	private void selectBackgroundAndPlusSign(Image[] backgrounds, Image[] foregrounds) {
		setBackgroundImage(backgrounds[selectedBackgroundIndex]);
		setPlusSignImage(scene2.getWidth() / 2, scene2.getHeight() / 2);

		scene2.setOnKeyPressed(event -> {
			if (flag){
				return;
			}
			if (event.getCode() == KeyCode.RIGHT) {
				selectedBackgroundIndex = (selectedBackgroundIndex + 1) % backgrounds.length;
				setBackgroundImage(backgrounds[selectedBackgroundIndex]);
			} else if (event.getCode() == KeyCode.LEFT) {
				selectedBackgroundIndex = (selectedBackgroundIndex - 1 + backgrounds.length) % backgrounds.length;
				setBackgroundImage(backgrounds[selectedBackgroundIndex]);
			}else if (event.getCode() == KeyCode.UP) {
				selectedPlusSignIndex = (selectedPlusSignIndex + 1) % plusSigns.length;
				setPlusSignImage(scene2.getWidth() / 2, scene2.getHeight() / 2);
			}else if (event.getCode() == KeyCode.DOWN) {
				selectedPlusSignIndex = (selectedPlusSignIndex - 1+plusSigns.length) % plusSigns.length;
				setPlusSignImage(scene2.getWidth() / 2, scene2.getHeight() / 2);
			}

			//loop of select screen
			if (event.getCode() == KeyCode.ENTER) {
				flag = true;
				Media.titleMedia.stop();
				Media.introMedia.play();
				Media.introMedia.setOnEndOfMedia(() -> {
					Media.introMedia.stop();
					Text bulletsText2 = DuckHunt.createStyledText("Arial", FontWeight.BOLD, 7*SCALE, Color.ORANGE, "Ammo Left: 3", 200*SCALE, 10*SCALE);
					Level1 c = new Level1(scene3, DuckHunt.thirdPane, bulletsText2);
					c.load();
					c.initialize();
					c.animation();
					DuckHunt.setScene(thirdPane, scene3, bulletsText2, c.getBirdView());
					c.getAnimation().play();
					DuckHunt.primaryStage.setScene(DuckHunt.scene3);
				});

			}
			if (event.getCode() == KeyCode.ESCAPE) {
				primaryStage.setScene(scene1);
			}
			Text text = createStyledText("Verdana", FontWeight.BOLD, 8*SCALE, Color.ORANGE, textContent, 65*SCALE, 17*SCALE);
			secondPane.getChildren().add(text);
		});
	}/**
	 * Sets the background image of the second pane.
	 *
	 * @param image The background image to set.
	 */

	private void setBackgroundImage(Image image) {
		BackgroundImage backgroundImage = new BackgroundImage(
				image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(250*SCALE,200*SCALE,false,false,false,false));

		secondPane.setBackground(new Background(backgroundImage));
	}/**
	 * Sets the plus sign image on the second pane.
	 *
	 * @param x The x-coordinate of the plus sign.
	 * @param y The y-coordinate of the plus sign.
	 */

	private void setPlusSignImage(double x, double y) {
		secondPane.getChildren().clear();

		Image plusSignImage = plusSigns[selectedPlusSignIndex];
		javafx.scene.image.ImageView plusSignImageView = new javafx.scene.image.ImageView(plusSignImage);
		plusSignImageView.setFitHeight(10*SCALE);
		plusSignImageView.setFitWidth(10*SCALE);

		plusSignImageView.setTranslateX(x - plusSignImage.getWidth() / 2);
		plusSignImageView.setTranslateY(y - plusSignImage.getHeight() / 2);

		secondPane.getChildren().add(plusSignImageView);
	}
}