package com.ivzb.irish_rail.data

import com.ivzb.irish_rail.data.trains.TrainsDataSource
import com.ivzb.irish_rail.model.ui.station.Station
import com.ivzb.irish_rail.model.ui.station.StationDetails
import com.ivzb.irish_rail.model.ui.train.TrainMovement
import com.ivzb.irish_rail.model.ui.train.TrainPosition
import com.ivzb.irish_rail.model.ui.train.TrainStatus

object TestData {

    val searchQuery = "Dublin"

    val trainPosition = TrainPosition(
        code = "B797",
        date = "16 Apr 2020",
        status = TrainStatus.NotRunningYet,
        message = "B797\nEnnis to Limerick\nExpected Departure 19:50",
        direction = "To Limerick"
    )

    val trainPositions: List<TrainPosition> = listOf(
        TrainPosition(
            code = "B797",
            date = "16 Apr 2020",
            status = TrainStatus.NotRunningYet,
            message = "B797\nEnnis to Limerick\nExpected Departure 19:50",
            direction = "To Limerick"
        ),
        TrainPosition(
            code = "A226",
            date = "16 Apr 2020",
            status = TrainStatus.Running,
            message = "A226\n20:00 - Dublin Heuston to Cork (-81 mins late)\nDeparted Killarney Junction next stop Cork",
            direction = "To Cork"
        ),
        TrainPosition(
            code = "A229",
            date = "16 Apr 2020",
            status = TrainStatus.Running,
            message = "A229\n19:25 - Cork to Dublin Heuston (-1 mins late)\nDeparted Mallow next stop Charleville",
            direction = "To Dublin Heuston"
        )
    )

    val trainMovement = TrainMovement(
        code = "A229",
        date = "26 Apr 2020",
        origin = "Cork",
        destination = "Dublin Heuston",
        locationCode = "CORK",
        locationName = "Cork",
        locationOrder = 1,
        scheduledArrival = "00:00:00",
        scheduledDeparture = "19:25:00",
        expectedArrival = "00:00:00",
        expectedDeparture = "19:25:00"
    )

    val trainMovements: Map<String, List<TrainMovement>> = mapOf(
        "A229" to listOf(
            TrainMovement(
                code = "A229",
                date = "26 Apr 2020",
                origin = "Cork",
                destination = "Dublin Heuston",
                locationCode = "CORK",
                locationName = "Cork",
                locationOrder = 1,
                scheduledArrival = "00:00:00",
                scheduledDeparture = "19:25:00",
                expectedArrival = "00:00:00",
                expectedDeparture = "19:25:00"
            ),
            TrainMovement(
                code = "A229",
                date = "26 Apr 2020",
                origin = "Cork",
                destination = "Dublin Heuston",
                locationCode = "RTPCN",
                locationName = "Rathpeacon",
                locationOrder = 2,
                scheduledArrival = "19:30:00",
                scheduledDeparture = "19:30:00",
                expectedArrival = "19:30:00",
                expectedDeparture = "19:30:00"
            ),
            TrainMovement(
                code = "A229",
                date = "26 Apr 2020",
                origin = "Cork",
                destination = "Dublin Heuston",
                locationCode = "HSTON",
                locationName = "Dublin Heuston",
                locationOrder = 41,
                scheduledArrival = "21:15:00",
                scheduledDeparture = "00:00:00",
                expectedArrival = "21:14:00",
                expectedDeparture = "00:00:00"
            )
        )
    )

    val station = Station(id = 30, code = "CORK", name = "Cork")

    val stations: List<Station> = listOf(
        Station(id = 30, code = "CORK", name = "Cork"),
        Station(id = 1517, code = "CENTJ", name = "Dublin Belfast"),
        Station(id = 40, code = "LMRCK", name = "Limerick")
    )

    val stationDetail = StationDetails(
        trainCode = "D250",
        stationName = "Cork",
        stationCode = "CORK",
        date = "26 Apr 2020",
        time = "20:51:57",
        expectedArrival = "00:00",
        expectedDeparture = "21:00",
        scheduledArrival = "00:00",
        scheduledDeparture = "21:00",
        direction = "To Cobh",
        originName = "Cork",
        originTime = "21:00",
        destinationName = "Cobh",
        destinationTime = "21:24"
    )

    val stationDetails: Map<String, List<StationDetails>> = mapOf(
        "CORK" to listOf(
            StationDetails(
                trainCode = "D250",
                stationName = "Cork",
                stationCode = "CORK",
                date = "26 Apr 2020",
                time = "20:51:57",
                expectedArrival = "00:00",
                expectedDeparture = "21:00",
                scheduledArrival = "00:00",
                scheduledDeparture = "21:00",
                direction = "To Cobh",
                originName = "Cork",
                originTime = "21:00",
                destinationName = "Cobh",
                destinationTime = "21:24"
            ),
            StationDetails(
                trainCode = "P249",
                stationName = "Cork",
                stationCode = "CORK",
                date = "26 Apr 2020",
                time = "20:51:57",
                expectedArrival = "00:00",
                expectedDeparture = "20:54",
                scheduledArrival = "00:00",
                scheduledDeparture = "20:54",
                direction = "To Cork",
                originName = "Cobh",
                originTime = "20:30",
                destinationName = "Cork",
                destinationTime = "20:54"
            ),
            StationDetails(
                trainCode = "P278",
                stationName = "Cork",
                stationCode = "CORK",
                date = "26 Apr 2020",
                time = "20:51:57",
                expectedArrival = "21:08",
                expectedDeparture = "00:00",
                scheduledArrival = "21:08",
                scheduledDeparture = "00:00",
                direction = "To Cork",
                originName = "Midleton",
                originTime = "20:45",
                destinationName = "Cork",
                destinationTime = "21:08"
            )
        )
    )
}
