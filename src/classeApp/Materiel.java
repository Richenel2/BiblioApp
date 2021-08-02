/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classeApp;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author the Richenel
 */
public class Materiel {
    protected int identifiant ;
    public String titre;
    public String auteur;
    public int nbEx;
    public int nbRestant;
    public String type;
    public String localisation;
    public String image;
    public static ArrayList list =null;
    protected static Connection conn = null;
    protected static Statement statement;
    public Materiel() {
    }
    
    public static void initialise() {
        try {
            conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/biblioapp", "root", "" );
            statement = conn.createStatement();/* Exécution d'une requête de lecture */
            Livre.bd("SELECT * FROM livre;",list );
            CD.bd("SELECT * FROM cd;",list );
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void initialise(String titre,String auteur,int nbEx,int nbRestant,String type,String localisation,String image){
        this.titre=titre;
        this.auteur=auteur;
        this.nbEx=nbEx;
        this.nbRestant=nbRestant;
        this.type=type;
        this.localisation=localisation;
        this.image=image;
    }
    public void supprimer(){
        String str="";
        
        if ((this.getClass()+"").equals("class classeApp.Livre")){
            list=Livre.list;
            str="livre";
        }else{
            list=CD.list;
            str="cd";
        }
        String sql = "DELETE FROM "+str+" WHERE identifiant = "+this.identifiant;
        try {
            statement.executeUpdate(sql);
            list.remove(this);
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifier(){        
        String str="";
        if ((this.getClass()+"").equals("class classeApp.Livre")){
            list=Livre.list;
            str="livre";
        }else{
            list=CD.list;
            str="cd";
        }
        try {
            statement.executeUpdate("update "+str+" set titre=\""+titre+"\" , nomAuteur= \""+auteur+"\" , nbExemplaire="+nbEx+" , type = \""+type+"\" , localisation = \""+localisation+"\" where identifiant="+identifiant);
        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
