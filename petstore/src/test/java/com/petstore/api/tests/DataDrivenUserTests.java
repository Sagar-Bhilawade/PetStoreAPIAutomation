package com.petstore.api.tests;

import org.testng.annotations.Test;

import com.petstore.api.endpoints.UserEndPoints;
import com.petstore.api.payload.User;

import io.restassured.response.Response;

public class DataDrivenUserTests {
    
    @Test(priority = 1, dataProvider = "getUserData", dataProviderClass = com.petstore.api.utils.DataProviders.class)
    public void testCreateUser(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
        User user = new User();
        System.out.println("Creating user with username: " + username);
        user.setId(parseRequiredInt(id, "id", username));
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);   
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(parseRequiredInt(userStatus, "userStatus", username));
       Response response = UserEndPoints.createUser(user);
        response.then().log().all().assertThat().statusCode(200);
       
    }

    private int parseRequiredInt(String value, String fieldName, String username) {
        String normalizedValue = value == null ? "" : value.trim();
        if (normalizedValue.isEmpty()) {
            throw new IllegalArgumentException(
                "Missing numeric value for '" + fieldName + "' in test data row for username '" + username + "'.");
        }

        return Integer.parseInt(normalizedValue);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser", dataProvider = "UserNames", dataProviderClass = com.petstore.api.utils.DataProviders.class)
    public void testGetUserByName(String username) {
        Response response = UserEndPoints.getUser(username);
        response.then().log().all().assertThat().statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = "testGetUserByName", dataProvider = "UserNames", dataProviderClass = com.petstore.api.utils.DataProviders.class)
    public void testDeleteUser(String username) {
        System.out.println("Deleting user with username: " + username);
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all().assertThat().statusCode(200);
        // Verify deletion by attempting to retrieve the user again
        Response getResponse = UserEndPoints.getUser(username);
        getResponse.then().log().all().assertThat().statusCode(404);
    }

}
