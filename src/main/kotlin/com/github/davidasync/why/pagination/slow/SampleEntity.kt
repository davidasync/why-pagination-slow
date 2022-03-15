/*
 * Copyright (c) 2022 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.davidasync.why.pagination.slow

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.persistence.Table

@Entity
@Table(name = "sample_table")
data class SampleEntity(
    @Id
    @GeneratedValue
    val id: Int = 0,

    @Column(nullable = false)
    val column1: String = "",

    @Column(nullable = false)
    val column2: String = "",

    @Column(nullable = false)
    val column3: String = "",

    @Column(nullable = false)
    val column4: String = "",

    @Column(nullable = false)
    val column5: String = "",

    @Column(nullable = false)
    val column6: String = "",

    @Column(nullable = false)
    val column7: String = "",

    @Column(nullable = false)
    val column8: String = "",

    @Column(nullable = false)
    val column9: String = "",

    @Column(nullable = false)
    var createdAt: Long = 0L,

    @Column(nullable = false)
    var updatedAt: Long = 0L,
) {
    @PreUpdate
    @PrePersist
    private fun onUpdate() {
        updatedAt = System.currentTimeMillis()
    }
}
