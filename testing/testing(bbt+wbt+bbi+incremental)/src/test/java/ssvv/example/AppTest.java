package ssvv.example;

import static org.junit.Assert.assertEquals;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

public class AppTest
{
    private static final String MAXINT = "2147483648";
    private Service service;
    File file1 = new File("studenti_test.xml");
    File file2 = new File("teme_test.xml");
    File file3 = new File("note_test.xml");

    private void initializeFile(String name) {
        try {
            File newFile = new File(name);
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter fileWriter = new FileWriter(name);
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<Entitati>\n" +
                    "\n" +
                    "</Entitati>\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Before
    public void initialize() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();
        initializeFile("studenti_test.xml");
        initializeFile("teme_test.xml");
        initializeFile("note_test.xml");
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository temeRepo = new TemaXMLRepository(temaValidator, "teme_test.xml");
        NotaXMLRepository noteRepo = new NotaXMLRepository(notaValidator, "note_test.xml");
        service = new Service(studentRepo, temeRepo, noteRepo);
    }

    @After
    public void deleteFiles() {
        file1.delete();
        file2.delete();
        file3.delete();
    }

    @Test
    public void test_addStudent_null_id(){
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        try{
            service.saveStudent(null, "Tudor", 666);
        }
        catch (ValidationException e) {
            assertEquals("ID invalid! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

    }
    @Test
    public void test_addStudent_empty_id()
    {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

        try{
            service.saveStudent("", "Tudor", 666);
        }
        catch (ValidationException e) {
            assertEquals("ID invalid! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

    }
    //LOWER BOUND */*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
    @Test
    public void test_addStudent_id_belowLowerBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

        try{
            service.saveStudent("-1", "Tudor", 666);
        }
        catch (ValidationException e) {
            assertEquals("Id incorect\n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_id_equalLowerBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        service.saveStudent("0", "Tudor", 666);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_id_aboveLowerBound() {
        service.saveStudent("1", "Tudor", 666);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    //UPPER BOUND */*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
    @Test
    public void test_addStudent_id_belowUpperBound() {
        service.saveStudent(String.valueOf(Integer.MAX_VALUE - 1), "Tudor", 666);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_id_equalUpperBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        service.saveStudent(String.valueOf(Integer.MAX_VALUE), "Tudor", 666);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_id_aboveUpperBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

        try{
            service.saveStudent(MAXINT, "Tudor", 666);
        }
        catch (ValidationException e) {
            assertEquals("Id incorect\n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }
    //LOWER BOUND */*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
    @Test
    public void test_addStudent_group_belowLowerBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        try{
            service.saveStudent("1", "Sergiu", 110);
        }
        catch (ValidationException e) {
            assertEquals("Grupa invalida! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_group_equalLowerBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        service.saveStudent("1", "Sergiu", 111);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_group_aboveLowerBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        service.saveStudent("1", "Sergiu", 112);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }
    //UPPER BOUND */*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/
    @Test
    public void test_addStudent_group_belowUpperBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        service.saveStudent("1", "Sergiu", 936);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_group_equalUpperBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        service.saveStudent("1", "Sergiu", 937);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_addStudent_group_aboveUpperBound() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        try{
            service.saveStudent("1", "Sergiu", 938);
        }
        catch (ValidationException e) {
            assertEquals("Grupa invalida! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }
    //NAME TEST
    @Test
    public void test_addStudent_null() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        try{
            service.saveStudent("1", null, 666);
        }
        catch (ValidationException e) {
            assertEquals("Nume invalid! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }
    @Test
    public void test_addStudent_empty(){
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

        try{
            service.saveStudent("1", "", 666);
        }
        catch (ValidationException e) {
            assertEquals("Nume invalid! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

    }
}
