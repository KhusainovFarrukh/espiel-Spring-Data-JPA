package kh.farrukh.espielspringdatajpa.endpoints.faculty;

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
}
