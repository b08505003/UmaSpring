package game.uma.controller;

import game.uma.Quiz;
import game.uma.dao.UmaDAO;
import game.uma.entity.Uma;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/umas")
public class GameController {
    private UmaDAO umaDAO;

    private List<Quiz> quizzes;
    private int currentIndex;

    // could be changed
    private static int quizCount = 5;
    private static int pointEachQuiz = 10;

    public GameController(UmaDAO umaDAO){
        this.umaDAO = umaDAO;
    }

    @GetMapping("")
    public String showHomePage(Model model, HttpSession session){
        // set game start flag to false
        session.setAttribute("gameStart", false);

        return "home";
    }

    @GetMapping("/list")
    public String showList(Model model){
        List<Uma> umas = umaDAO.findAllUmas();
        model.addAttribute("umas", umas);
        return "/list/uma-list";
    }

    @GetMapping("/game")
    public String showGamePage(Model model, HttpSession session){

        // select random umas if it is the first quiz (session game start flag has not been flipped)
        if(!(boolean)session.getAttribute("gameStart")){
            currentIndex = 0;
            quizzes = new ArrayList<>();

            var quizUmas = umaDAO.getRandomUmas(quizCount);
            for(int i = 0; i < quizCount; i++){
                Quiz quiz = new Quiz(i+1, quizUmas.get(i));
                quizzes.add(quiz);
            }

            session.setAttribute("gameStart", true);
        }

        // add attributes to model
        Quiz currentQuiz = quizzes.get(currentIndex);
        model.addAttribute("races", currentQuiz.getUma().getRaces());
        model.addAttribute("number", currentQuiz.getNumber());

        // show answer for now
        System.out.println(currentQuiz.getUma());
        return "/game/game";
    }

    @PostMapping("/game/submit")
    public String submit(@ModelAttribute("answer") String answer, Model model, @RequestParam("action") String action){
        // add attributes to model
        Quiz currentQuiz = quizzes.get(currentIndex);
        model.addAttribute("races", currentQuiz.getUma().getRaces());
        model.addAttribute("number", currentQuiz.getNumber());

        if(action.equals("pass")){ // if pass is clicked

            String name = quizzes.get(currentIndex).getUma().getNameJP();
            model.addAttribute("passMessage", "Answer: " + name);

        } else { // if submit is clicked

            if (answer.equals(currentQuiz.getUma().getNameJP())) { // correct answer

                model.addAttribute("correctMessage", "Correct Answer!");
                quizzes.get(currentIndex).setPoint(pointEachQuiz); // points each quiz can get

            } else { // wrong answer

                model.addAttribute("wrongMessage", "Wrong Answer!");
            }
        }

        return "/game/game";
    }

    @GetMapping("/game/pass")
    public String pass(Model model){
        // add attributes to model
        Quiz currentQuiz = quizzes.get(currentIndex);
        model.addAttribute("races", currentQuiz.getUma().getRaces());
        model.addAttribute("number", currentQuiz.getNumber());

        String answer = quizzes.get(currentIndex).getUma().getNameJP();
        model.addAttribute("passMessage", "Answer: " + answer);
        return "/game/game";
    }

    @GetMapping("/game/next")
    public String nextQuiz(){
        currentIndex++;

        // check if it is the last quiz
        if(currentIndex >= quizCount){
            return "redirect:/umas/game/result";
        }
        return "redirect:/umas/game";
    }

    @GetMapping("/game/result")
    public String result(Model model){
        int getPoints = 0;
        for(Quiz quiz : quizzes){
            getPoints += quiz.getPoint();
        }

        model.addAttribute("getPoints", getPoints);
        model.addAttribute("totalPoints", pointEachQuiz*quizCount);
        model.addAttribute("quizzes", quizzes);
        return "/game/result";
    }


}
