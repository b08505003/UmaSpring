package game.uma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/umas/setting")
public class SettingController {

    private static int quizCount;
    private static int pointEachQuiz;
    private static int difficulty;

    public SettingController() {
        quizCount = 5;
        pointEachQuiz = 10;
        difficulty = 2;
    }

    public static int getQuizCount() {
        return quizCount;
    }

    public static void setQuizCount(int quizCount) {
        SettingController.quizCount = quizCount;
    }

    public static int getPointEachQuiz() {
        return pointEachQuiz;
    }

    public static void setPointEachQuiz(int pointEachQuiz) {
        SettingController.pointEachQuiz = pointEachQuiz;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        SettingController.difficulty = difficulty;
    }

    @GetMapping("")
    public String showSettings(Model model){
        model.addAttribute("quizCount", quizCount);
        model.addAttribute("difficulty", difficulty);
        return "setting";
    }

    @PostMapping("/save")
    public String save(@RequestParam("difficulty") int difficulty, @RequestParam("quizCount") int count){
        setDifficulty(difficulty);
        setQuizCount(count);
        return "redirect:/umas/setting";
    }
}
