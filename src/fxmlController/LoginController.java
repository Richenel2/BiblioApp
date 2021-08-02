/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlController;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author the Richenel
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane close;
    @FXML
    private Label forget;
    @FXML
    private AnchorPane panelLogin;
    @FXML
    private AnchorPane panelLogo;
    @FXML
    private Label iconText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label isTrue;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panelLogin.setLayoutX(0);
        String content = iconText.getText();
        int n = content.length();
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1.5));
        transition.setToY(150);
        transition.setNode(panelLogin);
        transition.setDelay(Duration.seconds(3));
        
        TranslateTransition transit = new TranslateTransition();
        transit.setDuration(Duration.seconds(1.5));
        transit.setToY(-75);
        transit.setNode(panelLogo);
        transit.setDelay(Duration.seconds(3));
        
        Transition animation = new Transition(){
            {
            setCycleDuration(Duration.seconds(3));
            }
            @Override
            protected void interpolate(double frac){
                final int m = Math.round(n*(float) frac);
                iconText.setText(content.substring(0,m)+" |");
            
            }
        };
        
        animation.play();
        transition.play();
        transit.play();
    }    
    Stage stage;

    @FXML
    private void closeApp(MouseEvent event) {
        stage = (Stage)(close.getScene().getWindow()) ;
        stage.close();
    }

    @FXML
    private void color(MouseEvent event) {
        forget.setTextFill(Color.valueOf("#55aaff")); 
    }


    @FXML
    private void changeCloseColor(MouseEvent event) {
        close.setStyle("-fx-background-color: #ff007f;");
    }
 

    @FXML
    private void changeToOpenApp(ActionEvent event) throws IOException {

        if(password.getText().equals("admin") && username.getText().equals("admin")){
            
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlFile/accueil.fxml"));
            stage = (Stage)(close.getScene().getWindow()) ;
            stage.close();
            Stage stage2 = new Stage();
            Scene scene = new Scene(root);
            stage2.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage2.setScene(scene);
            stage2.show();
        }else {
            isTrue.setVisible(true);
            password.setText("");
            username.setText("");
        }
    }
    
}
