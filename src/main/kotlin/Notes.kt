package org.example

data class Notes(
    val noteID: Int,
    val ownerID: Int,
    var title: String,
    var text: String?,
    var comments: MutableList<Comments> = mutableListOf(),
    var privacy: Int, //0 — все пользователи, 1 — только друзья, 2 — друзья и друзья друзей, 3 — только пользователь
    var commentPrivacy: Int, //0 — все пользователи, 1 — только друзья, 2 — друзья и друзья друзей, 3 — только пользователь
    var privacyView: String,
    var privacyComment: String
)

