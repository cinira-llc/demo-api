package cinira.service

import org.apache.commons.lang3.StringUtils.getCommonPrefix
import java.time.Instant
import java.time.ZoneOffset.UTC
import java.time.format.DateTimeFormatter.ofPattern
import java.time.temporal.TemporalAmount

fun cycleCandidatePrefix(at: Instant, length: TemporalAmount): String =
    at.atZone(UTC).toLocalDate().let { date ->
        getCommonPrefix(date.minus(length).format(YEAR_MONTH), date.plus(length).format(YEAR_MONTH))
    }

private val YEAR_MONTH = ofPattern("YYMM")

