/** Clasa pentru reprezentarea întrebărilor cu răspuns multiplu
 * @author Negoescu-Cîrstea Ștefan-Daniel
 * @version 6 Ianuarie 2025
 */
package com.example.quizes.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntrebareCuRaspunsMultiplu extends Intrebare {
    private List<String> raspunsuriCorecte;
    private List<String> raspunsuriGresite;

    public IntrebareCuRaspunsMultiplu(String intrebare, List<String> raspunsuriCorecte, List<String> raspunsuriGresite) {
        super(intrebare);
        this.raspunsuriCorecte = raspunsuriCorecte;
        this.raspunsuriGresite = raspunsuriGresite;
    }

    public List<String> getRaspunsuriCorecte() {
        return raspunsuriCorecte;
    }

    public void setRaspunsuriCorecte(List<String> raspunsuriCorecte) {
        this.raspunsuriCorecte = raspunsuriCorecte;
    }

    public List<String> getRaspunsuriGresite() {
        return raspunsuriGresite;
    }

    public void setRaspunsuriGresite(List<String> raspunsuriGresite) {
        this.raspunsuriGresite = raspunsuriGresite;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRaspunsuri corecte: " + raspunsuriCorecte +
                "\nRaspunsuri gresite: " + raspunsuriGresite;
    }

    public List<String> getRaspunsuriPosibile() {
        List<String> raspunsuriPosibile = new ArrayList<>(raspunsuriCorecte);
        raspunsuriPosibile.addAll(raspunsuriGresite); // Adăugăm răspunsurile greșite
        Collections.shuffle(raspunsuriPosibile); // Amestecăm răspunsurile
        return raspunsuriPosibile;
    }

    public boolean valideazaRaspunsuri(List<String> raspunsuri) {
        return raspunsuri.containsAll(raspunsuriCorecte) && raspunsuriCorecte.containsAll(raspunsuri);
    }

}
