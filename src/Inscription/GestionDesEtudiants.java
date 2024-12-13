package Inscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Examen.Matiere;
import Examen.Notes;

public class GestionDesEtudiants {

    public static void main(String[] args) {

        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();

        ArrayList<Matiere> matieres = new ArrayList<Matiere>();

        ArrayList<Notes> notes = new ArrayList<Notes>();

        int i = 0;

        int choix = 0;

        try (Scanner entrer = new Scanner(System.in)) {

            do {
                // clearConsole();
                afficherMenu(etudiants, matieres);
                System.out.print("Choix: ");
                choix = entrer.nextInt();
                entrer.nextLine(); // Consomme le retour à la ligne

                switch (choix) {
                    case 1:
                        entrerEtudiants(entrer, etudiants);
                        break;
                    case 2:
                        entrerMatieres(entrer, matieres);
                        break;

                    case 3:
                        if (etudiants.size() > 0 && matieres.size() > 0) {
                            entrerNotes(entrer, etudiants, matieres, notes);
                        } else {
                            System.out.println("Veuillez d'abord entrer des étudiants et des matières.");
                        }
                        break;
                    case 4:
                        // clearConsole();
                        afficherOrdreDeMerite(notes);
                        break;
                    case 5:
                        // clearConsole();
                        afficherPremierEtDernier(notes);
                        break;
                    case 6:
                        // clearConsole();

                        if (notes.size() <= 0) {
                            System.out.println("Aucune note pour le moment");
                            break;
                        }
                        System.out.print("Moyenne de classe: ");
                        System.out.println(getMoyenneClasse(notes));
                        break;

                    case 7:
                        // clearConsole();

                        i = 0;
                        for (Etudiant etudiant : etudiants) {
                            System.out.println("========= Etudiant " + (i + 1) + "======");
                            System.out.println(etudiant.toString());
                            i++;
                        }
                        break;
                    case 8:
                        // clearConsole();

                        i = 0;

                        System.out.println("========= Liste des admis " + (i + 1) + "======");
                        for (Etudiant etudiant : getListAdmis(notes, etudiants)) {
                            System.out.println("========= Etudiant " + (i + 1) + "======");
                            System.out.println(etudiant.toString());
                            i++;
                        }
                        break;
                    case 9:
                        // clearConsole();

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
                        // clearConsole();

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

    // private static void clearConsole() {
    // try {
    // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    private static void afficherPremierEtDernier(ArrayList<Notes> notes) {
        if (notes.size() <= 0) {
            System.out.println("Aucune note pour le moment");
        }
        ArrayList<Etudiant> etudiants2 = ClasserParOrdreMerite(notes);
        System.out.println("Premier de la classe: ");
        System.out.println(etudiants2.get(0).toString() + "\n -> moyenne: "
                + getMoyenneEtudiant(notes, etudiants2.get(0).GetMatricule()));
        System.out.println("Dernier de la classe: ");
        System.out.println(etudiants2.get((etudiants2.size() - 1)).toString() + "\n -> moyenne: "
                + getMoyenneEtudiant(notes, etudiants2.get((etudiants2.size() - 1)).GetMatricule()));
    }

    private static void afficherOrdreDeMerite(ArrayList<Notes> notes) {
        if (notes.size() <= 0) {
            System.out.println("Aucune note pour le moment");
        }
        for (Etudiant etudiant : ClasserParOrdreMerite(notes)) {
            System.out.println(etudiant.toString() + "\n -> moyenne: "
                    + getMoyenneEtudiant(notes, etudiant.GetMatricule()));
        }
    }

    private static void entrerNotes(Scanner entrer, ArrayList<Etudiant> etudiants, ArrayList<Matiere> matieres,
            ArrayList<Notes> notes) {
        System.out.println("========= Choix =========:");
        System.out.println("1. Entrer les notes de tous les étudiants");
        System.out.println("2. Entrer les notes d'un nombre spécifique d'étudiants");
        System.out.print("Choix: ");
        int choix = entrer.nextInt();
        entrer.nextLine(); // Consommer le retour à la ligne

        switch (choix) {
            case 1:
                for (Etudiant etudiant : etudiants) {
                    System.out.println("Entrez les notes pour l'étudiant: " + etudiant.GetNom());
                    entrerNotesPourEtudiant(etudiant, matieres, notes, entrer);
                }
                break;

            case 2:
                System.out.print("Entrez le nombre d'étudiants: ");
                int nbEtudiants = entrer.nextInt();
                entrer.nextLine(); // Consommer le retour à la ligne

                for (int i = 0; i < nbEtudiants; i++) {
                    System.out.print("Entrez le matricule de l'étudiant: ");
                    String mat = entrer.nextLine();
                    int indiceEtudiant = rechEtudiant(etudiants, mat);
                    if (indiceEtudiant != -1) {
                        Etudiant etudiant = etudiants.get(indiceEtudiant);
                        entrerNotesPourEtudiant(etudiant, matieres, notes, entrer);
                    } else {
                        System.out.println("Étudiant non trouvé avec le matricule: " + mat);
                    }
                }
                break;

            default:
                System.out.println("Choix invalide!");
                break;
        }
    }

    private static void entrerMatieres(Scanner entrer, ArrayList<Matiere> matieres) {
        System.out.print("Entrez le nombre de matières: ");
        int nbMatieres = entrer.nextInt();
        entrer.nextLine(); // Consommer le retour à la ligne

        for (int i = 0; i < nbMatieres; i++) {
            System.out.println("========== Matière " + (i + 1) + " ==========");
            System.out.print("Code: ");
            String code = entrer.nextLine();
            System.out.print("Nom: ");
            String nom = entrer.nextLine();
            System.out.print("Coefficient: ");
            double coef = entrer.nextDouble();
            entrer.nextLine(); // Consommer le retour à la ligne
            matieres.add(new Matiere(code, nom, coef));
        }
    }

    private static void entrerEtudiants(Scanner entrer, ArrayList<Etudiant> etudiants) {
        System.out.print("Entrez l'effectif de la classe: ");
        int effectifs = entrer.nextInt();
        entrer.nextLine(); // Consommer le retour à la ligne

        for (int i = 0; i < effectifs; i++) {
            System.out.println("========== Etudiant " + (i + 1) + " ==========");
            System.out.print("Nom: ");
            String nom = entrer.nextLine();
            System.out.print("Prénom: ");
            String prenom = entrer.nextLine();
            System.out.print("Matricule: ");
            String mat = entrer.nextLine();
            System.out.print("Âge: ");
            int age = entrer.nextInt();
            entrer.nextLine(); // Consommer le retour à la ligne
            etudiants.add(new Etudiant(nom, prenom, mat, age));
        }
    }

    private static void afficherMenu(ArrayList<Etudiant> etudiants, ArrayList<Matiere> matieres) {
        System.out.println("=====================================================");
        System.out.println("                        Menu                        ");
        System.out.println("=====================================================");
        System.out.println("===== 1.  Entrer les etudiants");
        System.out.println("===== 2.  Entrer les matieres");

        if (!etudiants.isEmpty() && !matieres.isEmpty()) {
            System.out.println("===== 3.  Entrer les notes des étudiants");
            System.out.println("===== 4.  Resultat par ordre de mérite");
            System.out.println("===== 5.  Afficher le premier et le dernier de la classe");
            System.out.println("===== 6.  Calculer et afficher la moyenne de la classe");
            System.out.println("===== 7.  Afficher la liste des étudiants");
            System.out.println("===== 8.  Afficher la liste des admis");
            System.out.println(
                    "===== 9.  Afficher les étudiants avec une moyenne supérieure ou égale à la moyenne de la classe");
            System.out.println("===== 10. Afficher la liste des matières");
        }

        System.out.println("===== 11. Quitter");
    }

    // methode pour trouver l'indice d'un etudiant
    public static int rechEtudiant(ArrayList<Etudiant> etudiants, String mat) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).GetMatricule().equals(mat)) {
                return i;
            }
        }
        return -1;
    }

    // methode pour trouver l'indice d'une matiere
    public static int rechMatiere(ArrayList<Matiere> matieres, String code) {
        for (int i = 0; i < matieres.size(); i++) {
            if (matieres.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    // methode pour trouver l'indice de note d'un etudiant
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
        entrer.nextLine();

        for (int i = 0; i < nbMatieres; i++) {
            System.out.print("Entrez le code de la matière : ");
            String code = entrer.nextLine();
            int indiceMatiere = rechMatiere(matieres, code);

            if (indiceMatiere != -1) {
                Matiere matiere = matieres.get(indiceMatiere);
                System.out.print("Entrez la note pour la matière " + matiere.getNom() + " : ");
                double valeur = entrer.nextDouble();
                entrer.nextLine();
                Notes note = new Notes(etudiant, matiere, valeur);
                notes.add(note);
            } else {
                System.out.println("Matière non trouvée pour le code : " + code);
            }
        }
    }

    // methode pour retourner la moyenne d'un etudiant
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

    // methode pour trouver le moyenne de la classe
    public static double getMoyenneClasse(ArrayList<Notes> notes) {
        double somme = 0;
        int nombreNotes = 0;
        for (Notes note : notes) {
            somme += note.getValeurNote();
            nombreNotes++;
        }

        if (nombreNotes == 0) {
            return 0;
        }
        return somme / nombreNotes;

    }

    // methode pour classer les etudiants par ordre de mérite
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

    // Methode pour retourner la liste des admins
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

    // Methode pour obtenir la liste des etudiants dont la moyenne est superieur ou
    // egale a la moyenne de la classe
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

}
