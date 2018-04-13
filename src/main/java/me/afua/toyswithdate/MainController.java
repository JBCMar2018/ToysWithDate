package me.afua.toyswithdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    ToyRepository toyRepo;

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("toys",toyRepo.findAll());
        return "index";
    }

    @RequestMapping("/addtoy")
    public String addToy(Model model)
    {
        model.addAttribute("aToy",new Toy());
        return "addtoy";
    }


    @RequestMapping("/savetoy")
    public String saveToy(@ModelAttribute("aToy") Toy toy, HttpServletRequest request)
    {
        String yesno = request.getParameter("yeno")==null?"no":request.getParameter("yesno");

        //Allow a user to enter a yes or no value on the HTML page, and convert that to a boolean
        toy.setInStock(yesno.equalsIgnoreCase("yes")?true:false);

        //Allow a user to enter a date as a string in the user interface, convert it before it is saved to the database
        String theDate = request.getParameter("manufactured");

        toy.setManufactureDate(LocalDate.parse(theDate));
        toyRepo.save(toy);
        return "redirect:/";
    }

}
