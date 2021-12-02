package com.example.myapirit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var  btnGet :Button
    private lateinit var  btnAdd : Button
    private lateinit var  btnLoad :Button
    private lateinit var btnBrowse:Button

    private lateinit var img : ImageView
    private var imgUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGet = findViewById(R.id.btnGet)
        btnAdd = findViewById(R.id.btnAdd)
        btnLoad = findViewById(R.id.btnLoad)
        btnBrowse = findViewById(R.id.btnBrowse)
        img = findViewById(R.id.imgProfile)

        btnBrowse.setOnClickListener(){
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            launchSomeActivity.launch(intent)
        }

        btnGet.setOnClickListener(){

            val call = RetrofitInstance.api.getStudentById("W003")

            call.enqueue(object:Callback<StudentRespond>{
                override fun onResponse(
                    call: Call<StudentRespond>,
                    response: Response<StudentRespond>
                ) {

                    val rs = response.body()

                    findViewById<TextView>(R.id.tfID).text = rs?.id
                    findViewById<TextView>(R.id.tfName).text = rs?.name
                    findViewById<TextView>(R.id.tfProgramme).text = rs?.programme

                    val imgViewer = findViewById<ImageView>(R.id.imgProfile)

                    Glide.with(imgViewer.context).load(rs?.imgURL).into(imgViewer)

                }

                override fun onFailure(call: Call<StudentRespond>, t: Throwable) {
                   Toast.makeText(applicationContext, "Error:....", Toast.LENGTH_LONG).show()

                }


            })

        }


        btnLoad.setOnClickListener(){

        }


        btnAdd.setOnClickListener() {

            val tfId :TextView = findViewById(R.id.tfID)
            val tfName : TextView = findViewById(R.id.tfName)
            val tfProgramme : TextView = findViewById(R.id.tfProgramme)

            val id = tfId.text.toString()
            val name = tfName.text.toString()
            val programme = tfProgramme.text.toString()

            //todo:: convert image to base64 string


            //todo:: call API to upload data

        }


    }

    var launchSomeActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            imgUri  = data?.data
            img.setImageURI(data?.data)
        }
    }

}