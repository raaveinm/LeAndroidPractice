package com.raaceinm.androidpracticals.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.raaceinm.androidpracticals.R
import com.raaceinm.androidpracticals.Tools.deleteGPUDataSet
import com.raaceinm.androidpracticals.Tools.setGPUDataSet
import com.raaceinm.androidpracticals.fragments.AddNewCard
import com.raaceinm.androidpracticals.fragments.AnotherPrivate
import com.raaceinm.androidpracticals.fragments.ContentResolver
import com.raaceinm.androidpracticals.fragments.ListViewFragment

class PrivateActivity : AppCompatActivity() {
    val EXTRA_DATA = "com.raaceinm.androidpracticals.activities.EXTRA_DATA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_private)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @Override
    override fun onStart() {
        super.onStart()

        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val login: Button = findViewById(R.id.login)
        val hiddenContent: FrameLayout = findViewById(R.id.hiddenFrameLayout)
        val buttons: View = findViewById(R.id.buttons)

        if (!intent.getBooleanExtra("isRegistered", false)) {
            login.setOnClickListener({
                var userName = username.getText().toString()
                var userPassword = password.getText().toString()

                if (userName == "era" && userPassword == "s1234") {
                    intent.putExtra(EXTRA_DATA, "valid")
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    intent.putExtra(EXTRA_DATA, "invalid")
                    setResult(RESULT_CANCELED, intent)
                    finish()
                }
            })
        }else{
            username.visibility = View.GONE
            password.visibility = View.GONE
            login.visibility = View.GONE
            hiddenContent.visibility = View.VISIBLE
            buttons.visibility = View.VISIBLE

            supportFragmentManager.beginTransaction()
                .add(R.id.hiddenFrameLayout, AnotherPrivate::class.java,null)
                .commit()
        }
    }

    fun fragmentManager(view: View) {
        val textView: TextView = findViewById(R.id.newGpuName)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val contentResolverFragment = ContentResolver()
        val meetNix = AnotherPrivate()
        val cardManager = AddNewCard()

        when (view.id) {
            R.id.key -> {
                fragmentTransaction.replace(R.id.hiddenFrameLayout, contentResolverFragment, "fragment1")
            }
            R.id.shield -> {
                fragmentTransaction.replace(R.id.hiddenFrameLayout, meetNix, "fragment2")
            }
            R.id.addGPU -> {
                fragmentTransaction.replace(R.id.hiddenFrameLayout, cardManager, "fragment3")
            }
            R.id.confirm -> {
                updateList(textView.text.toString(), "")
                fragmentTransaction.replace(R.id.hiddenFrameLayout, contentResolverFragment, "fragment1")
            }
            R.id.remove -> {
                updateList("", textView.text.toString())
                fragmentTransaction.replace(R.id.hiddenFrameLayout, contentResolverFragment, "fragment1")
            }
            else -> {
                Log.e("EEE", "Unknown button pressed")
            }
        }
        fragmentTransaction.commit()
    }

    private fun updateList(newItem: String, deleteItem: String) {
        val fragmentManager = supportFragmentManager
        var listViewFragment = fragmentManager.findFragmentByTag("fragment1") as? ListViewFragment

        if (newItem.isEmpty() && deleteItem.isEmpty()) {
            Log.e("EEE", "Nothing to do")
        } else if (deleteItem.isEmpty()) {
            setGPUDataSet(newItem)
        } else if (newItem.isEmpty()) {
            deleteGPUDataSet(deleteItem)
        }

        listViewFragment?.updateList()
    }
}