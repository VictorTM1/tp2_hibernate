package fuck;
// Generated Dec 11, 2016 3:12:20 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Ordonnancechirurgie generated by hbm2java
 */
public class Ordonnancechirurgie  implements java.io.Serializable {


     private OrdonnancechirurgieId id;
     private Chirurgie chirurgie;
     private Ordonnance ordonnance;
     private BigDecimal rang;

    public Ordonnancechirurgie() {
    }

	
    public Ordonnancechirurgie(OrdonnancechirurgieId id, Chirurgie chirurgie, Ordonnance ordonnance) {
        this.id = id;
        this.chirurgie = chirurgie;
        this.ordonnance = ordonnance;
    }
    public Ordonnancechirurgie(OrdonnancechirurgieId id, Chirurgie chirurgie, Ordonnance ordonnance, BigDecimal rang) {
       this.id = id;
       this.chirurgie = chirurgie;
       this.ordonnance = ordonnance;
       this.rang = rang;
    }
   
    public OrdonnancechirurgieId getId() {
        return this.id;
    }
    
    public void setId(OrdonnancechirurgieId id) {
        this.id = id;
    }
    public Chirurgie getChirurgie() {
        return this.chirurgie;
    }
    
    public void setChirurgie(Chirurgie chirurgie) {
        this.chirurgie = chirurgie;
    }
    public Ordonnance getOrdonnance() {
        return this.ordonnance;
    }
    
    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }
    public BigDecimal getRang() {
        return this.rang;
    }
    
    public void setRang(BigDecimal rang) {
        this.rang = rang;
    }




}

