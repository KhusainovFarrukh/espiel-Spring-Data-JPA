package kh.farrukh.espielspringdatajpa.endpoints.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {

    @NotBlank
    @JsonProperty("first_name")
    private String firstName;
    @NotBlank
    @JsonProperty("last_name")
    private String lastName;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StaffDegree degree;
    @JsonProperty("main_subject")
    private String mainSubject;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StaffRole role;
    @NotNull
    @JsonProperty("department_id")
    private Long departmentId;
}
