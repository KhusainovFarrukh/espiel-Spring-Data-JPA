package kh.farrukh.espielspringdatajpa;

import kh.farrukh.espielspringdatajpa.fetch_type_and_cascade.ChildEntity;
import kh.farrukh.espielspringdatajpa.fetch_type_and_cascade.ChildEntityRepository;
import kh.farrukh.espielspringdatajpa.fetch_type_and_cascade.ParentEntity;
import kh.farrukh.espielspringdatajpa.fetch_type_and_cascade.ParentEntityRepository;
import kh.farrukh.espielspringdatajpa.jpa_specification.*;
import kh.farrukh.espielspringdatajpa.jpa_specification.custom_specifications.ArticleHasAuthorsSpecification;
import kh.farrukh.espielspringdatajpa.jpa_specification.custom_specifications.ArticleHasTagsSpecification;
import kh.farrukh.espielspringdatajpa.jpa_specification.custom_specifications.ArticleMaxPublishedYearSpecification;
import kh.farrukh.espielspringdatajpa.jpa_specification.custom_specifications.ArticleMinPublishedYearSpecification;
import kh.farrukh.espielspringdatajpa.jpa_specification.specification_utils.ArticleSpecifications;
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
import kh.farrukh.espielspringdatajpa.relationships.country.Country;
import kh.farrukh.espielspringdatajpa.relationships.country.CountryRepository;
import kh.farrukh.espielspringdatajpa.relationships.course.Course;
import kh.farrukh.espielspringdatajpa.relationships.course.CourseRepository;
import kh.farrukh.espielspringdatajpa.relationships.enrolment.Enrolment;
import kh.farrukh.espielspringdatajpa.relationships.enrolment.EnrolmentRepository;
import kh.farrukh.espielspringdatajpa.relationships.enrolment.EnrolmentType;
import kh.farrukh.espielspringdatajpa.relationships.phone_number.PhoneNumber;
import kh.farrukh.espielspringdatajpa.relationships.room.Room;
import kh.farrukh.espielspringdatajpa.relationships.room.RoomRepository;
import kh.farrukh.espielspringdatajpa.relationships.student.Student;
import kh.farrukh.espielspringdatajpa.relationships.student.StudentRepository;
import kh.farrukh.espielspringdatajpa.relationships.student_card.StudentCard;
import kh.farrukh.espielspringdatajpa.relationships.student_card.StudentCardRepository;
import kh.farrukh.espielspringdatajpa.relationships.teacher.Teacher;
import kh.farrukh.espielspringdatajpa.relationships.teacher.TeacherRepository;
import kh.farrukh.espielspringdatajpa.secondary_table.one_entity_separate_tables.MealV2;
import kh.farrukh.espielspringdatajpa.secondary_table.one_entity_separate_tables.MealV2Repository;
import kh.farrukh.espielspringdatajpa.secondary_table.secondary_table_with_embedded.AllergensV3;
import kh.farrukh.espielspringdatajpa.secondary_table.secondary_table_with_embedded.MealV3;
import kh.farrukh.espielspringdatajpa.secondary_table.secondary_table_with_embedded.MealV3Repository;
import kh.farrukh.espielspringdatajpa.secondary_table.separate_entities.AllergensV1;
import kh.farrukh.espielspringdatajpa.secondary_table.separate_entities.AllergensV1Repository;
import kh.farrukh.espielspringdatajpa.secondary_table.separate_entities.MealV1;
import kh.farrukh.espielspringdatajpa.secondary_table.separate_entities.MealV1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class EspielSpringDataJpaApplication implements CommandLineRunner {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final StaffRepository staffRepository;

    private final CountryRepository countryRepository;
    private final CourseRepository courseRepository;
    private final BookRepository bookRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentCardRepository studentCardRepository;
    private final RoomRepository roomRepository;
    private final EnrolmentRepository enrolmentRepository;

    private final ParentEntityRepository parentEntityRepository;
    private final ChildEntityRepository childEntityRepository;

    private final ArticleRepository articleRepository;
    private final WriterRepository writerRepository;

    private final AllergensV1Repository allergensV1Repository;
    private final MealV1Repository mealV1Repository;
    private final MealV2Repository mealV2Repository;
    private final MealV3Repository mealV3Repository;

    private final EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(EspielSpringDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // TODO: 7/28/22 write tests for each situation
//        populateMainTestData();
//        populateRelationshipsTestData();
//        testLazyFetchWithOneParentEntity();
//        testLazyFetchWithMultipleParentEntity();
//        testCascadeTypePersist();
//        testCascadeTypeRemove();
//        testCascadeTypeMerge();
//        testOrphanRemoval();
//        testEntityGraph();
//        testSpecificationsViaCustomImplementationsForEachFilter();
//        testSpecificationsViaUtilMethods();
//        testCriteriaAPI();
//        testSecondaryTable_separateEntities();
//        testSecondaryTable_oneEntity_separateTable();
        testSecondaryTable_secondaryTable_with_embedded();
    }

    private void populateMainTestData() {
        log.info("START: populateMainTestData");
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
        log.info("START: populateRelationshipsTestData");
        try {
            // populate countries
            List<Country> countries = List.of(
                    new Country(0, "Uzbekistan"),
                    new Country(0, "Russia"),
                    new Country(0, "China")
            );
            countries = countryRepository.saveAll(countries);

            // populate teachers
            PhoneNumber fakePhoneNumber = new PhoneNumber("+998", "98-765-43-21", countries.get(0));
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

            // populate enrolments
            List<Enrolment> enrolments = List.of(
                    new Enrolment(students.get(0), courses.get(0), EnrolmentType.FREE),
                    new Enrolment(students.get(0), courses.get(1), EnrolmentType.PAID),
                    new Enrolment(students.get(0), courses.get(2), EnrolmentType.TRIAL),
                    new Enrolment(students.get(1), courses.get(2), EnrolmentType.PAID),
                    new Enrolment(students.get(2), courses.get(2), EnrolmentType.PAID)
            );

            enrolments = enrolmentRepository.saveAll(enrolments);

            printAllPersistedData();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void printAllPersistedData() {
        teacherRepository.findAll().forEach(teacher -> log.info(teacher.toString()));
        roomRepository.findAll().forEach(room -> log.info(room.toString()));
        studentRepository.findAll().forEach(student -> log.info(student.toString()));
        studentCardRepository.findAll().forEach(studentCard -> log.info(studentCard.toString()));
        bookRepository.findAll().forEach(book -> log.info(book.toString()));
        courseRepository.findAll().forEach(course -> log.info(course.toString()));
        enrolmentRepository.findAll().forEach(enrolment -> log.info(enrolment.toString()));
        countryRepository.findAll().forEach(country -> log.info(country.toString()));
    }

    private void testLazyFetchWithOneParentEntity() {
        log.info("START: testLazyFetchWithOneParentEntity");
        childEntityRepository.deleteAll();
        parentEntityRepository.deleteAll();

        //persist all testing data
        ParentEntity parent = new ParentEntity(0, "test parent", Collections.emptySet());
        parent = parentEntityRepository.save(parent);
        ChildEntity child1 = new ChildEntity(0, "test child 1", parent);
        child1 = childEntityRepository.save(child1);
        ChildEntity child2 = new ChildEntity(0, "test child 2", parent);
        child2 = childEntityRepository.save(child2);

        //eager fetch parent from child
        ChildEntity fetchedChild = childEntityRepository.findById(child1.getId()).orElse(new ChildEntity());
        log.info("child name: " + fetchedChild.getName());
        log.info("parent title: " + fetchedChild.getParent().getTitle());

        //lazy fetch children from parent
        ParentEntity fetchedParent = parentEntityRepository.findById(parent.getId()).orElse(new ParentEntity());
        log.info("parent title: " + fetchedParent.getTitle());
        log.info("children size: " + fetchedParent.getChildren().size());
    }

    private void testLazyFetchWithMultipleParentEntity() {
        log.info("START: testLazyFetchWithMultipleParentEntity");
        childEntityRepository.deleteAll();
        parentEntityRepository.deleteAll();

        //persist all testing data
        ParentEntity parent1 = new ParentEntity(0, "test parent 1", Collections.emptySet());
        parent1 = parentEntityRepository.save(parent1);
        ParentEntity parent2 = new ParentEntity(0, "test parent 2", Collections.emptySet());
        parent2 = parentEntityRepository.save(parent2);
        ChildEntity child1 = new ChildEntity(0, "test child 1", parent1);
        child1 = childEntityRepository.save(child1);
        ChildEntity child2 = new ChildEntity(0, "test child 2", parent1);
        child2 = childEntityRepository.save(child2);
        ChildEntity child3 = new ChildEntity(0, "test child 3", parent1);
        child3 = childEntityRepository.save(child3);
        ChildEntity child4 = new ChildEntity(0, "test child 4", parent2);
        child4 = childEntityRepository.save(child4);

        //lazy fetch children from parent (multiple parents with forEach loop) (maybe n+1 problem)
        List<ParentEntity> fetchedParentsList = parentEntityRepository.findAll();
        log.info("parents size: " + fetchedParentsList.size());
        fetchedParentsList.forEach(parent -> log.info("parent title: " + parent.getTitle()));

        fetchedParentsList.forEach(parent -> {
            Set<ChildEntity> children = parent.getChildren();
            log.info("children size: " + children.size());
            children.forEach(child -> log.info("child name: " + child.getName()));
        });
    }

    private void testCascadeTypePersist() {
        childEntityRepository.deleteAll();
        parentEntityRepository.deleteAll();
        log.info("START: testCascadeTypePersist");

        // persist all testing data
        ParentEntity parent = new ParentEntity(0, "test parent", Collections.emptySet());
        ChildEntity child = new ChildEntity(0, "test child", null);
        parent.setChildren(Set.of(child));
        child.setParent(parent);
        parent = parentEntityRepository.save(parent);

        log.info("parent title: " + parentEntityRepository.findById(parent.getId()).orElse(new ParentEntity()).getTitle());
        log.info("child name: " + childEntityRepository.findById(child.getId()).orElse(new ChildEntity()).getName());
    }

    private void testCascadeTypeRemove() {
        childEntityRepository.deleteAll();
        parentEntityRepository.deleteAll();
        log.info("START: testCascadeTypeRemove");

        // persist all testing data
        ParentEntity parent = new ParentEntity(0, "test parent", Collections.emptySet());
        ChildEntity child = new ChildEntity(0, "test child", null);
        parent.setChildren(Set.of(child));
        child.setParent(parent);
        parent = parentEntityRepository.save(parent);

        parentEntityRepository.delete(parent);

        log.info("parents size: " + parentEntityRepository.findAll().size());
        log.info("children size: " + childEntityRepository.findAll().size());
    }

    private void testCascadeTypeMerge() {
        childEntityRepository.deleteAll();
        parentEntityRepository.deleteAll();
        log.info("START: testCascadeTypeMerge");

        // persist all testing data
        ParentEntity parent = new ParentEntity(0, "test parent", Collections.emptySet());
        ChildEntity child = new ChildEntity(0, "test child", null);
        parent.setChildren(Set.of(child));
        child.setParent(parent);
        parent = parentEntityRepository.save(parent);

        ParentEntity fetchedParent = parentEntityRepository.findById(parent.getId()).orElse(new ParentEntity());
        Set<ChildEntity> fetchedChildren = fetchedParent.getChildren();
        fetchedParent.setTitle(fetchedParent.getTitle() + " updated");
        fetchedChildren.forEach(childItem -> childItem.setName(childItem.getName() + " updated"));
        fetchedParent = parentEntityRepository.save(fetchedParent);

        log.info("parent title: " + parentEntityRepository.findById(parent.getId()).orElse(new ParentEntity()).getTitle());
        log.info("child name: " + childEntityRepository.findById(child.getId()).orElse(new ChildEntity()).getName());
    }

    private void testOrphanRemoval() {
        childEntityRepository.deleteAll();
        parentEntityRepository.deleteAll();
        log.info("START: testOrphanRemoval");

        // persist all testing data
        ChildEntity child = new ChildEntity(0, "test child", null);

        ParentEntity parent1 = new ParentEntity(0, "test parent 1", Collections.emptySet());
        parent1.setChildren(Set.of(child));
        child.setParent(parent1);
        parent1 = parentEntityRepository.saveAndFlush(parent1);

        log.info("parents size before: " + parentEntityRepository.findAll().size());
        log.info("children size before: " + childEntityRepository.findAll().size());

        parent1.setChildren(Collections.emptySet());
        child.setParent(null);
        parent1 = parentEntityRepository.saveAndFlush(parent1);

        log.info("parents size after: " + parentEntityRepository.findAll().size());
        log.info("children size after: " + childEntityRepository.findAll().size());
    }

    private void testEntityGraph() {
        log.info("START: testEntityGraph");
        childEntityRepository.deleteAll();
        parentEntityRepository.deleteAll();

        //persist all testing data
        ParentEntity parent = new ParentEntity(0, "test parent", Collections.emptySet());
        parent = parentEntityRepository.save(parent);
        ChildEntity child1 = new ChildEntity(0, "test child 1", parent);
        child1 = childEntityRepository.save(child1);
        ChildEntity child2 = new ChildEntity(0, "test child 2", parent);
        child2 = childEntityRepository.save(child2);

        //fetch using entity graph
        parentEntityRepository.findAllWithChildren().forEach(parentEntity -> {
            log.info("parent title: " + parentEntity.getTitle());
            parentEntity.getChildren().forEach(child -> log.info("child name: " + child.getName()));
        });
    }

    private void initSpecificationsData() {
        articleRepository.deleteAll();
        writerRepository.deleteAll();
        Writer writer1 = new Writer("Hamdam Xudayberganov", Profession.PROGRAMMER);
        Writer writer2 = new Writer("Adam Smith", Profession.ECONOMIST);
        Writer writer3 = new Writer("Uncle Ben", Profession.PROGRAMMER);
        Writer writer4 = new Writer("John Krakovski", Profession.SCIENTIST);
        Writer writer5 = new Writer("Sam Jones", Profession.OTHER);

        Article book1 = new Article(
                "My Java journey",
                2023,
                List.of("programming", "life", "autobiography"),
                89.9,
                427,
                List.of(writer1)
        );

        Article book2 = new Article(
                "Supply and Demand",
                1876,
                List.of("economics", "science"),
                119.9,
                241,
                List.of(writer2)
        );
        Article book3 = new Article(
                "Very large book",
                2025,
                List.of("programming", "life", "economics"),
                19.9,
                1429,
                List.of(writer1, writer2, writer3, writer4, writer5)
        );

        Article book4 = new Article(
                "Economics and Clean Code",
                2037,
                List.of("economics", "programming"),
                159.9,
                436,
                List.of(writer1, writer3)
        );
        Article book5 = new Article(
                "Science about everything",
                2017,
                List.of("science"),
                59.9,
                43,
                List.of(writer4)
        );
        Article book6 = new Article(
                "Everything about everything",
                1999,
                List.of("science", "programming", "life", "economics"),
                19.9,
                444,
                List.of(writer5)
        );

        articleRepository.saveAll(List.of(book1, book2, book3, book4, book5, book6));

        // get all and print
//        articleRepository.findAll().forEach(book -> {
//            System.out.println("title: " + book.getTitle());
//            System.out.println("tags: " + book.getTags());
//            System.out.println("authors:");
//            book.getAuthors().forEach(author -> {
//                System.out.println("name: " + author.getFullName());
//                System.out.println("profession: " + author.getProfession().name().toLowerCase());
//            });
//            System.out.println("--------------------------------------");
//        });
    }

    private void printSpecificationResults(List<Article> articles) {
        System.out.println("size: " + articles.size());
        articles.forEach(article -> {
            System.out.println("title: " + article.getTitle());
            System.out.println("tags: " + article.getTags());
            System.out.println("authors:");
            article.getAuthors().forEach(author -> {
                System.out.println("name: " + author.getFullName());
                System.out.println("profession: " + author.getProfession().name().toLowerCase());
            });
            System.out.println("--------------------------------------");
        });
    }

    private void testSpecificationsViaCustomImplementationsForEachFilter() {
        initSpecificationsData();

        Specification<Article> specification = Specification
                .where(new ArticleMinPublishedYearSpecification(2000))
                .and(new ArticleMaxPublishedYearSpecification(null))
                .and(new ArticleHasTagsSpecification(List.of("programming")))
                .and(new ArticleHasAuthorsSpecification(List.of(1L, 3L)));

        printSpecificationResults(articleRepository.findAll(specification));
    }

    private void testSpecificationsViaUtilMethods() {
        initSpecificationsData();

        Specification<Article> specification = ArticleSpecifications.filterBy(
                new ArticleFilterParams(
                        2000,
                        null,
                        List.of("programming"),
                        List.of(1L, 3L))
        );

        printSpecificationResults(articleRepository.findAll(specification));
    }

    private void testCriteriaAPI() {
        initSpecificationsData();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);
        query.distinct(true);

        Predicate minimumPublishedYearPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("publishedYear"), 2000);
        Predicate maximumPublishedYearPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("publishedYear"), 2099);
        Join<Article, String> tags = root.join("tags");
        Predicate hasTagPredicate = tags.in(List.of("programming"));
        Join<Article, Writer> authorsJoin = root.join("authors");
        Predicate hasAuthors = authorsJoin.get("id").in(List.of(1L, 3L));

        query.where(criteriaBuilder.and(
                minimumPublishedYearPredicate,
                maximumPublishedYearPredicate,
                hasTagPredicate,
                hasAuthors
        ));

        printSpecificationResults(em.createQuery(query.select(root)).getResultList());
    }

    private void testSecondaryTable_separateEntities() {
        allergensV1Repository.deleteAll();
        mealV1Repository.deleteAll();

        AllergensV1 allergens1 = allergensV1Repository.save(new AllergensV1(false, false, false));
        AllergensV1 allergens2 = allergensV1Repository.save(new AllergensV1(true, true, false));
        AllergensV1 allergens3 = allergensV1Repository.save(new AllergensV1(true, true, true));

        MealV1 meal1 = mealV1Repository.save(new MealV1("meal 1", "desc", 1.0, allergens1));
        MealV1 meal2 = mealV1Repository.save(new MealV1("meal 1", "desc", 1.0, allergens2));
        MealV1 meal3 = mealV1Repository.save(new MealV1("meal 1", "desc", 1.0, allergens3));

        mealV1Repository.findAll().forEach(meal -> System.out.println(meal.toString()));
    }

    private void testSecondaryTable_oneEntity_separateTable() {
        mealV2Repository.deleteAll();

        MealV2 meal1 = mealV2Repository.save(
                new MealV2("meal 1", "desc", 1.0, false, false, false)
        );
        MealV2 meal2 = mealV2Repository.save(
                new MealV2("meal 1", "desc", 1.0, true, true, false)
        );
        MealV2 meal3 = mealV2Repository.save(
                new MealV2("meal 1", "desc", 1.0, true, true, true)
        );

        mealV2Repository.findAll().forEach(meal -> System.out.println(meal.toString()));
    }

    private void testSecondaryTable_secondaryTable_with_embedded() {
        mealV3Repository.deleteAll();

        AllergensV3 allergens1 = new AllergensV3(false, false, false);
        AllergensV3 allergens2 = new AllergensV3(true, true, false);
        AllergensV3 allergens3 = new AllergensV3(true, true, true);

        MealV3 meal1 = mealV3Repository.save(new MealV3("meal 1", "desc", 1.0, allergens1));
        MealV3 meal2 = mealV3Repository.save(new MealV3("meal 1", "desc", 1.0, allergens2));
        MealV3 meal3 = mealV3Repository.save(new MealV3("meal 1", "desc", 1.0, allergens3));

        mealV3Repository.findAll().forEach(meal -> System.out.println(meal.toString()));
    }
}