package com.FlightsSystem.FlightsSystem.Controlles;
import com.FlightsSystem.FlightsSystem.Facades.AnonymousFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RequestMapping("/test")
public class FrontendTest {
    @Autowired
    AnonymousFacade anonymousFacade;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("dkas", anonymousFacade.get_all_countries());
        model.addAttribute("flight", anonymousFacade.get_all_flights());
        return "index";
    }
}
