/*import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import com.stripe.param.*;
import models.*;
import services.*;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Stripe.apiKey = "sk_test_51Oo7aQLjJljsz2MFpuJDsDFI9BXxos0TFBtXvLTgy0ycVNuHsv7ywF2IoRmrTmglV4OeFtCBeIzjeYsBAghDsULh00jbXIVHgu";

        try {

            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(500L)
                            .setCurrency("gbp")
                            .setPaymentMethod("pm_card_visa")
                            .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);


                  } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
*/