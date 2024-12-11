package Inscription;

import java.util.ArrayList;
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

    public static void main(String[] args) {

        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        ArrayList<Matiere> matieres = new ArrayList<Matiere>();
        ArrayList<Notes> notes = new ArrayList<Notes>();

        // Information par rapport au note
        String nom;
        String prenom;
        int age;
        String mat;
        // INformation par rapport au matiere
        int NbMatiere = 0;
        String code;
        String nomMatiere;
        double coef;
        // INformation par rapport au note
        double valeur = 0;
        String matricule;
        int choix = 0;

        do {
            System.out.println("=====================================================");
            System.out.println("                        Menue                        ");
            System.out.println("=====================================================\n");
            System.out.println("===== 1.  Entrer les etudiants      ==================");
            System.out.println("===== 2.  Entrer les matieres       ==================");
            System.out.println("===== 3.  Entrer les notes des etudiants par rapport au matiere   ==================");
            System.out.println("===== 4.  Resultat par ordre de merite ==================");
            System.out.println("===== 5.  Afficher le premier et le dernier de la classe ==================");
            System.out.println("===== 6.  Afficher le premier et le dernier de la classe ==================");
            System.out.println("===== 7.  Calculer et afficher la moyenne de la classe ==================");
            System.out.println("===== 8.  Afficher la liste des etudiants  ==================");
            System.out.println("===== 9.  Afficher la liste des admis  ==================");
            System.out.println(
                    "===== 10.  Afficher les etudiants ayant une moyenne superieur ou egal a la moyenne  ==================");
            System.out.println("===== 11.  Quitter                  ==================\n");

            try (Scanner entrer = new Scanner(System.in)) {
                int effectifs = 0;
                System.out.print("Choix: ");
                choix = entrer.nextInt();
                switch (choix) {
                    case 1:
                        System.out.println("Entrez l'effectifs de la classe");
                        effectifs = entrer.nextInt();
                            entrer.nextLine(); // Consomme le retour à la ligne
                        for (int i = 0; i < effectifs; i++) {
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
                        for (int i = 0; i < NbMatiere; i++) {
                            System.out.println("========== Matiere " + (i + 1) + " ==========");
                            System.out.println("Code : ");
                            code = entrer.nextLine();
                            System.out.println("Nom  : ");
                            nomMatiere = entrer.nextLine();
                            System.out.println("coef : ");
                            coef = entrer.nextDouble();

                            Matiere matiere = new Matiere(code, nomMatiere, coef);
                            matieres.add(matiere);
                        }
                        break;

                    case 3:

                        System.out.println("========= choix =========: ");
                        System.out.println("1. Entrez les notes de tout les etudiants ");
                        System.out.println("2. Entrez les notes d'un nombre specifique d'Etudiant");

                        int choix2 = 0;
                        choix2 = entrer.nextInt();
                        entrer.nextLine(); // Consommer le retour à la ligne

                        int nbEtudiant = 0;
                        switch (choix2) {
                            case 1:
                                for (int i = 0; i < etudiants.size(); i++) {
                                    System.out.println("Entrez le matricule de l'etudiant: ");
                                    mat = entrer.nextLine();
                                    int indiceEtudiant = rechEtudiant(etudiants, mat);
                                    if (indiceEtudiant != -1) {
                                        Etudiant etudiant = etudiants.get(indiceEtudiant);
                                        System.out.println("Entrer le code de la matiere: ");
                                        code = entrer.nextLine();
                                        int indiceMatiere = rechMatiere(matieres, code);

                                        if (indiceMatiere != -1) {
                                            Matiere matiere = matieres.get(indiceMatiere);
                                            System.out.println("Entrer la valeur de la matiere: ");
                                            valeur = entrer.nextDouble();
                                            Notes note = new Notes(etudiant, matiere, valeur);
                                            notes.add(note);
                                        }

                                    } else {
                                        System.out.println("Etudiant non trouver ");
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("Entrez le nombre d'etudiant: ");
                                nbEtudiant = entrer.nextInt();
                                for (int i = 0; i < nbEtudiant; i++) {
                                    System.out.println("Entrez le matricule de l'etudiant: ");
                                    mat = entrer.nextLine();
                                    int indiceEtudiant = rechEtudiant(etudiants, mat);
                                    if (indiceEtudiant != -1) {
                                        Etudiant etudiant = etudiants.get(indiceEtudiant);
                                        System.out.println("Entrer le code de la matiere: ");
                                        code = entrer.nextLine();
                                        int indiceMatiere = rechMatiere(matieres, code);

                                        if (indiceMatiere != -1) {
                                            Matiere matiere = matieres.get(indiceMatiere);
                                            System.out.println("Entrer la valeur de la matiere: ");
                                            valeur = entrer.nextDouble();
                                            Notes note = new Notes(etudiant, matiere, valeur);
                                            notes.add(note);
                                        }

                                    } else {
                                        System.out.println("Etudiant non trouver ");
                                        break;
                                    }
                                }
                                break;

                            default:
                                break;
                        }

                        for (int i = NbMatiere; i < NbMatiere; i++) {
                            System.out.println("========== Note " + (i + 1) + " ==========");
                            System.out.println("Code : ");
                            code = entrer.nextLine();
                            System.out.println("Nom  : ");
                            nomMatiere = entrer.nextLine();
                            System.out.println("coef : ");
                            coef = entrer.nextDouble();

                            Matiere matiere = new Matiere(code, nomMatiere, coef);
                            matieres.add(matiere);
                        }
                        break;
                    case 8:
                        int i = 0;
                        for (Etudiant etudiant : etudiants) {
                            System.out.println("========= Etudiant " + (i + 1) + "======");
                            System.out.println(etudiant.toString());
                        }
                        break;
                    default:
                        System.out.println(" Valeur non comprise!!!");
                        break;
                }

            }
        } while (choix < 11);

    }
}
