package web.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String ShowAllUsers(Model model) {
        model.addAttribute("users",userService.showAllUsers());
        return "showAllUsers";
    }
    @GetMapping("/{id}")
    public String ShowUserId(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUserId(id));
        return "showUser";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "newUser";
    }
    @PostMapping()
    public String createUser(@ModelAttribute("user")  User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showUserId(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user")  User user,
                             @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
