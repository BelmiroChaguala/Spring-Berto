package daw.todirverosol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path="/add") 
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam Integer idade, @RequestParam String comida, @RequestParam String email, @RequestParam String endereco) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User usr = new User();
    usr.setName(name);
	  usr.setIdade(idade);
    usr.setComida(comida);
    usr.setEmail(email);
    usr.setEndereco(endereco);
    userRepository.save(usr);
    return "Saved\n";
  }

  @GetMapping(path="/findAll")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path="/findById/{id}")
  public @ResponseBody Object getById(@PathVariable Integer id) {
    return userRepository.findById(id);
  }

 
  @DeleteMapping(path="/deleteAll")
  public @ResponseBody String delete() {
    userRepository.deleteAll();
    return "Tudo apagado\n";
  }

}
