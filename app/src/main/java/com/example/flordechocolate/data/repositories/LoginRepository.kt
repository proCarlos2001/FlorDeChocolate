package com.example.flordechocolate.data.repositories

import android.graphics.Bitmap
import androidx.core.location.LocationRequestCompat.Quality
import com.example.flordechocolate.R
import com.example.flordechocolate.USER_COLLECTION
import com.example.flordechocolate.data.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class LoginRepository(private val auth: FirebaseAuth, private val db: FirebaseFirestore, private val storage: FirebaseStorage) {
    suspend fun login(email: String, password: String){
//       delay(1000)
//       if (email != "restrepoospino@gmail.com" || password != "123467554") {
//           throw Exception("Credenciales invalidas")
//       }
        try {
            auth.signInWithEmailAndPassword(email,password).await()
        }catch (e: FirebaseAuthException) {
            throw Exception(e.message)
        }
    }

    suspend fun signUp(email: String, password: String, name: String, gender: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            val user = auth.currentUser!!
            user.updateProfile(profileUpdate).await()
            val userInfo = hashMapOf(
                "gender" to gender
            )
            db.collection(USER_COLLECTION).document(user.uid)
                .set(userInfo).await()
        }catch (e: FirebaseAuthException) {
            throw Exception(e.message)
        }
    }

    suspend fun currentUser(): UserModel {
 //       return UserModel("1", "Carlos Restrepo",
 //           "restrepoospino@gmail.com", "Masculino", R.drawable.logo.toString())
        val user = auth.currentUser
        if (user != null) {
             val info = db.collection(USER_COLLECTION).document(user.uid)
                 .get().await()
            var image: String? = null
            if(user.photoUrl != null) image = user.photoUrl.toString()
            return UserModel(user.uid, user.displayName!!, user.email!!,
                info.get("gender").toString(), image)
        }else {
            throw Exception("Not user found")
        }
    }

    suspend fun uploadImage(image: Bitmap): UserModel {
        val user = auth.currentUser
        if(user != null) {
            val ref = storage.reference
            val imageRef = ref.child("${user.uid}.jpg")
            val baos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            imageRef.putBytes(baos.toByteArray()).await()
            val url = imageRef.downloadUrl.await()
            val profileUpdate = userProfileChangeRequest {
                photoUri = url
            }
            user.updateProfile(profileUpdate).await()
        }
        return this.currentUser()
    }

    suspend fun logOut() {
 //       delay(1000)
        auth.signOut()
    }
}