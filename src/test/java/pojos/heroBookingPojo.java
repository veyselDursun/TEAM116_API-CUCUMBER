package pojos;

public class heroBookingPojo {

    /*
    {
    "bookingid": 4979,
    "booking": {
        "firstname": "Ahmet",
        "lastname": "Bulut",
        "totalprice": 500,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2021-06-01",
            "checkout": "2021-06-10"
        },
        "additionalneeds": "wi-fi"
    }
}
     */


    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;

    private heroBookingdatesPojo bookingdates;
    private String additionalneeds;
}
