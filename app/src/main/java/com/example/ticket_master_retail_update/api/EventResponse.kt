package com.example.ticket_master_retail_update.api

data class EventResponse(
    val _embedded: EmbeddedEvents?
)

data class EmbeddedEvents(
    val events: List<Event>?
)

data class Event(
    val name: String,
    val type: String,
    val id: String,
    val url: String,
    val locale: String,
    val images: List<EventImage>,
    val dates: EventDates,
    val priceRanges: List<PriceRange>? = null
)

data class EventImage(
    val ratio: String,
    val url: String,
    val width: Int,
    val height: Int,
    val fallback: Boolean
)

data class EventDates(
    val start: EventStart,
    val timezone: String,
    val status: EventStatus,
    val spanMultipleDays: Boolean
)

data class EventStart(
    val localDate: String,
    val localTime: String,
    val dateTime: String,
    val dateTBD: Boolean,
    val dateTBA: Boolean,
    val timeTBA: Boolean,
    val noSpecificTime: Boolean
)

data class EventStatus(
    val code: String
)

data class PriceRange(
    val type: String,
    val currency: String,
    val min: Double,
    val max: Double
)
