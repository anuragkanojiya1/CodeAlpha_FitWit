package com.example.fitnesstrackerapp.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.fitnesstrackerapp.data.PersonalData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FitnessViewModel: ViewModel() {
    fun savePersonalData(
        personalData: PersonalData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        val firestoreRef = Firebase.firestore
            .collection("users")
            .document(userId.toString())
            .collection("personal")
            .document(personalData.name)

        try {
            firestoreRef.set(personalData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully Saved your Data", Toast.LENGTH_SHORT)
                        .show()
                }
        } catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrievePersonalData(
        name: String,
        context: Context,
        data: (PersonalData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        val firestoreRef = Firebase.firestore
            .collection("users")
            .document(userId.toString())
            .collection("personal")
            .document(name)

        try {
            firestoreRef.get()
                .addOnSuccessListener {
                    if(it.exists()){
                        val personalData = it.toObject<PersonalData>()!!
                        data(personalData)
                    } else{
                        Toast.makeText(context, "No Personal Data found in Database", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}