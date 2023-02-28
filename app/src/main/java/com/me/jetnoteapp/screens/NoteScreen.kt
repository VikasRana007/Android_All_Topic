package com.me.jetnoteapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.me.jetnoteapp.R
import com.me.jetnoteapp.components.NoteButton
import com.me.jetnoteapp.components.NoteInputText
import com.me.jetnoteapp.data.NotesDataSource
import com.me.jetnoteapp.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(R.string.app_name))
        }, actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon"
            )
        },
            backgroundColor = Color(0xFF3FD1DE)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 9.dp
                ),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                })

            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 9.dp
                ),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(Note(title = title,
                    description = description))
                    title = ""
                    description = ""
                    val toast = Toast(context,"Note Added", Toast.LENGTH_LONG)
                    toast.setGravity(200,0,515)
                    toast.show()
                }
            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(modifier = Modifier, note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
            }
        }
    }
}

fun Toast(context : Context, s: String, lengthLong: Int): Toast {
     return Toast.makeText(context,s,lengthLong)
}


@Composable
fun NoteRow(modifier: Modifier,
            note: Note,
            onNoteClicked : (Note) -> (Unit)){
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 23.dp, bottomStart = 23.dp))
            .fillMaxWidth(), color = Color(0xFF3FD1DE), elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start ) {
            Text(text = note.title,
            style = MaterialTheme.typography.subtitle2)

            Text(text = note.description,
            style = MaterialTheme.typography.subtitle1)
            
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d, MMM")),
            style = MaterialTheme.typography.caption)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreensPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}