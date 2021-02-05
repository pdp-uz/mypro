package uz.tdpu.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.tdpu.entity.User;
import uz.tdpu.repository.RoleRepository;
import uz.tdpu.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initMode;


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
      if (initMode.equals("always")){
          userRepository.save(
                  new User(
                          "admin",
                          passwordEncoder.encode("admin"),
                          "admin123@gmal.com",
                          "Super admin",
                           roleRepository.findAllByRoleName("ROLE_ADMIN")
                  )
          );
//          structureRepository.save(new Structure(null, "Rektorat"));
//          structureRepository.save(new Structure(1L, "Markazlar va bo'limlar"));
//          structureRepository.save(new Structure(2L, "Fakultetlar"));
//          structureRepository.save(new Structure(3L, "Kafedralar"));
//          structureRepository.save(new Structure(3L, "Xodimlar"));
      }
    }
}
