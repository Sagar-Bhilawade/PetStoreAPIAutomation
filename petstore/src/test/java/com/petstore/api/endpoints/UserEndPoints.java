package com.petstore.api.endpoints;

import com.petstore.api.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

/*
User Endpoints
This class defines the API endpoints related to user management in the Swagger Petstore application.
*/
public class UserEndPoints {

    // Create User(POST /user)
    public static Response createUser(User user) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post(Routes.create_user);

    }

    // Get User by Username(GET /user/{username}):
    public static Response getUser(String username) {

        return given()
                .pathParam("username", username)
                .when()
                .get(Routes.get_user);

    }

    // Update User(PUT /user/{username})
    public static Response updateUser(String username, User user) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(user)
                .when()
                .put(Routes.update_user);

    }

    // Delete User(DELETE /user/{username})
    public static Response deleteUser(String username) {

        return given()
                .pathParam("username", username)
                .when()
                .delete(Routes.delete_user);

    }

    // Login User(GET /user/login)
    public static Response loginUser(String username, String password) {

        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(Routes.login_user);

    }

    // Logout User(GET /user/logout)
    public static Response logoutUser() {
        return when()
                .get(Routes.logout_user);

    }

    // Create Users with Array(POST /user/createWithArray)
    public static Response createUsersWithArray(User[] users) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(users)
                .when()
                .post(Routes.create_users_with_array);

    }

    // Create Users with List(POST /user/createWithList)
    public static Response createUsersWithList(java.util.List<User> users) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(users)
                .when()
                .post(Routes.create_users_with_list);

    }

    // Additional user-related endpoints can be added here as needed

}
