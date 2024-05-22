package djavan.demo.models;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


    @Entity
    @Table(name = Cliente.TABLE_NAME)
    
    public class Cliente {
        String nome;

    
        public interface CreateCliente {}
        public interface UpdateCliente {}
    
        public static final String TABLE_NAME = "cliente";
    
        public Cliente() {}
    
        @Id
        @Column(name = "id", unique = true)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        @Column(name = "nome", length = 100, nullable = false, unique = true)
        @NotNull(groups = CreateCliente.class)
        @NotEmpty(groups = CreateCliente.class) 
        @Size(groups = CreateCliente.class, min = 2, max = 100)
        private String nomeCliente;
    

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

	
	public void setNome(String nome) {
		this.nome = nome;
	}

    public void setId(Long id) {
        this.id = id;
    }



}
