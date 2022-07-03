/*
* Copyright 2020 BlessedCoders
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
* */

package com.mulatya.surveyapp.ui

/* In the case where you want to implement video capture

// import androidx.camera.video.Recorder
//import androidx.camera.video.Recording
//import androidx.camera.video.VideoCapture
// import androidx.camera.video.FallbackStrategy
//import androidx.camera.video.MediaStoreOutputOptions
//import androidx.camera.video.Quality
//import androidx.camera.video.QualitySelector
//import androidx.camera.video.VideoRecordEvent
 */
import android.Manifest
import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mulatya.surveyapp.databinding.FragmentEndNoteBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.widget.Toast
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.core.Preview
import androidx.camera.core.CameraSelector
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.core.content.PermissionChecker
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.checkSelfPermission
import com.mulatya.surveyapp.R

class EndNoteFragment : Fragment() {

    private val CAMERA_PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 1001
    private var imageView: ImageView? = null
    private var imageUri: Uri? = null


    companion object {
        fun newInstance() = EndNoteFragment()
    }


    private lateinit var viewBinding: FragmentEndNoteBinding
    private var imageCapture: ImageCapture? = null

    private lateinit var cameraExecutor: ExecutorService


    private lateinit var viewModel: EndNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_end_note, container, false)
    }

    private fun requestCameraPermission(): Boolean {
        var permissionGranted = false

        // If system OS is Marshmallow or Above, we need to request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            val cameraPermissionNotGranted = checkSelfPermission(activity as Context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED

            if (cameraPermissionNotGranted){
                val permission = arrayOf(Manifest.permission.CAMERA)

                // Display permission dialog
                requestPermissions(permission, CAMERA_PERMISSION_CODE) //-> deprecated method use


            }else {
                // Permission already granted
                permissionGranted = true

            }
        }

        return permissionGranted
    }

    // Handle Allow or Deny response from the permission dialog
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        if (requestCode ===  CAMERA_PERMISSION_CODE){
            if (grantResults.size === 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // Permission was granted
                openCameraInterface()
            }
            else{
            // Permission was denied
            showAlert("Camera permission was denied. Unable to take a picture.")
            }

        }

    }



    private fun openCameraInterface(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, R.string.take_photo_btn)
        values.put(MediaStore.Images.Media.DESCRIPTION, R.string.take_photo_btn)
        // Uri that will be used to assign the location of the resulting image
        imageUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        // Create camera intent
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        // Launch intent
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        // Callback from camera intent
        if (resultCode == Activity.RESULT_OK){
            // Set image captured to image view
            imageView?.setImageURI(imageUri)
        }
        else{
            // Failed to take picture
            showAlert("Failed to take camera picture")
        }
    }
    private fun showAlert(message: String){
        val builder = AlertDialog.Builder(activity as Context)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)

        val dialog = builder.create()
        dialog.show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel = ViewModelProvider(this).get(EndNoteViewModel::class.java)

        /*
        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }*/

        view.findViewById<Button>(R.id.take_pic_btn).setOnClickListener {
            // Request camera permissions
            val permissionGranted = requestCameraPermission()
            if (permissionGranted) {

                // Open the camera interface
                openCameraInterface()

            }
        }
    }
}