package ssvv.example;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

public class AppTestBigBang {
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

    //FROM BBT
    @Test
    public void test_bbi_addStudent_null_id(){
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());
        try{
            service.saveStudent(null, "Tudor", 666);
        }
        catch (ValidationException e) {
            assertEquals("ID invalid! \n", e.getMessage());
        }
        assertEquals(0, service.findAllStudents().spliterator().getExactSizeIfKnown());

    }
    //FROM WBT
    @Test
    public void test_bbi_null_description_addAssignment() {
        assertEquals(0, service.findAllTeme().spliterator().getExactSizeIfKnown());
        try{
            service.saveTema("99", null, 8, 3);
        }
        catch (ValidationException e) {
            assertEquals("Descriere invalida! \n", e.getMessage());
        }
        assertEquals(0, service.findAllTeme().spliterator().getExactSizeIfKnown());
    }
    //BIG BANG
    @Test
    public void test_bbi_addGrade_nota_belowLowerBound() {
        assertEquals(0, service.findAllNote().spliterator().getExactSizeIfKnown());
        try {
            service.saveNota("1", "1", -1, 5, "none");
        }
        catch (ValidationException e) {
            assertEquals("Nota invalida! \n", e.getMessage());
        }
        assertEquals(0, service.findAllNote().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void test_bbi_bigBang_Combined() {
        assertEquals(0, service.findAllNote().spliterator().getExactSizeIfKnown());
        service.saveStudent("1", "Sergiu", 666);
        service.saveTema("1", "oTema", 5, 1);

        try {
            service.saveNota("1", "1", -1, 5, "none");
        }
        catch (ValidationException e) {
            assertEquals("Nota invalida! \n", e.getMessage());
        }
        assertEquals(0, service.findAllNote().spliterator().getExactSizeIfKnown());
    }
}
