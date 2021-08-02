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
public class Livre extends Materiel{    
    public static final ArrayList<Livre>list = new ArrayList<Livre>();
    public Livre(){
    }
    public Livre(int id,String titre,String auteur,int nbEx,int nbRestant,String type,String localisation,String image) {
        this.identifiant=id;
        initialise(titre,auteur,nbEx,nbRestant,type,localisation,image);
        list.add(this);
    }
    public Livre(String titre,String auteur,int nbEx,int nbRestant,String type,String localisation,String image) {
        try{
            this.identifiant =list.get(list.size()-1).identifiant+1;
        }catch(Exception es){
            this.identifiant=1;
        }
        initialise(titre,auteur,nbEx,nbRestant,type,localisation,image);
        this.save();
        list.add(this);
    }
    private void save(){
        try {
            int statut = statement.executeUpdate( "INSERT INTO livre(identifiant,titre,nomAuteur,nbExemplaire,type,localisation,image,nbRestant) Values ("+identifiant+",\""+titre+"\",\""+auteur+"\","+nbEx+",\""+type+"\",\""+localisation+"\",\""+image+"\","+nbRestant+");" );
        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void research(String str,String str2) {
        try {
            str=str.toLowerCase();
            if (str.equals("auteur")){
                str="nomAuteur";
            }
            if (str.equals("nombre restant")){
                str="nbRestant";
            }
            bd("SELECT * FROM livre where "+str+" Like '%"+str2+"%'Order by "+str ,list);
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void tri(String str) {
        try {
            str=str.toLowerCase();
            if (str.equals("auteur")){
                str="nomAuteur";
            }
            if (str.equals("nombre restant")){
                str="nbRestant";
            }
            bd("SELECT * FROM livre Order by "+str ,list);
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
                String nomAuteur = resultat.getString( "nomAuteur" );
                String localisation = resultat.getString( "localisation" );
                int nbExemplaire = resultat.getInt("nbExemplaire");
                int nbRestant = resultat.getInt("nbRestant");
                String type = resultat.getString("type");
                String image = resultat.getString("image");
                new Livre(id,titre, nomAuteur, nbExemplaire,nbRestant, type, localisation, image);
            }
    }

}
