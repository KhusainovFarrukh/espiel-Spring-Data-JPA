package kh.farrukh.espielspringdatajpa.endpoints.faculty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDTO {

    @NotBlank
    private String name;
    @JsonProperty("dean_id")
    private Long deanId;
}
