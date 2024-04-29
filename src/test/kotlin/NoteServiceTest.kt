import org.example.Comments
import org.example.NoteService.add
import org.example.NoteService.clearForTests
import org.example.NoteService.createComment
import org.example.NoteService.delete
import org.example.NoteService.deleteComment
import org.example.NoteService.edit
import org.example.NoteService.editComment
import org.example.NoteService.get
import org.example.NoteService.getComments
import org.example.NoteService.notes
import org.example.NoteService.printNotes
import org.example.NoteService.restoreComment
import org.example.Notes
import org.example.ObjectNotFoundException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class NoteServiceTest {

    @Test
    fun addTestNote() {
        // Создаем тестовый пост
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        assertNotEquals(0, notes[0].noteID)
    }

    @Test
    fun createTestComment() {
        // Создаем тестовый пост
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        add(note)
        createComment(1, comment)
        assertEquals("Тестовый комментарий", notes[0].comments[0].message)
    }

    @Test
    fun createTestCommentException() {
        // Создаем тестовый пост
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        add(note)
        createComment(1, comment)
        assertEquals("Тестовый комментарий", notes[0].comments[0].message)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun createCommentExceptionForNonExistingNote() {
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        createComment(100, comment)
    }

    @Test
    fun deleteNoteTest() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        val index = delete(1)
        assertEquals(1, index)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun deleteNoteExceptionForNonExistingNote() {
        delete(100)
    }

    @Test
    fun deleteCommentTest() {
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        add(
            note = Notes(
                noteID = 0,
                ownerID = 777,
                title = "Тестовый заголовок",
                text = "Тело заметки",
                comments = mutableListOf(),
                privacy = 0,
                commentPrivacy = 0,
                privacyView = "Not",
                privacyComment = "Not"
            )
        )
        createComment(1, comment)
        val index = deleteComment(1, 1)
        assertEquals(1, index)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun deleteCommentExceptionForNonExistingNote() {
        deleteComment(1, 1)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun findCommentToDelete() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        add(note)
        createComment(1, comment)
        deleteComment(1, 100)
    }

    @Test
    fun editNote() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        val index = edit(
            Notes(
                noteID = 1,
                ownerID = 777,
                title = "Измененный заголовок",
                text = "Тело заметки",
                comments = mutableListOf(),
                privacy = 0,
                commentPrivacy = 0,
                privacyView = "Not",
                privacyComment = "Not"
            )
        )
        assertEquals(1, index)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun editNoteExceptionForNonExistingNote() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        edit(
            Notes(
                noteID = 100,
                ownerID = 777,
                title = "Измененный заголовок",
                text = "Тело заметки",
                comments = mutableListOf(),
                privacy = 0,
                commentPrivacy = 0,
                privacyView = "Not",
                privacyComment = "Not"
            )
        )
    }

    @Test
    fun editCommentTest() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        add(note)
        createComment(1, comment)
        val updatedComment = Comments(
            commentID = 1, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        val index = editComment(
            notes[0].noteID, updatedComment
        )
        assertEquals(1, index)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun editCommentExceptionForNonExistingNote() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        val updatedComment = Comments(
            commentID = 1, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        editComment(2, updatedComment)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun editCommentExceptionForNonExistingComment() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        val updatedComment = Comments(
            commentID = 1, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        editComment(1, updatedComment)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun editCommentExceptionForDeletedComment() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        add(note)
        createComment(1, comment)
        deleteComment(1, 1)
        val updatedComment = Comments(
            commentID = 1, ownerID = 777, message = "Тестовый комментарий", isDeleted = true
        )
        editComment(1, updatedComment)
    }

    @Test
    fun getList() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val note2 = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок 2",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        add(note2)
        val list = listOf(note.copy(noteID = 1), note2.copy(noteID = 2))
        val checkList = get()
        assertEquals(list, checkList)
    }

    @Test
    fun getCommentsTest() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        val comment2 = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий2", isDeleted = false
        )
        add(note)
        createComment(1, comment)
        createComment(1, comment2)
        val list = listOf(comment.copy(commentID = 1), comment2.copy(commentID = 2))
        val checkList = getComments(1)
        assertEquals(list, checkList)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun getCommentExceptionForNonExistingNote() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        getComments(2)
    }

    @Test
    fun printNotesShouldPrintNotesWithoutComments() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Заголовок",
            text = "Текст",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        notes.add(note)
        printNotes()
    }

    @Test
    fun printNotesShouldPrintNotesWithComments() {
        val comment = Comments(1, 1, "Test Comment")
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Заголовок",
            text = "Текст",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        add(note)
        createComment(1, comment)
        printNotes()
    }

    @Test
    fun printNotesShouldSotPrintDeletedComments() {
        val deletedComment = Comments(1, 1, "Test Comment", isDeleted = true)
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Заголовок",
            text = "Текст",
            comments = mutableListOf(deletedComment),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        notes.add(note)
        printNotes()
    }

    @Test
    fun restoreCommentTest() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = true
        )
        add(note)
        createComment(1, comment)
        val check = restoreComment(1,1)
        assertEquals(1, check)
    }

    @Test(expected = ObjectNotFoundException::class)
    fun restoreCommentExceptionForNonDeletedComment() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = false
        )
        add(note)
        createComment(1, comment)
        restoreComment(1, 1)

    }

    @Test(expected = ObjectNotFoundException::class)
    fun restoreCommentExceptionForNonExistingComment() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = true
        )
        add(note)
        createComment(1, comment)
        restoreComment(1, 2)

    }

    @Test(expected = ObjectNotFoundException::class)
    fun restoreCommentExceptionForNonExistingNote() {
        val note = Notes(
            noteID = 0,
            ownerID = 777,
            title = "Тестовый заголовок",
            text = "Тело заметки",
            comments = mutableListOf(),
            privacy = 0,
            commentPrivacy = 0,
            privacyView = "Not",
            privacyComment = "Not"
        )
        val comment = Comments(
            commentID = 0, ownerID = 777, message = "Тестовый комментарий", isDeleted = true
        )
        add(note)
        createComment(1, comment)
        restoreComment(2, 1)

    }


    @Before
    fun clearBeforeTest() {
        // Очищаем список постов и комментов перед каждым тестом
        clearForTests()
    }
}