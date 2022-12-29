package com.serkanemek.spacenasaphotos.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.serkanemek.spacenasaphotos.R
import com.serkanemek.spacenasaphotos.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.refreshData()

        getLiveData()

        swipeRefreshLayout.setOnRefreshListener {
            linearLayout1.visibility = View.GONE
            errorText.visibility = View.GONE
            progressBar.visibility = View.GONE

            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }


    }

    private fun getLiveData() {
        viewModel.photo_data.observe(this, Observer { livedata ->
            livedata?.let {
                linearLayout1.visibility = View.VISIBLE
                title_Textview.text = livedata.title.toString()
                date_Textview.text = livedata.date.toString()
                explanation_Textview.text = livedata.explanation.toString()

                Glide.with(this).load(livedata.url).into(imageview)  //We are getting images with Glide and putting to Imageview.
            }
        })

        viewModel.photo_loading.observe(this, Observer { loading ->
            loading?.let {
                if (it){
                    progressBar.visibility = View.VISIBLE
                    errorText.visibility = View.GONE
                    linearLayout1.visibility = View.GONE
                }else{
                    progressBar.visibility = View.GONE
                }
            }
        })

        viewModel.photo_error.observe(this, Observer { error ->
            error?.let {
                if (it){
                    errorText.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    linearLayout1.visibility = View.GONE
                }else{
                    errorText.visibility = View.GONE
                }
            }
        })

    }
}