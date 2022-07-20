package kh.farrukh.espielspringdatajpa.utils.checkers;

import kh.farrukh.espielspringdatajpa.endpoints.faculty.FacultyRepository;
import kh.farrukh.espielspringdatajpa.exception.custom_exceptions.BadRequestException;
import kh.farrukh.espielspringdatajpa.exception.custom_exceptions.ResourceNotFoundException;

/**
 * It contains static methods that check if a resource exists in the database,
 * is unique, request parameter is valid and etc.
 */
public class Checkers {

    /**
     * If the page number is less than 1, throw a BadRequestException.
     *
     * @param page The page number to return.
     */
    public static void checkPageNumber(int page) {
        if (page < 1) {
            throw new BadRequestException("Page index");
        }
    }


    /**
     * If the facultyId does not exist in the database, throw a ResourceNotFoundException
     *
     * @param facultyRepository The repository that will be used to check if the faculty exists.
     * @param facultyId         The id of the faculty to be checked
     */
    public static void checkFacultyId(FacultyRepository facultyRepository, long facultyId) {
        if (!facultyRepository.existsById(facultyId)) {
            throw new ResourceNotFoundException("Faculty", "id", facultyId);
        }
    }
}