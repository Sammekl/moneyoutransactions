package com.sammekleijn.moneyoutransactions.extension

import android.content.Intent
import android.support.test.rule.ActivityTestRule

fun ActivityTestRule<*>.launch(intent: Intent? = null) {
    this.launchActivity(intent)
}