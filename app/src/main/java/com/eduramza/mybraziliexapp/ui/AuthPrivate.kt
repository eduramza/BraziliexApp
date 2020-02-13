package com.eduramza.mybraziliexapp.ui

import java.math.BigInteger
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

const val API_KEY = "f15acda658162f827cc5ea35e494a966753d7cd1e4ebd528acb6ab3c5d0cf29d04291d3daaa8c1d0c04eedfc6c2da5f7f81391c53f55495a49db805920749714"
const val API_SECRET = "41e94499ec2a5db1def559f32e77f5417d0329531acb8e5cad0a90bf1c3f602c0f4a627299e05730096b767fe4d6267a297cc8809f75fff7793f2e72abbd17cd"
const val ALGORITM = "aq9530ASsq3a12a0Tjh"

class AuthPrivate {
    companion object{

        fun buildHmacSignature(
            value: String,
            secret: String
        ): String? {
            var result: String
            try {
                val hmacSHA512: Mac = Mac.getInstance("HmacSHA512")
                val secretKeySpec = SecretKeySpec(
                    secret.toByteArray(),
                    "HmacSHA512"
                )
                hmacSHA512.init(secretKeySpec)
                val digest: ByteArray = hmacSHA512.doFinal(value.toByteArray())
                val hash = BigInteger(1, digest)
                result = hash.toString(16)
                if (result.length % 2 != 0) {
                    result = "0$result"
                }
            } catch (ex: IllegalStateException) {
                throw RuntimeException("Problemas calculando HMAC", ex)
            } catch (ex: InvalidKeyException) {
                throw RuntimeException("Problemas calculando HMAC", ex)
            } catch (ex: NoSuchAlgorithmException) {
                throw RuntimeException("Problemas calculando HMAC", ex)
            }
            return result
        }
    }
}