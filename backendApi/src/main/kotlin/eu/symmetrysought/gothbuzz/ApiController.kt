package eu.symmetrysought.gothbuzz

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/")
class ApiController {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Produces(MediaType.TEXT_HTML)
    @Get("lizard/wizard")
    fun getLizardWizard(request: HttpRequest<*>): HttpResponse<*> {
        val body = "<html><head><title>api lizard</title><body>API wizard - laggdax</body></html>"
        logger.info("Logger lizard!")
        return HttpResponse.ok(body).contentType(MediaType.TEXT_HTML)
    }
}