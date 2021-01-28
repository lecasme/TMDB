package com.tmdb.tv

import com.tmdb.tv.domain.utils.toDate
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExtensionsUnitTest {

    val dateString = "1991-07-19"

    @Test
    fun toDate() {
        assertThat(dateString.toDate(), instanceOf(Date()::class.java))
    }

}