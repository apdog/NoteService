package org.example

object NoteService {
    var notes: MutableList<Notes> = mutableListOf()
    var comments: MutableList<Comments> = mutableListOf()

    fun add(note: Notes): Notes {
        val newNote = note.copy(noteID = notes.size + 1)
        newNote.comments = mutableListOf()
        notes += newNote
        return newNote
    }

    fun createComment(noteId: Int, comment: Comments): Comments {
        val note = getById(noteId) ?: throw ObjectNotFoundException("Заметка с ID $noteId не найдена")
        val newComment = comment.copy(commentID = note.comments.size + 1)
        comments += newComment
        note.comments.add(newComment)
        return newComment
    }

    fun delete(noteId: Int): Int {
        getById(noteId) ?: throw ObjectNotFoundException("Заметка с ID $noteId не найдена")
        notes.removeAt(noteId - 1)
        return 1
    }

    fun deleteComment(noteId: Int, commentId: Int): Int {
        val note = getById(noteId) ?: throw ObjectNotFoundException("Заметка с ID $noteId не найдена")
        val commentToDelete = note.comments.find { it.commentID == commentId }
            ?: throw ObjectNotFoundException("Комментарий с ID $commentId в заметке $noteId не найден")
        commentToDelete.isDeleted = true
        return 1
    }

    fun edit(newNote: Notes): Int {
        val note = getById(newNote.noteID) ?: throw ObjectNotFoundException("Заметка с ID ${newNote.noteID} не найдена")
        notes[note.noteID - 1] = newNote.copy()
        return 1
    }

    fun editComment(noteId: Int, comment: Comments): Int {
        val note = getById(noteId) ?: throw ObjectNotFoundException("Заметка с ID $noteId не найдена")
        val index = note.comments.indexOfFirst { it.commentID == comment.commentID }
        if (index == -1) {
            throw ObjectNotFoundException("Комментарий с ID ${comment.commentID} в заметке $noteId не найден")
        }
        if (!comment.isDeleted) {
            note.comments[index] = comment.copy()
            return 1
        } else {
            throw ObjectNotFoundException("Комментарий с ID ${comment.commentID} удален")
        }

    }

    fun get(): List<Notes> {
        return notes.toList()
    }

    fun getById(noteId: Int): Notes? {
        return notes.find { it.noteID == noteId }
    }

    fun getComments(noteId: Int): List<Comments> {
        val note = getById(noteId) ?: throw ObjectNotFoundException("Заметка с ID $noteId не найдена")
        return note.comments.toList()
    }

    fun printNotes() {
        val allNotes = get()
        for (note in allNotes) {
            println(
                "Заметка №: ${note.noteID}\n" +
                        "Заголовок: ${note.title}\n" +
                        "${note.text}\n" +
                        "Комментариев: ${note.comments.count { !it.isDeleted }}"
            )
            note.comments.forEach { comment ->
                if (!comment.isDeleted) {
                    println(" - Комментарий ${comment.commentID} пользователя ${comment.ownerID}, Текст: ${comment.message}")
                }
            }
        }
    }

    fun clearNotes() {
        notes.clear()
    }

    fun clearComments() {
        comments.clear()
    }
}