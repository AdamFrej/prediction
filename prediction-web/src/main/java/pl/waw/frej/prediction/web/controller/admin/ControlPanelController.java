package pl.waw.frej.prediction.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.waw.frej.prediction.core.boundary.control.Admin;
import pl.waw.frej.prediction.web.model.Converter;
import pl.waw.frej.prediction.web.model.UserForm;

@Controller
public class ControlPanelController {
    public static final String URI = "/admin";
    public static final String USER_REMOVE = "/user/remove";
    public static final String USER_ADD = "/user/add";

    @Autowired
    private Admin admin;

    @Autowired
    private Converter converter;

    @RequestMapping(value = URI, method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pages/admin/controlPanel");
        modelAndView.addObject("websiteTitle", "Panel administracyjny");
        modelAndView.addObject("users", admin.readUsers());
        return modelAndView;
    }

    @RequestMapping(value = USER_ADD, method = RequestMethod.POST)
    public String addUser(UserForm userForm){
        admin.addUser(converter.toDomain(userForm));
        return "redirect:"+URI;
    }
    @RequestMapping(value = USER_REMOVE+"/{id}", method = RequestMethod.GET)
    public String removeUser(@PathVariable("id") Long id){
        admin.removeUser(id);
        return "redirect:"+URI;
    }
}
