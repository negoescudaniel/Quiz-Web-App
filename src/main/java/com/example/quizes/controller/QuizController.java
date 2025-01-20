/** Clasa pentru CONTROLLER
 * @author Negoescu-Cîrstea Ștefan-Daniel
 * @version 8 Ianuarie 2025
 */
package com.example.quizes.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Controller
@SessionAttributes("quizSession")
public class QuizController {

    private List<Intrebare> intrebari = new ArrayList<>();

    // Constructor pentru a inițializa întrebările
    public QuizController() {
        // Adăugăm întrebări cu răspuns unic
        intrebari.add(new IntrebareCuRaspunsUnic(
                "Ce reprezintă JVM în Java?",
                "Java Virtual Machine",
                Arrays.asList("Java Version Manager", "Java Virtual Method", "Java Very Much")
        ));
        intrebari.add(new IntrebareCuRaspunsUnic(
                "Care este cuvântul cheie folosit pentru a crea un obiect în Java?",
                "new",
                Arrays.asList("object", "create", "instance")
        ));
        intrebari.add(new IntrebareCuRaspunsUnic(
                "Care este metoda principală într-o aplicație Java?",
                "main",
                Arrays.asList("start", "run", "execute")
        ));

        // Adăugăm întrebări cu răspunsuri multiple
        intrebari.add(new IntrebareCuRaspunsMultiplu(
                "Care dintre acestea sunt tipuri de date primare în Java?",
                Arrays.asList("int", "float", "char"),
                Arrays.asList("String", "ArrayList", "HashMap")
        ));
        intrebari.add(new IntrebareCuRaspunsMultiplu(
                "Care dintre acestea sunt structuri de control în Java?",
                Arrays.asList("if", "for", "while"),
                Arrays.asList("int", "class", "public")
        ));

        // Continuăm cu alte întrebări
        intrebari.add(new IntrebareCuRaspunsUnic(
                "Care este pachetul implicit în toate clasele Java?",
                "java.lang",
                Arrays.asList("java.util", "java.io", "java.net")
        ));
        intrebari.add(new IntrebareCuRaspunsUnic(
                "Cum sunt apelate metodele într-o clasă statică?",
                "Cu numele clasei",
                Arrays.asList("Cu un obiect", "Implicit", "Cu super")
        ));
        intrebari.add(new IntrebareCuRaspunsMultiplu(
                "Care dintre acestea sunt metode de colectare în Java? ",
                Arrays.asList("ArrayList", "HashMap", "Set"),
                Arrays.asList("BufferedReader", "Thread", "Runnable")
        ));
        intrebari.add(new IntrebareCuRaspunsUnic(
                "Care este cuvântul cheie utilizat pentru a moșteni o clasă?",
                "extends",
                Arrays.asList("inherits", "implements", "super")
        ));
        intrebari.add(new IntrebareCuRaspunsMultiplu(
                "Care dintre acestea sunt caracteristici OOP în Java?",
                Arrays.asList("Encapsulation", "Inheritance", "Polymorphism"),
                Arrays.asList("Execution", "Threading", "Networking")
        ));
    }

