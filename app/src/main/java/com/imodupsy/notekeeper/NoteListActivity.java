package com.imodupsy.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.imodupsy.notekeeper.databinding.ActivityNoteListBinding;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNoteListBinding binding;
    private ArrayAdapter<NoteInfo> mAdapterNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNoteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, MainActivity.class));
            }
        });
        
        initializeDisplayContent();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void initializeDisplayContent() {
       final ListView listNotes = findViewById(R.id.note_lists);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        mAdapterNote = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listNotes.setAdapter(mAdapterNote);
        
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(NoteListActivity.this, MainActivity.class);
//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                intent.putExtra(MainActivity.NOTE_POSITION, position);
                startActivity(intent);
            }
        });
    }
}