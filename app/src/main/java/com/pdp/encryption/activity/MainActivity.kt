package com.pdp.encryption.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pdp.encryption.R
import com.pdp.encryption.utils.Asymmetric
import com.pdp.encryption.utils.Asymmetric.Companion.decryptMessage
import com.pdp.encryption.utils.Asymmetric.Companion.encryptMessage
import com.pdp.encryption.utils.Symmetric.decrypt
import com.pdp.encryption.utils.Symmetric.encrypt
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testSymmetric()
        testAsymmetric()
    }

    private fun testSymmetric() {
        // key
        val secretKey = "mamarayim"
        // secret text
        val originalString = "PDP Academy"
        // Encryption
        val encryptedString = encrypt(originalString, secretKey)
        // Decryption
        val decryptedString = decrypt(encryptedString, secretKey)
        // Printing originalString,encryptedString,decryptedString
        Log.d("@@@","Original String:$originalString")
        Log.d("@@@","Encrypted value:$encryptedString")
        Log.d("@@@","Decrypted value:$decryptedString")
    }

    private fun testAsymmetric() {
        val secretText = "PDP University"
        val keyPairGenerator = Asymmetric()
        // Generate private and public key
        val privateKey: String = Base64.getEncoder().encodeToString(keyPairGenerator.privateKey.encoded)
        val publicKey: String = Base64.getEncoder().encodeToString(keyPairGenerator.publicKey.encoded)
        Log.d("@@@","Private Key: $privateKey")
        Log.d("@@@","Public Key: $publicKey")
        // Encrypt secret text using public key
        val encryptedValue = encryptMessage(secretText, publicKey)
        Log.d("@@@","Encrypted Value: $encryptedValue")
        // Decrypt
        val decryptedText = decryptMessage(encryptedValue, privateKey)
        Log.d("@@@","Decrypted output: $decryptedText")
    }
}