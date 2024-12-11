package Inscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Examen.Matiere;
import Examen.Notes;

public class GestionDesEtudiants {

    public static int rechEtudiant(ArrayList<Etudiant> etudiants, String mat) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).GetMatricule().equals(mat)) {
                return i;
            }
        }
        return -1;
    }

    public static int rechMatiere(ArrayList<Matiere> matieres, String code) {
        for (int i = 0; i < matieres.size(); i++) {
            if (matieres.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public static int rechNoteEtudiant(ArrayList<Notes> notes, String matricule) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getEtudiant().GetMatricule().equals(matricule)) {
                return i;
            }
        }
        return -1;
    }

    // Méthode pour entrer les notes pour un étudiant
    private static void entrerNotesPourEtudiant(Etudiant etudiant, ArrayList<Matiere> matieres, ArrayList<Notes> notes,
            Scanner entrer) {
        System.out.print("Combien de matières voulez-vous saisir pour cet étudiant ? ");
        int nbMatieres = entrer.nextInt();
        entrer.nextLine(); // Consommer le retour à la ligne

        for (int i = 0; i < nbMatieres; i++) {
            System.out.print("Entrez le code de la matière : ");
            String code = entrer.nextLine();
            int indiceMatiere = rechMatiere(matieres, code);

            if (indiceMatiere != -1) {
                Matiere matiere = matieres.get(indiceMatiere);
                System.out.print("Entrez la note pour la matière " + matiere.getNom() + " : ");
                double valeur = entrer.nextDouble();
                entrer.nextLine(); // Consommer le retour à la ligne

                Notes note = new Notes(etudiant, matiere, valeur);
                notes.add(note);
            } else {
                System.out.println("Matière non trouvée pour le code : " + code);
            }
        }
    }

    public static double getMoyenneEtudiant(ArrayList<Notes> notes, String matricule) {
        double somme = 0;
        int nombreNote = 0;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getEtudiant().GetMatricule().equals(matricule)) {
                somme += notes.get(i).getValeurNote();
                nombreNote++;
            }
        }
        if (somme == 0)
            return 0;

        return somme / nombreNote;
    }

    public static double getMoyenneClasse(ArrayList<Notes> notes) {
        double somme = 0;
        int nombreNotes = 0;
        for (Notes note : notes) {
            somme += note.getValeurNote();
            nombreNotes++;
        }

        if (nombreNotes == 0) {
            return 0; // Pas de notes, moyenne par défaut à 0.
        }
        return somme / nombreNotes;

    }

    public static ArrayList<Etudiant> ClasserParOrdreMerite(ArrayList<Notes> notes) {
        Map<String, Double> moyennes = new HashMap<>();
        for (Notes note : notes) {
            String matricule = note.getEtudiant().GetMatricule();
            moyennes.put(matricule, getMoyenneEtudiant(notes, matricule));
        }
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        for (Notes note : notes) {
            Etudiant etudiant = note.getEtudiant();
            if (!etudiants.contains(etudiant)) {
                etudiants.add(etudiant);
            }
        }
        etudiants.sort((etud1, etud2) -> Double.compare(moyennes.get(etud2.GetMatricule()),
                moyennes.get(etud1.GetMatricule())));

        return etudiants;
    }

    public static ArrayList<Etudiant> getListAdmis(ArrayList<Notes> notes, ArrayList<Etudiant> etudiants) {
        ArrayList<Etudiant> etudiants2 = new ArrayList<Etudiant>();
        for (Etudiant etudiant : etudiants) {
            double moyenne = getMoyenneEtudiant(notes, etudiant.GetMatricule());
            if (moyenne >= 10) {
                etudiants2.add(etudiant);
            }
        }
        return etudiants2;
    }

    public static ArrayList<Etudiant> getEtudiantSupOuEgalMoyenne(ArrayList<Notes> notes,
            ArrayList<Etudiant> etudiants) {
        double moyenneClasse = getMoyenneClasse(notes);
        ArrayList<Etudiant> etudiants2 = new ArrayList<Etudiant>();
        for (Etudiant etudiant : etudiants) {
            double moyenne = getMoyenneEtudiant(notes, etudiant.GetMatricule());
            if (moyenne >= moyenneClasse) {
                etudiants2.add(etudiant);
            }
        }
        return etudiants2;
    }

    public static void main(String[] args) {

        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        etudiants.add(new Etudiant("John", "Doe", "MAT001", 20));
        etudiants.add(new Etudiant("Jane", "Smith", "MAT002", 22));
        etudiants.add(new Etudiant("Alice", "Johnson", "MAT003", 21));
        etudiants.add(new Etudiant("Bob", "Brown", "MAT004", 19));
        etudiants.add(new Etudiant("Charlie", "Davis", "MAT005", 23));

        ArrayList<Matiere> matieres = new ArrayList<Matiere>();

        matieres.add(new Matiere("MATH101", "Mathématiques", 3));
        matieres.add(new Matiere("PHYS101", "Physique", 3));
        matieres.add(new Matiere("CHEM101", "Chimie", 3));
        matieres.add(new Matiere("CS101", "Informatique", 4));
        matieres.add(new Matiere("ENG101", "Anglais", 2));

        ArrayList<Notes> notes = new ArrayList<Notes>();
        // Notes pour John Doe
        notes.add(new Notes(etudiants.get(0), matieres.get(0), 15));
        notes.add(new Notes(etudiants.get(0), matieres.get(1), 16));
        notes.add(new Notes(etudiants.get(0), matieres.get(2), 14));
        notes.add(new Notes(etudiants.get(0), matieres.get(3), 17));
        notes.add(new Notes(etudiants.get(0), matieres.get(4), 18));

        // Notes pour Jane Smith
        notes.add(new Notes(etudiants.get(1), matieres.get(0), 18));
        notes.add(new Notes(etudiants.get(1), matieres.get(1), 19));
        notes.add(new Notes(etudiants.get(1), matieres.get(2), 17));
        notes.add(new Notes(etudiants.get(1), matieres.get(3), 20));
        notes.add(new Notes(etudiants.get(1), matieres.get(4), 15));

        // Notes pour Alice Johnson
        notes.add(new Notes(etudiants.get(2), matieres.get(0), 12));
        notes.add(new Notes(etudiants.get(2), matieres.get(1), 14));
        notes.add(new Notes(etudiants.get(2), matieres.get(2), 13));
        notes.add(new Notes(etudiants.get(2), matieres.get(3), 15));
        notes.add(new Notes(etudiants.get(2), matieres.get(4), 16));

        // Notes pour Bob Brown
        notes.add(new Notes(etudiants.get(3), matieres.get(0), 10));
        notes.add(new Notes(etudiants.get(3), matieres.get(1), 12));
        notes.add(new Notes(etudiants.get(3), matieres.get(2), 11));
        notes.add(new Notes(etudiants.get(3), matieres.get(3), 14));
        notes.add(new Notes(etudiants.get(3), matieres.get(4), 13));

        // Notes pour Charlie Davis
        notes.add(new Notes(etudiants.get(4), matieres.get(0), 16));
        notes.add(new Notes(etudiants.get(4), matieres.get(1), 17));
        notes.add(new Notes(etudiants.get(4), matieres.get(2), 18));
        notes.add(new Notes(etudiants.get(4), matieres.get(3), 19));
        notes.add(new Notes(etudiants.get(4), matieres.get(4), 20));

        int i = 0;
        // Information par rapport au note
        String nom = "";
        String prenom = "";
        int age = 0;
        String mat = "";
        // INformation par rapport au matiere
        int NbMatiere = 0;
        String code = "";
        String nomMatiere = "";
        double coef = 0;
        int choix = 0;
        try (Scanner entrer = new Scanner(System.in)) {
            do {
                System.out.println("=====================================================");
                System.out.println("                        Menue                        ");
                System.out.println("=====================================================\n");
                System.out.println("===== 1.  Entrer les etudiants   ");
                System.out.println("===== 2.  Entrer les matieres    ");
                if (etudiants.size() > 0 && matieres.size() > 0) {

                    System.out.println(
                            "===== 3.  Entrer les notes des etudiants par rapport au matiere ");
                    System.out.println("===== 4.  Resultat par ordre de merite ");
                    System.out.println("===== 5.  Afficher le premier et le dernier de la classe ");
                    System.out.println("===== 6.  Calculer et afficher la moyenne de la classe ");
                    System.out.println("===== 7.  Afficher la liste des etudiants  ");
                    System.out.println("===== 8.  Afficher la liste des admis  ");
                    System.out.println(
                            "===== 9.  Afficher les etudiants ayant une moyenne superieur ou egal a la moyenne de la classe ");
                    System.out.println("===== 10.  Afficher la liste des matieres");

                    System.out.println("===== 11.  Quitter \n");
                }

                else {
                    System.out.println("===== 3.  Quitter \n");
                }

                int effectifs = 0;
                System.out.print("Choix: ");
                choix = entrer.nextInt();
                entrer.nextLine(); // Consomme le retour à la ligne
                switch (choix) {
                    case 1:
                        System.out.println("Entrez l'effectifs de la classe");
                        effectifs = entrer.nextInt();
                        entrer.nextLine(); // Consomme le retour à la ligne
                        for (i = 0; i < effectifs; i++) {
                            System.out.println("========== Etudiant " + (i + 1) + " ==========");
                            System.out.print("Nom : ");
                            nom = entrer.nextLine();
                            System.out.print("Prénom : ");
                            prenom = entrer.nextLine();
                            System.out.print("Matricule : ");
                            mat = entrer.nextLine();
                            System.out.print("Âge : ");
                            age = entrer.nextInt();
                            entrer.nextLine(); // Consommer le retour à la ligne
                            Etudiant etudiant = new Etudiant(nom, prenom, mat, age);
                            etudiants.add(etudiant);
                        }

                        break;

                    // Enregistrement des matieres

                    case 2:

                        System.out.println("Entrez le nombre de matiere: ");
                        NbMatiere = 0;
                        NbMatiere = entrer.nextInt();
                        entrer.nextLine(); // Consommer le retour à la ligne

                        for (i = 0; i < NbMatiere; i++) {
                            System.out.println("========== Matiere " + (i + 1) + " ==========");
                            System.out.print("Code : ");
                            code = entrer.nextLine();
                            System.out.print("Nom  : ");
                            nomMatiere = entrer.nextLine();
                            System.out.print("coef : ");
                            coef = entrer.nextDouble();
                            entrer.nextLine(); // Consommer le retour à la ligne
                            Matiere matiere = new Matiere(code, nomMatiere, coef);
                            matieres.add(matiere);
                        }
                        break;

                    case 3:
                        System.out.println("========= Choix =========:");
                        System.out.println("1. Entrez les notes de tous les étudiants");
                        System.out.println("2. Entrez les notes d'un nombre spécifique d'étudiants");

                        System.out.print("Choix : ");
                        int choix2 = entrer.nextInt();
                        entrer.nextLine(); // Consommer le retour à la ligne

                        switch (choix2) {
                            case 1: // Entrer les notes de tous les étudiants
                                for (Etudiant etudiant : etudiants) {
                                    System.out.println("Entrez les notes pour l'étudiant : " + etudiant.GetNom() + " ("
                                            + etudiant.GetMatricule() + ")");
                                    entrerNotesPourEtudiant(etudiant, matieres, notes, entrer);
                                }
                                break;

                            case 2: // Entrer les notes pour un nombre spécifique d'étudiants
                                System.out.print("Entrez le nombre d'étudiants : ");
                                int nbEtudiant = entrer.nextInt();
                                entrer.nextLine(); // Consommer le retour à la ligne

                                for ( i = 0; i < nbEtudiant; i++) {
                                    System.out.print("Entrez le matricule de l'étudiant : ");
                                    mat = entrer.nextLine();
                                    int indiceEtudiant = rechEtudiant(etudiants, mat);
                                    if (indiceEtudiant != -1) {
                                        Etudiant etudiant = etudiants.get(indiceEtudiant);
                                        entrerNotesPourEtudiant(etudiant, matieres, notes, entrer);
                                    } else {
                                        System.out.println("Étudiant non trouvé avec le matricule : " + mat);
                                    }
                                }
                                break;

                            default:
                                System.out.println("Choix invalide !");
                                break;
                        }
                        break;
                    case 4:
                        if (notes.size() <= 0) {
                            System.out.println("Aucune note pour le moment");
                            break;
                        }
                        for (Etudiant etudiant : ClasserParOrdreMerite(notes)) {
                            System.out.println(etudiant.toString() + "\n -> moyenne: "
                                    + getMoyenneEtudiant(notes, etudiant.GetMatricule()));
                        }
                        break;
                    case 5:
                        if (notes.size() <= 0) {
                            System.out.println("Aucune note pour le moment");
                            break;
                        }
                        ArrayList<Etudiant> etudiants2 = ClasserParOrdreMerite(notes);
                        System.out.println("Premier de la classe: ");
                        System.out.println(etudiants2.get(0).toString() + "\n -> moyenne: "
                                + getMoyenneEtudiant(notes, etudiants2.get(0).GetMatricule()));
                        System.out.println("Dernier de la classe: ");
                        System.out.println(etudiants2.get((etudiants2.size() - 1)).toString() + "\n -> moyenne: "
                                + getMoyenneEtudiant(notes, etudiants2.get((etudiants2.size() - 1)).GetMatricule()));
                        break;
                    case 6:
                        if (notes.size() <= 0) {
                            System.out.println("Aucune note pour le moment");
                            break;
                        }
                        System.out.print("Moyenne de classe: ");
                        System.out.println(getMoyenneClasse(notes));
                        break;

                    case 7:
                        i = 0;
                        for (Etudiant etudiant : etudiants) {
                            System.out.println("========= Etudiant " + (i + 1) + "======");
                            System.out.println(etudiant.toString());
                            i++;
                        }
                        break;
                    case 8:
                        i = 0;

                        System.out.println("========= Liste des admis " + (i + 1) + "======");
                        for (Etudiant etudiant : getListAdmis(notes, etudiants)) {
                            System.out.println("========= Etudiant " + (i + 1) + "======");
                            System.out.println(etudiant.toString());
                            i++;
                        }
                        break;
                    case 9:
                        i = 0;

                        System.out.println(
                                "========= Etudiant dont la moyenne est superieur ou egale la moyenne de la classe ======");
                        for (Etudiant etudiant : getEtudiantSupOuEgalMoyenne(notes, etudiants)) {
                            System.out.println("========= Etudiant " + (i + 1) + "======");
                            System.out.println(etudiant.toString());
                            i++;
                        }
                        break;

                    case 10:
                        i = 0;
                        for (Matiere matiere : matieres) {
                            System.out.println("========= Matiere " + (i + 1) + "======");
                            System.out.println(matiere.toString());
                            i++;
                        }
                        break;
                    case 11:
                        System.out.println("Good bye");
                        break;

                    default:
                        System.out.println(" Valeur non comprise!!!");
                        break;
                }

            } while (choix < 11);
        }

    }
}