    @GetMapping("/view_quiz")
    public String viewQuiz(Model model) {
        model.addAttribute("intrebari", intrebari);
        return "view_quiz";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/java-quiz")
    public String javaQuiz() {
        return "java-quiz";
    }

    @GetMapping("/professor-home")
    public String professorHome() {
        return "professor-home";
    }

    @GetMapping("/add_question")
    public String addQuestion() {
        return "add_question";
    }

    @GetMapping("/modify_question")
    public String modifyQuestion() {
        return "modify_question";
    }

    // Metoda pentru
    @GetMapping("/delete_question")
    public String deleteQuestion() {
        return "delete_question";
    }

    // Metoda pentru a vizualiza formularul de adăugare întrebare cu un singur raspuns Corect
    @GetMapping("/add_question_sa")
    public String showAddQuestionForm() {
        return "add_question_sa"; // Formularul de adăugare întrebare
    }

    // Metoda pentru a vizualiza formularul de adăugare întrebare cu un raspuns Multiplu
    @GetMapping("/add_question_ma")
    public String showAddQuestionMaForm() {
        return "add_question_ma"; // Formularul de adăugare întrebare
    }

    // Metoda pentru a adăuga o întrebare cu raspuns multiplu
    @PostMapping("/add_question_ma")
    public String addQuestionMA(
            @RequestParam("intrebare") String intrebare,
            @RequestParam("raspunsuriCorecte") String raspunsuriCorecte,
            @RequestParam("raspunsuriGresite") String raspunsuriGresite) {

        // Împărțim răspunsurile corecte și greșite în liste
        List<String> raspunsuriCorecteList = Arrays.asList(raspunsuriCorecte.split(","));
        List<String> raspunsuriGresiteList = Arrays.asList(raspunsuriGresite.split(","));

        // Creăm o întrebare de tip IntrebareCuRaspunsMultiplu și o adăugăm în lista de întrebări
        IntrebareCuRaspunsMultiplu intrebareNoua = new IntrebareCuRaspunsMultiplu(intrebare, raspunsuriCorecteList, raspunsuriGresiteList);
        intrebari.add(intrebareNoua);

        // După ce întrebarea a fost adăugată, redirecționăm către pagina cu întrebările
        return "redirect:/view_quiz";
    }


    // Metoda pentru a modifica textul întrebarii
    @PostMapping("/modify_question")
    public String modifyQuestion(@RequestParam("numarIntrebare") String numarIntrebare,
                                 @RequestParam("nouaIntrebare") String nouaIntrebare,
                                 Model model) {
        try {
            int index = Integer.parseInt(numarIntrebare);

            if (index > 0 && index <= intrebari.size()) {
                // Modificăm întrebarea la indexul dat
                Intrebare intrebareDeModificat = intrebari.get(index - 1);
                intrebareDeModificat.setIntrebare(nouaIntrebare);

                model.addAttribute("message", "Întrebarea a fost modificată cu succes!");
            } else {
                model.addAttribute("error", "Numărul introdus nu corespunde unei întrebări existente!");
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Te rog să introduci un număr!");
        }

        return "modify_question"; // Rămânem pe pagina de modificare
    }


    // Metoda pentru a sterge o întrebare
    @PostMapping("/delete_question")
    public String deleteQuestion(@RequestParam("numarIntrebare") String numarIntrebare, Model model) {
        // Verificăm dacă input-ul este un număr valid
        try {
            int index = Integer.parseInt(numarIntrebare);

            // Verificăm dacă indexul este în limitele listei de întrebări
            if (index > 0 && index < intrebari.size()+1) {
                intrebari.remove(index-1);
                model.addAttribute("message", "Întrebarea a fost ștearsă cu succes!");
            } else {
                model.addAttribute("error", "Numărul introdus nu corespunde unei întrebări existente!");
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Te rog să introduci un număr!");
        }

        // Rămânem pe pagina de ștergere a întrebării
        return "delete_question";
    }



    // Metoda pentru a adăuga o întrebare cu raspuns unic
    @PostMapping("/add_question_sa")
    public String addQuestionSA(@RequestParam("intrebare") String intrebare,
                              @RequestParam("raspunsCorect") String raspunsCorect,
                              @RequestParam("raspunsuriGresite") String raspunsuriGresite) {

        // Împărțim răspunsurile greșite într-o listă, folosind virgula ca delimitator
        List<String> raspunsuriGresiteList = Arrays.asList(raspunsuriGresite.split(","));

        // Creăm o întrebare de tipul IntrebareCuRaspunsUnic și o adăugăm în lista de întrebări
        IntrebareCuRaspunsUnic intrebareNoua = new IntrebareCuRaspunsUnic(intrebare, raspunsCorect, raspunsuriGresiteList);
        intrebari.add(intrebareNoua);

        // După ce întrebarea a fost adăugată, redirecționăm către pagina cu întrebările
        return "redirect:/view_quiz";
    }

    //radio button de pe meniul principal
    @PostMapping("/choose-option")
    public String chooseOption(@RequestParam("option") String option) {
        if ("option1".equals(option)) {
            // Redirecționare la paginac de home pentru utilizatorul de tip profesor
            return "redirect:/professor-home";
        } else if ("option2".equals(option)) {
            // Redirecționare la pagina pentru a sustine quizul
            return "redirect:/start_quiz";
        }
        // În caz de eroare sau opțiune invalidă, rămânem pe index
        return "redirect:/";
    }

    //Meniul pentru utilizatorul de tip profesor
    @PostMapping("/choose-option1")
    public String chooseOption1(@RequestParam("option") String option) {
        if ("option1".equals(option)) {
            // Redirecționare la pagina pentru vizualizarea quizului
            return "redirect:/view_quiz";
        } else if ("option2".equals(option)) {
            // Redirecționare la pagina pentru adaugare intrebare
            return "redirect:/add_question";
        } else if ("option3".equals(option)) {
            // Redirecționare la pagina pentru modificare intrebare
            return "redirect:/modify_question";
        } else if ("option4".equals(option)) {
            // Redirecționare la pagina pentru stergere intrebare
            return "redirect:/delete_question";
        }
        // În caz de eroare sau opțiune invalidă, rămânem pe index
        return "redirect:/";
    }

    // Alege ce fel de intrebare vrea sa adauge utiliztorul in quiz (raspuns unic/raspuns multiplu)
    @PostMapping("/choose-option2")
    public String chooseOption2(@RequestParam("option") String option) {
        if ("option1".equals(option)) {
            // Redirecționare la adauga intrebare raspuns unic
            return "redirect:/add_question_sa";
        } else if ("option2".equals(option)) {
            // Redirecționare la adauga intrebare raspuns multiplu
            return "redirect:/add_question_ma";
        }
        // În caz de eroare rămânem pe index
        return "redirect:/";
    }


    /// partea Studentului
    @ModelAttribute("quizSession")
    public QuizSession initializeQuizSession() {
        return new QuizSession();
    }

    // Start quiz
    @GetMapping("/start_quiz")
    public String startQuiz(Model model, @ModelAttribute("quizSession") QuizSession quizSession) {
        // Amestecăm întrebările
        List<Intrebare> shuffledIntrebari = new ArrayList<>(intrebari);
        Collections.shuffle(shuffledIntrebari);

        // Salvăm întrebările amestecate în sesiunea de quiz
        quizSession.setIntrebari(shuffledIntrebari);
        quizSession.setIndexCurent(0);
        quizSession.setScor(0);

        // Trimitem prima întrebare
        return "redirect:/question";
    }

    // Afisare Intrebare
    @GetMapping("/question")
    public String showQuestion(Model model, @ModelAttribute("quizSession") QuizSession quizSession) {
        // Obținem întrebarea curentă
        int indexCurent = quizSession.getIndexCurent();
        if (indexCurent < quizSession.getIntrebari().size()) {
            Intrebare intrebare = quizSession.getIntrebari().get(indexCurent);

            // Prepare a shuffled list of possible answers
            List<String> optiuni = new ArrayList<>();
            if (intrebare instanceof IntrebareCuRaspunsUnic) {
                IntrebareCuRaspunsUnic unic = (IntrebareCuRaspunsUnic) intrebare;
                optiuni.add(unic.getRaspunsCorect());
                optiuni.addAll(unic.getRaspunsuriGresite());
            } else if (intrebare instanceof IntrebareCuRaspunsMultiplu) {
                IntrebareCuRaspunsMultiplu multiplu = (IntrebareCuRaspunsMultiplu) intrebare;
                optiuni.addAll(multiplu.getRaspunsuriCorecte());
                optiuni.addAll(multiplu.getRaspunsuriGresite());
            }
            Collections.shuffle(optiuni);

            // Add question and options to the model
            model.addAttribute("intrebare", intrebare);
            model.addAttribute("optiuni", optiuni);
            return "quiz_question";
        } else {
            // Dacă nu mai sunt întrebări, calculăm scorul final
            return "redirect:/quiz_result";
        }
    }

    // Submit la raspuns
    @PostMapping("/submit_answer")
    public String submitAnswer(@RequestParam("raspuns[]") List<String> raspunsuri, @ModelAttribute("quizSession") QuizSession quizSession) {
        // Verificăm răspunsul
        int indexCurent = quizSession.getIndexCurent();
        Intrebare intrebareCurenta = quizSession.getIntrebari().get(indexCurent);

        if (intrebareCurenta instanceof IntrebareCuRaspunsUnic) {
            // Întrebarea cu răspuns unic
            IntrebareCuRaspunsUnic unic = (IntrebareCuRaspunsUnic) intrebareCurenta;
            if (unic.getRaspunsCorect().equalsIgnoreCase(raspunsuri.get(0).trim())) {
                quizSession.incrementScor();
            }
        } else if (intrebareCurenta instanceof IntrebareCuRaspunsMultiplu) {
            // Întrebarea cu răspunsuri multiple
            IntrebareCuRaspunsMultiplu multiplu = (IntrebareCuRaspunsMultiplu) intrebareCurenta;

            // Validăm răspunsurile multiple
            if (multiplu.valideazaRaspunsuri(raspunsuri)) {
                quizSession.incrementScor();
            }
        }

        // Trecem la următoarea întrebare
        quizSession.incrementIndex();
        return "redirect:/question";
    }

    // Rezultatul quizului
    @GetMapping("/quiz_result")
    public String showQuizResult(Model model, @ModelAttribute("quizSession") QuizSession quizSession) {
        int scorFinal = quizSession.getScor();
        model.addAttribute("scor", scorFinal);
        model.addAttribute("total", quizSession.getIntrebari().size());
        return "quiz_result";
    }

}
