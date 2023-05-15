package demo;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;

import java.util.ArrayList;

@Controller
public class SampleController {

    @Put
    public HttpResponse<String> handlePut(@Nullable @Body ArrayList<String> values) {
        if (values == null) {
            return HttpResponse.noContent();
        } else if (values.isEmpty()) {
            return HttpResponse.notModified();
        } else {
            return HttpResponse.ok(String.join(" -> ", values));
        }
    }
}
