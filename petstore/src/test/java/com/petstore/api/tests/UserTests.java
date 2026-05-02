package com.petstore.api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    public Logger logger;

    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
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
        logger.info("Test data initialized for user: {}", userPayload.getUsername());

    }

    @Test(priority = 1)
    public void testCreateUser() {
        logger.info("Creating user with username: {}", userPayload.getUsername());
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        logger.info("Create user response status: {}", response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User created successfully: {}", userPayload.getUsername());

    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUserByUsername() {
        logger.info("Fetching user by username: {}", userPayload.getUsername());
        Response response = UserEndPoints.getUser(userPayload.getUsername());
        response.then().log().all();
        logger.info("Get user response status: {}", response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User fetched successfully: {}", userPayload.getUsername());
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        logger.info("Updating user details for username: {}", userPayload.getUsername());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().all();
        logger.info("Update user response status: {}", response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Verifying updated details for username: {}", userPayload.getUsername());
        Response getResponse = UserEndPoints.getUser(userPayload.getUsername());
        logger.info("Verification response status: {}", getResponse.getStatusCode());
        Assert.assertEquals(getResponse.jsonPath().getString("firstName"), userPayload.getFirstName());
        Assert.assertEquals(getResponse.jsonPath().getString("lastName"), userPayload.getLastName());
        Assert.assertEquals(getResponse.jsonPath().getString("email"), userPayload.getEmail());
        logger.info("User details updated successfully for username: {}", userPayload.getUsername());
    }

    @Test(priority = 4, dependsOnMethods = "testCreateUser")
    public void testLoginUser() {
        logger.info("Logging in user: {}", userPayload.getUsername());
        Response response = UserEndPoints.loginUser(userPayload.getUsername(), userPayload.getPassword());
        response.then().log().all();
        logger.info("Login response status: {}", response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User logged in successfully: {}", userPayload.getUsername());
    }

    @Test(priority = 5, dependsOnMethods = "testLoginUser")
    public void testLogoutUser() {
        logger.info("Logging out current user session");
        Response response = UserEndPoints.logoutUser();
        response.then().log().all();
        logger.info("Logout response status: {}", response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User logged out successfully");
    }

    @Test(priority = 6, dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {
        logger.info("Deleting user: {}", userPayload.getUsername());
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();
        logger.info("Delete user response status: {}", response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Verifying deletion for username: {}", userPayload.getUsername());
        Response getResponse = UserEndPoints.getUser(userPayload.getUsername());
        getResponse.then().log().all();
        logger.info("Post-delete fetch response status: {}", getResponse.getStatusCode());
        Assert.assertEquals(getResponse.getStatusCode(), 404);
        logger.info("User deleted successfully: {}", userPayload.getUsername());
    }

}
