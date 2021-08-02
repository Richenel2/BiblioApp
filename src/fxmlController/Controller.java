/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlController;

import classeApp.Materiel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXScrollPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author the Richenel
 */
public class Controller {    
    Boolean bool=false;
    public static Materiel materiel = null;
    @FXML
    protected AnchorPane changeAnchor;
    @FXML
    protected HBox hBoxPrincipale;
    @FXML
    protected VBox vboxLivre;
    @FXML
    protected TextField searchField;
    @FXML
    protected JFXComboBox<String> choixSearch;
    @FXML
    protected JFXRadioButton radioButton;
    @FXML
    protected JFXScrollPane scrollpane;
    @FXML
    protected Label title;
    @FXML
    protected JFXButton btnAdd;
    /**
     * Initializes the controller class.
     */
}
