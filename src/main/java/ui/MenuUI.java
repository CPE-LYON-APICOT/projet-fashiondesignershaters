package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuUI {
    private final StackPane container;
    private final Runnable onPlayAction;

    public MenuUI(double width, double height, Runnable onPlayAction) {
        this.onPlayAction = onPlayAction;
        this.container = new StackPane();
        this.container.setPrefSize(width, height);

        // Fond sombre semi-transparent
        Rectangle background = new Rectangle(width, height, Color.rgb(30, 30, 46, 0.9));

        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);

        // Titre du jeu
        Text title = new Text("FASHION DESIGNERS HATERS");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        title.setFill(Color.WHITE);

        // Bouton Jouer
        Button playButton = new Button("DÉMARRER LA PARTIE");
        playButton.setPrefSize(250, 60);
        playButton.setStyle("-fx-font-size: 20px; -fx-base: #f38ba8; -fx-text-fill: white;");

        playButton.setOnAction(e -> {
            container.setVisible(false);
            onPlayAction.run(); // Déclenche la logique de jeu
        });

        layout.getChildren().addAll(title, playButton);
        container.getChildren().addAll(background, layout);
    }

    public StackPane getContainer() {
        return container;
    }
}