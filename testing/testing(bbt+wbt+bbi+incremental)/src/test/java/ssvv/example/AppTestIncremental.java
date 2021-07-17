package ssvv.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AppTestIncremental {
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
    public void test_incremental_addStudent_null_id(){
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        try{
            service.saveStudent(null, "Sergiu", 666);
        }
        catch (ValidationException e) {
            assertEquals("ID invalid! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

    }

    @Test
    public void test_incremental_student_assignment() {
        service.saveStudent("1", "Sergiu", 666);
        service.saveTema("1", "tema", 4, 3);
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
        assertEquals(1, service.findAllTeme().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_incremental_student_assignment_grade() {
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        assertEquals(0, service.findAllTeme().spliterator().getExactSizeIfKnown());
        assertEquals(0, service.findAllNote().spliterator().getExactSizeIfKnown());
        service.saveStudent("1", "Sergiu", 666);
        service.saveTema("1", "tema", 4, 3);
        service.saveNota("1", "1", 10, 3, "alles gut");
        assertEquals(1, service.findAllStudents().spliterator().getExactSizeIfKnown());
        assertEquals(1, service.findAllTeme().spliterator().getExactSizeIfKnown());
        assertEquals(1, service.findAllNote().spliterator().getExactSizeIfKnown());
    }
}
