package cinira.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "primary_P_F_base_Airport - Approach Procedures")
data class ApproachProcedure(

    @get:Id
    @get:Column(name = "_id")
    var id: Int,
    var fixIdentifier: String,
    var fixSubSectionCode: String,
    var landingFacilityIcaoIdentifier: String,
    var magneticCourse: Int,
    var rho: Int,
    var routeDistanceHoldingDistanceOrTime: Int,
    var routeType: String,
    var sequenceNumber: Int,
    var sidStarApproachIdentifier: String,
    var theta: Int,
    var transitionIdentifier: String
)

/**
 * sidStarApproachIdentifier
 *
 * SIDSTARApproachIdentifier:
 * Bxx=Backcourse
 * Dxx=VOR/DME
 * Hxx=RNAV(RNP)
 * Ixx=ILS
 * Lxx=LOC
 * Nxx=NDB
 * Pxx=GPS
 * Qxx=NDB
 * Rxx=RNAV
 * Sxx=VOR
 * Uxx=SDF
 * Xxx=LDA
 *
 * circling only:
 * GPS-x
 * LBC-x
 * LOC-x
 * NDB-x
 * RNV-x
 * VDM-x
 * VOR-x
 *
 */