def encode(String message) {
    return message.bytes.encodeBase64().toString()
}