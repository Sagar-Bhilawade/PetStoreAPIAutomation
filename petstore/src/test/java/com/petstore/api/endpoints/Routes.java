package com.petstore.api.endpoints;
/*
Swagger Petstore API Endpoints
This class defines the API endpoints for the Swagger Petstore application. 
It serves as a central location for managing and referencing the various routes used in the API tests. 
Each endpoint corresponds to a specific resource or action within the Petstore application, 
such as managing pets, orders, and users.
Create User(POST /user): https://petstore.swagger.io/v2/user
Get User by Username(GET /user/{username}): https://petstore.swagger.io/v2/user/{username}
Update User(PUT /user/{username}): https://petstore.swagger.io/v2/user/{username}
Delete User(DELETE /user/{username}): https://petstore.swagger.io/v2/user/{username}
*/
public class Routes {
    
    public static String base_url = "https://petstore.swagger.io/v2";
    // User Endpoints
    public static String create_user = base_url + "/user";
    public static String get_user = base_url + "/user/{username}";
    public static String update_user = base_url + "/user/{username}";
    public static String delete_user = base_url + "/user/{username}";
    public static String login_user = base_url + "/user/login";
    public static String logout_user = base_url + "/user/logout";
    public static String create_users_with_array = base_url + "/user/createWithArray";
    public static String create_users_with_list = base_url + "/user/createWithList";
    // Store Endpoints
    public static String get_inventory = base_url + "/store/inventory";
    public static String place_order = base_url + "/store/order";
    public static String get_order = base_url + "/store/order/{orderId}";
    public static String delete_order = base_url + "/store/order/{orderId}";
    // Pet Endpoints
    public static String add_pet = base_url + "/pet";
    public static String get_pet = base_url + "/pet/{petId}";
    public static String update_pet = base_url + "/pet";
    public static String delete_pet = base_url + "/pet/{petId}";
    public static String find_pets_by_status = base_url + "/pet/findByStatus";
    public static String find_pets_by_tags = base_url + "/pet/findByTags";
    public static String upload_image = base_url + "/pet/{petId}/uploadImage";

}
