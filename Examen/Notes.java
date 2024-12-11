package Examen;
import Inscription.Etudiant;

public class Notes {

    private Etudiant etudiant;
    private Matiere matiere;
    private double valeurNote;

    public Notes(Etudiant etudiant , Matiere matiere , double valeurNote)
    {
        this.etudiant = etudiant;
        this.matiere = matiere;
        this.valeurNote = valeurNote;
    }
    // Getter et Setter pour `etudiant`
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    // Getter et Setter pour `matiere`
    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    // Getter et Setter pour `valeurNote`
    public double getValeurNote() {
        return valeurNote;
    }

    public void setValeurNote(double valeurNote) {
        this.valeurNote = valeurNote;
    }
}