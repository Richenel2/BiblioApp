/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classeApp;

import static classeApp.Materiel.statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author the Richenel
 */
public class CD extends Materiel{
    public static final ArrayList<CD>list =new ArrayList<CD>();
    public int duree;
    public CD(int id,String titre,String auteur,int nbEx,int nbRestant,String type,int duree,String localisation,String image) {
        this.identifiant=id;
        initialise(titre,auteur,nbEx,nbRestant,type,localisation,image);
        this.duree=duree;
        list.add(this);
    }
    public CD(String titre,String auteur,int nbEx,int nbRestant,String type,int duree,String localisation,String image) {
        try{
            this.identifiant =list.get(list.size()-1).identifiant+1;
        }catch(Exception es){
            this.identifiant=1;
        }
        initialise(titre,auteur,nbEx,nbRestant,type,localisation,image);
        this.duree=duree;
        this.save();
        list.add(this);
    }
    private void save(){
        try {
            int statut = statement.executeUpdate( "INSERT INTO cd(identifiant,titre,auteur,nbExemplaire,type,duree,localisation,image,nbRestant) Values ("+identifiant+",\""+titre+"\",\""+auteur+"\","+duree+",\""+type+"\","+nbEx+",\""+localisation+"\",\""+image+"\","+nbRestant+");" );
        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimer(){
      String sql = "DELETE FROM cd WHERE identifiant = "+this.identifiant;
        try {
            statement.executeUpdate(sql);
            list.remove(this);
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void research(String str,String str2) {
        try {
            str=str.toLowerCase();
            bd("SELECT * FROM cd where "+str+" Like '%"+str2+"%'Order by "+str ,list);
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void tri(String str) {
        try {
            str=str.toLowerCase();
            if (str.equals("nombre restant")){
                str="nbRestant";
            }
            bd("SELECT * FROM cd Order by "+str ,list);
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    public static void bd(String str, ArrayList li) throws SQLException{
        if (li!=null){
            li.removeAll(li);
        }
        ResultSet resultat = statement.executeQuery(str);
            /* Récupération des données du résultat de la requête de lecture */
            while ( resultat.next() ) {
                int id = resultat.getInt( "identifiant" );
                String titre = resultat.getString( "titre" );
                String nomAuteur = resultat.getString( "auteur" );
                String localisation = resultat.getString( "localisation" );
                int nbExemplaire = resultat.getInt("nbExemplaire");
                int nbRestant = resultat.getInt("nbRestant");
                String type = resultat.getString("type");
                String image = resultat.getString("image");
                int duree = resultat.getInt("duree");
                new CD(id,titre, nomAuteur, nbExemplaire,nbRestant, type,duree, localisation, image);
            }
    }
    
}
