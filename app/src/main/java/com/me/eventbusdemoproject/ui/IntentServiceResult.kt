package com.me.eventbusdemoproject.ui

import kotlin.properties.Delegates

class IntentServiceResult {
    private var mResult by Delegates.notNull<Int>()
    private var mResultValue by Delegates.notNull<String>()
}