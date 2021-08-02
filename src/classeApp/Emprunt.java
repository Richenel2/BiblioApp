/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classeApp;

import static classeApp.Materiel.statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author the Richenel
 */
public class Emprunt {
    public static final int PRIX=500;
    public static final ArrayList<Emprunt>list =new ArrayList<Emprunt>();
    private int idMembre;
    public int idMateriel;
    public int delai;
    public Date date;
    public String type;
    public Boolean status=false;
    private Emprunt(int idMembre,int idMateriel, int delai,Date date,String type, boolean status){
        this.idMateriel=idMateriel;
        this.idMembre=idMembre;
        this.delai=delai;
        this.date=date;
        this.type=type;
        this.status=status;
        list.add(this);
    }
    public static void initialise(){
        try {
            bd("SELECT * FROM emprunt Group By idMembre,idMateriel;");
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
            int idMembre = resultat.getInt( "idMembre" );
            int idMateriel = resultat.getInt( "idMateriel" );
            int delai = resultat.getInt( "delai" );
            Date date = resultat.getDate( "Date" );
            String type = resultat.getString("type");
            Boolean status = resultat.getBoolean("status");

            new Emprunt(idMembre,idMateriel,delai,date,type,status);
        }
    }
    public static void research(String str,String str2) {
        try {
            bd("SELECT * FROM emprunt where "+str+" Like '%"+str2+"%'Order by "+str );
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
    public String getMembreName(){
        String str="";
        try {
            ResultSet resultat = statement.executeQuery("Select nom from membre where identifiant="+this.idMembre);
            while(resultat.next()){
                str= resultat.getString("nom");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
    public String getMaterielName(){
        String str="";
        try {
            ResultSet resultat = statement.executeQuery("Select titre from "+this.type+" where identifiant="+this.idMateriel);
            while(resultat.next()){
                str= resultat.getString("titre");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
    public int getPrice(){
        LocalDate date2=Instant.ofEpochMilli(date.getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
        if(!status){
            if(LocalDate.now().getDayOfYear()-delai>date2.getDayOfYear()){
                status=true;
                try {
                    statement.executeUpdate("update emprunt set status = "+1+" where idMembre = "+idMembre+" and idMateriel = "+idMateriel);
                } catch (SQLException ex) {
                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        int price=0;
        if(status){
            price = (LocalDate.now().getDayOfYear()-(date2).getDayOfYear())*PRIX;
        }
        return price;
    }
    public void remettre(){
        String sql = "DELETE FROM emprunt WHERE idMembre = "+this.idMembre+" and idMateriel = "+this.idMateriel;
        String sql2 = "Update "+this.type+" set nbExemplaire = nbExemplaire + 1 WHERE identifiant = "+this.idMateriel;

        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            list.remove(this);
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void relancer( ){
        try {
            statement.executeUpdate("update emprunt set delai = "+(delai+7)+" where idMembre = "+idMembre+" and idMateriel = "+idMateriel);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}