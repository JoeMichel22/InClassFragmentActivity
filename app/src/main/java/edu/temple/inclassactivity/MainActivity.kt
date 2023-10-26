package edu.temple.inclassactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewModel= ViewModelProvider(this)[ImageViewModel::class.java]

        // Fetch images into IntArray called imageArray
        val typedArray = resources.obtainTypedArray(R.array.image_ids)
        val imageArray = IntArray(typedArray.length()) {typedArray.getResourceId(it, 0)}
        typedArray.recycle()

        imageViewModel.setImages(imageArray)

        // Attach an instance of ImageDisplayFragment using factory method
        val imageFrag= ImageDisplayFragment.newInstance(imageArray)

        if(supportFragmentManager.findFragmentById(R.id.fragmentContainer) !is ImageDisplayFragment){
            val transaction= supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragmentContainer, imageFrag)
            transaction.commit()
        }


        findViewById<Button>(R.id.btnDisplay).setOnClickListener{
            imageFrag.setImages(imageArray)
        }
    }

}