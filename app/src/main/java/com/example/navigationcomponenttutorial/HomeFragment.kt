package com.example.navigationcomponenttutorial

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.navigationcomponenttutorial.api.JsonPlaceHolderApi
import com.example.navigationcomponenttutorial.model.Students
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    var isGet : Boolean = true
    var isPost : Boolean = false
    var isPut : Boolean = false
    var isPetch : Boolean = false
    var isDelete : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //Retrofit Builder
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofitBuilder.create(JsonPlaceHolderApi::class.java)

        view.rb_get.isChecked = true
        view.btnAdd_retrofitDb.setText("Apply GET Method")

        view.radioGroup.setOnCheckedChangeListener { group, i ->
            val checkedRadioButton = group?.findViewById(group.checkedRadioButtonId) as? RadioButton
            checkedRadioButton?.let {

                if (checkedRadioButton.isChecked && checkedRadioButton?.text.trim()=="Get") {
                    isGet = true
                    isPost = false
                    isPut = false
                    isPetch = false
                    isDelete = false
                    view.btnAdd_retrofitDb.setText("Apply GET Method")
                } else if (checkedRadioButton.isChecked && checkedRadioButton?.text.trim()=="Post") {
                    isGet = false
                    isPost = true
                    isPut = false
                    isPetch = false
                    isDelete = false
                    view.btnAdd_retrofitDb.setText("Apply Post Method")
                } else if (checkedRadioButton.isChecked && checkedRadioButton?.text.trim()=="Put") {
                    isGet = false
                    isPost = false
                    isPut = true
                    isPetch = false
                    isDelete = false
                    view.btnAdd_retrofitDb.setText("Apply Put Method")
                } else if (checkedRadioButton.isChecked && checkedRadioButton?.text.trim()=="Petch") {
                    isGet = false
                    isPost = false
                    isPut = false
                    isPetch = true
                    isDelete = false
                    view.btnAdd_retrofitDb.setText("Apply Petch Method")
                } else {
                    isGet = false
                    isPost = false
                    isPut = false
                    isPetch = false
                    isDelete = true
                    view.btnAdd_retrofitDb.setText("Apply Delete Method")
                }
            }
        }

        view.btnAdd_retrofitDb.setOnClickListener {

            if(isGet){
                Toast.makeText(requireContext(), "Get Method not applied", Toast.LENGTH_SHORT).show()
            }

            if(isPost){
                if(view.et_retrofit_id.text.isNotEmpty() && view.et_retrofit_title.text.isNotEmpty() && view.et_retrofit_body.text.isNotEmpty()){
                    val postStd = Students(stdName = et_retrofit_id.text.toString(), stdEmail = et_retrofit_title.text.toString(), stdPhone = et_retrofit_body.text.toString())
                    val call = jsonPlaceHolderApi.post(postStd)

                    call.enqueue(object : Callback<Students> {
                        override fun onResponse(call: Call<Students>, response: Response<Students>) {
                            tv_response.setText(response.code().toString())
                            Log.d("response", response.code().toString())
                        }

                        override fun onFailure(call: Call<Students>, t: Throwable) {
                            tv_response.setText(t.message.toString())
                            Log.d("response", t.message.toString())
                        }

                    })
                }
                else {
                    Toast.makeText(requireContext(), "All fields must be fill required", Toast.LENGTH_SHORT).show()
                }
            }

            if(isPut){
                if(view.et_retrofit_id.text.isNotEmpty() && view.et_retrofit_title.text.isNotEmpty() && view.et_retrofit_body.text.isNotEmpty()){
                    val postStd = Students(stdName = et_retrofit_id.text.toString(), stdEmail = et_retrofit_title.text.toString(), stdPhone = et_retrofit_body.text.toString())
                    val call = jsonPlaceHolderApi.put(5,postStd)

                    call.enqueue(object : Callback<Students> {
                        override fun onResponse(call: Call<Students>, response: Response<Students>) {
                            tv_response.setText(response.code().toString())
                            Log.d("response", response.code().toString())
                        }

                        override fun onFailure(call: Call<Students>, t: Throwable) {
                            tv_response.setText(t.message.toString())
                            Log.d("response", t.message.toString())
                        }

                    })
                }
                else {
                    Toast.makeText(requireContext(), "All fields must be fill required", Toast.LENGTH_SHORT).show()
                }
            }

            if(isPetch){
                if(view.et_retrofit_id.text.isNotEmpty() && view.et_retrofit_title.text.isNotEmpty() && view.et_retrofit_body.text.isNotEmpty()){
                    val postStd = Students(stdName = et_retrofit_id.text.toString(), stdEmail = et_retrofit_title.text.toString(), stdPhone = et_retrofit_body.text.toString())
                    val call = jsonPlaceHolderApi.petch(5,postStd)

                    call.enqueue(object : Callback<Students> {
                        override fun onResponse(call: Call<Students>, response: Response<Students>) {
                            tv_response.setText(response.code().toString())
                            Log.d("response", response.code().toString())
                        }

                        override fun onFailure(call: Call<Students>, t: Throwable) {
                            tv_response.setText(t.message.toString())
                            Log.d("response", t.message.toString())
                        }

                    })
                }
                else {
                    Toast.makeText(requireContext(), "All fields must be fill required", Toast.LENGTH_SHORT).show()
                }
            }

            if(isDelete){

                if(view.et_retrofit_id.text.isNotEmpty()){
                    val call = jsonPlaceHolderApi.delete(et_retrofit_id.text.toString().toInt())

                    call.enqueue(object : Callback<Unit>{
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            tv_response.setText(response.code().toString())
                            Log.d("response", response.code().toString())
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            tv_response.setText(t.message.toString())
                            Log.d("response", t.message.toString())
                        }

                    })
                } else {
                    view.et_retrofit_id.setError("ID Must requied")
                    view.et_retrofit_id.requestFocus()
                }
            }

            clearFields()
        }

        return view
    }

    private fun clearFields() {
        et_retrofit_id.setText("")
        et_retrofit_title.setText("")
        et_retrofit_body.setText("")
    }
}