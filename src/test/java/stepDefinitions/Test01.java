package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Test01 {


    static RequestSpecification spec;

    public static void main(String[] args) {

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


        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build(); // ana sayfa oluşturuldu

        spec.pathParams("pp1","booking");// parametreler oluşturuldu

        String fullPath = "/{pp1}"; // parametre girişi için kolay bir string oluşturuldu


        JSONObject innerBody = new JSONObject();  // kucuk objemiz
        innerBody.put("checkin","2021-06-01");
        innerBody.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject(); // ana request body oluşturuldu
        reqBody.put("firstname","Ahmet");
        reqBody.put("lastname","Bulut");
        reqBody.put("totalprice",500);
        reqBody.put("depositpaid",false);
        reqBody.put("bookingdates",innerBody);
        reqBody.put("additionalneeds","wi-fi");



        Response response = given()
                .contentType(ContentType.JSON)  // gönderdiğim veriler json formatında
                .spec(spec) // olşturduğum ( spec isimli obje )base url ve parametreleri kullanacağım
                .headers(
                        "Content-Type", ContentType.JSON,  // gönderdiğim bilgilerJson formatında
                        "Accept",ContentType.JSON       // bana cevabı ( response) Json formatında gönder
                )
                .when()
                .body(reqBody.toString())  // request içinde body varsa
                .log().all()            // oluşturuduğumuz requesti toplu halde görmek için
                .post(fullPath);        // parametrelerle beraber, request type girişi


        System.out.println("************ Response **************");
        response.prettyPrint();

        //              status code’unun 200,
        //            ve content type’inin application-json, ve response body’sindeki
        //            “firstname”in,“Ahmet”, ve “lastname”in, “Bulut”,
        //            ve “totalprice”in,500,
        //            ve “depositpaid”in,false,
        //            ve “checkin” tarihinin 2021-06-01 ve “checkout” tarihinin 2021-06-10 ve “additionalneeds”in,“wi-fi” olduğunu test edin (edited)
        //

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname",equalTo("Ahmet"))
                .body("booking.lastname",equalTo("Bulut"))
                .body("booking.totalprice",equalTo(500))
                .body("booking.depositpaid",equalTo(false))
                .body("booking.bookingdates.checkin",equalTo("2021-06-01"))
                .body("booking.bookingdates.checkout",equalTo("2021-06-10"))
                .body("booking.additionalneeds",equalTo("wi-fi"))
        ;





    }
}