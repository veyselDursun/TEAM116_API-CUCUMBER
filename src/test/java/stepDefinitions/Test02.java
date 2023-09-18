package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Test02 {

    static RequestSpecification spec;

    @Test
    public void test01(){

        // api/opdList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
        // dönen status code'un 200 oldugu
        // ve response message bilgisinin "Success" oldugu dogrulanmali

        String token = "Wbg6prp8qsGoOjfnY5nerVhTikDGDv";

        spec = new RequestSpecBuilder().setBaseUri("http://www.heallifehospital.com").build();

        spec.pathParams("pp1","api","pp2","opdList");

        String fullPath = "{pp1}/{pp2}";

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .headers(
                        "Authorization","Bearer "+token,
                        "Content-Type",ContentType.JSON,
                        "Accept", ContentType.JSON)
                .when()

                .log().all()
                .get(fullPath);

        System.out.println("***********************RESPONSE*********************");

        response.prettyPrint();



    }
}
