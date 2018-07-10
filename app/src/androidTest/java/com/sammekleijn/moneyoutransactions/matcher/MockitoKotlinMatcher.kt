package com.sammekleijn.moneyoutransactions.matcher

import org.mockito.Mockito

class MockitoKotlinMatcher {
    companion object {
        fun <T> any(value: Class<T>?): T {
            Mockito.any<T>(value)
            return uninitialized()
        }

        fun <T> any(): T {
            Mockito.any<T>()
            return uninitialized()
        }

        fun anyString(): String? {
            Mockito.any<String?>()
            return uninitialized()
        }

        fun <T> eq(value: T?): T {
            Mockito.eq<T>(value)
            return uninitialized()
        }

        private fun <T> uninitialized(): T = null as T
    }
}