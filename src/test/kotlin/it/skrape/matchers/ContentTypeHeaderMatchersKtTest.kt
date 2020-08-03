package it.skrape.matchers

import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@Execution(CONCURRENT)
internal class ContentTypeHeaderMatchersKtTest {

    @ParameterizedTest(name = "can match content type {0}")
    @EnumSource(ContentTypes::class)
    fun `can match exact content types from string`(contentType: ContentTypes) {
        val returnedContentTypeValue = contentType.value `to be` contentType
        "${contentType.value}foo" `to be not` contentType
        expectThat(returnedContentTypeValue).isEqualTo(contentType.value)
    }

    @ParameterizedTest(name = "can match partial content type {0}")
    @EnumSource(ContentTypes::class)
    fun `can match partial content types from string`(contentType: ContentTypes) {
        "${contentType.value}foo" `to contain` contentType
        "${contentType.value}bar" toContain contentType
    }
}
