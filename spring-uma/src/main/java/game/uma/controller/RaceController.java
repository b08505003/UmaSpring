package game.uma.controller;

import game.uma.dao.UmaDAO;
import game.uma.entity.Race;
import game.uma.entity.RaceInfo;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/umas/manage/race")
public class RaceController {
    private UmaDAO umaDAO;

    public RaceController(UmaDAO umaDAO){
        this.umaDAO = umaDAO;
    }

    // pre-process
    // trim the input to avoid white-spaces
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("")
    public String showRaceTable(Model model){
        List<Race> races = umaDAO.findRaces();
        model.addAttribute("races", races);
        return "table/race-table";
    }

    @GetMapping("/add")
    public String add(@RequestParam("umaId") int id, Model model){
        Race race = new Race();
        race.setUma(umaDAO.findUmaById(id));
        List<RaceInfo> raceInfos = umaDAO.findRaceInfos();

        model.addAttribute("race", race);
        model.addAttribute("raceinfos", raceInfos);
        return "form/race-form";
    }

    @GetMapping("/update")
    public String update(@RequestParam("raceId") int id, Model model){
        Race race = umaDAO.findRaceById(id);
        List<RaceInfo> raceInfos = umaDAO.findRaceInfos();

        model.addAttribute("race", race);
        model.addAttribute("raceinfos", raceInfos);
        return "form/race-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("race") Race race, BindingResult bindingResult){
        System.out.println(bindingResult.toString());
        if(bindingResult.hasErrors()){
            return "/form/race-form";
        }
        umaDAO.save(race);
        return "redirect:/umas/manage/race";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("raceId") int id){
        Race race = umaDAO.findRaceById(id);
        umaDAO.delete(race);
        return "redirect:/umas/manage/race";
    }


}
