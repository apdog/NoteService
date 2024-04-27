package org.example

fun main() {

    val note1 = Notes(
        noteID = 0,
        ownerID = 2311,
        title = "Абракадабра",
        text = "Не используйте данное заклинание против свиномедведов!",
        comments = mutableListOf(),
        privacy = 0,
        commentPrivacy = 0,
        privacyView = "Not",
        privacyComment = "Not"
    )

    val comment1 = Comments(
        commentID = 1,
        ownerID = 5221,
        message = "Все это фигня!!"

    )

    val comment2 = Comments(
        commentID = 2,
        ownerID = 345,
        message = "А мне кажется так и нужно!"

    )

    val note2 = Notes(
        noteID = 1,
        ownerID = 2264,
        title = "Поменяли 1ю заметку",
        text = "Блабла",
        comments = mutableListOf(comment1, comment2),
        privacy = 0,
        commentPrivacy = 0,
        privacyView = "Not",
        privacyComment = "Not"
    )

    val updatedComment = Comments(1, 5221, "Я поменял комментарий")

    with(NoteService) {
        add(note1)
        add(note2)
        createComment(1, comment1)
        createComment(1, comment2)
////        edit(note2)
////        delete(1)
        printNotes()
//        deleteComment(1,1)
//        printNotes()
//        editComment(1, updatedComment)
//        val comments = getComments(1) // Получить комментарии первой заметки
//        for (comment in comments) {
//            println("Комментарий ${comment.commentID}: ${comment.message}")
//        }


    }

}

