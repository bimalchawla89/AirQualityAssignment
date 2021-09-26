package com.airquality.repo

import com.airquality.db.AirQuality
import com.airquality.utils.CommonFunctions
import com.airquality.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import javax.inject.Inject

class APIRepository @Inject constructor() : WebSocketClient(URI(Constants.WEB_SOCKET_URL)) {

    private val _events = MutableSharedFlow<List<AirQuality>>()
    val events = _events.asSharedFlow()

    override fun onOpen(handshakedata: ServerHandshake?) {
        // no op
    }

    override fun onMessage(message: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            message?.let {
                _events.emit(CommonFunctions.json.decodeFromString(it))
            }
        }
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        // no op
    }

    override fun onError(ex: Exception?) {
        // no op
    }

    override fun connect() {
        if (!isOpen)
            super.connect()
    }
}