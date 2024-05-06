package org.example

data class Comments(
    val commentID: Int,
    val ownerID: Int,
    var message: String,
    var isDeleted: Boolean = false
)
