package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // test right()
        List<Integer> test1 = Arrays.asList(1, 2, 3, 4, 5); // create an array for test
        int n = 3; // set count value for tests
        List<Integer> expectedList1 = Arrays.asList(1, 2, 3); // expected result
        List<Integer> resultList1 = Implementations.right(test1, n); // create a new array containing right() result
        assertThat(resultList1).isEqualTo(expectedList1); // compare them

        // test wrong1()
        List<Integer> test2 = new ArrayList<Integer>(); 
        List<Integer> expectedList2 = Arrays.asList(0); 
        List<Integer> resultList2 = Implementations.wrong1(test2, n); 
        assertThat(resultList2).isEqualTo(expectedList2); 

        // test wrong2()
        List<Integer> test3 = new ArrayList<Integer>(Arrays.asList(1, 2));
        List<Integer> expectedList3 = Arrays.asList(1, 2, 100); 
        List<Integer> resultList3 = Implementations.wrong2(test3, n); 
        assertThat(resultList3).isEqualTo(expectedList3); 

        // test wrong3()
        List<Integer> test4 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        List<Integer> expectedList4 = Arrays.asList(1, 2, 3, 10);
        List<Integer> resultList4 = Implementations.wrong3(test4, n); 
        assertThat(resultList4).isEqualTo(expectedList4); 

    }
}
