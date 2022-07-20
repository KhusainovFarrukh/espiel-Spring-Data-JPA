package kh.farrukh.espielspringdatajpa.endpoints.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    @NotBlank
    private String name;
    @JsonProperty("faculty_id")
    private long facultyId;
}
