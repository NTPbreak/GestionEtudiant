package Inscription;


public class Etudiant {

    private String mat;
    private String nom;
    private String prenom;
    private int age;


    public Etudiant(String nom, String prenom, String mat, int age) {
        this.age = age;
        this.nom = nom;
        this.mat = mat;
        this.prenom = prenom;
    }


    
    public void SetMatricule(String mat) {
        this.mat = mat;
    }

    public String GetMatricule() {
        return mat;
    }

    public void SetNom(String nom) {
        this.nom = nom;
    }

    public String GetNom() {
        return this.nom;
    }

    public void SetAge(int age) {
        this.age = age;
    }

    public int GetAge() {
        return this.age;
    }

    public void SetPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String GetPrenom() {
        return this.prenom;
    }

    
 
    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mat='" + mat + '\'' +
                ", age=" + age +
                '}';
    }
}
