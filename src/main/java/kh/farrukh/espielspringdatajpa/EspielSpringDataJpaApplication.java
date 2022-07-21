package kh.farrukh.espielspringdatajpa;

import kh.farrukh.espielspringdatajpa.endpoints.department.Department;
import kh.farrukh.espielspringdatajpa.endpoints.department.DepartmentRepository;
import kh.farrukh.espielspringdatajpa.endpoints.faculty.Faculty;
import kh.farrukh.espielspringdatajpa.endpoints.faculty.FacultyRepository;
import kh.farrukh.espielspringdatajpa.endpoints.staff.Staff;
import kh.farrukh.espielspringdatajpa.endpoints.staff.StaffDegree;
import kh.farrukh.espielspringdatajpa.endpoints.staff.StaffRepository;
import kh.farrukh.espielspringdatajpa.endpoints.staff.StaffRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class EspielSpringDataJpaApplication implements CommandLineRunner {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final StaffRepository staffRepository;

    public static void main(String[] args) {
        SpringApplication.run(EspielSpringDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Faculty faculty1 = new Faculty("History", null);
        Faculty faculty2 = new Faculty("Maths", null);
        Faculty faculty3 = new Faculty("Tourism", null);

        faculty1 = facultyRepository.save(faculty1);
        faculty2 = facultyRepository.save(faculty2);
        faculty3 = facultyRepository.save(faculty3);

        Department department1 = new Department("World history", faculty1, null);
        Department department2 = new Department("Anthropology", faculty1, null);
        Department department3 = new Department("Hotel works", faculty3, null);
        Department department4 = new Department("Green tourism", faculty3, null);
        Department department5 = new Department("Local tourism", faculty3, null);

        department1 = departmentRepository.save(department1);
        department2 = departmentRepository.save(department2);
        department3 = departmentRepository.save(department3);
        department4 = departmentRepository.save(department4);
        department5 = departmentRepository.save(department5);

        Staff staff1 = new Staff("test", "test", StaffDegree.MASTER, "test", StaffRole.ASSISTANT_TEACHER, department1);
        Staff staff2 = new Staff("test", "test", StaffDegree.MASTER, "test", StaffRole.ASSISTANT_TEACHER, department1);
        Staff staff3 = new Staff("test", "test", StaffDegree.MASTER, "test", StaffRole.ADMINISTRATIVE_WORKER, department1);
        Staff staff4 = new Staff("test", "test", StaffDegree.DOCTOR, "test", StaffRole.ADMINISTRATIVE_WORKER, department1);
        Staff staff5 = new Staff("test", "test", StaffDegree.DOCTOR, "test", StaffRole.TEACHER, department2);
        Staff staff6 = new Staff("test", "test", StaffDegree.DOCTOR, "test", StaffRole.LECTURER, department2);
        Staff staff7 = new Staff("test", "test", StaffDegree.BACHELOR, "test", StaffRole.LECTURER, department3);
        Staff staff8 = new Staff("test", "test", StaffDegree.NO_DEGREE, "test", StaffRole.LECTURER, department3);

        staff1 = staffRepository.save(staff1);
        staff2 = staffRepository.save(staff2);
        staff3 = staffRepository.save(staff3);
        staff4 = staffRepository.save(staff4);
        staff5 = staffRepository.save(staff5);
        staff6 = staffRepository.save(staff6);
        staff7 = staffRepository.save(staff7);
        staff8 = staffRepository.save(staff8);
    }
}
