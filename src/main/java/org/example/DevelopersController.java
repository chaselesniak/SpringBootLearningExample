package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DevelopersController {
    @Autowired
    DeveloperRepository repository;

    @Autowired
    SkillRepository skillRepository;

    @RequestMapping("/developer/{id}")
    public String developer(@PathVariable Long id, Model model) {
        model.addAttribute("developer", repository.findById(id).orElse(null));
        model.addAttribute("skills", skillRepository.findAll());
        return "developer";
    }
    @RequestMapping(value = "/developers",method = RequestMethod.GET)
    public String developersList(Model model){
        model.addAttribute("developers", repository.findAll());
        return "developers";
    }

    @RequestMapping(value ="/developers",method = RequestMethod.POST)
    public String developersAdd(@RequestParam String email,@RequestParam String firstName, @RequestParam String lastName, Model model) {
        Developer newDeveloper = new Developer();
        newDeveloper.setEmail(email);
        newDeveloper.setFirstName(firstName);
        newDeveloper.setLastName(lastName);
        repository.save(newDeveloper);

        model.addAttribute("developer",newDeveloper);
        model.addAttribute("skills", skillRepository.findAll());
        return "redirect:/developer/" + newDeveloper.getId();
    }

    @RequestMapping(value ="/developer/{id}/skills", method = RequestMethod.POST)
    public String developersAddSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
        Optional<Skill> skill = Optional.ofNullable(skillRepository.findById(skillId).orElse(null));
        Optional<Developer> developer = Optional.ofNullable(repository.findById(id).orElse(null));

        if(developer.isPresent()) {
            Developer dev = developer.get();

            if(skill.isPresent()) {
                Skill s = skill.get();

                if(!dev.hasSkill(s)) {
                    dev.getSkills().add(s);
                }
                repository.save(dev);
                model.addAttribute("developer", dev);
                model.addAttribute("skills", s);
                return "redirect:/developer/" + dev.getId();
            }
        }
        model.addAttribute("developers", repository.findAll());
        return "redirect:/developers";
    }
}
