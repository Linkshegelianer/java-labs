package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> test1 = Arrays.asList(1, 2, 3, 4, 5); // создаём массив
        int n = 3;
        List<Integer> expectedList1 = Arrays.asList(1, 2, 3); // сами пишем ожидаемый результат
        List<Integer> resultList1 = Implementations.right(test1, n); // создаём новый массив, который результат метода right
        assertThat(resultList1).isEqualTo(expectedList1); // сравниваем их

        List<Integer> test2 = new ArrayList<Integer>(); // создаём массив
        List<Integer> expectedList2 = Arrays.asList(0); // сами пишем ожидаемый результат
        List<Integer> resultList2 = Implementations.wrong1(test2, n); // создаём новый массив, который результат метода wrong1
        assertThat(resultList2).isEqualTo(expectedList2); // сравниваем их

        List<Integer> test3 = new ArrayList<Integer>(Arrays.asList(1, 2));// создаём массив
        List<Integer> expectedList3 = Arrays.asList(1, 2, 100); // сами пишем ожидаемый результат
        List<Integer> resultList3 = Implementations.wrong2(test3, n); // создаём новый массив, который результат метода wrong2
        assertThat(resultList3).isEqualTo(expectedList3); // сравниваем их

        List<Integer> test4 = new ArrayList<Integer>(Arrays.asList(1, 2, 3)); // создаём массив
        List<Integer> expectedList4 = Arrays.asList(1, 2, 3, 10); // сами пишем ожидаемый результат
        List<Integer> resultList4 = Implementations.wrong3(test4, n); // создаём новый массив, который результат метода wrong3
        assertThat(resultList4).isEqualTo(expectedList4); // сравниваем их

        // END
    }
}
