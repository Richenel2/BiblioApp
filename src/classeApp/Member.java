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
public class Member {
    public static final ArrayList<Member>list =new ArrayList<Member>();
    private int identifiant;
    public String nom;
    public String email;
    public int telephone;
    public int niveau;
    public String filiere;
    public Boolean status=false;
    private Member(int id,String nom, String email,int tel,int niveau, String filiere, Boolean status){
        this.identifiant=id;
        this.nom=nom;
        this.email=email;
        this.telephone=tel;
        this.niveau=niveau;
        this.filiere=filiere;
        this.status=status;
        list.add(this);
    }
    public Member(String nom, String email,int tel,int niveau, String filiere){
        try{
            this.identifiant =list.get(list.size()-1).identifiant+1;
        }catch(Exception es){
            this.identifiant=1;
        }
        this.nom=nom;
        this.email=email;
        this.telephone=tel;
        this.niveau=niveau;
        this.filiere=filiere;
        this.save();
        list.add(this);
    }
    public static void initialise(){
        try {
            bd("SELECT * FROM membre;");
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void bd(String str) throws SQLException{
        if (list!=null){
            list.clear();
        }
        ResultSet resultat = statement.executeQuery(str);
        /* Récupération des données du résultat de la requête de lecture */
        while ( resultat.next() ) {
            int id = resultat.getInt( "identifiant" );
            String nom = resultat.getString( "nom" );
            String email = resultat.getString( "email" );
            int telephone = resultat.getInt( "telephone" );
            int niveau = resultat.getInt("niveau");
            String filiere = resultat.getString("filiere");
            Boolean status = resultat.getBoolean("status");
            new Member(id,nom,email,telephone,niveau,filiere,status);
        }
    }
    private void save(){
        try {
            int statut = statement.executeUpdate( "INSERT INTO membre(identifiant,nom,email,telephone,niveau,filiere) Values ("+identifiant+",\""+nom+"\",\""+email+"\","+telephone+","+niveau+",\""+filiere+"\");" );
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void research(String str,String str2) {
        try {
            bd("SELECT * FROM membre where "+str+" Like '%"+str2+"%'Order by "+str );
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void tri(String str) {
        try {
            str=str.toLowerCase();
            bd("SELECT * FROM membre Order by "+str );
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void supprimer(){

        String sql = "DELETE FROM membre WHERE identifiant = "+this.identifiant;
        try {
            statement.executeUpdate(sql);
            list.remove(this);
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void emprunteLivre(ArrayList<Materiel> liste){
        for(Materiel materiel : liste){
            try {
                int statut = statement.executeUpdate( "INSERT INTO emprunt(idMembre,idMateriel,Type) Values ("+identifiant+","+materiel.identifiant+",\"livre\");" );
                status=true;
            } catch (SQLException ex) {
                Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql ="UPDATE livre SET nbRestant = nbRestant-1 WHERE identifiant="+materiel.identifiant;
            try {
                statement.executeUpdate(sql);
                sql="UPDATE membre SET status = 1 WHERE identifiant="+identifiant;
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    public void emprunteCD(ArrayList<Materiel> liste){
        for(Materiel materiel : liste){
            try {
                int statut = statement.executeUpdate( "INSERT INTO emprunt(idMembre,idMateriel,type) Values ("+identifiant+","+materiel.identifiant+",\"cd\");" );
                status=true;
            } catch (SQLException ex) {
                Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql ="UPDATE livre SET nbRestant = nbRestant-1 WHERE identifiant="+materiel.identifiant;
            
        }
    }
}
