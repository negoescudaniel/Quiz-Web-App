/** Clasa de baza pentru reprezentarea întrebărilor
 * @author Negoescu-Cîrstea Ștefan-Daniel
 * @version 4 Ianuarie 2025
 */
package com.example.quizes.controller;

public class Intrebare {
    protected String intrebare;

    public Intrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    @Override
    public String toString() {
        return "Intrebare: " + intrebare;
    }
}
