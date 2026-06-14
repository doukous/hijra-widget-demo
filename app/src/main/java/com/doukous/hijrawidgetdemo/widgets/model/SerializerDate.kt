package com.doukous.hijrawidgetdemo.widgets.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException

import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object DateSerializer : Serializer<DateState> {
    override val defaultValue: DateState = DateState(date = "")

    override suspend fun readFrom(input: InputStream): DateState =
        try {
            Json.decodeFromString<DateState>(
                input.readBytes().decodeToString()
            )
        }

        catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read DateState", serialization)
        }

    override suspend fun writeTo(t: DateState, output: OutputStream) {
        output.write(
            Json.encodeToString(t)
                .encodeToByteArray()
        )
    }
}