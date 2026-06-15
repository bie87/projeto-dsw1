import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {
  
	@NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String password;
       
   
    
    @NotBlank
    @Column(nullable = false, length = 10)
    private String role;
    
    @Column(nullable = false)
    private boolean enabled;
		
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}