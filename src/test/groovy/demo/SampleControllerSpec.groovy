package demo


import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class SampleControllerSpec extends Specification {

    @Inject
    @Client('/')
    HttpClient httpClient

    void 'test a null list'() {
        given:
        BlockingHttpClient client = httpClient.toBlocking()
        HttpRequest<?> request = HttpRequest.PUT('/', null)

        when:
        HttpResponse<String> response = client.exchange(request, String)

        then:
        noExceptionThrown()
        response.status() == HttpStatus.NO_CONTENT
    }

    void 'test an empty list'() {
        given:
        BlockingHttpClient client = httpClient.toBlocking()
        HttpRequest<?> request = HttpRequest.PUT('/', '[]')

        when:
        HttpResponse<String> response = client.exchange(request, String)

        then:
        noExceptionThrown()
        response.status() == HttpStatus.NOT_MODIFIED
    }

    void 'test a list of values'() {
        given:
        BlockingHttpClient client = httpClient.toBlocking()
        HttpRequest<?> request = HttpRequest.PUT('/', '["one", "two", "four"]')

        when:
        HttpResponse<String> response = client.exchange(request, String)

        then:
        noExceptionThrown()
        response.status() == HttpStatus.OK
        response.body() == "one -> two -> four"
    }

}
