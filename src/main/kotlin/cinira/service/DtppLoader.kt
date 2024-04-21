package cinira.service

import org.springframework.core.io.Resource
import java.time.Instant
import java.time.Period.ofDays

class DtppLoader {

    companion object {
        private val cycleLength = ofDays(56)

        fun load(at: Instant, base: String, resolver: (String) -> Array<Resource>): Array<Resource> =
            "$base${cycleCandidatePrefix(at, cycleLength)}".let { prefix ->
                val byName = resolver(prefix).associateBy(Resource::getFilename)
                if (!byName.containsKey("metafile.json.bz2")) {
                    emptyArray()
                } else {

                    /* Is it current? Does the set of resolved files include all segments? */
                    emptyArray()
                }
            }
    }
}