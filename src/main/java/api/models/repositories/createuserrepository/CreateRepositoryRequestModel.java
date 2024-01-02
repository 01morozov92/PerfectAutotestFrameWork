package api.models.repositories.createuserrepository;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class CreateRepositoryRequestModel{

	@JsonProperty("private")
	private Boolean isPrivate;

	@JsonProperty("is_template")
	private Boolean isTemplate;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("homepage")
	private String homepage;
}