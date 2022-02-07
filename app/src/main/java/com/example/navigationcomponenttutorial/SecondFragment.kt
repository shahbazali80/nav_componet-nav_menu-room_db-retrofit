package com.example.navigationcomponenttutorial

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationcomponenttutorial.model.Students
import com.example.navigationcomponenttutorial.viewmodels.DbViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*

class SecondFragment : Fragment(), DbAdapter.DbClickUpdateInterface, DbAdapter.DbClickDeleteInterface {

    lateinit var dbAdapter: DbAdapter

    lateinit var dbViewModel: DbViewModel
    var stdID = -1;

    var isUpdate: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        view.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        dbAdapter= DbAdapter(requireContext(),this,this)
        view.recyclerview.adapter = dbAdapter

        dbViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DbViewModel::class.java)

        dbViewModel.allStds.observe(viewLifecycleOwner, Observer { list -> dbAdapter.setData(list as ArrayList<Students>)
        })

        view.btnAdd_roomDb.setOnClickListener {

            if(view.et_name.text.isNotEmpty() && view.et_email.text.isNotEmpty() && view.et_phone.text.isNotEmpty()){
                if(isUpdate){
                    val updatedStd = Students(stdName = et_name.text.toString(), stdEmail = et_email.text.toString(), stdPhone = et_phone.text.toString())
                    updatedStd.id = stdID
                    dbViewModel.updateStudents(updatedStd)
                    Toast.makeText(requireContext(), "Student Info Successfully Updated..", Toast.LENGTH_LONG).show()
                    btnAdd_roomDb.setText("Add Now")
                    clearFields()
                } else {
                    dbViewModel.addStudents(
                        Students(stdName = et_name.text.toString(), stdEmail = et_email.text.toString(), stdPhone = et_phone.text.toString()))
                    Toast.makeText(requireContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
            } else {
                Toast.makeText(requireContext(), "All fields must be fill required", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun clearFields() {
        et_name.setText("")
        et_email.setText("")
        et_phone.setText("")
    }

    override fun onUpdateIconClick(students: Students) {
        isUpdate=true
        stdID=students.id
        et_name.setText(students.stdName)
        et_email.setText(students.stdEmail)
        et_phone.setText(students.stdPhone)
        btnAdd_roomDb.setText("Update Now")
    }

    override fun onDeleteIconClick(students: Students) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val del_note = dialogView.findViewById(R.id.del_note) as TextView

        del_note.setText("Are you sure you want to delete it?")

        dialogBuilder.setTitle("Delete Note")
        //dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
            dbViewModel.deleteStudents(students)
            Toast.makeText(requireContext(), "${students.stdName} Deleted", Toast.LENGTH_LONG).show()
        })
        dialogBuilder.setNegativeButton("Close", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
}