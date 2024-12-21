package game.uma.controller;

import game.uma.dao.UmaDAO;
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
@RequestMapping("/umas/manage/raceInfo")
public class RaceInfoController {
    private UmaDAO umaDAO;

    public RaceInfoController(UmaDAO umaDAO){
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
    public String showRaceInfoTable(Model model){
        List<RaceInfo> raceInfos = umaDAO.findRaceInfos();
        model.addAttribute("raceInfos", raceInfos);
        return "table/raceInfo-table";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("raceInfo", new RaceInfo());
        return "form/raceInfo-form";
    }

    @GetMapping("/update")
    public String update(@RequestParam("title") String title, Model model){
        RaceInfo raceInfo = umaDAO.findRaceInfoByTitle(title);
        model.addAttribute("raceInfo", raceInfo);
        return "form/raceInfo-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("raceInfo") RaceInfo raceInfo, BindingResult bindingResult){
        System.out.println(bindingResult.toString());
        if(bindingResult.hasErrors()){
            return "/form/raceInfo-form";
        }
        umaDAO.save(raceInfo);
        return "redirect:/umas/manage/raceInfo";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("title") String title){
        RaceInfo raceInfo = umaDAO.findRaceInfoByTitle(title);
        umaDAO.delete(raceInfo);
        return "redirect:/umas/manage/raceInfo";
    }


}
