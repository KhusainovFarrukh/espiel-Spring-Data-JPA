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
import kh.farrukh.espielspringdatajpa.relationships.phone_number.PhoneNumber;
import kh.farrukh.espielspringdatajpa.relationships.room.Room;
import kh.farrukh.espielspringdatajpa.relationships.room.RoomRepository;
import kh.farrukh.espielspringdatajpa.relationships.student.Student;
import kh.farrukh.espielspringdatajpa.relationships.student.StudentRepository;
import kh.farrukh.espielspringdatajpa.relationships.student_card.StudentCard;
import kh.farrukh.espielspringdatajpa.relationships.student_card.StudentCardRepository;
import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import kh.farrukh.espielspringdatajpa.relationships.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@EnableTransactionManagement
public class EspielSpringDataJpaApplication implements CommandLineRunner {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final StaffRepository staffRepository;

    private final CourseRepository courseRepository;
    private final BookRepository bookRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentCardRepository studentCardRepository;
    private final RoomRepository roomRepository;

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

    @Transactional
    void populateRelationshipsTestData() {
        try {
            // populate teachers
            PhoneNumber fakePhoneNumber = new PhoneNumber("+998", "98-765-43-21");
            List<Teacher> teachers = List.of(
                    new Teacher(0, "John", "Smith", fakePhoneNumber),
                    new Teacher(0, "Alex", "Pride", fakePhoneNumber),
                    new Teacher(0, "Adam", "Warlock", fakePhoneNumber)
            );
            teachers = (List<Teacher>) teacherRepository.saveAll(teachers);

            // populate rooms
            List<Room> rooms = List.of(
                    new Room(0, teachers.get(0)),
                    new Room(0, teachers.get(1)),
                    new Room(0, teachers.get(2))
            );
            rooms = (List<Room>) roomRepository.saveAll(rooms);

            // populate books
            List<Book> books = List.of(
                    new Book(0, "Java guides", 2001, Set.of(teachers.get(0), teachers.get(1))),
                    new Book(0, "From Java to Kotlin", 2019, Set.of(teachers.get(1), teachers.get(2))),
                    new Book(0, "Everything about Django", 2022, Set.of(teachers.get(0))),
                    new Book(0, "New generation: Java backend", 2022, Set.of(teachers.get(2))),
                    new Book(0, "Android development in Java", 2011, Set.of(teachers.get(0), teachers.get(1), teachers.get(2)))
            );
            books = (List<Book>) bookRepository.saveAll(books);

            // try to update book-teacher many-to-many association by owning side (book)
            // must work
            books.get(0).setAuthors(Set.of(teachers.get(0)));
            bookRepository.save(books.get(0));

            // try to update book-teacher many-to-many association by non-owning side (teacher)
            // must not work
            teachers.get(0).setBooks(Set.of(books.get(0)));
            teacherRepository.save(teachers.get(0));

            // populate students
            List<Student> students = List.of(
                    new Student(0, "Mark", "Ruffalo", fakePhoneNumber, teachers.get(0)),
                    new Student(0, "Chris", "Evans", fakePhoneNumber, teachers.get(1)),
                    new Student(0, "Robert", "Downey", fakePhoneNumber, teachers.get(2)),
                    new Student(0, "Scarlett", "Johansson", fakePhoneNumber, teachers.get(2)),
                    new Student(0, "Chris", "Hemsworth", fakePhoneNumber, teachers.get(1))
            );
            students = (List<Student>) studentRepository.saveAll(students);

            // populate student cards
            List<StudentCard> studentCards = List.of(
                    new StudentCard(0, students.get(0)),
                    new StudentCard(0, students.get(1)),
                    new StudentCard(0, students.get(2))
            );
            studentCards = (List<StudentCard>) studentCardRepository.saveAll(studentCards);

            // populate courses
            List<Course> courses = List.of(
                    new Course(0, "Master Java", "", teachers.get(0), Set.of(
                            books.get(0), books.get(3), books.get(1)
                    )),
                    new Course(0, "Backend development", "", teachers.get(0), Set.of(
                            books.get(2), books.get(3)
                    )),
                    new Course(0, "Android Development", "", teachers.get(1), Set.of(
                            books.get(0), books.get(4), books.get(1)
                    ))
            );
            courses = (List<Course>) courseRepository.saveAll(courses);

            // print out all persisted data
            teacherRepository.findAll().forEach(teacher -> log.info(teacher.toString()));
            roomRepository.findAll().forEach(room -> log.info(room.toString()));
            studentRepository.findAll().forEach(student -> log.info(student.toString()));
            studentCardRepository.findAll().forEach(studentCard -> log.info(studentCard.toString()));
            bookRepository.findAll().forEach(book -> log.info(book.toString()));
            courseRepository.findAll().forEach(course -> log.info(course.toString()));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}