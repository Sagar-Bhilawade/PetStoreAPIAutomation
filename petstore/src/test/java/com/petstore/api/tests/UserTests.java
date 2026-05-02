package com.petstore.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import com.petstore.api.endpoints.UserEndPoints;
import com.petstore.api.payload.User;

import io.restassured.response.Response;

public class UserTests {
    Faker faker;
    User userPayload;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUserStatus(1);
    }

    @Test(priority = 1)
    public void testCreateUser() {
        // Implement the test for creating a user
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUserByUsername() {
        // Implement the test for getting a user by username
        Response response = UserEndPoints.getUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        // Implement the test for updating a user
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        // Verify that the user details are updated
        Response getResponse = UserEndPoints.getUser(userPayload.getUsername());
        Assert.assertEquals(getResponse.jsonPath().getString("firstName"), userPayload.getFirstName());
        Assert.assertEquals(getResponse.jsonPath().getString("lastName"), userPayload.getLastName());
        Assert.assertEquals(getResponse.jsonPath().getString("email"), userPayload.getEmail());
    }

    @Test(priority = 4, dependsOnMethods = "testCreateUser")
    public void testLoginUser() {
        // Implement the test for logging in a user
        Response response = UserEndPoints.loginUser(userPayload.getUsername(), userPayload.getPassword());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5, dependsOnMethods = "testLoginUser")
    public void testLogoutUser() {
        // Implement the test for logging out a user
        Response response = UserEndPoints.logoutUser();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6, dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {
        // Implement the test for deleting a user
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        // Verify that the user is deleted
        Response getResponse = UserEndPoints.getUser(userPayload.getUsername());
        getResponse.then().log().all();
        Assert.assertEquals(getResponse.getStatusCode(), 404);
    }

}
