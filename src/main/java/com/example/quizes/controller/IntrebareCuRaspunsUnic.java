/** Clasa pentru reprezentarea întrebărilor cu răspuns unic
 * @author Negoescu-Cîrstea Ștefan-Daniel
 * @version 6 Ianuarie 2025
 */
package com.example.quizes.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntrebareCuRaspunsUnic extends Intrebare {
    private String raspunsCorect;
    private List<String> raspunsuriGresite;

    public IntrebareCuRaspunsUnic(String intrebare, String raspunsCorect, List<String> raspunsuriGresite) {
        super(intrebare);
        this.raspunsCorect = raspunsCorect;
        this.raspunsuriGresite = raspunsuriGresite;
    }

    public String getRaspunsCorect() {
        return raspunsCorect;
    }

    public void setRaspunsCorect(String raspunsCorect) {
        this.raspunsCorect = raspunsCorect;
    }

    public List<String> getRaspunsuriGresite() {
        return raspunsuriGresite;
    }

    public void setRaspunsuriGresite(List<String> raspunsuriGresite) {
        this.raspunsuriGresite = raspunsuriGresite;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRaspuns corect: " + raspunsCorect +
                "\nRaspunsuri gresite: " + raspunsuriGresite;
    }

    public List<String> getRaspunsuriPosibile() {
        List<String> raspunsuriPosibile = new ArrayList<>(raspunsuriGresite);
        raspunsuriPosibile.add(raspunsCorect); // Adăugăm răspunsul corect
        Collections.shuffle(raspunsuriPosibile); // Amestecăm răspunsurile
        return raspunsuriPosibile;
    }

    public boolean valideazaRaspuns(String raspuns) {
        return raspunsCorect.equalsIgnoreCase(raspuns);
    }

}