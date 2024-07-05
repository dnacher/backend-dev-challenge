import com.directa24.main.challenge.Main;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/** Daniel Nacher 2024-07-05 */
public class MainTest {

    @Test
    public void test() {
        assertEquals(4, Main.getDirectors(3).size());
    }

    @Test
    public void test2() {
        assertEquals(4, Main.getDirectors(5).size());
    }

    @Test
    public void test3() {
        assertEquals(6, Main.getDirectors(1).size());
    }

    @Test
    public void test4() {
        assertEquals(6, Main.getDirectors(0).size());
    }

    @Test
    public void test5() {
        List<String> directors = Main.getDirectors(3);
        System.out.println(directors);
        assertEquals("[M. Night Shyamalan, Martin Scorsese, Pedro Almodóvar, Woody Allen]", directors.toString());
    }

    @Test
    public void test6() {
        List<String> directors = Main.getDirectors(5);
        System.out.println(directors);
        assertEquals("[M. Night Shyamalan, Martin Scorsese, Pedro Almodóvar, Woody Allen]", directors.toString());
    }

    @Test
    public void test7() {
        List<String> directors = Main.getDirectors(1);
        System.out.println(directors);
        assertEquals("[Clint Eastwood, M. Night Shyamalan, Martin Scorsese, Pedro Almodóvar, Quentin Tarantino, Woody Allen]", directors.toString());
    }

    @Test
    public void test8() {
        List<String> directors = Main.getDirectors(0);
        System.out.println(directors);
        assertEquals("[Clint Eastwood, M. Night Shyamalan, Martin Scorsese, Pedro Almodóvar, Quentin Tarantino, Woody Allen]", directors.toString());
    }

    @Test
    public void test9() {
        List<String> directors = Main.getDirectors(4);
        System.out.println(directors);
        assertEquals("[M. Night Shyamalan, Martin Scorsese, Pedro Almodóvar, Woody Allen]", directors.toString());
    }

}
