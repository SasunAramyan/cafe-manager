package com.boot.cafemanager;

import com.boot.cafemanager.data.jpa.entity.UserEntity;
import com.boot.cafemanager.data.jpa.repository.UserRepository;
import com.boot.cafemanager.types.enums.Gender;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@ContextConfiguration(classes = CafeManagerApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/scripts/user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserTest {


    @Autowired
    UserRepository userRepository;

    @LocalServerPort
    private int port;


    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getById() {

        given().port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)

                .when()
                .get("/users/1")

                .then()
                .statusCode(200)
                .assertThat().body("id", equalTo(1))
                .assertThat().body("phoneNumber", equalTo("123456789"))
                .assertThat().body("username", equalTo("Benjamin123"))
                .assertThat().body("firstName", equalTo("Benjamin"))
                .assertThat().body("lastName", equalTo("Williams"))
                .assertThat().body("gender", equalTo("MALE"))
                .assertThat().body("email", equalTo("benjamin100@gmail.com"))
                .assertThat().body("roles", hasSize(1))
                .assertThat().body("roles[0].id", equalTo(1))
                .assertThat().body("roles[0].name", equalTo("ROLE_WAITER"));
    }

    @Test
    void getByInvalidId() {

        given().port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)

                .when()
                .get("/users/999")

                .then()
                .statusCode(404)
                .assertThat().body("message", equalTo("User not found: id = 999"));
    }


    @Test
    void getAll() {

        given().port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)

                .when()
                .get("/users")

                .then()
                .statusCode(200)
                .assertThat().body("$", hasSize(2))
                .assertThat().body("[0].id", equalTo(1))
                .assertThat().body("[0].phoneNumber", equalTo("123456789"))
                .assertThat().body("[0].username", equalTo("Benjamin123"))
                .assertThat().body("[0].firstName", equalTo("Benjamin"))
                .assertThat().body("[0].lastName", equalTo("Williams"))
                .assertThat().body("[0].gender", equalTo("MALE"))
                .assertThat().body("[0].email", equalTo("benjamin100@gmail.com"))
                .assertThat().body("[0].roles", hasSize(1))
                .assertThat().body("[0].roles[0].id", equalTo(1))
                .assertThat().body("[0].roles[0].name", equalTo("ROLE_WAITER"))

                .assertThat().body("[1].id", equalTo(2))
                .assertThat().body("[1].phoneNumber", equalTo("986564654"))
                .assertThat().body("[1].username", equalTo("Anie009"))
                .assertThat().body("[1].firstName", equalTo("Anie"))
                .assertThat().body("[1].lastName", equalTo("Akkerman"))
                .assertThat().body("[1].gender", equalTo("FEMALE"))
                .assertThat().body("[1].email", equalTo("anie123@gmail.com"))
                .assertThat().body("[1].roles", hasSize(1))
                .assertThat().body("[1].roles[0].id", equalTo(2))
                .assertThat().body("[1].roles[0].name", equalTo("ROLE_MANAGER"));

    }


    @Test
    void create() throws IOException {

        given()
                .port(port)
                .body(TestJsonFileReader.readAsText("user/valid.json"))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)

                .when()
                .post("/users")

                .then()
                .statusCode(200)
                .assertThat().body("id", equalTo(3))
                .assertThat().body("phoneNumber", equalTo("+374548487"))
                .assertThat().body("username", equalTo("EtanAreivo"))
                .assertThat().body("firstName", equalTo("Etan"))
                .assertThat().body("lastName", equalTo("Areivo"))
                .assertThat().body("gender", equalTo("MALE"))
                .assertThat().body("email", equalTo("Etan@gmail.com"))
                .assertThat().body("roles", hasSize(1))
                .assertThat().body("roles[0].id", equalTo(1))
                .assertThat().body("roles[0].name", equalTo("ROLE_WAITER"));

        UserEntity userEntity = userRepository.findById(3L).get();
        Assertions.assertThat(userEntity.getAge()).isEqualTo(20);
        Assertions.assertThat(userEntity.getUsername()).isEqualTo("EtanAreivo");
        Assertions.assertThat(userEntity.getFirstName()).isEqualTo("Etan");
        Assertions.assertThat(userEntity.getLastName()).isEqualTo("Areivo");
        Assertions.assertThat(userEntity.getGender()).isEqualTo(Gender.MALE);
        Assertions.assertThat(userEntity.getEmail()).isEqualTo("Etan@gmail.com");
    }

    @Test
    void createWithInvalidRole() throws IOException {

        given()
                .port(port)
                .body(TestJsonFileReader.readAsText("user/invalid-role.json"))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)

                .when()
                .post("/users")

                .then()
                .statusCode(404)
                .assertThat().body("message", equalTo("Role not found: id = 5"));
    }

    @Test
    void createWithEmptyFields() {

        given()
                .port(port)
                .body("{}")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)

                .when()
                .post("/users")

                .then()
                .statusCode(400)
                .assertThat().body("_embedded.errors", hasItem("email: must not be empty"))
                .assertThat().body("_embedded.errors", hasItem("lastName: must not be empty"))
                .assertThat().body("_embedded.errors", hasItem("age: must not be null"))
                .assertThat().body("_embedded.errors", hasItem("gender: must not be null"))
                .assertThat().body("_embedded.errors", hasItem("username: must not be empty"))
                .assertThat().body("_embedded.errors", hasItem("phoneNumber: must not be empty"))
                .assertThat().body("_embedded.errors", hasItem("firstName: must not be empty"));
    }

}
