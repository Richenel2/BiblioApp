/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlWidgets.widgets;


import fxmlController.ShowMemberController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author the Richenel
 */
public class TargetButton extends Button{
    public static final ArrayList<TargetButton> list = new ArrayList<TargetButton>();
    public static AnchorPane parentNode,childrenNode;
    String page;
    public TargetButton(){
        
    }
    public TargetButton(String str, String pag,AnchorPane parent,AnchorPane child){
        this.setText(str);
        this.setPrefHeight(50);
        this.setPrefWidth(200);
        list.add(this);
        TargetButton ceci= this;
        this.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle (ActionEvent event){
                try {
                    ceci.activeMenu();
                } catch (IOException ex) {
                    Logger.getLogger(TargetButton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        parentNode=parent;
        childrenNode=child;
        page=pag;
        
    }
    public TargetButton(String str, String pag){
        this.setText(str);
        this.setPrefHeight(50);
        this.setPrefWidth(200);
        list.add(this);
        TargetButton ceci= this;
        this.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle (ActionEvent event){
                try {
                    ceci.activeMenu();
                } catch (IOException ex) {
                    Logger.getLogger(TargetButton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        page=pag;
        
    }
    public void activeMenu() throws IOException{
        parentNode.getChildren().remove(parentNode.getChildren().indexOf(childrenNode));
        AnchorPane newChildrenNode = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlFile/"+page+".fxml"));
        parentNode.getChildren().add(newChildrenNode);
        for (TargetButton button : list){
            button.setStyle("-fx-background-color :none;");
            childrenNode=newChildrenNode;
        }
        this.setStyle("-fx-background-color:linear-gradient(to right top, #ff9f2b, #ff8138, #ff5f4c, #ff3a64, #ff007f);");
        ShowMemberController.list=null;
    }
    public void activeMenu(ArrayList list) throws IOException{
        ShowMemberController.list=list;
        activeMenu();
    }
    public void act(){
        parentNode.getChildren().remove(parentNode.getChildren().indexOf(childrenNode));
        AnchorPane newChildrenNode=null;
        try {
            newChildrenNode = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxmlFile/"+page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(TargetButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        parentNode.getChildren().add(newChildrenNode);
        childrenNode=newChildrenNode;
    }
}
