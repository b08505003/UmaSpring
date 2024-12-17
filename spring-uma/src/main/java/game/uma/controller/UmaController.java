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
@RequestMapping("/umas/manage")
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
    public String manage(Model model){
        List<Uma> umas = umaDAO.findAllUmas();
        model.addAttribute("umas", umas);
        return "/manage/uma-list-for-management";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("uma", new Uma());
        return "/manage/uma-form";
    }

    @GetMapping("/update")
    public String update(@RequestParam("umaId") int id, Model model){
        Uma uma = umaDAO.findUmaById(id);
        model.addAttribute("uma", uma);
        return "/manage/uma-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("uma") Uma uma, BindingResult bindingResult){
        System.out.println(bindingResult.toString());
        if(bindingResult.hasErrors()){
            return "/manage/uma-form";
        }
        umaDAO.save(uma);
        return "redirect:/umas/manage";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("umaId") int id){
        Uma uma = umaDAO.findUmaById(id);
        umaDAO.delete(uma);
        return "redirect:/umas/manage";
    }


}
