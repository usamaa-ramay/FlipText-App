package com.example.fliptextapp.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fliptextapp.R;
import com.example.fliptextapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBar.myToolBar);


        binding.flipBtn.setOnClickListener(view -> {
            String inputText = binding.inputET.getText().toString();
            binding.outputET.setText(inputText);
//            binding.outputET.setScaleX(-1);
            binding.outputET.setScaleY(-1);
            binding.outputET.setTextDirection(View.TEXT_DIRECTION_LTR);
            binding.outputField.setVisibility(view.VISIBLE);
        });

        binding.reverseBtn.setOnClickListener(view -> {
            StringBuffer buffer = new StringBuffer(binding.inputET.getText().toString());
            String inputText = String.valueOf(buffer.reverse());
            binding.outputET.setText(inputText);
        });

        binding.copyBtn.setOnClickListener(view -> {
            String copyTV= binding.outputET.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copy", copyTV);
            if (copyTV.isEmpty()) {
                Toast.makeText(this, "Please enter text firstly", Toast.LENGTH_SHORT).show();
            } else {
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        binding.shearBtn.setOnClickListener(view -> {
            String inputText = binding.inputET.getText().toString();
            if (inputText.isEmpty()){
                Toast.makeText(this, "Please enter text firstly", Toast.LENGTH_SHORT).show();
            }else {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, inputText);
                startActivity(Intent.createChooser(share, inputText));
            }
        });

        binding.clearBtn.setOnClickListener(view -> {
            binding.inputET.getText().clear();
            binding.outputET.getText().clear();
            binding.outputField.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu , menu);
        return true;
    }
}