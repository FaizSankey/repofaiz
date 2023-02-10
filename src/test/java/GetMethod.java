import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetMethod {
    @Test
    void Get(){
        Response response = get("https://fakestoreapi.com/products");
        System.out.println(response.getBody().asString());

//        given()
//                .get("https://reqres.in/api/users?page=2")
//                .then()
//                .statusCode(200)
//                .body("data.id[1]",equalTo(8));
    }

    @Test
    void GetEqual(){
        given()
                .get("https://fakestoreapi.com/products")
                .then()
                .statusCode(200)
                .body("rating.rate[0]",equalTo(3.9F));
    }

    @Test
    void GetTest() {
        Response response = get("https://fakestoreapi.com/products");
//        response.then().body("price[0]",equalTo(109.95F));
//        response.then().body("price",hasItem(109.95F));
//        response.then().body("rating.rate[0]",equalTo(3.9F));

//        response.then().body("price[0]",equalTo(109.95F))
//                .body("rating.rate[0]",equalTo(3.9F))
//                .log().body().extract().response();
//        response.then().body("response",equalTo(20));
        System.out.println(response.getStatusLine());
    }

    @Test
    public void getCount(){
        baseURI = "https://fakestoreapi.com";
        Response response = get("/products");
        System.out.println("JSONPATH" + response.jsonPath().prettify());
        List count = response.jsonPath().get("id");
        System.out.println("************COUNT********** " + count.size());
    }

    @Test
    void GetContain(){
        given()
                .get("https://fakestoreapi.com/products")
                .then()
                .body("category",hasItem("men's clothing"));
    }

    @Test
    void Post(){
        Map<String,Object> rating = new HashMap<>();
        rating.put("rate",3.4);
        rating.put("count",120);

        Map<String,Object> data = new HashMap<>();
        data.put("title","test");
        data.put("price",100);
        data.put("description","test prod");
        data.put("category","test prod shoe");
        data.put("image","https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        data.put("rating",rating);

        System.out.println(data);

        given().
            body(data).
            contentType("application/json").
        when().
            post("https://fakestoreapi.com/products").
        then().
            statusCode(200).
            log().body().extract().response();
    }

    @Test
    void PostMultiple(){
        Map<String,Object> user1 = new HashMap<>();
        user1.put("name","faiz");
        user1.put("job","test1");

        Map<String,Object> user2 = new HashMap<>();
        user2.put("name","sayas");
        user2.put("job","test2");

        Map<String,Object> user3 = new HashMap<>();
        user3.put("name","vijay");
        user3.put("job","test3");

        List<Map<String,Object>> payload = new ArrayList<>();
        payload.add(user1);
        payload.add(user2);
        payload.add(user3);

//        Gson gson = new Gson();
//        String ConvertedUser = gson.toJson(payload);

        given().
                body(payload).
                contentType("application/json").
                when().post("https://reqres.in/api/users").
                then().
                assertThat().
                statusCode(201).
                log().body().extract().response();

    }



}
