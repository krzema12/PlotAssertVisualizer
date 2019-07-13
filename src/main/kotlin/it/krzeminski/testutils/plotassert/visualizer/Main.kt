package it.krzeminski.testutils.plotassert.visualizer

import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    val contents = document.getElementById("contents") as HTMLDivElement
    contents.innerHTML = "Hello from Kotlin! Search param: $searchParam"
}

val searchParam: String
    get() {
        val searchParamWithQuestionMark = window.location.search
        return searchParamWithQuestionMark.substring(1)
    }
