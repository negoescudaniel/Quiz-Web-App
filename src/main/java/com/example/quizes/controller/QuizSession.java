/** Clasa pentru a păstra starea sesiunii pe durata unui quiz.
 * @author Negoescu-Cîrstea Ștefan-Daniel
 * @version 6 Ianuarie 2024
 */
package com.example.quizes.controller;

import java.util.List;

public class QuizSession {
    private List<Intrebare> intrebari;
    private int indexCurent;
    private int scor;

    // Getteri și setteri
    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    public void setIntrebari(List<Intrebare> intrebari) {
        this.intrebari = intrebari;
    }

    public int getIndexCurent() {
        return indexCurent;
    }

    public void setIndexCurent(int indexCurent) {
        this.indexCurent = indexCurent;
    }

    public int getScor() {
        return scor;
    }

    public void setScor(int scor) {
        this.scor = scor;
    }

    public void incrementIndex() {
        this.indexCurent++;
    }

    public void incrementScor() {
        this.scor++;
    }
}
