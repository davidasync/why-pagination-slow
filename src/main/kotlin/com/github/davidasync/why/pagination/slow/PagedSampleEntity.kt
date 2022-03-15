/*
 * Copyright (c) 2022 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.davidasync.why.pagination.slow

import org.springframework.data.domain.Page

data class PagedSampleEntity(
    val latency: Long,
    val data: Page<SampleEntity>,
)