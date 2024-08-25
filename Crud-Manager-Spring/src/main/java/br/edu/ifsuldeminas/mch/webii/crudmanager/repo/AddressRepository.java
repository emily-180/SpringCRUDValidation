package br.edu.ifsuldeminas.mch.webii.crudmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	//save, delete, update ja estão ai por padrão, caso quisermos implemetntar mais é aqui
}
