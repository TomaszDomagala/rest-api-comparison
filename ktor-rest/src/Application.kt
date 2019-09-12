package com.example

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.jackson.jackson
import java.util.*

data class Snippet(val text: String)

data class PostSnippet(val snippet: Text) {
    data class Text(val text: String)
}

val snippets: MutableList<Snippet> = Collections.synchronizedList(
    mutableListOf(
        Snippet("hello"),
        Snippet("world")
    )
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON
        }
    }

    routing {
        get("/") {
            call.respondText("Hello, Ktor!", ContentType.Text.Html)
        }

        route("/snippets") {
            get {
                call.respond(mapOf("snippets" to synchronized(snippets) { snippets.toList() }))
            }
            post {
                val post = call.receive<PostSnippet>()
                snippets += Snippet(post.snippet.text)
                call.respond(mapOf("OK" to true))
            }
        }

    }
}