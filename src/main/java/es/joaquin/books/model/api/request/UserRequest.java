package es.joaquin.books.model.api.request;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	public interface Basico { }

	public interface Info { }
	
	public interface Extra { }
	
	public interface User extends Info, Extra { }
	
	public interface UserDetail extends Basico, Extra { }
	
	public interface UserDetailExtra extends Basico, Info, Extra { }	
		
	@JsonView(Basico.class)
	@JsonProperty("id")
	private Long id;
	
	@JsonView(Info.class)
	@JsonProperty("nick")
	private String nick;
	
	@Email(message="Please provide a valid email address")
	@JsonView(Extra.class)
	@JsonProperty("email")
	private String email;

}
