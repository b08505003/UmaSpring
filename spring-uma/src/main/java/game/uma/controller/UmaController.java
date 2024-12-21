package game.uma.controller;

import game.uma.dao.UmaDAO;
import game.uma.entity.Uma;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/umas/manage/uma")
public class UmaController {

    private UmaDAO umaDAO;


    public UmaController(UmaDAO umaDAO){
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
    public String showUmaTable(Model model){
        List<Uma> umas = umaDAO.findUmas();
        model.addAttribute("umas", umas);
        return "/table/uma-table";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("uma", new Uma());
        return "/form/uma-form";
    }

    @GetMapping("/addRace")
    public String addRace(@RequestParam("umaId") int id, Model model){
        Uma uma = umaDAO.findUmaById(id);
        model.addAttribute("uma", uma);
        model.addAttribute("races", uma.getRaces());
        return "/table/race-table";
    }

    @GetMapping("/update")
    public String update(@RequestParam("umaId") int id, Model model){
        Uma uma = umaDAO.findUmaById(id);
        model.addAttribute("uma", uma);
        return "/form/uma-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("uma") Uma uma, BindingResult bindingResult){
        System.out.println(bindingResult.toString());
        if(bindingResult.hasErrors()){
            return "/form/uma-form";
        }
        umaDAO.save(uma);
        return "redirect:/umas/manage/uma";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("umaId") int id){
        Uma uma = umaDAO.findUmaById(id);
        umaDAO.delete(uma);
        return "redirect:/umas/manage/uma";
    }


}
