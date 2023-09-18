package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Test04Pojo {
     /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body’ye sahip bir POST
    request gonderdigimizde
    {
      “firstname” : “Ahmet”,
      “lastname” : “Bulut”,
      “totalprice” : 500,
      “depositpaid” : false,
      “bookingdates” : {
                        “checkin” : “2021-06-01”,
                        “checkout” : “2021-06-10”
                        },
       “additionalneeds” : “wi-fi”
    }
                donen Response’un,
                status code’unun 200,
                ve content type’inin application-json, ve response body’sindeki
                “firstname”in,“Ahmet”, ve “lastname”in, “Bulut”,
                ve “totalprice”in,500,
                ve “depositpaid”in,false,
                ve “checkin” tarihinin 2021-06-01 ve “checkout” tarihinin 2021-06-10 ve “additionalneeds”in,“wi-fi” olduğunu test edin (edited)
         */

    static RequestSpecification spec;

    @Test
    public void Test01(){

        spec = new RequestSpecBuilder().setBaseUri("http://www.heallifehospital.com").build();

        spec.pathParams("pp1","api","pp2","ipdList");

        String fullPath = "{pp1}/{pp2}";
        JSONObject innerBody = new JSONObject();
        innerBody.put("checkin","2021-06-01");
        innerBody.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname","Ahmet");
        reqBody.put("lastname","Bulut");
        reqBody.put("totalprice",500);
        reqBody.put("depositpaid",false);
        reqBody.put("bookingdates",innerBody);
        reqBody.put("additionalneeds","wi-fi");

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .headers("Content-Type",ContentType.JSON,
                        "Accept", ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .log().all()
                .post(fullPath);

        System.out.println("***********************RESPONSE*********************");

        response.prettyPrint();


    }
}
