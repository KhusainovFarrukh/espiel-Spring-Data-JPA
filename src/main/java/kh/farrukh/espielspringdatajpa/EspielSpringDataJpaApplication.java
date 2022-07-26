package kh.farrukh.espielspringdatajpa;

import kh.farrukh.espielspringdatajpa.main.endpoints.department.Department;
import kh.farrukh.espielspringdatajpa.main.endpoints.department.DepartmentRepository;
import kh.farrukh.espielspringdatajpa.main.endpoints.faculty.Faculty;
import kh.farrukh.espielspringdatajpa.main.endpoints.faculty.FacultyRepository;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.Staff;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffDegree;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRepository;
import kh.farrukh.espielspringdatajpa.main.endpoints.staff.StaffRole;
import kh.farrukh.espielspringdatajpa.relationships.book.Book;
import kh.farrukh.espielspringdatajpa.relationships.book.BookRepository;
import kh.farrukh.espielspringdatajpa.relationships.course.Course;
import kh.farrukh.espielspringdatajpa.relationships.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class EspielSpringDataJpaApplication implements CommandLineRunner {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final StaffRepository staffRepository;

    private final CourseRepository courseRepository;
    private final BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(EspielSpringDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        populateMainTestData();
        populateRelationshipsTestData();
    }

    private void populateMainTestData() {
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

    private void populateRelationshipsTestData() {
        try {
            List<Book> books = List.of(
                    new Book(0, "Java guides", "Kim Jung On", 2001),
                    new Book(0, "From Java to Kotlin", "Raw Wonder", 2019),
                    new Book(0, "Everything about Django", "Mike Sims", 2022),
                    new Book(0, "New generation: Java backend", "Spring publishers", 2022),
                    new Book(0, "Android development in Java", "Google", 2011)
            );

            books = (List<Book>) bookRepository.saveAll(books);

            List<Course> courses = List.of(
                    new Course(0, "Master Java", "", Set.of(
                            books.get(0), books.get(3), books.get(1)
                    )),
                    new Course(0, "Backend development", "", Set.of(
                            books.get(2), books.get(3)
                    )),
                    new Course(0, "Android Development", "", Set.of(
                            books.get(0), books.get(4), books.get(1)
                    ))
            );

            courses = (List<Course>) courseRepository.saveAll(courses);

            bookRepository.findAll().forEach(book -> log.info(book.toString()));
            courseRepository.findAll().forEach(course -> log.info(course.toString()));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
