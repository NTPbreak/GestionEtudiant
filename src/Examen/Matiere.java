package Examen;

public class Matiere {

    private String code;
    private String nom;
    private double coef;

    public Matiere(String code, String nom, double coef) {
        this.code = code;
        this.coef = coef;
        this.nom = nom;
    }

    // Getter et Setter pour `code`
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // Getter et Setter pour `nom`
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour `coef`
    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    @Override
    public String toString() {
        return "Matiere{" +
                "nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                ", coef='" + coef + '\'' +
                '}';
    }
}
