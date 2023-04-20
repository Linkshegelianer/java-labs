package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp(); // получите экземпляр приложения
        app.start(0); // Запустите приложение при помощи метода start() на случайном порту
        int port = app.port(); // Получите порт, на котором запустилось приложение
        baseUrl = "http://localhost:" + port; // сформируйте базовый URL и запишите его в свойство baseUrl класса
    }

    @AfterAll
    public static void afterAll() {
        // Останавливаем приложение
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void test302() {
        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Ivan")
                .field("lastName", "Ivanov")
                .field("email", "meow@yandex.ru")
                .field("password", "12345")
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(302);

        User actualUser = new QUser()
                .email.equalTo("meow@yandex.ru")
                .findOne();

        assertThat(actualUser).isNotNull();

        assertThat(actualUser.getFirstName()).isEqualTo("Ivan");
        assertThat(actualUser.getLastName()).isEqualTo("Ivanov");
        assertThat(actualUser.getEmail()).isEqualTo("meow@yandex.ru");
        assertThat(actualUser.getPassword()).isEqualTo("12345");
    }


    @Test
    void test422() {
        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("field", "value")
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);
    }
    // END
}
