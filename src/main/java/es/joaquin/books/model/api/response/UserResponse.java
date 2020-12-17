package es.joaquin.books.model.api.response;

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
public class UserResponse {
	
	public interface Basico { }
			
	@JsonView(Basico.class)
	@JsonProperty("id")
	private Long id;
	
	@JsonView(Basico.class)
	@JsonProperty("nick")
	private String nick;
	
	@Email(message="Please provide a valid email address")
	@JsonView(Basico.class)
	@JsonProperty("email")
	private String email;


}
