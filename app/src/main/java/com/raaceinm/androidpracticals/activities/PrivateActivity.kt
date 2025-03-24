package com.raaceinm.androidpracticals.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.raaceinm.androidpracticals.R
import com.raaceinm.androidpracticals.Tools.deleteGPUDataSet
import com.raaceinm.androidpracticals.Tools.setGPUDataSet
import com.raaceinm.androidpracticals.fragments.AddNewCard
import com.raaceinm.androidpracticals.fragments.AnotherPrivate
import com.raaceinm.androidpracticals.fragments.ContentResolver
import com.raaceinm.androidpracticals.fragments.ListViewFragment

class PrivateActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "com.raaceinm.androidpracticals.activities.EXTRA_DATA"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private)

        val mainLayout: FrameLayout? = findViewById(R.id.main)
        mainLayout?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val username: EditText? = findViewById(R.id.username)
        val password: EditText? = findViewById(R.id.password)
        val login: Button? = findViewById(R.id.login)
        val hiddenContent: FrameLayout? = findViewById(R.id.hiddenFrameLayout)
        val buttons: View? = findViewById(R.id.buttons)

        if (!intent.getBooleanExtra("isRegistered", false)) {
            login?.setOnClickListener { // Use safe call here too
                val userName = username?.text.toString() //and here
                val userPassword = password?.text.toString() //and here
                val intent = Intent()

                if (userName == "era" && userPassword == "s1234") {
                    intent.putExtra(EXTRA_DATA, "valid")
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    intent.putExtra(EXTRA_DATA, "invalid")
                    setResult(RESULT_CANCELED, intent)
                    finish()
                }
            }
        } else {
            username?.visibility = View.GONE
            password?.visibility = View.GONE
            login?.visibility = View.GONE
            hiddenContent?.visibility = View.VISIBLE
            buttons?.visibility = View.VISIBLE

            supportFragmentManager.beginTransaction()
                .add(R.id.hiddenFrameLayout, AnotherPrivate::class.java, null)
                .commit()
        }
    }

    fun fragmentManager(view: View) {
        val textView: TextView? = findViewById(R.id.newGpuName)
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        when (view.id) {
            R.id.key -> {
                fragmentTransaction.replace(R.id.hiddenFrameLayout, ContentResolver(), "fragment1")
                Log.d("EEE", "pressed key")
            }
            R.id.shield -> {
                fragmentTransaction.replace(R.id.hiddenFrameLayout, AnotherPrivate(), "fragment2")
                Log.d("EEE", "pressed shield")
            }
            R.id.addGPU -> {
                fragmentTransaction.replace(R.id.hiddenFrameLayout, AddNewCard(), "fragment3")
                Log.d("EEE", "pressed addGPU")
            }
            R.id.confirm -> {
                reNewList(textView?.text.toString(), "")
                fragmentTransaction.replace(R.id.hiddenFrameLayout, ContentResolver(), "fragment1")
                Log.d("EEE", "pressed confirm")
            }
            R.id.remove -> {
                reNewList("", textView?.text.toString())
            }
            else -> {
                Log.e("EEE", "Unknown button pressed")
            }
        }
        fragmentTransaction.commit()
    }


    private fun reNewList(newItem: String, deleteItem: String) {
        if (newItem.isEmpty() && deleteItem.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "please enter a name", Snackbar.LENGTH_LONG).show()
        } else if (deleteItem.isEmpty()) {
            setGPUDataSet(newItem)
            val fragmentManager: FragmentManager = supportFragmentManager
            val listViewFragment: ListViewFragment? = fragmentManager.findFragmentByTag("fragment1") as? ListViewFragment

            listViewFragment?.updateList()
        } else if (newItem.isEmpty()) {
            deleteGPUDataSet(deleteItem)
            val fragmentManager: FragmentManager = supportFragmentManager
            val listViewFragment: ListViewFragment? = fragmentManager.findFragmentByTag("fragment1") as? ListViewFragment

            listViewFragment?.updateList()
        }
    }
    override fun onRestart() {
        super.onRestart()
        startActivity(Intent(this, ExperimentalSheesh::class.java))
    }
}