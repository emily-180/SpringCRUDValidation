package br.edu.ifsuldeminas.mch.webii.crudmanager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.ClassType;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Enrollment;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AddressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.ClassRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.EnrollmentRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo; // criando o repositorio
	
	 @Autowired
	 private ClassRepository classRepo;

	 @Autowired
	 private EnrollmentRepository enrollmentRepo;
	 
	@Override
	public void run(String... args) throws Exception {
		User emily = new User();
		emily.setName("Emily");
		emily.setGender("F");
		emily.setEmail("emily@gmail.com");
		
		Address addressE = new Address();
		addressE.setPlace("Rua 13 de maio");
		addressE.setNumber(11);
		addressE.setZipCode("31245-376");
		
		User lu = new User();
		lu.setName("Luciana");
		lu.setGender("F");
		lu.setEmail("luy@gmail.com");
		
		Address addressL = new Address();
		addressL.setPlace("Rua 11 de novembro");
		addressL.setNumber(11);
		addressL.setZipCode("31245-376");
		
		
		addressRepo.save(addressE);
		addressRepo.save(addressL);
		addressRepo.flush(); //atualiza as referencia do objeto
		
		emily.setAddress(addressE); //set as variaveis do usuario
		lu.setAddress(addressL);
		
		userRepo.save(emily); ///save os usuarios
		userRepo.save(lu);
		
		 ClassType yogaClass = new ClassType();
        yogaClass.setName("Yoga");
        yogaClass.setDescription("Uma aula para aqueles novos no Yoga.");
        yogaClass.setDuration(60); // Duração em minutos
        yogaClass.setLevel("Iniciante");

        ClassType pilatesClass = new ClassType();
        pilatesClass.setName("Pilates");
        pilatesClass.setDescription("Uma aula desafiadora de Pilates para praticantes avançados.");
        pilatesClass.setDuration(45);
        pilatesClass.setLevel("Avançado");

        classRepo.save(yogaClass);
        classRepo.save(pilatesClass);
        classRepo.flush();

        // Enrollments
        Enrollment enrollmentEmilyYoga = new Enrollment();
        enrollmentEmilyYoga.setUser(emily);
        enrollmentEmilyYoga.setClazz(yogaClass);
        enrollmentEmilyYoga.setStatus("Ativa");
        enrollmentEmilyYoga.setPrice(90.0);


        Enrollment enrollmentLuPilates = new Enrollment();
        enrollmentLuPilates.setUser(lu);
        enrollmentLuPilates.setClazz(pilatesClass);
        enrollmentLuPilates.setStatus("Ativa");
        enrollmentLuPilates.setPrice(220.0);
        
        enrollmentRepo.save(enrollmentEmilyYoga);
        enrollmentRepo.save(enrollmentLuPilates);
        enrollmentRepo.flush();
	}

}
