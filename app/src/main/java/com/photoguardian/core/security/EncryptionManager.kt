package com.photoguardian.core.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * Manages AES-256-GCM encryption using the Android Keystore system.
 * Ensures keys are stored in hardware-backed security where available.
 */
class EncryptionManager {

    private val provider = "AndroidKeyStore"
    private val alias = "photo_guardian_master_key"
    private val transformation = "AES/GCM/NoPadding"

    private val keyStore: KeyStore = KeyStore.getInstance(provider).apply {
        load(null)
    }

    init {
        createKeyIfNotExists()
    }

    private fun createKeyIfNotExists() {
        if (!keyStore.containsAlias(alias)) {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, provider)
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
            keyGenerator.generateKey()
        }
    }

    private fun getSecretKey(): SecretKey {
        return keyStore.getKey(alias, null) as SecretKey
    }

    /**
     * Encrypts data from [inputStream] to [outputStream].
     * Prepend the IV to the output stream for decryption.
     */
    fun encrypt(inputStream: InputStream, outputStream: OutputStream): ByteArray {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())
        
        val iv = cipher.iv
        outputStream.write(iv) // Write IV to the beginning of the stream

        val buffer = ByteArray(4096)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            val encryptedBytes = cipher.update(buffer, 0, bytesRead)
            if (encryptedBytes != null) {
                outputStream.write(encryptedBytes)
            }
        }
        val finalBytes = cipher.doFinal()
        if (finalBytes != null) {
            outputStream.write(finalBytes)
        }
        
        return iv // Return IV if needed for reference
    }

    /**
     * Decrypts data from [inputStream] to [outputStream].
     * Expects IV at the beginning of the [inputStream].
     */
    fun decrypt(inputStream: InputStream, outputStream: OutputStream) {
        // Read IV (GCM standard is usually 12 bytes)
        val iv = ByteArray(12) 
        val ivBytesRead = inputStream.read(iv)
        
        if (ivBytesRead != 12) {
            throw IllegalStateException("Invalid encrypted file format: missing IV")
        }

        val cipher = Cipher.getInstance(transformation)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec)

        val buffer = ByteArray(4096)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            val decryptedBytes = cipher.update(buffer, 0, bytesRead)
            if (decryptedBytes != null) {
                outputStream.write(decryptedBytes)
            }
        }
        val finalBytes = cipher.doFinal()
        if (finalBytes != null) {
            outputStream.write(finalBytes)
        }
    }
}