package com.pdp.encryption.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.android_advanced_kotlin.activity.model.User
import com.example.android_mvc.network.RetrofitHttp
import com.pdp.encryption.R
import com.pdp.encryption.utils.Asymmetric
import com.pdp.encryption.utils.Asymmetric.Companion.decryptMessage
import com.pdp.encryption.utils.Asymmetric.Companion.encryptMessage
import com.pdp.encryption.utils.Symmetric.decrypt
import com.pdp.encryption.utils.Symmetric.encrypt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val b_save = findViewById<Button>(R.id.b_save)
        val b_load = findViewById<Button>(R.id.b_load)
        b_save.setOnClickListener {
            val user = User("Firdavs", "+998909998899")
            user.fullName = encrypt(user.fullName)!!
            user.phoneNumber = encrypt(user.phoneNumber)!!
            Log.d("TAG",user.toString())
            apiSaveUser(user)
        }
        b_load.setOnClickListener {
            apiLoadUser()
        }
    }

    private fun apiSaveUser(user: User) {
        RetrofitHttp.userService.createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d("TAG", response.toString())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG", t.toString())
            }
        })
    }

    private fun apiLoadUser() {
        RetrofitHttp.userService.getUserById(1).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                Log.d("TAG", decrypt(user!!.fullName).toString())
                Log.d("TAG", decrypt(user.phoneNumber).toString())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG", t.toString())
            }
        })
    }

    private fun testSymmetric() {
        // secret text
        val originalString = "PDP Academy"
        // Encryption
        val encryptedString = encrypt(originalString)
        // Decryption
        val decryptedString = decrypt(encryptedString)
        // Printing originalString,encryptedString,decryptedString
        Log.d("@@@", "Original String:$originalString")
        Log.d("@@@", "Encrypted value:$encryptedString")
        Log.d("@@@", "Decrypted value:$decryptedString")
    }

    private fun testAsymmetric() {
        val secretText = "Omad yordur dovyuraklarga!!!"
        val keyPairGenerator = Asymmetric()
        // Generate private and public key
        val privateKey: String =
            Base64.getEncoder().encodeToString(keyPairGenerator.privateKey.encoded)
        val publicKey: String =
            Base64.getEncoder().encodeToString(keyPairGenerator.publicKey.encoded)
        Log.d("@@@", "Private Key: $privateKey")
        Log.d("@@@", "Public Key: $publicKey")
        // Encrypt secret text using public key
        val encryptedValue = encryptMessage(secretText, publicKey)
        Log.d("@@@", "Encrypted Value: $encryptedValue")
        // Decrypt
        val decryptedText = decryptMessage(encryptedValue, privateKey)
        Log.d("@@@", "Decrypted output: $decryptedText")
    }
}