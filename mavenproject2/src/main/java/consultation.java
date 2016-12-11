import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Gabriel
 */
public class consultation implements Serializable{

    private String codeDocteur;
    private int numDos;
    private Date date;
    private String diagnostic;
    private int numOrd;
    
    public String getCodeDocteur() {
        return codeDocteur;
    }

    public void setCodeDocteur(String codeDocteur) {
        this.codeDocteur = codeDocteur;
    }

    public int getNumDos() {
        return numDos;
    }

    public void setNumDos(int numDos) {
        this.numDos = numDos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public int getNumOrd() {
        return numOrd;
    }

    public void setNumOrd(int numOrd) {
        this.numOrd = numOrd;
    }
}
