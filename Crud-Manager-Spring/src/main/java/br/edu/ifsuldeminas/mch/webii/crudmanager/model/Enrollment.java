package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name="enrollments")
public class Enrollment {
    
	 @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @NotNull(message = "Usuário é obrigatório")
    private User user;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id")
    @NotNull(message = "Curso é obrigatório")
    private ClassType clazz;
    
    @NotEmpty(message = "Status não pode estar vazio")
    private String status;
    
    @Positive(message = "Valor deve ser positivo")
    @NotNull(message = "Valor não pode ser nulo")
    private Double price;

    public Enrollment() {
    } // Construtor

    public Enrollment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClassType getClazz() {
        return clazz;
    }

    public void setClazz(ClassType clazz) {
        this.clazz = clazz;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
