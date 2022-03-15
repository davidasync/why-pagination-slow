/*
 * Copyright (c) 2022 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.davidasync.why.pagination.slow

import com.github.javafaker.Faker
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@RestController
class SampleController(private val sampleRepository: SampleRepository) {
    val faker = Faker()
    val logger = LoggerFactory.getLogger(SampleController::class.java)

    @GetMapping
    suspend fun getSample(
        @RequestParam(required = false, defaultValue = "0") @PositiveOrZero page: Int,
        @RequestParam(required = false, defaultValue = "10") @Positive size: Int,
    ): PagedSampleEntity {
        val pageRequest = PageRequest.of(page, size)

        val t1 = System.currentTimeMillis()
        val result = sampleRepository.findAll(pageRequest)
        val latency = System.currentTimeMillis() - t1

        return PagedSampleEntity(
            latency = latency,
            data = result
        )
    }

    @GetMapping("/init-data")
    suspend fun getSample(
        @RequestParam(required = false, defaultValue = "1000") @Positive count: Int,
    ): String = coroutineScope {
        repeat(count / 1000) {
            val samples = mutableListOf<SampleEntity>()

            repeat(1000) {
                val newSample = SampleEntity(
                    column1 = faker.name().name(),
                    column2 = faker.pokemon().name(),
                    column3 = faker.music().genre(),
                    column4 = faker.leagueOfLegends().rank(),
                    column5 = faker.stock().nsdqSymbol(),
                    column6 = faker.superhero().name(),
                    column7 = faker.team().name(),
                    column8 = faker.job().title(),
                    column9 = faker.zelda().character(),
                )

                samples.add(newSample)
            }

            logger.info("[$it/$count]")
            sampleRepository.saveAll(samples)
        }


        return@coroutineScope "DONE"
    }
}