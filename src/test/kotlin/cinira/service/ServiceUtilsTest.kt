package cinira.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant.parse
import java.time.Period.ofDays

class ServiceUtilsTest {

    @Test
    fun `cycleCandidatePrefix() produces expected values`() {
        val length = ofDays(56)
        assertThat(cycleCandidatePrefix(parse("2023-10-31T00:00:00Z"), length)).isEqualTo("23")
        assertThat(cycleCandidatePrefix(parse("2023-12-31T00:00:00Z"), length)).isEqualTo("2")
        assertThat(cycleCandidatePrefix(parse("2024-01-01T00:00:00Z"), length)).isEqualTo("2")
        assertThat(cycleCandidatePrefix(parse("2024-03-01T00:00:00Z"), length)).isEqualTo("240")
        assertThat(cycleCandidatePrefix(parse("2024-04-20T00:00:00Z"), length)).isEqualTo("240")
    }
}