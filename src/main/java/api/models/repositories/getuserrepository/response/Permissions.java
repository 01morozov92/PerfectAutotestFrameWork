package api.models.repositories.getuserrepository.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Permissions{

	@JsonProperty("pull")
	private Boolean pull;

	@JsonProperty("maintain")
	private Boolean maintain;

	@JsonProperty("admin")
	private Boolean admin;

	@JsonProperty("triage")
	private Boolean triage;

	@JsonProperty("push")
	private Boolean push;
}